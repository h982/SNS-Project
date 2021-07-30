<template>
  <div>
    <h1>내 이메일 : {{memberInfo.email}}</h1>
    <team-management></team-management>
  </div>
</template>

<script>
import { mapState} from "vuex";
import TeamManagement from "./TeamManagement"

export default {
  name: "mypage",
  components: {
    TeamManagement
  },
  computed: {
    ...mapState(["memberInfo"])
  },
  mounted() {
    this.$store.dispatch('GET_MY_TEAM_INFO', this.memberInfo.memberId);
  },
};
</script>

<style></style>
