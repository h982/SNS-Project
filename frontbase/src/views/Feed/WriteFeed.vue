<template>
  <div>
    <div>
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
    <div>
      사진올리기
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
import http from "@/util/http-common";

export default {
  data: () => {
    return {
      contents: "",
      //challenges: ["aaaaaa", "bbbbbb", "cccccc"],
      challenges: [],
      challenge: ""
    };
  },
  methods: {
    write() {
      console.log(this.challenge);
      console.log("content: " + this.contents);
      // http
      // .post('', {
      //   challenge: this.challengel,
      //   content: this.content,
      // })
      // .then(({data})=> {
      //   let msg = "피드 등록에 문제가 생겼습니다";
      //   if(data === 'SUCCESS'){
      //     msg = "피드 등록이 완료되었습니다."
      //   }
      //   alert(msg);
      // })
      // .catch(()=> {
      //   alert("글 작성시 에러가 발생했습니다.");
      // })
    },
    loadf() {
      // console.log("되는가?");
      var file = document.getElementById("chooseFile");

      let preview = document.querySelector(".preview");
      preview.src = URL.createObjectURL(file.files[0]);

      console.log(file.files[0]);

      preview.style.width = "60%";
      preview.style.height = "60%";
    }
  },
  created() {
    // http
    //   .get("challenge/list")
    //   .then(({ data }) => {
    //     this.challenges = data;
    //   })
    //   .catch(() => {
    //     alert("챌린지 받아오기 실패");
    //   });
  }
};
</script>
<style scoped>
textarea,
select {
  border: 1px solid black;
}
</style>
