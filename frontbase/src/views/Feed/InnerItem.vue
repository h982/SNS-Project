<template>
  <div class="wrap">
    <div>{{ inner.member.name }}</div>
    <div class="contents">{{ inner.contents }}</div>
  </div>
</template>

<script>
export default {
  props: ["inner"],
  data: () => {
    return {};
  }
};
</script>

<style scoped>
.wrap {
  display: flex;
}
.contents {
  margin-left: 10px;
  font-weight: 700;
}
</style>
