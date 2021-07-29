<template>
  <div>
    <h1>{{memberInfo.name}}</h1>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "mypage",
  components: {
    
  },
  computed: {
    ...mapState(["memberInfo"])
  }
};
</script>

<style></style>
