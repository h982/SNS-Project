<template>
  <div>
    <h1>팀원 조회 및 팀장 권환 부여</h1>
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
              <td class="text-xs-right">{{ props.item.member.point }}</td>
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

export default {
  name: "TeamManagement",
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
    chageleader() {
      this.$store.dispatch("changeTeamLeader", { teamId: this.managingTeam.teamId, memberId: this.selected[0].member.memberId })
      // console.log(this.selected[0].member.memberId);
      // console.log(this.managingTeam.teamId);
    },
  },
};
</script>

<style scoped>

</style>
