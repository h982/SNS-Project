<template>
  <div>
    <h2>팀 가입 신청</h2>
    <table>
        <tr v-for="request in waitingReqests" v-bind:key="request.requestId">
            <td>{{request.member.name}}</td>
            <td><button v-on:click="acceptRequest(request.requestId, request.member.memberId)">수락</button></td>
            <td><button v-on:click="rejectRequest(request.requestId)">거절</button></td>
        </tr>
    </table>
  </div>
</template>

<script>
import { mapState} from "vuex";
import http from "@/util/http-common";

export default {
  name: "JoinRequest",
  computed: {
    waitingReqests: function(){
        return this.joinRequests.filter(i => i.status.includes("WAITING"));
    },
    ...mapState(["managingTeam", "joinRequests", "memberInfo"]),
  },
  mounted() {
    this.$nextTick(function () {
        // this.$store.dispatch('getRequests', this.managingTeam.teamId);
    })
    
  },
  methods: {
      rejectRequest: function(requestId){
        http
            .put("/request/reject/" + requestId)
            .then(() => this.$store.dispatch('getRequests', this.managingTeam.teamId));
      },
      acceptRequest: function(requestId, memberId){
        http
            .put("/request/accept/" + requestId)
            .then(() => this.$store.dispatch('getRequests', this.managingTeam.teamId));
        let joinTeam = {
            "member": {
                "memberId": this.memberInfo.memberId
            },
            "team": {
                "teamId": this.managingTeam.teamId
            }
        };
        console.log(joinTeam);
        http
            .post("/jointeam", {
                "member": {
                    "memberId": memberId
                },
                "team": {
                    "teamId": this.managingTeam.teamId
                }
            });
      },
  },
};
</script>

<style></style>
