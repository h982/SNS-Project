<template>
  <div class="wrap">
    <div class="challenge_wrap">
      <label for="challenge">챌린지 선택</label>
      <select id="challenge" v-model="challenge">
        <option
          v-for="(challenge, idx) in challenges"
          :key="idx"
          :value="challenge"
        >
          {{ challenge }}
        </option>
      </select>
    </div>
    <div class="img_wrap">
      <label for="chooseFile" class="fileBtn">
        사진올리기
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
    <div>
      <textarea v-model="contents"></textarea>
    </div>
    <v-btn @click="write">등록</v-btn>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import { createInstance } from "@/api/teamindex.js";

export default {
  data: () => {
    return {
      contents: "",
      challenges: [],
      challenge: Number(),
      file: null
    };
  },
  computed: {
    // ...mapGetters(["team_challenges"]),
    ...mapGetters(["memberInfo", "teamInfo"])
  },
  // created() {
  //   this.$store.dispatch("GET_MY_TEAM_INFO");
  // },
  methods: {
    write() {
      this.challenge = -1;

      const formData = new FormData();
      // formData.append("teamchallenge_id", JSON.stringify(this.challenge));
      // formData.append("jointeam_id", JSON.stringify(this.teamInfo.teamId));
      // formData.append("team_name", JSON.stringify(this.teamInfo.name));
      // formData.append("contents", JSON.stringify(this.contents));
      // formData.append("writer", JSON.stringify(this.memberInfo.name));
      // formData.append("file", document.getElementById("chooseFile").files[0]);
      formData.append("teamChallengeId", JSON.stringify(this.challenge));
      formData.append("joinTeamId", JSON.stringify(1));
      formData.append("teamName", JSON.stringify("test"));
      formData.append("contents", JSON.stringify(this.contents));
      formData.append("writer", JSON.stringify("test"));
      formData.append("images", document.getElementById("chooseFile").files[0]);
      console.log(formData.teamChallengeId);
      // this.file = document.getElementById("chooseFile").files[0];
      // console.log(this.file);
      const instance = createInstance();
      instance
        .post("/feed", formData, {
          Headers: {
            "Content-Type": "multiart/form-data"
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
  // created() {
  //   instance
  //     .get("challenge/list")
  //     .then(({ data }) => {
  //       this.challenges = data;
  //     })
  //     .catch(() => {
  //       alert("챌린지 받아오기 실패");
  //     });
  // }
};
</script>
<style scoped>
.wrap {
  border: 1px solid black;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 1em;
}
textarea,
select {
  border: 1px solid black;
}
.img_wrap {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.preview {
  display: block;
  margin: 20px 0;
}
.challenge_wrap {
  margin: 15px 0;
}
.challenge_wrap label {
  margin-right: 10px;
}
#chooseFile {
  display: none;
}
.fileBtn {
  background: lavender;
  border-radius: 15px;
  padding: 0.5em 1em;
}
.fileBtn:hover {
  cursor: pointer;
}
</style>
