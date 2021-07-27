<template>
  <v-container grid-list-xl>
    
    <h2 class="pl-4">
      <span>전체 </span>
      <span class="green--text">챌린지</span>
    </h2>
    <v-layout row justify-center align-center wrap class="mt-4 pt-2">
      <v-dialog
        v-model="project.dialog"
        lazy
        max-width="1000"
        v-for="project in whole_challenges"
        :key="project.title"
      >
        <template v-slot:activator="{ on }">
          <v-flex xs12 sm6 md4 lg4 xl4 v-on="on">
            <v-card hover flat color="transparent">
              <v-img
                :src="project.poster"
                :alt="project.title"
                height="230"
                lazy-src="https://cdn.dribbble.com/users/503653/screenshots/3143656/fluid-loader.gif"
              ></v-img>
              <v-card-title primary-title class="justify-center">{{project.title}}</v-card-title>
            </v-card>
          </v-flex>
        </template>
        <v-card v-if="project.dialog">
          <v-img :src="project.poster"></v-img>
          <v-card-text>
            <h3 class="headline mb-0">
              <span>{{project.title}}</span>
            </h3>
            
            <v-chip color="green" text-color="white">{{project.tech.tech1}}</v-chip>
            <v-chip color="green" text-color="white">{{project.tech.tech2}}</v-chip>
            <v-chip color="green" text-color="white">{{project.tech.tech3}}</v-chip>

          </v-card-text>
          <v-card-actions>
            <v-btn flat large dark color="black" target="_blank" @click="enroll">
              등록하기
            </v-btn>

          
          <date-picker v-model="date" range></date-picker>

            <!-- <v-btn large flat dark color="green" :href="project.demo" target="_blank">
              <v-icon left>fas fa-desktop</v-icon>Demo
            </v-btn> -->
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-layout>
  </v-container>
</template>

<script>
import DatePicker from 'vue2-datepicker';
import { mapGetters, mapActions } from "vuex";

export default {
  components:{
    DatePicker 
  },
  computed: {
    ...mapGetters(["whole_challenges"])
  },
  created() {
    this.$store.dispatch("GET_WHOLECHALLENGE_INFO");
  },
  data() {
    return {
      dialog: false,
      // whole_projects: [
      //   {
      //     dialog: false,
      //     title: "작심삼일이라도 달리기",
      //     git: "",
      //     demo: "",
      //     tech: {
      //       tech1: "러닝",
      //       tech2: "팀챌린지",
      //       tech3: "기간: 3일",
      //     },
      //     poster: "https://i.ibb.co/p2cLTzW/image.jpg"
      //   },
      //   {
      //     dialog: false,
      //     title: "러닝은 거들뿐",
      //     git: "https://github.com/EldinZaimovic/Eldin-Space-Vue",
      //     demo: "https://eldin.space/",
      //     tech: {
      //       tech1: "VUE",
      //       tech2: "Storyblok",
      //       tech3: "HTML",
      //       tech4: "JavaScript"
      //     },
      //     poster: "https://i.ibb.co/p2cLTzW/image.jpg"
      //   },
      //   {
      //     dialog: false,
      //     title: "일주일만 버티자",
      //     git: "https://cdn.neow.in/news/images/uploaded/2018/11/1543476286_cybersecurity.jpg",
      //     demo: "https://www.brandly.com/",
      //     tech: {
      //       tech1: "VUE",
      //       tech2: "SCSS",
      //       tech3: "HTML",
      //       tech4: "JavaScript"
      //     },
      //     poster: "https://i.ibb.co/p2cLTzW/image.jpg"
      //   },
      //   {
      //     dialog: false,
      //     title: "달리기는 최고야",
      //     git: "",
      //     demo: "",
      //     tech: {
      //       tech1: "러닝",
      //       tech2: "팀챌린지",
      //       tech3: "기간: 3일",
      //     },
      //     poster: "https://i.ibb.co/p2cLTzW/image.jpg"
      //   },
      //   {
      //     dialog: false,
      //     title: "러닝은 거들뿐2",
      //     git: "https://github.com/EldinZaimovic/Eldin-Space-Vue",
      //     demo: "https://eldin.space/",
      //     tech: {
      //       tech1: "VUE",
      //       tech2: "Storyblok",
      //       tech3: "HTML",
      //       tech4: "JavaScript"
      //     },
      //     poster: "https://i.ibb.co/p2cLTzW/image.jpg"
      //   },
      //   {
      //     dialog: false,
      //     title: "일주일만 버티자2",
      //     git: "https://cdn.neow.in/news/images/uploaded/2018/11/1543476286_cybersecurity.jpg",
      //     demo: "https://www.brandly.com/",
      //     tech: {
      //       tech1: "VUE",
      //       tech2: "SCSS",
      //       tech3: "HTML",
      //       tech4: "JavaScript"
      //     },
      //     poster: "https://i.ibb.co/p2cLTzW/image.jpg"
      //   },
      // ],

      date: null,
    };
  },
  
  methods: {
    enroll() {
      console.log(this.date);
    },
    
    
  },
  
};
</script>


<style>

</style>