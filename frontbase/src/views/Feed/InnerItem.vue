<template>
  <div class="mini_wrap">
    <div class="mini_profile"></div>
    <div>{{ inner.member.name }}</div>
    <div class="mini_contents">{{ inner.contents }}</div>
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
