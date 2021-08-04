<template>
  <div class="feed-item">
    <div class="feed_t">
      <div class="feed_writer">{{ feed.writer.replaceAll('"', "") }}</div>
      <div class="feed_date">{{ feed.writeDate }}</div>
    </div>
    <div class="feed-card">
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
        <div class="showComment" @click="mvComment">댓글보기</div>
      </div>
    </div>
  </div>
</template>

<script>
import defaultImage from "../../assets/images/img-placeholder.png";
import defaultProfile from "../../assets/images/profile_default.png";
export default {
  props: ["feed"],
  data: () => {
    return { defaultImage, defaultProfile };
  },
  methods: {
    likeBtn() {
      alert("like");
    },
    mvComment() {
      this.$router.push("/comment");
    }
  }
};
</script>
