<template>
  <div class="comment">
    <div class="profile"></div>
    <div>
      <div class="content">
        <div>{{ comment.member.name }}</div>
        <div>{{ comment.contents }}</div>
      </div>
      <div @click="makeCom">
        답글달기
        <div v-if="this.parentId == null"></div>
        <div v-else class="writeCom">
          <input type="text" v-model="inputCom" class="inputSpace" />
          <div @click="writeComment">작성</div>
        </div>
      </div>
      <div class="reCom">
        <inner-item
          v-for="(inner, idx) in comment.coComments"
          :key="idx"
          :inner="inner"
        ></inner-item>
      </div>
    </div>
  </div>
</template>

<script>
import InnerItem from "@/views/Feed/InnerItem.vue";
import { mapGetters } from "vuex";
// import { createInstance } from "@/api/index.js";
import { createInstance } from "@/api/teamindex.js";

export default {
  props: ["comment"],
  data: () => {
    return {
      parentId: null,
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
    makeCom() {
      this.parentId = this.comment.commentId;
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
            this.parentId = null;
            this.inputCom = "";
            alert("대댓글 등록 완료");
            this.$store.dispatch("GET_COMMENTS", this.feedid);
          } else {
            alert("대댓글 등록 실패");
          }
        })
        .catch();
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
.writeCom input,
.writeCom div {
  display: inline-block;
}
.writeCom div:hover {
  cursor: pointer;
}

.contents {
  font-weight: 700;
}
.inputSpace {
  border: 1px solid black;
}
</style>
