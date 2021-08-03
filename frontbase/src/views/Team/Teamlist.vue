<template>
  <v-container grid-list-xl>
    <v-layout >
      <v-bottom-navigation
        class="mx-auto"
        shift
        x-large
      >
        <v-btn color="purple">
          <i class="fas fa-list-ul fa-2x white--text" @click="all"></i>
        </v-btn>

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
      v-for="(teamList, idx) in this.teamLists"
      v-if="teamList.sportDto.sportId === selected || selected === 5"
      :key="idx"
      xs12 sm6 md4 lg3 xl3
      >
        <v-card
          hover
          flat
          height="230"
          @click="confirm(teamList)"
        >
          <div v-if="teamList.photoDto === null">
            <v-img v-bind:src="thumbnail" aspect-ratio="2.75" height="130" contain></v-img>
          </div>
          <div v-else>
            <v-img :src="teamList.photoDto.filePath" aspect-ratio="2.75" height="130" contain></v-img>
          </div>
          <v-card-title primary-title class="justify-center">
            <v-flex text-xs-center subheading font-weight-bold>{{teamList.name.replaceAll("\"", "")}}
              <br>
              <div v-if="teamList.introduction.length >16 " class="txt_line">{{teamList.introduction.replaceAll("\"", "").slice(0,13)+"..."}}</div>                
              <div v-else class="txt_line">{{teamList.introduction.replaceAll("\"", "")}}</div>                
            </v-flex>
          </v-card-title> 
        </v-card>
      </v-flex>
    </v-layout>
    <img src="thumbnail" alt="">
    <v-btn
      @click="mvTeam"
      color="secondary"
      elevation="7"
      fab
      large
      x-large
      x-small
      class="create"
    ><i class="fas fa-plus"></i>
    </v-btn>
  </v-container>
</template>

<script>
import { mapGetters } from "vuex";
import thumbnail from "@/assets/images/thumbnail.jpg";


export default {
  components: {
    
  },
  computed: {
    ...mapGetters(["teamLists"]),
  },
  created() {
    this.$store.dispatch("getTeamLists");
  },
  data() {
    return {
      selected: 5,
      thumbnail: thumbnail,
    };
  },
  methods: {
    mvTeam() {
      // 생성버튼 클릭시 팀생성 페이지 이동
      this.$router.push("/teammake");
    },
    all() {
      this.selected = 5;
    },
    running() {
      this.selected = 1;
    },
    helth() {
      this.selected = 2;
    },
    swimming() {
      this.selected = 3;
    },
    tableTennis() {
      this.selected = 4;
    },
    confirm(data){
      this.$store.dispatch("SET_SELECT_TEAM",data).then(()=>{
        this.$router.replace("/teammain");
      });
    }
  },
};
</script>

<style scoped>
.create {
  position: absolute;
  right: 0px;
  bottom: 0px;
};
.txt_line {
  width:70px;
  padding:0 5px;
  overflow:hidden;
  text-overflow:ellipsis;
  white-space:nowrap;
};
</style>
