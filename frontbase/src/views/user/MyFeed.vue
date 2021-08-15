<template>
  <v-container grid-list-xl>
    <v-toolbar-title class="headline">
      <span><b>My</b></span>
      <span class="green--text"><b>Feed</b></span>
    </v-toolbar-title>
    <v-layout justify-center align-center wrap class="mt-4 pt-2">
      <v-card   elevation="0">
        <v-chip :color="nameColor" dark>{{memberInfo.name}}<v-btn @click="move()" icon elevation="0"><v-icon>settings</v-icon></v-btn></v-chip>
        <br>
        <p id ="nameColor"><b>
            게시물: {{myFeeds.length}} &nbsp;&nbsp; 
            가입된그룹:{{myTeamList.length}} &nbsp;&nbsp; 
            포인트: {{memberInfo.point}} &nbsp;&nbsp;
            Grade: {{grade}}
          </b></p>
        
      </v-card>
    </v-layout>
    <hr style="height:1px;border:none;color:#333;background-color:#333;" />

    <v-layout row justify-center align-center wrap class="mt-4 pt-2">
      <v-dialog
        lazy
        max-width="1000"
        v-for="myFeed in myFeeds"
        :key="myFeed.contents"
      >
        <template v-slot:activator="{ on }">
          <v-flex xs12 sm6 md4 lg4 xl4 v-on="on">
            <v-card hover flat color="transparent">
              <v-img
                :src="myFeed.photos[0].filePath"
                :alt="myFeed.contents"
                height="230"
                lazy-src="https://cdn.dribbble.com/users/503653/screenshots/3143656/fluid-loader.gif"
              ></v-img>
              <v-card-title primary-title class="justify-center">{{myFeed.contents}}</v-card-title>
            </v-card>
          </v-flex>
        </template>
        <v-card>
          <v-img :src="myFeed.photos[0].filePath"></v-img>
          
          <v-card-text>
            <p class="headline mb-3">
              <span>{{myFeed.contents}}</span>
            </p>
            <v-chip color="green" text-color="white">{{myFeed.contents}}</v-chip>
            <v-chip color="green" text-color="white">{{myFeed.writeDate}}</v-chip>

          </v-card-text>
        
        </v-card>
      </v-dialog>
    </v-layout>
  </v-container>
</template>

<script>

import { mapGetters } from "vuex";

export default {
  computed:{
    ...mapGetters(["memberInfo","feeds","myTeamList","team_challenging","myFeeds"])
    
  },
  created(){
    if(this.memberInfo.authenticated){
      this.grade ="Premium";
    }
  },
  mounted(){
    if(this.memberInfo.point>=100){
      this.nameColor = "#9400D3"
    }else if(this.memberInfo.point>=75){
      this.nameColor = "#7AD7BE"
    }else if(this.memberInfo.point>=50){
      this.nameColor = "#FFA500"
    }else if(this.memberInfo.point>=25){
      this.nameColor = "#52478B"
    }
    var domObj = document.getElementById("nameColor");
    domObj.style.color = this.nameColor;
  },
  data() {
    return {
      dialog: false,
      grade : "Normal",
      nameColor: "#8B4513"
    };
  },
  methods:{
    move(){
      this.$router.push("/mypage");
    },
    check(){
      console.log(this.myFeeds);
    }
  }
};
</script>

<style  scoped>

</style>