<template>
  <v-container grid-list-xl>
    

    <v-layout >
      <v-bottom-navigation
        class="mx-auto"
        shift
        x-large
      >
        <v-btn color="secondary" @click="moveMain">
          <i class="fas fa-address-card fa-2x"></i>
          <div>&nbsp;&nbsp;&nbsp;정보</div>
        </v-btn>

        <v-btn color="success" @click="moveTeamFeed">
          <i class="fas fa-clipboard fa-2x" ></i>
          <div>&nbsp;&nbsp;&nbsp;피드</div>
        </v-btn>

        <v-btn color="primary" @click="moveBoard">
          <i class="fas fa-check fa-2x" ></i>
          <div>&nbsp;&nbsp;&nbsp;공지사항</div>
        </v-btn>
        
        <v-btn color="warning" @click="moveChattingRoom">
          <i class="fas fa-comments fa-2x"></i>
          <div>&nbsp;&nbsp;&nbsp;채팅</div>
        </v-btn>

        <v-btn color="error"  @click="moveTeamChallenge">
          <i class="fas fa-trophy fa-2x"></i>
          <div>&nbsp;&nbsp;&nbsp;챌린지</div>
        </v-btn>
      </v-bottom-navigation>
    </v-layout>
    
    <br>
    <h2 class="pl-4">
      <span>팀에서 진행중인 </span>
      <span class="green--text">챌린지</span>
    </h2>
    
    <br>

    <div v-if="team_challenges.length">
      <table id="book-list">
        <colgroup>
          <col style="width: 10%" />
          <col style="width: 30%" />
          <col style="width: 20%" />
          <col style="width: 20%" />
          <col style="width: 20%" />
        </colgroup>
        <thead>
          <tr>
            <th>챌린지번호</th>
            <th>챌린지 제목</th>
            <th>내용</th>
            <th>종료일자</th>
            <th>수행여부</th>
          </tr>
        </thead>
        <tbody>
        
          <list-row-team-challenge
            v-for="(challenge, index) in items"
            
            :key="index"
            :no="index+1"
            :title="challenge.text.title"
            :contents="challenge.text.contents"
            :endDate="challenge.text.endDate"
            
          />
          </tbody>
      </table>
    </div>

    <br><br>

    <h2 class="pl-4">
      <span>전체 </span>
      <span class="green--text">챌린지</span>
    </h2>
    <br>
    <div v-if="team_challenges.length">
      <table id="book-list">
        <colgroup>
          <col style="width: 10%" />
          <col style="width: 30%" />
          <col style="width: 20%" />
          <col style="width: 20%" />
          <col style="width: 20%" />
        </colgroup>
        <thead>
          <tr>
            <th>챌린지번호</th>
            <th> 챌린지 제목</th>
            <th>내용</th>
            <th>종료일자</th>
            
          </tr>
        </thead>
        <tbody>
          <list-row-whole-challenge
            v-for="(challenge, index) in team_challenges"
            :key="index"
            :no="index+1"
            :title="challenge.text.title"
            :contents="challenge.text.contents"
            :endDate="challenge.text.endDate"
            
          />
          </tbody>
      </table>
    </div>

  
    <v-btn flat large dark color="black" target="_blank" @click="check()">
      확인하기
    </v-btn>
    

    <!-- <h2 class="pl-4">
      <span>진행중인 </span>
      <span class="green--text">팀 챌린지</span>
    </h2>
    <v-layout row justify-center align-center wrap class="mt-4 pt-2">
      <v-dialog
        v-model="team_challenge.dialog"
        lazy
        max-width="1000"
        v-for="team_challenge in team_projects"
        :key="team_challenge.title"
      >
        <template v-slot:activator="{ on }">
          <v-flex xs12 sm6 md4 lg4 xl4 v-on="on">
            <v-card hover flat color="transparent">
              <v-img
                :src="team_challenge.poster"
                :alt="team_challenge.title"
                height="230"
                lazy-src="https://cdn.dribbble.com/users/503653/screenshots/3143656/fluid-loader.gif"
              ></v-img>
              <v-card-title primary-title class="justify-center">{{team_challenge.title}}</v-card-title>
            </v-card>
          </v-flex>
        </template>
        <v-card v-if="team_challenge.dialog">
          <v-img :src="team_challenge.poster"></v-img>
          <v-card-text>
            <h3 class="headline mb-0">
              <span>{{team_challenge.title}}</span>
            </h3>
            <v-chip color="green" text-color="white">{{team_challenge.tech.tech1}}</v-chip>
            <v-chip color="green" text-color="white">{{team_challenge.tech.tech2}}</v-chip>
            <v-chip color="green" text-color="white">{{team_challenge.tech.tech3}}</v-chip>
            <v-btn flat large dark color="black" target="_blank" @click="pariticipate(team_challenge)">
              참여하기
            </v-btn>

            <v-btn flat large dark color="black" target="_blank" @click="giveUp(team_challenge)">
              포기하기
            </v-btn>
          </v-card-text>
          <v-card-actions>
            
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-layout> -->

    <!-- <h2 class="pl-4">
      <span>전체 </span>
      <span class="green--text">챌린지</span>
    </h2> -->
    <!-- <v-layout row justify-center align-center wrap class="mt-4 pt-2">
      <v-dialog
        lazy
        max-width="1000"
        v-for="whole_challenge in whole_challenges"
        :key="whole_challenge.title"
      >
        <template v-slot:activator="{ on }">
          <v-flex xs12 sm6 md4 lg4 xl4 v-on="on">
            <v-card hover flat color="transparent">
              <v-img
                :src="whole_challenge.poster"
                :alt="whole_challenge.title"
                height="230"
                lazy-src="https://cdn.dribbble.com/users/503653/screenshots/3143656/fluid-loader.gif"
              ></v-img>
              <v-card-title primary-title class="justify-center">{{whole_challenge.title}}</v-card-title>
            </v-card>
          </v-flex>
        </template>
        <v-card >
          <v-img :src="whole_challenge.poster"></v-img>
          <v-card-text>
            <h3 class="headline mb-0">
              <span>{{whole_challenge.title}}</span>
            </h3>
            
            <v-chip color="green" text-color="white">{{whole_challenge.title}}</v-chip>
            <v-chip color="green" text-color="white">{{whole_challenge.contents}}</v-chip>

          </v-card-text>
          <v-card-actions>
            <v-btn large flat dark color="green" target="_blank" @click="enroll(whole_challenge)">
              등록하기
            </v-btn>

          <date-picker v-model="whole_challenge.date" range></date-picker>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-layout> -->
    <v-btn flat to="/challengemake" active-class="green--text headline">챌린지만들기</v-btn>

  </v-container>
