<template>
    <tr>
      <td>{{ no }}</td>
      <td>{{ title }}</td>
      <td>{{ contents }}</td>
      <td>{{ endDate }}</td>
      <td><v-btn @click="participate">챌린지신청</v-btn></td>
    </tr>
</template>

<script>
import { mapState } from 'vuex';
import { createInstance } from "@/api/index.js";

export default {
  name: "ListRow",
  props: {
    no: Number,
    title: String,
    contents: String,
    endDate: String,
  },
  data() {
    return {
    
    }
  },
  computed:{
    ...mapState(["memberInfo","teamInfo","selectTeam","team_challenges","team_challenging"])
  },
  methods:{
    participate(){
        const instance = createInstance();
        const body = {
          memberId: this.memberInfo.memberId,
          teamChallengeId: this.no,
        }; 
        console.log(body);
        instance.post("/team_challenge_participate", JSON.stringify(body))
        .then(
          (response) => {
            console.log(response);
            if (response.data.data === "success") {
              this.$store.dispatch("GET_TEAMCHALLENGE_INFO", this.memberInfo.memberId);
              const token={
                memberId: this.memberInfo.memberId,
                teamId:this.selectTeam.teamId
              };
              this.$store.dispatch("GET_TEAMCHALLENGER_INFO", token);
              alert("챌린지에 등록되었습니다.");
              
            } else {
              alert("챌린지 추가에 실패하였습니다.");
            }
          }
        )
        .catch(() =>{
          alert("이미 신청한 챌린지 입니다.");
        });
      },
      giveUp(){
        const body = {
          memberId: this.memberInfo.memberId,
          teamChallengeId: this.no,
        }; 
        console.log(body);
        const instance = createInstance();
      
        instance.post("/team_challenge_giveup", JSON.stringify(body))
        .then(
          (response) => {
            console.log(response);
            if (response.data.data === "success") {
              alert("챌린지를 포기하셨습니다.");
              
            } else {
              alert("챌린지 포기에 실패하셨습니다.");
            }
          }
        )
        .catch();
      },  
  }
};
</script>
<style scope>
td {
  text-align: center;
  border-bottom: 1px solid #ddd;
  height: 30px;
  width: 70px;
}
</style>
