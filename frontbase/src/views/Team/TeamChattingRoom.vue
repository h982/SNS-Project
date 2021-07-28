<template>
    <div class="room">
        <v-layout >
            <v-bottom-navigation
                class="mx-auto"
                shift
                x-large
            >
                <v-btn color="secondary" @click="moveMain">
                <i class="fas fa-address-card fa-2x"></i>
                <div>&nbsp;&nbsp;&nbsp;정보</div>
                </v-btn>

                <v-btn color="success" @click="moveTeamFeed">
                <i class="fas fa-clipboard fa-2x" ></i>
                <div>&nbsp;&nbsp;&nbsp;피드</div>
                </v-btn>

                <v-btn color="primary" @click="moveBoard">
                <i class="fas fa-check fa-2x" ></i>
                <div>&nbsp;&nbsp;&nbsp;공지사항</div>
                </v-btn>
                
                <v-btn color="warning" @click="moveChattingRoom">
                <i class="fas fa-comments fa-2x"></i>
                <div>&nbsp;&nbsp;&nbsp;채팅</div>
                </v-btn>

                <v-btn color="error"  @click="moveTeamChallenge">
                <i class="fas fa-trophy fa-2x"></i>
                <div>&nbsp;&nbsp;&nbsp;챌린지</div>
                </v-btn>
            </v-bottom-navigation>
        </v-layout>

        <h1>{{title}}</h1>
        
        <input type="text" v-model="content" placeholder="보낼 메세지" size="100" />
        <button @click="sendMessage()"> SEND</button>   
    </div>
</template>

<script>


export default {
    name: "Room",
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
        moveMain(){
            this.$router.push("/teammain");
        },
        moveTeamChallenge(){
            this.$router.push("/teamChallenge");
        },
        moveBoard(){
            this.$router.push("/board");
        },
        moveChattingRoom(){
            this.$router.push("/chattingroom");
        },
        moveTeamFeed(){
            this.$router.push("/teamFeed");
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
