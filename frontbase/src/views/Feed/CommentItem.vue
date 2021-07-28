<template>
  <div class="comment">
    <div class="profile"></div>
    <div>
      <div class="content">
        <div>멤버이름</div>
        <div class="contents">댓글내용</div>
        <!-- <div>{{comment.contents}}</div> -->
      </div>
      <button @click="writeRe" class="showWriteBtn">답글달기</button>
      <button @click="showRe" class="showReBtn">대댓글보기</button>
      <div class="reCom">
        <inner-item></inner-item>
        <!-- <inner-item v-for="(inner, idx) in inners" :key="idx" :inner="inner"> -->
      </div>
      <div class="writeCom">
        <input type="text" v-model="inputCom" />
        <div @click="writeComment">작성</div>
      </div>
    </div>
  </div>
</template>

<script>
import InnerItem from "@/views/Feed/InnerItem.vue";
import http from "@/util/http-common";

export default {
  props: ["comment"],
  data: () => {
    return {
      inputCom: ""
    };
  },
  components: {
    InnerItem
  },
  methods: {
    writeRe() {
      var comm = document.querySelector(".writeCom");
      //console.log(comm.style.display);
      if (comm.style.display == "none") {
        comm.style.display = "block";
        document.querySelector(".showWriteBtn").innerHTML = "닫기";
      } else {
        comm.style.display = "none";
        document.querySelector(".showWriteBtn").innerHTML = "답글달기";
      }
    },
    writeComment() {
      // alert(this.inputCom);
      //  http
      //   .post("",{
      //     inputCom : this.inputCom
      //   })
      //   .then (({data}) => {
      //     console.log(this.data);
      //   })
      //   .catch(({data})=> {
      //     alert("실패");
      //   })
    },
    showRe() {
      var comm = document.querySelector(".reCom");
      if (comm.style.display == "none") {
        comm.style.display = "block";
        document.querySelector(".showReBtn").innerHTML = "닫기";
      } else {
        comm.style.display = "none";
        document.querySelector(".showReBtn").innerHTML = "대댓글보기";
      }
    }
  }
};
</script>
<style scoped>
.comment {
  display: flex;
  width: calc(100% - 50px);
  margin: 0 auto;
  background: #ffffff;
  padding: 0.5em 1em;
}
.profile {
  border: 1px solid black;
  border-radius: 50%;
  width: 2.5em;
  height: 2.5em;
  margin-right: 1em;
}
.content {
  width: 100%;
}
button {
  margin: 0.5em;
  padding: 0.2em;
  border: 1px solid black;
}
.writeCom {
  display: none;
  border: 1px solid black;
}
.writeCom input,
.writeCom div {
  display: inline-block;
}
.writeCom div:hover {
  cursor: pointer;
}
.reCom {
  display: none;
}
.contents {
  font-weight: 700;
}
</style>
