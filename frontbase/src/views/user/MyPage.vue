<template>
  <div>
    <navi />
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
                <b-col cols="2" align-self="end">이름</b-col
                ><b-col cols="4" align-self="start">{{
                  memberInfo.name
                }}</b-col>
                <b-col cols="2"></b-col>
              </b-row>
              <b-row>
                <b-col cols="2"></b-col>
                <b-col cols="2" align-self="end">이메일</b-col
                ><b-col cols="4" align-self="start">{{ memberInfo.email }}</b-col>
                <b-col cols="2"></b-col>
              </b-row>
              
              
            </b-container>
            <hr class="my-4" />

            <b-button variant="primary" href="#" class="mr-1"
              >정보수정</b-button
            >
            <b-button variant="danger" href="#">회원탈퇴</b-button>
          </b-jumbotron>
        </b-col>
        <b-col></b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import Navi from "@/components/common/Navi";
import { mapState } from "vuex";

export default {
  name: "mypage",
  components: {
    Navi
  },
  computed: {
    ...mapState(["memberInfo"])
  }
};
</script>

<style></style>
