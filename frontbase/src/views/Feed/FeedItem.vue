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
      <div>작성자</div>
      <!-- <div>{{feed.writer}}</div> -->
      <div>일시</div>
      <!-- <div>{{feed.write_date}}</div> -->
    </div>
    <div class="feed-card">
      <div
        class="img"
        :style="{ 'background-image': 'url(' + defaultImage + ')' }"
      ></div>
      <div class="contentsWrap">
        <div class="desc">
          사용자경험(UX)을 이해하는 팀원이 되기 위하여 - 사용자에게 '기본적인'
          UX를 선사하기 위해 우리 모두 알아야할 사실들
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
