<template>
  <div>
    <h1>내 팀 관리</h1>
    <h1>내 팀 이름: {{managingTeam.name}}</h1>
    <join-request></join-request>
  </div>
</template>

<script>
import { mapState} from "vuex";
import JoinRequest from "./JoinRequest"

export default {
  name: "team-management",
  components: {
      JoinRequest,
  },
  computed:{
    ...mapState(["managingTeam"]),
  },
};
</script>

<style></style>
