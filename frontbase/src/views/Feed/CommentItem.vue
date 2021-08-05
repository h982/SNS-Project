<template>
  <div class="comment">
    <div class="profile"></div>
    <div>
      <div class="content">
        <div>{{ comment.member.name }}</div>
        <div>{{ comment.contents }}</div>
      </div>
      <button @click="writeRe(comment.commentId)" class="showWriteBtn">
        답글달기
      </button>
      <button @click="showRe(comment.commentId)" class="showReBtn">
        대댓글보기
      </button>
      <div class="reCom">
        <inner-item
          v-for="(inner, idx) in comment.coComments"
          :key="idx"
          :inner="inner"
        ></inner-item>
      </div>
      <div class="writeCom">
        <input type="text" v-model="inputCom" class="inputSpace" />
        <div @click="writeComment">작성</div>
      </div>
    </div>
  </div>
</template>

<script>
import InnerItem from "@/views/Feed/InnerItem.vue";
import { mapGetters } from "vuex";
import { createInstance } from "@/api/index.js";

export default {
  props: ["comment"],
  data: () => {
    return {
      parentId: "",
      inputCom: ""
    };
  },
  components: {
    InnerItem
  },
  computed: {
    ...mapGetters(["comments", "memberInfo", "feedid"])
  },
  methods: {
    writeRe(data) {
      this.parentId = data;
      console.log(this.parentId);
      //  document.querySelector(".inputSpace").value = "";

      var temp1 = document.querySelectorAll(".writeCom");
      temp1[this.parentId - 1].classList.add("writeCom" + this.parentId);
      var temp2 = document.querySelectorAll(".showWriteBtn");
      temp2[this.parentId - 1].classList.add("showWriteBtn" + this.parentId);

      var comm = document.querySelector(".writeCom" + this.parentId);
      if (comm.style.display == "none") {
        comm.style.display = "block";
        document.querySelector(".showWriteBtn" + this.parentId).innerHTML =
          "닫기";
      } else {
        comm.style.display = "none";
        document.querySelector(".showWriteBtn" + this.parentId).innerHTML =
          "답글달기";
        // document
        //   .querySelector(".writeCom" + this.parentId)
        //   .classList.remove(".writeCom" + this.parentId);
        // document
        //   .querySelector(".showWriteBtn" + this.parentId)
        //   .classList.remove("showWriteBtn" + this.parentId);
      }
    },
    writeComment() {
      const instance = createInstance();
      const body = {
        parentId: this.parentId,
        feedId: this.feedid,
        memberId: this.memberInfo.memberId,
        contents: this.inputCom,
        className: 1,
        order: 1
      };
      console.log(JSON.stringify(body));
      instance
        .post("/comment/", JSON.stringify(body))
        .then(response => {
          if (response.data.data === "success") {
            alert("대댓글 등록 완료");
            document.querySelector(".writeCom" + this.parentId).style.display =
              "none";
            document.querySelector(".showWriteBtn" + this.parentId).innerHTML =
              "답글달기";
            this.$store.dispatch("GET_COMMENTS", this.feedid);
          } else {
            alert("대댓글 등록 실패");
          }
        })
        .catch();
    },
    showRe(data) {
      this.parentId = data;
      var temp1 = document.querySelectorAll(".reCom");
      temp1[this.parentId - 1].classList.add("reCom" + this.parentId);
      var temp2 = document.querySelectorAll(".showReBtn");
      temp2[this.parentId - 1].classList.add("showReBtn" + this.parentId);

      var comm = document.querySelector(".reCom" + this.parentId);
      if (comm.style.display == "none") {
        comm.style.display = "block";
        document.querySelector(".showReBtn" + this.parentId).innerHTML = "닫기";
      } else {
        comm.style.display = "none";
        document.querySelector(".showReBtn" + this.parentId).innerHTML =
          "대댓글보기";
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
.inputSpace {
  border: 1px solid black;
}
</style>
