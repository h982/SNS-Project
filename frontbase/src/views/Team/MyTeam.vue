<template>
  <v-container grid-list-xl>
    <v-layout justify-center align-center class="mt-4 pt-2">
      <v-layout row wrap class="mt-4 pt-2">
        <PostPreview2
          v-for="post in myTeamList"
          :key="post.text.teamId"
          :name="post.text.name"
          :introduction="post.text.introduction"
          :imgPath="post.text.photoDto.filePath"
          :teamId="post.text.teamId"
          :selectMyTeam="post.text"
        />

      </v-layout>
    </v-layout>
  </v-container>
  
</template>

<script>
import { mapGetters } from "vuex";
import PostPreview2 from "@/components/PostPreview2";

export default {
  components: {
    PostPreview2
  },
  computed: {
    ...mapGetters(["myTeamList","memberInfo"]),
  },
  created() {
    this.$store.dispatch("GET_MY_TEAM_INFO",this.memberInfo.memberId);
  },
  data() {
    return {
      
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
    setFilter: function(filter) {
			this.currentFilter = filter;
		},
    confirm(){
      alert(this.myTeamList[0].text.imgPath);
      console.log(this.myTeamList);
    },
  },
};
</script>

<style scoped>

</style>
