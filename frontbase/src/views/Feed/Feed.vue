<template>
  <div class="feed newsfeed">
    <div class="wrapB">
      <feed-item
        v-for="(feed, index, idx) in feeds"
        :key="idx"
        :feed="feed"
        :index="index"
      />
      <v-btn
        @click="mvWrite"
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
    <infinite-loading
      @infinite="infiniteHandler"
      spinner="spinner"
    ></infinite-loading>
  </div>
</template>
<script>
import "../../components/css/feed/newsfeed.scss";
import FeedItem from "@/views/Feed/FeedItem.vue";
import "../../components/css/feed/feed-item.scss";
import { mapGetters } from "vuex";
import InfiniteLoading from "vue-infinite-loading";
import { createInstance } from "@/api/teamindex.js";
export default {
  data() {
    return {
      feedget: {
        memberId: "",
        page: ""
      },
      limit: 3,
      page: 0,
      scrollHeight: 0,
      scrollTop: 0,
      clientHeight: 0
    };
  },
  components: {
    FeedItem,
    InfiniteLoading
  },
  computed: {
    ...mapGetters(["memberInfo", "feeds"])
  },
  mounted() {},
  created() {
    this.feedget.memberId = this.memberInfo.memberId;
    this.feedget.page = 0;
    this.page = 0;
    this.$store.dispatch("getFeeds", this.feedget);
    this.$store.dispatch("GET_LIKELIST", this.memberInfo.memberId);
    this.$store.dispatch("GET_MY_TEAM_INFO", this.memberInfo.memberId);
    this.$store.dispatch("getTeamLists");
    this.$store.dispatch("GET_ENTIRECHALLENGE_INFO", this.memberInfo.memberId);
    this.$store.dispatch("getMyFeeds", this.memberInfo.memberId);
  },
  methods: {
    mvWrite() {
      this.$store.dispatch(
        "GET_TEAMCHALLENGEING_INFO",
        this.memberInfo.memberId
      );
      this.$router.push("/writefeed");
    },

    check() {
      console.log(this.feeds);
      console.log(this.feedsList);
    },

    infiniteHandler($state) {
      const instance = createInstance();
      this.page += 1;
      console.log(this.page);
      instance
        .get("/feed/" + this.memberInfo.memberId + "/" + this.page)
        .then(response => {
          console.log(response);
          setTimeout(() => {
            if (response.data.object.length) {
              this.$store.commit("setFeeds", response.data.object);
              $state.loaded();
              this.limit += 3;
            } else {
              $state.complete();
            }
          }, 1300);
        })
        .catch(error => {
          console.log(error);
        });
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

.create {
  position: absolute;
  right: 120px;
  top: 150px;
}
</style>
