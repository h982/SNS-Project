<template>
  <v-container grid-list-xl>
    <v-layout >
      <v-bottom-navigation
        class="mx-auto"
        shift
        x-large
      >
        <v-btn color="purple" @click="all">
          <i class="fas fa-list-ul fa-2x white--text" ></i>
        </v-btn>

        <v-btn color="secondary" @click="running">
          <i class="fas fa-running fa-2x" ></i>
        </v-btn>

        <v-btn color="success" @click="health">
          <i class="fas fa-dumbbell fa-2x" ></i>
        </v-btn>

        <v-btn color="primary" @click="swimming">
          <i class="fas fa-swimmer fa-2x" ></i>
        </v-btn>
        
        <v-btn color="error" @click="tableTennis">
          <i class="fas fa-table-tennis fa-2x" ></i>
        </v-btn>
      </v-bottom-navigation>
    </v-layout>
    <div v-if="this.teamLists">
      <v-layout row justify-center align-center wrap class="mt-4 pt-2">
        <v-flex
        v-for="(teamList, idx) in this.filteredTeam"
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
    </div>
    
    <div v-else>
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
    </div>
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
    
    filteredTeam : function() { /* 배열의 아이템중 조건을 만족하는 아이템을 모아서 새로운 배열을 만들어 리턴함 */
        var cname = this.name.trim();
        return this.teamLists.filter(function(item,index) {
        if (item.sportDto.name.indexOf(cname) >-1) {
            return true;
        }
      });
    }
  },
  created() {
    this.$store.dispatch("getTeamLists");
  },
  data() {
    return {
      selected: 5,
      thumbnail: thumbnail,
      name: "",
    };
  },
  methods: {
    mvTeam() {
      this.$router.push("/teammake");
    },
    all() {
      this.name="";
    },
    running() {
      this.name ="러닝";
      console.log("러닝");
    },
    health() {
      this.name ="헬스";
      console.log("헬스");
    },
    swimming() {
      this.name="수영";
      console.log("수영");
    },
    tableTennis() {
      this.name="탁구";
      console.log("탁구");
      // this.selected = 4;
    },
    confirm(data){
      this.$store.dispatch("SET_SELECT_TEAM", data).then(()=>{
        this.$router.replace("/teammain");
      });
    },
  },
};
</script>

<style scoped>
.create {
  position: absolute;
  right: 80px;
  bottom: 50px;
};
.txt_line {
  width:70px;
  padding:0 5px;
  overflow:hidden;
  text-overflow:ellipsis;
  white-space:nowrap;
};
</style>
