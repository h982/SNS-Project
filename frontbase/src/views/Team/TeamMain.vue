<template>
  <v-container>

    <v-layout >
      <v-bottom-navigation
        v-if="teamcheck === true | this.selectTeam.memberId === this.memberInfo.memberId"
        class="mx-auto"
        shift
        x-large
      >
      <team-header />
      </v-bottom-navigation>
      <v-bottom-navigation
        v-else
        class="mx-auto"
        shift
        x-large
      >
      <team-header-2 />
      </v-bottom-navigation>

      
    </v-layout>
    <v-layout column justify-center class="mt-4 pt-2">
      <p class="text-xs-center mb-4 pb-2" style="font-size: x-large;">{{selectTeam.name.replaceAll("\"", "")}}</p>
      <br>
      <div v-if="selectTeam.photoDto === null">
        <v-img :src="thumbnail1" aspect-ratio="2.75" height="330" contain></v-img>
      </div>
      <div v-else>
        <v-img :src="selectTeam.photoDto.filePath" aspect-ratio="2.75" height="330" contain></v-img>
      </div>
      <v-layout column justify-center align-center class="mt-4 pt-2">
        <h2 style="color:green">팀 소개</h2>
        <v-flex wrap justify-center align-center class="textbox">
        
            <p>{{selectTeam.introduction.replaceAll("\"", "")}}</p>
          
        </v-flex>
        <br>

        <h2 style="color:green">팀장</h2>
        <p>{{selectTeam.leader.replaceAll("\"", "")}}</p>
        <br>

        <h2 style="color:green">팀원</h2>
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
              :headers="headers"
              :items="this.selectTeamMembers"
              item-key="member.memberId"
              hide-actions
              :pagination.sync="pagination"
              :search="search"
              class="elevation-1"
            >
              <template v-slot:items="props">
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
                <td class="text-xs-right">{{ props.item.member.mbti }}</td>
              </template>
            </v-data-table>
            <div class="text-xs-center pt-2">
              <v-pagination v-model="pagination.page" :length="pages"></v-pagination>
            </div>
          </v-card>
        </template>
        <br>
        <v-btn
          v-if="selectTeam.memberId != memberInfo.memberId && teamcheck === false"
          @click="join"
          type="button"
          color="green"
          class="white--text"
        >가입하기
        </v-btn>
        <v-btn
          v-else
          @click="leave"
          type="button"
          color="red"
          class="white--text"
        >팀탈퇴
        </v-btn>
      </v-layout>
      <v-btn
        v-if="selectTeam.memberId != memberInfo.memberId && teamcheck === false"
        color="secondary"
        @click="join"
        elevation="7"
        fab
        large
        x-large
        x-small
        class="join"
      ><i class="fas fa-sign-in-alt fa-2x"></i>
      </v-btn> 
      <br>
      <br>
      <v-layout>
        <v-btn large flat to="/teamlist" class="green--text">
          <v-icon>arrow_back</v-icon>Back to Teamlist
        </v-btn>
        <!-- <v-btn @click="check()">
          
        </v-btn> -->
      </v-layout>
    </v-layout>

  </v-container>
</template>

<script>
import { mapGetters } from 'vuex';
import TeamHeader from "@/components/TeamHeader.vue"
import { createInstance } from "@/api/teamindex.js";
import thumbnail1 from "@/assets/images/thumbnail.jpg";
import TeamHeader2 from '../../components/TeamHeader2.vue';
// import { pop, render } from "@/api/event.js";

export default {
  name: "TeamMain",
  computed:{
    ...mapGetters(["selectTeam","memberInfo","myTeamList","team_challenges","team_challenging", "managingTeam", "selectTeamMembers"]),
    pages () {
      if (this.pagination.rowsPerPage == null ||
        this.pagination.totalItems == null
      ) return 0

      return Math.ceil(this.pagination.totalItems / this.pagination.rowsPerPage)
    },
  },
  created() {
    this.$store.dispatch("GET_MY_TEAM_INFO",this.memberInfo.memberId);
    this.teamchecking();
    this.$store.dispatch("GET_TEAMCHALLENGE_INFO", this.memberInfo.memberId);
    const token={
      memberId: this.memberInfo.memberId,
      teamId:this.selectTeam.teamId
    };
    this.$store.dispatch("GET_TEAMCHALLENGER_INFO", token); 
    this.$store.dispatch("getSelectTeamMembers", this.selectTeam.teamId);
    console.log(this.selectTeamMembers);
    // console.log(this.managingTeamMembers);

  },
  data() {
    return {
      teamcheck: false,
      thumbnail1: thumbnail1,
      pagination: {},
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
        { text: 'mbti', value: 'member.mbti' },
      ],
    };
  },
  components: {
    TeamHeader,
    TeamHeader2,
  },
  methods: {
    join() {
      const instance = createInstance();
      instance
      .post("/request", {
        "member":{
          "memberId": this.memberInfo.memberId,
        },
        "team":{
          "teamId": this.selectTeam.teamId
        }
      })
      .then(
        (response) => {
          console.log(response);
          if (response.status === 201) {
            // alert("가입 요청 완료");
          } else {
            // alert("가입 요청 실패");
          }
        }
      )
      .catch(() => {
      });
    },
    moveMain(){
      this.$router.push("/teammain");
    },
    moveTeamChallenge(){
      //alert(this.memberInfo.memberId);
      this.$store.dispatch("GET_TEAMCHALLENGE_INFO", this.memberInfo.memberId);
      this.$router.push("/teamChallenge");
    },
    moveBoard(){
      this.$router.push("/board");
    },
    moveChattingRoom(){
      this.$router.push("/chattingroom");
    },
    moveTeamFeed(){
      this.$router.push("/teamFeed");
    },
    teamchecking() {
      for(let i=0; i<this.myTeamList.length; i++) {
        if (this.myTeamList[i].value.teamId === this.selectTeam.teamId) {
          this.teamcheck = true;
          break;
        }
      }
    },
    check(){
      console.log(this.memberInfo);
    },
    getColor (point) {
      if (point > 100) return 'green'
      else if (point > 50) return 'orange'
      else return 'red'
    },
    leave() {
      if (this.managingTeam.member.memberId == this.memberInfo.memberId) {
        alert("팀장을 넘겨주세요:)");
      } else {
        const instance = createInstance();
        instance.delete("/jointeam?memberId="+this.memberInfo.memberId+"&teamId="+this.selectTeam.teamId)
        .then(
          (response) => {
            if (response.message === "success") {
              // alert("팀 탈퇴");
              console.log(response);
            } else {
              // alert("팀 탈퇴 실패");
              console.log(response);
            }
          }
        )
        .catch();
      }
    },
  },
}
</script>

<style scoped>
.join {
  position: absolute;
  right: 0px;
  bottom: 0px;  
}
.textbox {
  padding: 0px 150px;
}
.birthday {
  position: relative;
  right: 0px;
}
</style>
