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
      <h1 class="text-xs-center mb-4 pb-2">팀 이름</h1>
      <span class="birthday">팀 생일 : 2021.07.26</span>
      <br>
      <v-img :src="thumbnail1" aspect-ratio="2.75" height="330" contain></v-img>
      <v-layout column justify-center align-center class="mt-4 pt-2">
        <h2>팀 소개</h2>
        <br>
        <v-flex wrap justify-center align-center class="textbox">
          <h3>
          Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
          </h3>
        </v-flex>
        <br>
        <v-btn @click="join" type="button" color="green" class="white--text">가입하기</v-btn>
        <br>
        <h2>팀원 소개(0 명)</h2>
        <br>
        <p>김아무개</p>
        <p>김아무개</p>
        <p>김아무개</p>
        <p>김아무개</p>
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
import thumbnail1 from "@/assets/images/thumbnail.jpg";
import { mapGetters } from 'vuex';
import TeamHeader from "@/components/TeamHeader"
export default {
  data() {
    return {
      thumbnail1: thumbnail1,
    };
  },
  components: {
    TeamHeader
    
  },
  // computed: {
  //   ...mapGetters(["groupInfo"]),
  // },
  // created() {
  //   this.$store.dispatch("getGroupInfo");
  // },
  methods: {
    join() {
      console.log("가입되었습니다.");
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
