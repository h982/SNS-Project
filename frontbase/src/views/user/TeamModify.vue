<template>
  <v-container grid-list-xl>
    <v-layout row justify-center align-center wrap class="mt-4 pt-2">
      <v-flex xs12 sm12 md6 lg6 xl6>
        <h2 class="pb-4 mb-4">
          <span>팀정보</span>
          <span class="green--text">변경</span>
        </h2>

        <form method="POST">
          <v-text-field
            name="team.name"
            color="green"
            background-color="transparent"
            v-model="team.name"
            label="새로운 팀이름"
          ></v-text-field>

          <v-btn
            @click="duplicateName"
            type="button"
            color="blue"
            class="white--text duplicate"
          >
            중복검사
          </v-btn>
          
          <v-text-field
            name="team.introduction"
            color="green"
            background-color="transparent"
            v-model="team.introduction"
            label="새로운 팀소개"
          ></v-text-field>

          <div class="img_wrap">
            <label for="chooseFile" class="fileBtn">
              팀 썸네일
            </label>
            <input
              type="file"
              id="chooseFile"
              name="chooseFile"
              accept="image/*"
              @change="loadf"
            />
            <img v-bind:src="thumbnail" class="preview" />
          </div>
          
          <br>
          <div class="buttons">
            <v-btn
              @click="modify"
              type="button"
              color="green"
              class="white--text"
              >수정하기</v-btn
            >
            <v-btn @click="clear">clear</v-btn>
          </div>
        </form>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { createInstance } from "@/api/teamindex.js";
import { mapGetters } from "vuex";

export default {
  computed:{
    ...mapGetters(["managingTeam"]),
  },
  data() {
    return {
      thumbnail: "",
      team: {
        teamId: "",
        name: "",
        introduction: "",
        leader: "",
        member: {
          memberId: Number()
        },
        sportDto: {
          sportId: Number()
        }
      },
    };
  },
  created(){
    this.thumbnail = this.managingTeam.photo.filePath.replaceAll("\"", "");
    this.team.name = this.managingTeam.name.replaceAll("\"", "");
    this.team.introduction = this.managingTeam.introduction.replaceAll("\"", "");
    this.team.leader = this.managingTeam.leader.replaceAll("\"", "");
    this.team.member.memberId = this.managingTeam.member.memberId;
    this.team.sportDto.sportId = this.managingTeam.sport.sportId;
    this.team.teamId = this.managingTeam.teamId;
    console.log(this.managingTeam);
  },
  methods: {
    loadf() {
      var file = document.getElementById("chooseFile");

      let preview = document.querySelector(".preview");
      preview.src = URL.createObjectURL(file.files[0]);

      preview.style.width = "60%";
      preview.style.height = "60%";
      preview.style.maxHeight = "500px";
    },
    modify() {
      const formData = new FormData();
      formData.append("teamId", JSON.stringify(this.team.teamId));
      formData.append("name", JSON.stringify(this.team.name));
      formData.append("introduction", JSON.stringify(this.team.introduction));
      formData.append("leader", JSON.stringify(this.team.leader));
      formData.append("memberId", JSON.stringify(this.team.member.memberId));
      formData.append("sportId", JSON.stringify(this.team.sportDto.sportId));
      if(document.getElementById("chooseFile").files[0] !=null){
        formData.append("multipartFile", document.getElementById("chooseFile").files[0]);
      }
      
      for (var key of formData.keys()) {
      console.log(key);
      }

      for (var value of formData.values()) {
      console.log(value);
      }

      const instance = createInstance();
      instance
        .put("/team", formData, {
          Headers: {
            "Content-Type": "multipart/form-data"
          }
        })
        .then(response => {
          if (response.data.message === "success") {
            alert("정보 변경 완료");
            console.log(response);
            // this.$router.push("/teamlist");
          } else {
            alert("정보 변경 실패");
            console.log(response);
          }
        })
        .catch(() => {
          alert("에러발생!");
        });
    },
    clear() {
      this.team.name = null;
      this.team.introduction = null;
    },
    duplicateName() {
      const instance = createInstance();
      instance.get('/team/"' + this.team.name + '"/exists').then(({ data }) => {
        if (data) {
          alert("이미 사용된 팀명입니다!");
        } else {
          alert("사용가능한 팀명입니다!");
          // console.log(this.memberInfo)
        }
      });
    },
  }
};
</script>

<style scoped>
.duplicate {
  position: relative;
  margin-left: 85%;
}
.buttons {
  position: relative;
  margin-left: 33%;
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