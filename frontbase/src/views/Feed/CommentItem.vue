<template>
  <div class="comment">
    <div class="profile"></div>
    <div>
      <div class="content">
        <div>멤버이름</div>
        <div>댓글내용</div>
      </div>
      <v-btn>답글달기</v-btn>
    </div>
  </div>
</template>

<script>
export default {
  props: ["comment"],
  methods: {}
};
</script>
<style scoped>
.comment {
  display: flex;
  border: 1px solid black;
  padding: 1em;
}
.profile {
  border: 1px solid black;
  border-radius: 50%;
  width: 5.5em;
  height: auto;
  margin-right: 1em;
}
.content {
  width: 100%;
}
</style>
