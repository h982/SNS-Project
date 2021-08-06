<template>
    <div class="room">
        <v-layout >
            <v-bottom-navigation
                class="mx-auto"
                shift
                x-large
            >
                <team-header />
            </v-bottom-navigation>
        </v-layout>
        
        <h1>{{title}}</h1>
        <hr />
        <div v-for="(m, idx) in msg" :key="idx">
            <div v-bind:class="m.style">
                <h5 style="margin:3px">
                    {{m.senderNickname}}
                    </h5>
                {{m.content}}
                {{m.writeDate}}
            </div>
        </div>
        <hr />
        <input type="text" v-model="content" placeholder="보낼 메시지" size="100" />
        <button @click="sendMessage()"> SEND</button>  
    </div>
</template>

<script>
import TeamHeader from '@/components/TeamHeader.vue';
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
import http from "@/util/http-common";
import { mapState} from "vuex";

export default {
    name: "Room",
    components:{
        TeamHeader
    },
    computed: {
        ...mapState(["memberInfo", "selectTeam"]),
    },
    data: () => {
        return {
                id: -1,
                nickname: '',
                title:'채팅방',
                roomid:-1,
                idx:0,
                msg:[],
                content:"",
                stompClient:null    
            }
    },
    created(){
        console.log(this.memberInfo)
        console.log(this.selectTeam)

        //채팅방 내용 불러오기
        http
            .get('/message/'+this.selectTeam.teamId+'?page=0', )
            .then(res=>{
                this.msg = []
                let resMsg = res.data.data;
                for(let i=resMsg.length-1; i>-1; i--){
                    let m={
                        'senderNickname':resMsg[i].writer,
                        'content':resMsg[i].contents,
                        'style': resMsg[i].memberId == this.memberInfo.memberId ? 'myMsg':'otherMsg',
                        'writeDate': resMsg[i].writeDate
                    }
                    this.msg.push(m)
                }

                console.log(this.msg);
            }, err=>{
                console.log(err)
                alert("error : 새로고침하세요")
            })

        // socket 연결
        let socket = new SockJS('http://localhost:8080/ws')
        this.stompClient = Stomp.over(socket)
        this.stompClient.connect({}, frame=>{
            console.log("success", frame)
            this.stompClient.subscribe("/sub/"+this.selectTeam.teamId, res=>{
                console.log("aa");
                let jsonBody = JSON.parse(res.body)
                let m={
                    'senderNickname':jsonBody.writer,
                    'content':jsonBody.contents,
                    'style': jsonBody.memberId == this.memberInfo.memberId ? 'myMsg':'otherMsg',
                    'writeDate': jsonBody.writeDate
                }
                this.msg.push(m)
            })
        }, err=>{
            console.log("fail", err)
        })
    },
        
    methods:{
        sendMessage(){
            if(this.content.trim() !='' && this.stompClient!=null) {
                let chatMessage = {
                    'teamId': this.selectTeam.teamId,
                    'memberId': this.memberInfo.memberId,
                    'contents': this.content,
                    'writer': this.memberInfo.name,
                }
                this.stompClient.send("/pub/message", JSON.stringify(chatMessage),{})
        
                this.content=''
            }
        }
    }
};
</script>
<style scoped>
.myMsg{
text-align: right;
color : gray;
}
.otherMsg{
    text-align: left;
}
</style>
