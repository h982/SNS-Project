<template>
  <div>
    <h3>댓글창</h3>
    <div class="wrap">
      <comment-item></comment-item>
    </div>
    <div class="writeC">
      <label for="writeComment">댓글작성</label>
      <input type="text" id="writeComment" />
      <v-btn>작성</v-btn>
    </div>
  </div>
</template>
<script>
import CommentItem from "@/views/Feed/CommentItem.vue";
export default {
  components: {
    CommentItem
  }
};
</script>

<style scoped>
h3 {
  text-align: center;
}
.wrap {
  width: 80%;
  margin: 0 auto;
}
.writeC {
  width: 80%;
  margin: 2em auto 0;
}
.writeC input {
  border: 1px solid black;
}
</style>
