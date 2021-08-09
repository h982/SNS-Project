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
            :items="items"
            item-key="name"
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
                  primary
                  hide-details
                ></v-checkbox>
              </td>
              <td>{{ props.item.name }}</td>
              <td class="text-xs-right">{{ props.item.point }}</td>
              <td class="text-xs-right">{{ props.item.email }}</td>
              <td class="text-xs-right">{{ props.item.phone }}</td>
              <td class="text-xs-right">{{ props.item.mbti }}</td>
              <td class="text-xs-right">{{ props.item.date }}</td>
            </template>
            <template v-slot:no-results>
              <v-alert :value="true" color="error" icon="warning">
                Your search for "{{ search }}" found no results.
              </v-alert>
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
        text: '팀원이름',
        align: 'start',
        sortable: false,
        value: 'name',
      },
      { text: '포인트', value: 'point' },
      { text: '이메일', value: 'email' },
      { text: '번호', value: 'phone' },
      { text: 'mbti', value: 'mbti' },
      { text: '가입날짜', value: 'date' },
    ],
    items: [
      {
        name: '차범희',
        point: 150,
        email: 'qqq960909@naver.com',
        phone: '010-4533-0093',
        mbti: 'iiii',
        date: '2021-08-09',
      },
      {
        name: '김범희',
        point: 100,
        email: 'qqq960909@naver.com',
        phone: '010-4533-0093',
        mbti: 'iiii',
        date: '2021-08-09',
      },
      {
        name: '박범희',
        point: 50,
        email: 'qqq960909@naver.com',
        phone: '010-4533-0093',
        mbti: 'iiii',
        date: '2021-08-09',
      },
      {
        name: '이범희',
        point: 200,
        email: 'qqq960909@naver.com',
        phone: '010-4533-0093',
        mbti: 'iiii',
        date: '2021-08-09',
      },
      {
        name: '이범희',
        point: 80,
        email: 'qqq960909@naver.com',
        phone: '010-4533-0093',
        mbti: 'iiii',
        date: '2021-08-09',
      },
      {
        name: '이범희',
        point: 200,
        email: 'qqq960909@naver.com',
        phone: '010-4533-0093',
        mbti: 'iiii',
        date: '2021-08-09',
      },
      {
        name: '이범희',
        point: 200,
        email: 'qqq960909@naver.com',
        phone: '010-4533-0093',
        mbti: 'iiii',
        date: '2021-08-09',
      },
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
  created() {
    this.$store.dispatch("getTeamMembers", this.managingTeam.teamId);
    console.log(this.managingTeamMembers);
  },
  methods: {
    chageleader() {

    },
  },
};
</script>

<style scoped>

</style>
