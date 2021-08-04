<template>
  <div class="feed newsfeed">
    <div class="wrapB">
      <feed-item v-for="(feed, idx) in feeds" :key="idx" :feed="feed" />
      <div class="writebtn" @click="mvWrite" />
    </div>
  </div>
</template>
<script>
import "../../components/css/feed/newsfeed.scss";
import FeedItem from "@/views/Feed/FeedItem.vue";
import "../../components/css/feed/feed-item.scss";
import { mapGetters } from "vuex";
import http from "@/util/http-common";

export default {
  components: {
    FeedItem
  },
  computed: {
    ...mapGetters(["feeds"])
  },
  created() {
    this.$store.dispatch("getFeeds");
  },
  methods: {
    mvWrite() {
      this.$router.push("/writefeed");
    }
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
