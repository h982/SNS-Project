<template>
  <div class="wrap">
    <div class="comments">
      <comment-item
        v-for="(comment, idx) in comments"
        :key="idx"
        :comment="comment"
      ></comment-item>
    </div>
    <div class="writeC">
      <label for="writeC">댓글작성</label>
      <input type="text" id="writeC" v-model="inputC" @keyup.enter="writeComment()"/>
      <v-btn @click="writeComment">작성</v-btn>
    </div>
  </div>
</template>
<script>
import "../../components/css/feed/comment.scss";
import { createInstance } from "@/api/teamindex.js";
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
  computed: {
    ...mapGetters(["comments", "memberInfo", "feedid"])
  },
  methods: {
    writeComment() {
      const instance = createInstance();
      const body = {
        parentId: 0,
        feedId: this.feedid,
        memberId: this.memberInfo.memberId,
        contents: this.inputC,
        className: 1,
        order: 1
      };
      console.log(JSON.stringify(body));
      instance
        .post("/comment/", JSON.stringify(body))
        .then(response => {
          if (response.data.data === "success") {
            this.inputC = "";
            //alert("댓글 등록 완료");
            this.$store.dispatch("GET_COMMENTS", this.feedid);
          } else {
            //alert("댓글 등록 실패");
          }
        })
        .catch();
    }
  }
};
</script>
