<template>
  <v-container>

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
          <i class="fas fa-clipboard fa-2x"></i>
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
    <v-layout column justify-center class="mt-4 pt-2">
      <h1 class="text-xs-center mb-4 pb-2">{{selectTeam.name.replaceAll("\"", "")}}</h1>
      <br>
      <v-img :src="selectTeam.photoDto.filePath" aspect-ratio="2.75" height="330" contain></v-img>
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
        <v-btn @click="join" type="button" color="green" class="white--text">가입하기</v-btn>
      </v-layout>
      <v-btn
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
      <v-btn large flat to="/teamlist" class="green--text">
        <v-icon>arrow_back</v-icon>Back to Teamlist
      </v-btn>
    </v-layout>
  </v-container>
</template>

<script>
import { mapGetters } from 'vuex';
import TeamHeader from "@/components/TeamHeader"
import { createInstance } from "@/api/index.js";

export default {
  computed:{
    ...mapGetters(["selectTeam","memberInfo","myTeamList"]),
  },
  created() {
    console.log(this.selectTeam);
    // console.log(this.memberInfo);
    this.$store.dispatch("GET_MY_TEAM_INFO",this.memberInfo.memberId);
    console.log(this.myTeamList);
  },
  data() {
    return {
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
          if (response.data.message === "success") {
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
