<template>
  <v-flex xs12 sm6 md4 lg4 xl4>
  
    <v-card @click="enrollCntTeam()" hover>
    
      <v-img :src="imgPath" aspect-ratio="2.75" height="230" :alt="name"></v-img>
      <v-card-title primary-title>
        <div>
          <h3 class="headline mb-0">{{name}}</h3>
          <div>
            <p class="green--text font-weight-medium">{{introduction}}</p>
          </div>
        </div>
      </v-card-title>
      
    </v-card>
  

  </v-flex>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  computed: {
    ...mapGetters(["selectTeam","myTeamList","memberInfo","SET_SELECT_TEAM"]),
  },
  props: {
    name: {
      type: String,
      required: true
    },
    introduction:{
      type:String,
      required:true
    },
    imgPath: {
      type: String,
      required: true
    },
    teamId: {
      type: Number,
      required: true
    },
    selectMyTeam:{
      type: Object,
      required:true
    },
    leader:{
      type:String,
      required:true
    }
  },
  methods: {
    enrollCntTeam(){
      console.log(this.selectMyTeam);
      const body = {
        name: this.name,
        introduction: this.introduction,
        leader: this.leader,
        photoDto:{
          filePath : this.imgPath,
        }
      };
      this.$store.dispatch("SET_SELECT_TEAM", body).then(()=>{
        this.$router.replace("/teammain");
      });
      //this.selectTeam=this.selectMyTeam;
      console.log(this.selectTeam);
      //alert(this.selectMyTeam);
    }
    
  },
};
</script>

<style scoped>
</style>