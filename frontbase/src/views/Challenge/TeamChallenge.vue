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

    <h2 class="pl-4">
      <span>팀에서 진행중인 </span>
      <span class="green--text">챌린지</span>
    </h2>
    <div>
      <ul>
        <li v-for="(challenge,idx) in team_challenges" v-bind:key="challenge"
        >
          <div v-if="challenge.text.team.teamId==selectTeam.teamId">
            <h3>챌린지 : {{idx}}</h3>
            <h3>챌린지 제목: {{challenge.text.title}}</h3>
            <h3>챌린지 내용: {{challenge.text.contents}}</h3>
            <h3>시작 일자: {{challenge.text.startDate}}</h3>
            <h3>종료 일자: {{challenge.text.endDate}}</h3>
          </div>
        </li>
      </ul>
    </div>
    <v-btn flat large dark color="black" target="_blank" @click="check()">
      확인하기
    </v-btn>

    <h2 class="pl-4">
      <span>전체 </span>
      <span class="green--text">챌린지</span>
    </h2>
    <div>
      <ul>
        <li v-for="(challenge,idx) in team_challenges" v-bind:key="challenge"
        >
          
            <h3>챌린지 : {{idx}}</h3>
            <h3>챌린지 제목: {{challenge.text.title}}</h3>
            <h3>챌린지 내용: {{challenge.text.contents}}</h3>
            <h3>시작 일자: {{challenge.text.startDate}}</h3>
            <h3>종료 일자: {{challenge.text.endDate}}</h3>
        
        </li>
      </ul>
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

export default {
  components:{
    DatePicker 
  },
  computed:{
    ...mapGetters ([
      
    ]),
    ...mapState(["memberInfo","teamInfo","selectTeam","team_challenges"])
  },
  data() {
    return {
      dialog: false,
      team_projects: [
        {
          dialog: false,
          title: "작심삼일이라도 달리기",
          git: "",
          demo: "",
          tech: {
            tech1: "러닝",
            tech2: "팀챌린지",
            tech3: "기간: 3일",
          },
          poster: "https://i.ibb.co/p2cLTzW/image.jpg"
        },
        {
          dialog: false,
          title: "러닝은 거들뿐",
          git: "https://github.com/EldinZaimovic/Eldin-Space-Vue",
          demo: "https://eldin.space/",
          tech: {
            tech1: "VUE",
            tech2: "Storyblok",
            tech3: "HTML",
            tech4: "JavaScript"
          },
          poster: "https://i.ibb.co/p2cLTzW/image.jpg"
        },
        {
          dialog: false,
          title: "일주일만 버티자",
          git: "https://cdn.neow.in/news/images/uploaded/2018/11/1543476286_cybersecurity.jpg",
          demo: "https://www.brandly.com/",
          tech: {
            tech1: "VUE",
            tech2: "SCSS",
            tech3: "HTML",
            tech4: "JavaScript"
          },
          poster: "https://i.ibb.co/p2cLTzW/image.jpg"
        },
      ],
      
  
      date: null,
      whole_challenges:[
        {
          title: "설문안나간다고 구라치고 달리기",
          contents: "설문좀그만해라 싸피야",
          status : 0,
          member_count : 0,
          goal_count : 3,
          date:null,
          poster: "https://i.ibb.co/p2cLTzW/image.jpg"
        },
        {
          title: "퇴근 후 달리기",
          contents: "퇴근 후 달리고 인증하자",
          status : 0,
          member_count : 0,
          goal_count : 5,
          date:null,
          poster: "https://i.ibb.co/p2cLTzW/image.jpg"
        }
      ]
    };
  },
  
  methods: {
      check(){
        console.log(this.team_challenges);
        alert(this.team_challenges);
        console.log(this.selectTeam);
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

</style>