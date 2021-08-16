<template>
  <div class="mini_wrap">
    <div class="mini_profile">
      <img
          v-bind:src="inner.member.photo.filePath"
          style="border-radius: 50%;"
          height="24"
      />
      
    </div>
    <div>{{ inner.member.name }}</div>
    <div class="mini_contents">{{ inner.contents }}</div>
  </div>
</template>

<script>
export default {
  props: ["inner"],
  data: () => {
    return {};
  },
  created(){
    console.log(this.inner);
  }
};
</script>
