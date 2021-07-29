<template>
  <div class="feed newsfeed">
    <div class="wrapB">
      <feed-item v-for="(feed, idx) in feeds" :key="idx" :feed="feed" />
      <!-- <feed-item></feed-item> -->
    </div>
  </div>
</template>
<script>
import "../../components/css/feed/feed-item.scss";
import "../../components/css/feed/newsfeed.scss";
import FeedItem from "@/views/Feed/FeedItem.vue";
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
  }
};
</script>
