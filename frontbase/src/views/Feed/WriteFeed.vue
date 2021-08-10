<template>
  <div class="wrap">
    <div class="challenge_wrap">
      <label for="challenge" class="feedType">▼일상글|챌린지▼</label>
      <select id="challenge" v-model="challenge" class="dailyFeed">
        <option>일상글</option>
        <option
          v-for="(challenge, idx) in feed_challenging"
          :key="idx"
          :value="challenge"
        >
          {{ challenge.text.teamChallenge.title }}
        </option>
      </select>
    </div>
    <div class="img_wrap">
      <label for="chooseFile" class="fileBtn">
        👉 UPLOAD IMAGE 👈
      </label>
      <input
        type="file"
        id="chooseFile"
        name="chooseFile"
        accept="image/*"
        @change="loadf"
      />
      <img src="" class="preview" />
    </div>
    <div class="contents_wrap">
      <textarea v-model="contents" class="contents"></textarea>
    </div>
    <v-btn v-if="this.type === 'update'" @click="modify">수정</v-btn>
    <v-btn v-else @click="write">등록</v-btn>
    <!-- <v-btn @click="check">확인</v-btn> -->
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import { createInstance } from "@/api/teamindex.js";
import "../../components/css/feed/writeFeed.scss";

export default {
  props: {
    type: { type: String }
  },
  data: () => {
    return {
      contents: "",
      challenge: Number(),
      file: null
    };
  },
  computed: {
    ...mapGetters([
      "memberInfo",
      "myTeamList",
      "team_challenges",
      "feed_challenging",
      "oneFeed",
      "feedid"
    ])
  },
  created() {
    this.$store.dispatch("GET_MY_TEAM_INFO", this.memberInfo.memberId);

    if (this.type === "update") {
      this.contents = this.oneFeed.contents;
    }
  },

  methods: {
    write() {
      var daily = document.querySelector(".dailyFeed");
      const formData = new FormData();

      if (daily.options[daily.selectedIndex].value == "일상글") {
        formData.append("teamchallengeId", 0);
      } else {
        formData.append(
          "teamchallengeId",
          this.challenge.text.teamChallenge.teamChallengeId
        );
      }

      formData.append("memberId", this.memberInfo.memberId);
      formData.append("teamId", this.myTeamList[0].text.teamId);
      formData.append("teamName", this.myTeamList[0].text.name);
      formData.append("contents", this.contents);
      formData.append("writer", this.memberInfo.name);
      formData.append("image", document.getElementById("chooseFile").files[0]);

      const instance = createInstance();
      instance
        .post("/feed", formData, {
          Headers: {
            "Content-Type": "multipart/form-data"
          }
        })
        .then(response => {
          if (response.data.data === "success") {
            this.$store.dispatch(
              "GET_TEAMCHALLENGEING_INFO",
              this.memberInfo.memberId
            );
            alert("피드 작성 완료");
            this.$router.push("/feed");
          } else {
            alert("피드 작성 실패");
          }
        })
        .catch(() => {
          alert("에러발생!.");
        });
    },
    modify() {
      var daily = document.querySelector(".dailyFeed");
      const formData = new FormData();

      if (daily.options[daily.selectedIndex].value == "일상글") {
        formData.append("teamchallengeId", 0);
      } else {
        formData.append(
          "teamchallengeId",
          this.challenge.text.teamChallenge.teamChallengeId
        );
      }
      formData.append("feedId", this.feedid);
      formData.append("memberId", this.oneFeed.memberId);
      formData.append("teamId", this.oneFeed.teamId);
      formData.append("teamName", this.oneFeed.teamName);
      formData.append("contents", this.contents);
      formData.append("writer", this.oneFeed.writer);
      formData.append("image", document.getElementById("chooseFile").files[0]);

      const instance = createInstance();
      instance
        .put("/feed", formData, {
          Headers: {
            "Content-Type": "multipart/form-data"
          }
        })
        .then(response => {
          if (response.data.data === "success") {
            this.$store.dispatch(
              "GET_TEAMCHALLENGEING_INFO",
              this.memberInfo.memberId
            );
            alert("피드 수정 완료");
            this.$router.push("/feed");
          } else {
            alert("피드 수정 실패");
            // this.$router.push("/feed");
          }
        })
        .catch(() => {
          alert("에러발생!.");
          // this.$store.dispatch(
          //   "GET_TEAMCHALLENGEING_INFO",
          //   this.memberInfo.memberId
          // );
          // this.$router.push("/feed");
        });
    },
    loadf() {
      var file = document.getElementById("chooseFile");

      let preview = document.querySelector(".preview");
      preview.src = URL.createObjectURL(file.files[0]);

      console.log(file.files[0]);

      preview.style.width = "60%";
      preview.style.height = "60%";
      preview.style.maxHeight = "500px";
    }
    // check() {
    //   console.log(this.feed_challenging);
    // }
  }
};
</script>
