<template>
  <div class="wrap">
    <div class="challenge_wrap">
      <label for="challenge" class="feedType">▼일상글|챌린지▼</label>
      <select id="challenge" v-model="challenge" class="dailyFeed">
        <option>일상글</option>
        <option
          v-for="(challenge, idx) in team_challenges"
          :key="idx"
          :value="challenge"
        >
          {{ challenge.text.title }}
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
    <v-btn @click="write">등록</v-btn>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import { createInstance } from "@/api/teamindex.js";
import "../../components/css/feed/writeFeed.scss";

export default {
  data: () => {
    return {
      contents: "",
      challenge: Number(),
      file: null
    };
  },
  computed: {
    ...mapGetters(["memberInfo", "myTeamList", "team_challenges"])
  },
  created() {
    this.$store.dispatch("GET_TEAMCHALLENGE_INFO", this.memberInfo.memberId);
    this.$store.dispatch("GET_MY_TEAM_INFO", this.memberInfo.memberId);
  },
  methods: {
    write() {
      var daily = document.querySelector(".dailyFeed");
      const formData = new FormData();

      if (daily.options[daily.selectedIndex].value == "일상글") {
        formData.append("teamchallengeId", 0);
      } else {
        formData.append("teamchallengeId", this.challenge.text.teamChallengeId);
      }
      formData.append("memberId", this.memberInfo.memberId);
      formData.append("teamId", this.myTeamList[0].text.teamId);
      formData.append("teamName", JSON.stringify(this.myTeamList[0].text.name));
      formData.append("contents", JSON.stringify(this.contents));
      formData.append("writer", JSON.stringify(this.memberInfo.name));
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
    loadf() {
      // console.log("되는가?");
      var file = document.getElementById("chooseFile");

      let preview = document.querySelector(".preview");
      preview.src = URL.createObjectURL(file.files[0]);

      console.log(file.files[0]);

      preview.style.width = "60%";
      preview.style.height = "60%";
      preview.style.maxHeight = "500px";
    }
  }
};
</script>
