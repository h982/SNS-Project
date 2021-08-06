<template>
  <div class="feed newsfeed">
    <v-layout>
      <v-bottom-navigation class="mx-auto" shift x-large>
        <team-header />
      </v-bottom-navigation>
    </v-layout>
    <div class="wrapB">
      <feed-item v-for="(feed, idx) in teamFeeds" :key="idx" :feed="feed" />
      <!-- <div class="writebtn" @click="mvWrite" /> -->
    </div>
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
  computed: {
    ...mapGetters(["teamFeeds", "selectTeam"]),
  },
  created() {
    this.$store.dispatch("getMyTeamFeeds", this.selectTeam.teamId);
  },
  methods: {
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
