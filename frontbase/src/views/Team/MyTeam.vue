<template>
  <v-container grid-list-xl>
    <v-layout >
      <v-bottom-navigation
        class="mx-auto"
        shift
        x-large
      >
        <v-btn color="secondary">
          <i class="fas fa-running fa-2x" @click="running"></i>
        </v-btn>

        <v-btn color="success">
          <i class="fas fa-dumbbell fa-2x" @click="helth"></i>
        </v-btn>

        <v-btn color="primary">
          <i class="fas fa-swimmer fa-2x" @click="swimming"></i>
        </v-btn>
        
        <v-btn color="error">
          <i class="fas fa-table-tennis fa-2x" @click="tableTennis"></i>
        </v-btn>
      </v-bottom-navigation>
    </v-layout>

    <v-layout row justify-center align-center wrap class="mt-4 pt-2">
      <v-flex
      v-for="(myTeam, idx) in this.myTeamList"
      :key="idx"
      xs12 sm6 md4 lg3 xl3
      >
          <v-card
            v-if="myTeam.sportDto.sportId === selected || selected === 4"
            hover
            flat
            height="230"
          >
            <v-img :src="myTeam.imgPath" aspect-ratio="2.75" height="130" contain></v-img>
            <v-card-title primary-title class="justify-center">
              <v-flex text-xs-center subheading font-weight-bold>{{myTeam.name}}</v-flex>
            </v-card-title> 
            <v-card-text>
              {{ myTeam.introduction }}
            </v-card-text>
          </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { mapGetters } from "vuex";
import thumbnail1 from "@/assets/images/thumbnail.jpg";


export default {
  components: {
  
  },
  computed: {
    ...mapGetters(["myTeamList"]),
  },
  created() {
    this.$store.dispatch("GET_TEAM_INFO");
  },
  data() {
    return {
      selected: 4,
      thumbnail1: thumbnail1,
    };
  },
  methods: {
    running() {
      this.selected = 0;
    },
    helth() {
      this.selected = 1;
    },
    swimming() {
      this.selected = 2;
    },
    tableTennis() {
      this.selected = 3;
    },
  },
};
</script>

<style scoped>

</style>
