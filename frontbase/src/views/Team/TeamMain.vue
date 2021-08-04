<template>
  <v-container>

    <v-layout >
      <v-bottom-navigation
        v-if="teamcheck === true"
        class="mx-auto"
        shift
        x-large
      >
      <team-header />
        
      </v-bottom-navigation>
    </v-layout>
    <v-layout column justify-center class="mt-4 pt-2">
      <h1 class="text-xs-center mb-4 pb-2">{{selectTeam.name.replaceAll("\"", "")}}</h1>
      <br>
      <div v-if="selectTeam.photoDto === null">
        <v-img :src="thumbnail1" aspect-ratio="2.75" height="330" contain></v-img>
      </div>
      <div v-else>
        <v-img :src="selectTeam.photoDto.filePath" aspect-ratio="2.75" height="330" contain></v-img>
      </div>
      <v-layout column justify-center align-center class="mt-4 pt-2">
        <h2>팀 소개</h2>
        <v-flex wrap justify-center align-center class="textbox">
          <h3>
            {{selectTeam.introduction.replaceAll("\"", "")}}
          </h3>
        </v-flex>
        <br>

        <h2>팀장</h2>
        <p>{{selectTeam.leader.replaceAll("\"", "")}}</p>
        <br>
        <br>
        <v-btn
          v-if="selectTeam.member.memberId != memberInfo.memberId && teamcheck === false"
          @click="join"
          type="button"
          color="green"
          class="white--text"
        >가입하기
        </v-btn>
      </v-layout>
      <v-btn
        v-if="selectTeam.member.memberId != memberInfo.memberId && teamcheck === false"
        color="secondary"
        @click="join"
        elevation="7"
        fab
        large
        x-large
        x-small
        class="join"
      ><i class="fas fa-sign-in-alt fa-2x"></i>
      </v-btn>
      <br>
      <br>
      <v-layout>
        <v-btn large flat to="/teamlist" class="green--text">
          <v-icon>arrow_back</v-icon>Back to Teamlist
        </v-btn>
      </v-layout>
    </v-layout>
  </v-container>
</template>

<script>
import { mapGetters } from 'vuex';
import TeamHeader from "@/components/TeamHeader.vue"
import { createInstance } from "@/api/index.js";
import thumbnail1 from "@/assets/images/thumbnail.jpg";

export default {
  computed:{
    ...mapGetters(["selectTeam","memberInfo","myTeamList","team_challenges"]),
  },
  created() {
    // console.log(this.memberInfo.memberId);
    this.$store.dispatch("GET_MY_TEAM_INFO",this.memberInfo.memberId);
    this.teamchecking();
    // console.log(this.myTeamList);
    // console.log(this.selectTeam.teamId);
    
  },
  data() {
    return {
      teamcheck: false,
      thumbnail1: thumbnail1,
    };
  },
  components: {
    TeamHeader
  },
  methods: {
    join() {
      const instance = createInstance();
      instance
      .post("/request", {
        "member":{
          "memberId": this.memberInfo.memberId,
        },
        "team":{
          "teamId": this.selectTeam.teamId
        }
      })
      .then(
        (response) => {
          console.log(response);
          if (response.status === 201) {
            alert("가입 요청 완료");
          } else {
            alert("가입 요청 실패");
          }
        }
      )
      .catch(() => {
        alert("에러");
      });
    },
    moveMain(){
      this.$router.push("/teammain");
    },
    moveTeamChallenge(){
      //alert(this.memberInfo.memberId);
      this.$store.dispatch("GET_TEAMCHALLENGE_INFO", this.memberInfo.memberId);
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
    teamchecking() {
      for(let i=0; i<this.myTeamList.length; i++) {
        if (this.myTeamList[i].value.teamId === this.selectTeam.teamId) {
          this.teamcheck = true;
          console.log(this.teamcheck);
          break;
        }
      }
    },
  },
}
</script>

<style scoped>
.join {
  position: absolute;
  right: 0px;
  bottom: 0px;  
}
.textbox {
  padding: 0px 150px;
}
.birthday {
  position: relative;
  right: 0px;
}
</style>
