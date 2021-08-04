<template>
    <div>
        <v-layout >
            <v-btn color="secondary" @click="moveMain">
                <i class="fas fa-address-card fa-2x" ></i>
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
                
            <v-btn color="warning"  @click="moveChattingRoom">
                <i class="fas fa-comments fa-2x"></i>
                <div>&nbsp;&nbsp;&nbsp;채팅</div>
            </v-btn>

            <v-btn color="error" @click="moveTeamChallenge">
                <i class="fas fa-trophy fa-2x" ></i>
                <div>&nbsp;&nbsp;&nbsp;챌린지</div>
            </v-btn>
        </v-layout>
    </div>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
    
    computed: {
        ...mapGetters(["selectTeam","memberInfo","myTeamList","team_challenges"]),
    },
    methods: {
        moveMain(){
            console.log("메인으로가자");
            this.$router.push("/teammain");
        },
        moveTeamChallenge(){
            this.$store.dispatch("GET_TEAMCHALLENGE_INFO", this.memberInfo.memberId);
            const token={
                memberId: this.memberInfo.memberId,
                teamId:this.selectTeam.teamId
            };
            this.$store.dispatch("GET_TEAMCHALLENGER_INFO", token);       
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
        },          
    },
}
</script>

<style >
</style>