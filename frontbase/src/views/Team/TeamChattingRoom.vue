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
        
        <input type="text" v-model="content" placeholder="보낼 메세지" size="100" />
        <button @click="sendMessage()"> SEND</button>   
    </div>
</template>

<script>
import TeamHeader from '@/components/TeamHeader.vue';


export default {
    name: "Room",
    components:{
        TeamHeader
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
        
    },
        
    methods:{
        sendMessage(){
        
        },
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
