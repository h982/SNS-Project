<template>
  <div>
    <b-container class="mt-4" v-if="memberInfo">
      <b-row>
        <b-col></b-col>
        <b-col cols="8">
          <h1>My Page</h1>

            <h3>
              내 정보 확인페이지입니다.
            </h3>

          <hr class="my-4" />

          <v-text-field
            name="member.name"
            color="green"
            background-color="transparent"
            v-model="memberInfo.name"
            label="Name"
            readonly
          ></v-text-field>

          <v-text-field
            type="email"
            color="green"
            background-color="transparent"
            name="member.email"
            v-model="memberInfo.email"
            label="E-mail"
            readonly
            required
          ></v-text-field>

          <v-text-field
            type="point"
            color="green"
            background-color="transparent"
            name="member.email"
            v-model="memberInfo.point"
            label="Point"
            readonly
            required
          ></v-text-field>

          
          <v-text-field
            address="address"
            color="green"
            background-color="transparent"
            v-model="memberInfo.address"
            label="주소"
            readonly
            required
          ></v-text-field>

          <v-text-field
            name="member.addressDetail"
            color="green"
            background-color="transparent"
            v-model="memberInfo.addressDetail"
            label="상세주소"
            readonly
          ></v-text-field>

          <v-text-field
            type="mbti"
            color="green"
            background-color="transparent"
            name="member.mbti"
            v-model="memberInfo.mbti"
            label="MBTI"
            readonly
            required
          ></v-text-field>
            
          <hr class="my-4" />

          <v-layout row justify-center align-center wrap class="mt-4 pt-2">
            <v-btn color="blue" class="white--text" @click="memberModify">정보수정</v-btn>
            <div v-if="memberInfo.memberId === managingTeam.member.memberId">
              <v-btn color="green" class="white--text" @click="teamManagement">팀 관리</v-btn>
            </div>
            <v-btn color="red" class="white--text" @click.prevent="check()">회원탈퇴</v-btn>
          </v-layout>
          <br>
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
    console.log(this.memberInfo.memberId);
    console.log(this.managingTeam.member.memberId);
  },
  methods: {
    modify() {
      alert("아직 안만듬");
    },
    delete(){
      alert("아직 안만듬");
    },
    check(){
      console.log(this.managingTeam);
    },
    teamManagement() {
      this.$router.push("/teamManagement");
    },
    memberModify(){
      this.$router.push("/membermodify");
    },
  },
};
</script>

<style></style>
