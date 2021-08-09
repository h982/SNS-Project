<template>
  <div class="feed-item">
    <div class="feed_t">
      <div class="feed_writer">{{ feed.writer.replaceAll('"', "") }}</div>
      <div class="feed_date">{{ feed.writeDate }}</div>
    </div>
    <div class="feed-card">
      <div class="feed-btns">
        <div class="feed-show-btn" @click="showBtns"></div>
        <div class="feed-change">
          <div @click="modifyFeed">수정</div>
          <div>삭제</div>
        </div>
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
          {{ feed.contents.replaceAll('"', "") }}
        </div>
        <div class="showComment" @click="mvComment()">댓글보기</div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import defaultImage from "../../assets/images/img-placeholder.png";
import defaultProfile from "../../assets/images/profile_default.png";

export default {
  props: ["feed"],
  data: () => {
    return { defaultImage, defaultProfile };
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
    showBtns() {
      var showitem = document.querySelector(".feed-change");
      if (showitem.style.display == "none") {
        showitem.style.display = "block";
      } else {
        showitem.style.display = "none";
      }
    },
    modifyFeed() {
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
    }
  }
};
</script>
