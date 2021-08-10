<template>
  <div class="feed newsfeed">
    <v-layout>
      <v-bottom-navigation
        v-if="teamcheck === true | this.selectTeam.memberId === this.memberInfo.memberId"
        class="mx-auto"
        shift x-large>
        <team-header />
      </v-bottom-navigation>

      <v-bottom-navigation v-else class="mx-auto" shift x-large>
        <team-header-2 />
      </v-bottom-navigation>
    </v-layout>

    <div class="wrapB">
      <feed-item v-for="(feed, idx) in teamFeeds" :key="idx" :feed="feed" />
      <div class="writebtn" @click="mvWrite" /> 
    </div>
  </div>
</template>
<script>
import "@/components/css/feed/feed-item.scss";
import "@/components/css/feed/newsfeed.scss";
import FeedItem from "@/views/Feed/FeedItem.vue";
import TeamHeader from "@/components/TeamHeader.vue";
import { mapGetters } from "vuex";

export default {
  components: {
    FeedItem,
    TeamHeader
  },
  data() {
    return {
      teamcheck: false,
    }
  },
  computed: {
    ...mapGetters(["teamFeeds", "selectTeam", "myTeamList", "memberInfo"]),
  },
  created() {
    this.$store.dispatch("getMyTeamFeeds", this.selectTeam.teamId);
    this.teamchecking();
    console.log(this.selectTeam.memberId);
    console.log(this.memberInfo.memberId);
    console.log(this.teamcheck);
  },
  methods: {
    teamchecking() {
      for(let i=0; i<this.myTeamList.length; i++) {
        if (this.myTeamList[i].value.teamId === this.selectTeam.teamId) {
          this.teamcheck = true;
          break;
        }
      }
    },
    // mvWrite() {
    //   this.$router.push("/writefeed");
    // }
  }
};
</script>

<style scoped>
.writebtn {
  position: fixed;
  bottom: 30px;
  right: 30px;
  width: 50px;
  height: 50px;
  background-image: url("../../assets/plus.png");
  background-size: cover;
}
.writebtn:hover {
  cursor: pointer;
}
</style>
