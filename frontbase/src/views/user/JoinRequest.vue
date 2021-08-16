<template>
    <div>
      <v-layout justify-start align-center data-aos="fade-up">
        <v-toolbar-title class="headline">
            <span><b>팀</b></span>
            <span class="green--text"><b>&nbsp;가입신청</b></span>
        </v-toolbar-title>
        <br><br><br><br>
      </v-layout>
        <table v-if="waitingReqests.length !== 0">

            <tr v-for="request in waitingReqests" v-bind:key="request.requestId">
                <td @click ="sendMemberId(request.member)" style="display:inline;cursor:pointer;"><b>{{request.member.name}}</b></td> &nbsp;
                <b-button variant="primary" v-on:click="acceptRequest(request.requestId, request.member.memberId)">수락</b-button>
                <b-button variant="danger" v-on:click="rejectRequest(request.requestId)">거절</b-button>
            </tr>
        </table>

    <table v-else>
      <tr><b>
        가입 요청이 존재하지 않습니다.
      </b></tr>
    </table>
  </div>
</template>

<script>
import { mapState } from "vuex";
// import http from "@/util/http-common";
import { createInstance } from "@/api/teamindex.js";

export default {
  name: "JoinRequest",
  computed: {
    waitingReqests: function() {
      return this.joinRequests.filter(i => i.status.includes("WAITING"));
    },
    ...mapState(["managingTeam", "joinRequests", "memberInfo"])
  },
  mounted() {
    this.$nextTick(function() {
      // this.$store.dispatch('getRequests', this.managingTeam.teamId);
    });
  },
  methods: {
    rejectRequest: function(requestId) {
      const instance = createInstance();
      instance
        .put("/request/reject/" + requestId)
        .then(() =>
          this.$store.dispatch("getRequests", this.managingTeam.teamId)
        );
    },
    acceptRequest: function(requestId, memberId) {
      const instance = createInstance();
      instance
        .put("/request/accept/" + requestId)
        .then(() =>
          this.$store.dispatch("getRequests", this.managingTeam.teamId)
        );
        this.$store.dispatch("getTeamMembers", this.managingTeam.teamId)
        alert("요청이 승인되었습니다. 잠시 뒤 반영됩니다.");
      let joinTeam = {
        member: {
          memberId: this.memberInfo.memberId
        },
        team: {
          teamId: this.managingTeam.teamId
        }
      };
    },
    sendMemberId(payload){
      console.log(payload);
      this.$store.dispatch("getMemberByEmail", payload.email);
      this.$store.dispatch("getYourFeeds", payload.memberId);
      this.$router.push("/memberdetail");
    }
  }
};
</script>

<style></style>
