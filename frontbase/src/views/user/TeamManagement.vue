<template>
  <div>
    <h1>내 팀 관리</h1>
    <br>
    <v-row>
      <join-request />
    </v-row>
    <br>
    <h3>팀원 조회</h3>
    <div>
      <template>
        <v-card>
          <v-card-title>
            <v-text-field
              v-model="search"
              append-icon="search"
              label="Search"
              single-line
              hide-details
            ></v-text-field>
          </v-card-title>
          <v-data-table
            v-model="selected"
            :headers="headers"
            :items="this.managingTeamMembers"
            item-key="member.memberId"
            select-all
            hide-actions
            :pagination.sync="pagination"
            :search="search"
            class="elevation-1"
          >
            <template v-slot:items="props">
              <td>
                <v-checkbox
                  v-model="props.selected"
                  :input-value = "props.selected"
                  primary
                  hide-details
                ></v-checkbox>
              </td>
              <td>{{ props.item.member.memberId }}</td>
              <td class="text-xs-right">{{ props.item.member.name }}</td>
              <td class="text-xs-right">
                <v-chip
                  :color="getColor(props.item.member.point)"
                  dark
                >
                  {{ props.item.member.point }}
                </v-chip>
              </td>
              <td class="text-xs-right">{{ props.item.member.email }}</td>
              <td class="text-xs-right">{{ props.item.member.phone }}</td>
              <td class="text-xs-right">{{ props.item.member.mbti }}</td>
            </template>
          </v-data-table>
          <div class="text-xs-center pt-2">
            <v-pagination v-model="pagination.page" :length="pages"></v-pagination>
          </div>
        </v-card>
      </template>
      <div class="text-center pt-2">
        <v-btn
          color="primary"
          class="mr-2"
          @click="chageleader"
        >
          팀장 부여
        </v-btn>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import http from "@/util/http-common";
import JoinRequest from "@/views/user/JoinRequest";

export default {
  name: "TeamManagement",
  components: {
    JoinRequest
  },
  data: () => ({
    pagination: {},
    selected: [],
    search: '',
    headers: [
      {
        text: '멤버ID',
        align: 'start',
        sortable: false,
        value: 'member.memberId',
      },
      { text: '이름', value: 'member.name' },
      { text: '포인트', value: 'member.point' },
      { text: '이메일', value: 'member.email' },
      { text: '번호', value: 'member.phone' },
      { text: 'mbti', value: 'member.mbti' },
    ],
  }),
  computed: {
    ...mapGetters(["managingTeamMembers", "managingTeam"]),
    pages () {
        if (this.pagination.rowsPerPage == null ||
          this.pagination.totalItems == null
        ) return 0

        return Math.ceil(this.pagination.totalItems / this.pagination.rowsPerPage)
      },
  },
  methods: {
    // chageleader() {
    //   this.$store.dispatch("changeTeamLeader", { teamId: this.managingTeam.teamId, memberId: this.selected[0].member.memberId });
      // console.log(this.selected[0].member.memberId);
      // console.log(this.managingTeam.teamId);
    // },
    getColor (point) {
      if (point > 100) return 'green'
      else if (point > 50) return 'orange'
      else return 'red'
    },
    chageleader() {
      http.put("/team/leader/"+this.selected[0].member.memberId+"?teamId="+this.managingTeam.teamId).then(({ data }) => {
        console.log("changeTeamLeader : " + data.message);
        alert("리더가 변경되었습니다. 잠시 뒤 다시 로그인해주세요.");
        // this.$router.push("/mypage");
      });
    },
  },
};
</script>

<style scoped>

</style>