</template>

<script>
import DatePicker from 'vue2-datepicker';
import 'vue2-datepicker/index.css';
import { mapGetters, mapState } from 'vuex';
import { createInstance } from "@/api/index.js";
import ListRowWholeChallenge from "@/components/ListRowWholeChallenge.vue";
import ListRowTeamChallenge from "@/components/ListRowTeamChallenge.vue";
export default {
  components:{
    DatePicker,
    ListRowWholeChallenge,
    ListRowTeamChallenge
  },
  computed:{
    ...mapGetters ([
      
    ]),
    ...mapState(["memberInfo","teamInfo","selectTeam","team_challenges"])
  },
  created: function(){
    if(this.team_challenges.length>0){
      for(var i = 0; i< this.team_challenges.length;i++){
        if(this.team_challenges[i].text.team.teamId===this.selectTeam.teamId)
          this.items.push(this.team_challenges[i]);
          console.log(i);
      }
    }
  },

  data() {
    return {
      items:[],
      headers: [
        { text: '챌린지ID', align: 'center', value: 'teamChallengeId' },
        { text: '챌린지제목', align: 'center',value: 'title' },
        { text: '챌린지내용', align: 'center',  value: 'contents' },
        { text: '종료일자', align: 'center', value: 'endDate' },
      ],
      page: 1,
      pageCount: 0,
      loading: false,
      itemsPerPage: 10,
      dialog: false,
      
    }
  },
  
  methods: {
      check(){
        console.log(this.items);
        console.log(this.team_challenges);
      },
      enroll(data) {
        let start_date = JSON.stringify(data.date[0]).replaceAll('"', "");
        let end_date = JSON.stringify(data.date[1]).replaceAll('"', "");
        const instance = createInstance();
        start_date = start_date.replaceAll('\\', "");
        end_date = end_date.replaceAll('\\',"");
        console.log(this.selectTeam);
        const body = {
          "contents":data.contents,
          "endDate":end_date,
          "startDate":start_date,
          "teamId": this.selectTeam.teamId,
          "title": data.title
        }; 
        console.log(JSON.stringify(body));
        instance.put("/team_challenge_enroll", JSON.stringify(body))
        .then(
          (response) => {
            if (response.data.message === "success") {
              alert("팀 챌린지 리스트 등록 완료");
              
            } else {
              alert("팀 챌린지 리스트 등록 실패");
            }
          }
        )
        .catch();
      },
      participate(challengeInfo){
        const instance = createInstance();
        const body = {
          challenge: challengeInfo,
          memberInfo: this.memberInfo,
        }; 
        console.log(body);
        instance.post("/challenge/team_challenge_participate", JSON.stringify(body))
        .then(
          (response) => {
            if (response.data.message === "success") {
              alert("챌린지에 등록되었습니다.");
              
            } else {
              alert("챌린지 추가에 실패하였습니다.");
            }
          }
        )
        .catch();
      },  
      giveUp(challengeInfo){
        const body = {
          challenge: challengeInfo,
          memberInfo: this.memberInfo,
        }; 
        console.log(body);
        const instance = createInstance();
      
        instance.post("/challenge/team_challenge_giveUp", JSON.stringify(body))
        .then(
          (response) => {
            if (response.data.message === "success") {
              alert("챌린지를 포기하셨습니다.");
              
            } else {
              alert("챌린지 포기에 실패하셨습니다.");
            }
          }
        )
        .catch();
      },
      moveMain(){
      this.$router.push("/teammain");
    },
    moveTeamChallenge(){
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
    }          
    },
      
};
</script>


<style>
#book-list {
  border-collapse: collapse;
  width: 100%;
}

#book-list thead {
  font-weight: bold;
}

#book-list td,
#book-list th {
  text-align: center;
  border-bottom: 1px solid #ddd;
  height: 50px;
}
</style>