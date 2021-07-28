<template>
    <div class="feed newsfeed">
        <v-layout >
            <v-bottom-navigation
                class="mx-auto"
                shift
                x-large
            >
                <v-btn color="secondary" @click="moveMain">
                <i class="fas fa-address-card fa-2x"></i>
                <div>&nbsp;&nbsp;&nbsp;정보</div>
                </v-btn>

                <v-btn color="success" @click="moveTeamFeed">
                <i class="fas fa-clipboard fa-2x" ></i>
                <div>&nbsp;&nbsp;&nbsp;피드</div>
                </v-btn>

                <v-btn color="primary" @click="moveBoard">
                <i class="fas fa-check fa-2x" ></i>
                <div>&nbsp;&nbsp;&nbsp;공지사항</div>
                </v-btn>
                
                <v-btn color="warning" @click="moveChattingRoom">
                <i class="fas fa-comments fa-2x"></i>
                <div>&nbsp;&nbsp;&nbsp;채팅</div>
                </v-btn>

                <v-btn color="error"  @click="moveTeamChallenge">
                <i class="fas fa-trophy fa-2x"></i>
                <div>&nbsp;&nbsp;&nbsp;챌린지</div>
                </v-btn>
            </v-bottom-navigation>
        </v-layout>
        <div class="wrapB">
            <feed-item></feed-item>
            <feed-item></feed-item>
            <feed-item></feed-item>
            <!-- <feed-item v-for="(feed, idx) in feeds" :key="idx" :feed="feed" /> -->
        </div>
    </div>
</template>
<script>
import "@/components/css/feed/feed-item.scss";
import "@/components/css/feed/newsfeed.scss";
import FeedItem from "@/views/Feed/FeedItem.vue";

export default {
    components: {
        FeedItem
    },
  // computed: {
  //   ...mapGetters(["feeds"])
  // },
  // created() {
  //   this.$store.dispatch("getFeeds");
  // }
    methods: {
    moveMain(){
        this.$router.push("/teammain");
    },
    moveTeamChallenge(){
        this.$router.push("/teamChallenge");
    },
    moveBoard(){
            this.$router.push("/board");
    },
    moveChattingRoom(){
        this.$router.push("/chattingroom");
    },
    moveTeamFeed(){
        this.$router.push("/teamFeed");
    }
},
};
</script>
