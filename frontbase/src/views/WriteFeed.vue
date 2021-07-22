<template>
  <div>
    <div>
      사진올리기
      <input
        type="file"
        id="chooseFile"
        name="chooseFile"
        accept="image/*"
        @change="loadf"
      />
      <!-- <v-file-input
        label="File input"
        v-model="image"
        accept="image/*"
        @change="selectFile"
      ></v-file-input> -->
      <img src="" class="preview" />
    </div>
    <div>
      <textarea v-model="contents"></textarea>
    </div>
    <v-btn @click="write">등록</v-btn>
  </div>
</template>

<script>
export default {
  methods: {
    write() {
      console.log(this.contents);
    },
    loadf() {
      // console.log("되는가?");
      var file = document.getElementById("chooseFile"); //파일 선택 필드 요소 얻기

      let preview = document.querySelector(".preview");
      preview.src = URL.createObjectURL(file.files[0]);

      console.log(file.files[0]);

      preview.style.width = "60%";
      preview.style.height = "60%";
    }
    // selectFile(file) {
    //   console.log("test");
    //   this.file = file;
    // }
  },
  data: () => {
    return {
      contents: ""
    };
  }
};
</script>
<style scoped>
textarea {
  border: 1px solid black;
}
</style>
