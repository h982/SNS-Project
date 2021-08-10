<template>
  <div class="feed-item">
    <div class="feed_t">
      <div class="feed_writer">{{ feed.writer }}</div>
      <div class="feed_date">{{ feed.writeDate }}</div>
    </div>
    <div class="feed-card">
      <div class="feed-btns">
        <div @click="modifyFeed(feed.feedId)" class="modiBtn"></div>
        <div @click="deleteFeed" class="delBtn"></div>
      </div>
      <div class="feed-wrap">
        <div
          class="img"
          :style="{
            'background-image': 'url(' + feed.photos[0].filePath + ')'
          }"
        ></div>
      </div>
      <div class="contentsWrap">
        <div class="like_wrap">
          <div class="likeBtn" @click="likeBtn"></div>
        </div>
        <div class="desc">
          {{ feed.contents }}
        </div>
        <div class="showComment" @click="mvComment()">댓글보기</div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import { createInstance } from "@/api/teamindex.js";
import defaultImage from "../../assets/images/img-placeholder.png";
import defaultProfile from "../../assets/images/profile_default.png";

export default {
  props: ["feed"],
  data: () => {
    return {
      defaultImage,
      defaultProfile,
      feedget: {
        memberId: "",
        page: ""
      }
    };
  },
  computed: {
    ...mapGetters(["memberInfo", "myTeamList"])
  },
  methods: {
    likeBtn() {
      alert("like");
    },
    mvComment() {
      console.log("피드아이디");
      console.log(this.feed.feedId);
      this.$store.dispatch("GET_COMMENTS", this.feed.feedId);
      this.$store.dispatch("SET_FEEDID", this.feed.feedId);
      this.$router.replace("/comment");
    },
    modifyFeed(data) {
      console.log(data);
      if (this.feed.writer.replaceAll('"', "") === this.memberInfo.name) {
        this.$store.dispatch("SET_FEEDID", this.feed.feedId);
        this.$store.dispatch(
          "GET_TEAMCHALLENGEING_INFO",
          this.memberInfo.memberId
        );
        const body = {
          memberId: this.memberInfo.memberId,
          teamId: this.myTeamList[0].text.teamId,
          teamName: this.myTeamList[0].text.name,
          contents: this.feed.contents,
          writer: this.feed.writer,
          image: this.feed.photos[0].filePath
        };
        console.log(body);
        this.$store.dispatch("SET_ONEFEED", body);
        this.$router.push("/updateFeed");
      } else {
        alert("본인만 수정할 수 있습니다");
      }
    },
    deleteFeed() {
      if (this.feed.writer.replaceAll('"', "") === this.memberInfo.name) {
        const instance = createInstance();
        instance
          .delete("/feed/" + this.feed.feedId)
          .then(response => {
            if (response.data.data === "success") {
              this.$store.dispatch(
                "GET_TEAMCHALLENGEING_INFO",
                this.memberInfo.memberId
              );
              alert("피드 삭제 완료");
              this.feedget.memberId = this.memberInfo.memberId;
              this.feedget.page = 0;
              this.$store.dispatch("getFeeds", this.feedget);
              this.$router.push("/feed");
            } else {
              alert("피드 삭제 실패");
            }
          })
          .catch(() => {
            alert("에러");
          });
      } else {
        alert("본인만 삭제할 수 있습니다");
      }
    }
  }
};
</script>
