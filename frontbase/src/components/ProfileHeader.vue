<template>
    <div>
        <v-layout >
            <v-btn color="secondary" @click="moveMain">
                <i class="fas fa-user-edit fa-2x" ></i>
                <div>&nbsp;&nbsp;&nbsp;내 정보 수정</div>
            </v-btn>

            <v-btn color="secondary" @click="moveMain">
                <i class="fas fa-edit fa-2x" ></i>
                <div>&nbsp;&nbsp;&nbsp;팀 정보 수정</div>
            </v-btn>

            <v-btn color="success" @click="moveTeamFeed">
                <i class="fas fa-users fa-2x" ></i>
                <div>&nbsp;&nbsp;&nbsp;팀원 목록</div>
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