<template>
  <div>
    <br>
    <br>
    <v-layout align-center data-aos="fade-right">
      <v-toolbar-title class="headline">
          <span><b>내 팀</b></span>
          <span class="green--text"><b>&nbsp;관리<v-btn @click="move()" icon elevation="0"><v-icon>settings</v-icon></v-btn></b></span>
      </v-toolbar-title>
      <br><br><br><br>
      <v-btn @click="check()"></v-btn>
    </v-layout>
    <v-row>
      <join-request />
    </v-row>
    <br>
    <v-layout justify-start align-center data-aos="fade-up">
      <v-toolbar-title class="headline">
          <span><b>팀원</b></span>
          <span class="green--text"><b>&nbsp;조회</b></span>
      </v-toolbar-title>
      <br><br><br><br>
    </v-layout>
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
              <td>
                <img
                    v-bind:src="props.item.member.photo.filePath"
                    style="border-radius: 50%;"
                    height="30"
                  /> 
              </td>
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
// import http from "@/util/http-common";
import { createInstance } from "@/api/teamindex.js";
import JoinRequest from "@/views/user/JoinRequest";

export default {
  components: {
    JoinRequest
  },
  data: () => ({
    pagination: {},
    selected: [],
    search: '',
    headers: [
      {
        text: '프로필',
        align: 'start',
        sortable: false,
        value: 'member.memberId',
        width: '100'
      },
      { text: '이름', value: 'member.name' ,width: '0'},
      { text: '포인트', value: 'member.point', width: '0'},
      { text: '이메일', value: 'member.email',width: '-10'},
      { text: '번호', value: 'member.phone', width: '0'},
      { text: 'mbti', value: 'member.mbti', width: '0'},
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
    
    getColor (point) {
      if (point >= 100) return '#9400D3'
      else if (point >= 75) return '#7AD7BE'
      else if (point >= 50) return '#FFA500'
      else if (point >= 25) return '#52478B'
      else return '#8B4513'
    },
    chageleader() {
      const instance = createInstance();
      instance.put("/team/leader/"+this.selected[0].member.memberId+"?teamId="+this.managingTeam.teamId).then(({ data }) => {
        console.log("changeTeamLeader : " + data.message);
        alert("리더가 변경되었습니다. 잠시 뒤 다시 로그인해주세요.");
      });
    },
    move(){
      this.$router.push("/teamModify");
    },
    check(){
      console.log(this.managingTeamMembers);
    }
  },
};
</script>

<style scoped>

</style>
