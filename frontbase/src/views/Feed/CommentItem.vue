<template>
  <div class="comment">
    <div class="profile"></div>
    <div>
      <div class="content">
        <div>멤버이름</div>
        <div>댓글내용</div>
      </div>
      <button>답글달기</button>
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
  width: 90%;
  min-width: 300px;
  max-width: 580px;
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
</style>
