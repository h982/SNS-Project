<template>
  <div>
    <feed-item></feed-item>
    <feed-item></feed-item>
    <feed-item></feed-item>
    <!-- <feed-item v-for="(feed, idx) in feeds" :key="idx" :feed="feed" /> -->
  </div>
</template>
<script>
import FeedItem from "@/views/Feed/FeedItem.vue";
import mapGetters from "vuex";

export default {
  components: {
    FeedItem
  }
  // computed: {
  //   ...mapGetters(["feeds"])
  // },
  // created() {
  //   this.$store.dispatch("getFeeds");
  // }
};
</script>
