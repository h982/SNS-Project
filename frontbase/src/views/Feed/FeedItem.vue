<template>
  <div class="feed">
    <!-- 피드 상단 -->
    <div class="feed_t">
      <div>작성자</div>
      <!-- <div>{{feed.writer}}</div> -->
      <div>일시</div>
      <!-- <div>{{feed.write_date}}</div> -->
    </div>
    <!-- 피드 중단 -->
    <div class="feed_m">
      사진
    </div>
    <!-- 피드 하단 -->
    <div class="feed_b">
      <div>
        <v-btn>좋아요</v-btn>
      </div>
      <div>
        내용
      </div>
      <!-- <div>{{feed.contents}}</div> -->
      <v-btn @click="mvComment">
        댓글 모두 보기
      </v-btn>
    </div>
  </div>
</template>

<script>
export default {
  props: ["feed"],
  methods: {
    mvComment() {
      this.$router.push("/comment");
    }
  }
};
</script>

<style scoped>
.feed {
  border: 1px solid black;
  width: 70%;
  margin: 0 auto;
}
.feed_t {
  display: flex;
  justify-content: space-between;
}
.feed_m {
  width: 100%;
  background: lavender;
  height: 35vh;
}
.feed_b div:first-child {
  display: flex;
  justify-content: flex-end;
}
.feed_b div:nth-child(2) {
  margin: 0.5em;
  border: 1px solid black;
  height: 5vh;
}
</style>
