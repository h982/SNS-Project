<template>
  <div class="feed-item">
    <div class="feed_t">
      <!-- <div
        class="profile-image"
        :style="{ 'background-image': 'url(' + defaultProfile + ')' }"
      ></div> -->
      <!-- <div class="user-info">
        <div class="user-name">
          <button>SSAFY</button>
        </div>
        <p class="date">9시간 후</p>
      </div>
      <div class="content">
        <p>이 글은 아주 좋습니다.</p>
      </div> -->
      <!-- <div>작성자</div> -->
      <div>{{ feed.writer.replaceAll('"', "") }}</div>
      <!-- <div>일시</div> -->
      <div>{{ feed.writeDate }}</div>
    </div>
    <div class="feed-card">
      <div
        class="img"
        :style="{ 'background-image': 'url(' + feed.photos[0].filePath + ')' }"
      ></div>
      <div class="contentsWrap">
        <div class="desc">
          {{ feed.contents.replaceAll('"', "") }}
        </div>
        <!-- <div class="wrap">
          <div class="url">
            <a href="https://brunch.co.kr/@@63JW/25"
              >https://brunch.co.kr/@@63JW/25</a
            >
          </div>
          <p class="date">2020.06.18</p>
        </div> -->
        <div class="likeBtn" @click="likeBtn"></div>
        <div class="showComment" @click="mvComment">댓글보기</div>
      </div>
    </div>
    <!-- <div class="btn-group wrap">
      <div class="likeBtn"></div>
    </div> -->
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
