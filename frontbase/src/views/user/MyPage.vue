<template>
  <div>
    <b-container class="mt-4" v-if="memberInfo">
      <b-row>
        <b-col></b-col>
        <b-col cols="8">
          <b-jumbotron>
            <template #header>My Page</template>

            <template #lead>
              내 정보 확인페이지입니다.
            </template>

            <hr class="my-4" />

            <b-container class="mt-4">
              <b-row>
                <b-col cols="2"></b-col>
                <b-col cols="3" align-self="end">이름: </b-col>
                <b-col cols="4" align-self="start">{{memberInfo.name}}</b-col>
                <b-col cols="2"></b-col>
              </b-row>

              <b-row>
                <b-col cols="2"></b-col>
                <b-col cols="3" align-self="end">이메일: </b-col>
                <b-col cols="4" align-self="start">{{memberInfo.email}}</b-col>
                <b-col cols="2"></b-col>
              </b-row>

              <b-row>
                <b-col cols="2"></b-col>
                <b-col cols="3" align-self="end">포인트: </b-col>
                <b-col cols="4" align-self="start">{{memberInfo.point}}</b-col>
                <b-col cols="2"></b-col>
              </b-row>

              <b-row>
                <b-col cols="2"></b-col>
                <b-col cols="3" align-self="end">주소: </b-col>
                <b-col cols="4" align-self="start">{{memberInfo.address}}</b-col>
                <b-col cols="2"></b-col>
              </b-row>

              <b-row>
                <b-col cols="2"></b-col>
                <b-col cols="3" align-self="end">우편번호: </b-col>
                <b-col cols="6" align-self="start">{{memberInfo.zonecode}}</b-col>
                <b-col cols="2"></b-col>
              </b-row>
              
            </b-container>
            <hr class="my-4" />

            
            
            <b-row class="col-md-5" style="float: none; margin:0 auto;">
              <b-button variant="primary" class="mr-1" @click.prevent="modify">정보수정</b-button>
              <b-button variant="danger" href="#" @click.prevent="check()">회원탈퇴</b-button>
            </b-row>
            <br>
            <b-row>
              <join-request />
            </b-row>

            
          </b-jumbotron>
        </b-col>
        <b-col></b-col>
      </b-row>
      
    </b-container>
    
  </div>
</template>

<script>
import { mapState } from "vuex";
import JoinRequest from "@/views/user/JoinRequest";
export default {
  name: "mypage",
  components: {
    JoinRequest
  },
  computed: {
    ...mapState(["memberInfo","managingTeam"])
  },
  
  created: function(){
    this.$store.dispatch("getRequests", this.managingTeam.teamId);
  },
  methods: {
    modify() {
      alert("아직 안만듬");
      //this.$router.push('/');
    },
    delete(){
      alert("아직 안만듬");
    },
    check(){
      console.log(this.managingTeam);
    }
  },
};
</script>

<style></style>
