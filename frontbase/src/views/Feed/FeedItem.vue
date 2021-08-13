<template>
  <div class="feed-item">
    <div class="feed_t">
      <div class="user_wrap">
        <div class="profile"></div>
        <div class="feed_writer">{{ feed.writer }}</div>
      </div>
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
        <div class="like_wrap" @click="changeLike">
          <img
            class="likeBtn"
            v-if="this.isLike == true"
            src="../../assets/heart.png"
          />
          <img class="likeBtn" v-else src="../../assets/heart_b.png" />
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
  props: ["feed", "index"],
  data: () => {
    return {
      defaultImage,
      defaultProfile,
      feedget: {
        memberId: "",
        page: ""
      },
      isLike: null,
      feedlikeId: ""
    };
  },
  computed: {
    ...mapGetters(["memberInfo", "myTeamList", "feedLike", "likeList"])
  },
  created() {
    this.likeLists = this.likeList;
    for (let index = 0; index < this.likeList.length; index++) {
      if (
        this.feed.feedId == this.likeList[index].feedId &&
        this.likeList[index].feedLike == 1
      ) {
        this.feedlikeId = this.likeList[index].feedlikeId;
        this.isLike = true;
        break;
      } else {
        this.isLike = false;
      }
    }
  },
  methods: {
    changeLike() {
      if (!this.isLike) {
        var feedlike = {
          feedId: this.feed.feedId,
          feedLike: 1,
          memberId: this.memberInfo.memberId
        };
        const instance = createInstance();
        instance
          .post("/feedlike", feedlike)
          .then(response => {
            if (response.data.data === "success") {
              this.isLike = true;
              const instance = createInstance();
              instance
                .get("/feedlike/feed/" + this.feed.feedId)
                .then(({ data }) => {
                  this.feedlikeId = data.object[0].feedlikeId;
                })
                .catch(() => {});
              alert("좋아요!");
            } else {
              alert("좋아요실패");
            }
          })
          .catch();
      } else {
        const instance = createInstance();
        instance
          .delete("/feedlike/" + this.feedlikeId)
          .then(response => {
            if (response.data.data === "success") {
              this.isLike = false;
              alert("좋아요취소!");
            } else {
              alert("좋아요 취소실패");
            }
          })
          .catch();
      }
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
          image: this.feed.photos[0].filePath,
          photos: this.feed.photos[0]
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
