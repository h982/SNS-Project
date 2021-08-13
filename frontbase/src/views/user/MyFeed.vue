<template>
  <v-container grid-list-xl>
    <v-toolbar-title class="headline">
      <span>My</span>
      <span class="green--text">Feed</span>
    </v-toolbar-title>
    <v-layout justify-center align-center wrap class="mt-4 pt-2">
      <v-card   elevation="0">
        <p>{{memberInfo.name}}   <v-btn @click="move()" icon elevation="0"><v-icon>settings</v-icon></v-btn></p>
        <br>
        <p>게시물: {{myFeeds.length}} &nbsp&nbsp 가입된그룹:{{myTeamList.length}} &nbsp&nbsp  도전중인 챌린지: {{feed_challenging.length}}</p>
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
    ...mapGetters(["memberInfo","feeds","myTeamList","feed_challenging","myFeeds"])
  },
  created(){
  
  },
  data() {
    return {
      dialog: false,
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