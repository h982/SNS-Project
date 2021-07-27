<template>
  <div class="wrap">
    <h3>댓글창</h3>
    <div class="comments">
      <!-- <comment-item v-for="(comment, idx) in comments" :key="idx" :comment="comment"> -->
      <!-- <comment-item></comment-item>
      <comment-item></comment-item> -->
      <comment-item></comment-item>
    </div>
    <div class="writeC">
      <label for="writeC">댓글작성</label>
      <input type="text" id="writeC" v-model="inputC" />
      <v-btn @click="writeComment">작성</v-btn>
    </div>
  </div>
</template>
<script>
import http from "@/util/http-common";
import CommentItem from "@/views/Feed/CommentItem.vue";
import { mapGetters } from "vuex";

export default {
  data: () => {
    return {
      inputC: "",
      comment: ""
    };
  },
  components: {
    CommentItem
  },
  // computed: {
  //   ...mapGetters(["comments"])
  // },
  // created() {
  //   this.$store.dispatch("getComments");
  // },
  methods: {
    writeComment() {
      document.querySelector("#writeC").value = "";
      // var com = this.inputC;
      // document.querySelector("#writeC").value = "";
      // var comm = document.createElement("div");
      // comm.innerHTML = com;
      // document.querySelector(".writeC").appendChild(comm);
      // http
      //   .put("", {
      //     comment: this.comment
      //   })
      //   .then(({ data }) => {
      //     console.log(data);
      //     let msg = "작성 실패";
      //     if (data == "SUCCESS") {
      //       msg = "작성 성공";
      //     }
      //     alert(msg);
      //   })
      //   .catch(() => {
      //     alert(msg);
      //   });
    }
  }
};
</script>

<style scoped>
h3 {
  text-align: center;
}
.comments {
  margin: 0 auto;
  background: lavender;
  padding: 15px;
}
.writeC {
  width: 80%;
  margin: 2em auto 0;
}
.writeC input {
  border: 1px solid black;
}
</style>
