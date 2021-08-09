<template>
  <v-container grid-list-xl>
    <h2 class="pl-4">
      <span>My</span>
      <span class="green--text">Feed</span>
    </h2>
    <v-layout row justify-center align-center wrap class="mt-4 pt-2">
      <v-card>
        <h5>{{memberInfo.name}}   <v-btn>프로필 편집</v-btn></h5>
  
        <h5>{{memberInfo.email}}</h5>
        <h5>{{memberInfo.point}}</h5>
        <h5>{{memberInfo.address}}</h5>
        <h5>{{memberInfo.mbti}}</h5>
      </v-card>
    </v-layout>
    <hr style="height:1px;border:none;color:#333;background-color:#333;" />

    <v-layout row justify-center align-center wrap class="mt-4 pt-2">
      <v-dialog
        lazy
        max-width="1000"
        v-for="myFeed in feeds"
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
            <h3 class="headline mb-3">
              <span>{{myFeed.contents}}</span>
            </h3>
            <v-chip color="green" text-color="white">{{myFeed.teamchallenge.title}}</v-chip>
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
  created(){
    this.$store.dispatch("getFeeds");
    //this.$store.dispatch("getMyFeeds");
  },
  computed:{
    ...mapGetters(["memberInfo","feeds"])
  },
  data() {
    return {
      dialog: false,
    };
  }
};
</script>

<style  scoped>
</style>