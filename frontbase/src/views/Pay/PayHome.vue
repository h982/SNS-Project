<template>
  <div>
  <v-container>
    <v-layout align-center data-aos="fade-right">
      <v-toolbar-title class="headline">
          <span><b>프리미엄</b></span>
          <span class="green--text"><b>&nbsp;서비스</b></span>
      </v-toolbar-title>
      <br><br><br><br>
    </v-layout>

    <v-layout justify-center align-center data-aos="fade-up">
      <v-toolbar-title class="headline">
          <span><b>당신이 누릴</b></span>
          <span class="green--text"><b>&nbsp;최고의 혜택</b></span>
      </v-toolbar-title>
      <br><br><br><br>
    </v-layout>

      <v-layout align-center data-aos="fade-left">
        <br><br>
          <v-toolbar-title class="headline">
          <span><b>1. Dark</b></span>
          <span class="green--text"><b>&nbsp;Mode</b></span>
          </v-toolbar-title>
      </v-layout>
      <br>

      <v-layout align-center column>
        <v-flex  v-for="portfolio in portfolios" :key="portfolio.src" >
          <v-img :src="portfolio.src" aspect-ratio="2.75" height="600" width="700" data-aos="fade-left"></v-img>
          <v-card-title primary-title class="justify-center">
          </v-card-title>
        </v-flex>
      </v-layout>
      <v-flex text-xs-center subheading font-weight-bold>다크모드를 즐겨보세요</v-flex> 
      
      <v-layout align-center data-aos="fade-left">
        <br><br>
          <v-toolbar-title class="headline">
          <span><b>2. Recommend</b></span>
          <span class="green--text"><b>&nbsp;Team</b></span>
          </v-toolbar-title>
      </v-layout>

      <v-layout justify-center align-center data-aos="fade-top">
        <v-btn @click="goPay()">
            <a-icon type="credit-card" />
            결제
        </v-btn>
      </v-layout>
  
  </v-container>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
  
  computed: {
    ...mapGetters(["memberInfo"]),
  },
  data() {
	  return {
      portfolios: [
        {
          src: "https://i.ibb.co/dQKZXhc/dark1.jpg",
        },

        {
          src: "https://i.ibb.co/4sQqWxw/dark2.jpg",
        },

         {
          src: "https://i.ibb.co/gvgzd15/dark3.jpg",
        },
        
      ]
    };
	},
  
  methods: {
    goPay(){
      if(!this.memberInfo.authenticated){
        this.$router.push("/payment");
      }else{
        alert("이미 프리미엄 회원입니다.")
      }
    }
  }
};
</script>

<style>


  .nav-container {
    display: flex;
    align-items: center;
    justify-content: center;
    position: absolute;
    top: -75px;
    left: 0;
    right: 0;
    
  }


</style>
