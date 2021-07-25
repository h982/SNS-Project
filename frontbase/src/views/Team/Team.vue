<template>
  <v-container grid-list-xl>
    <v-layout row justify-center align-center wrap class="mt-4 pt-2">

      <v-flex xs12 sm12 md6 lg6 xl6>
        <h2 class="pb-4 mb-4">
          <span>Team</span>
          <span class="green--text">Create</span>
        </h2>

        <form>
          <v-text-field
            name="team.name"
            color="green"
            background-color="transparent"
            v-model="team.name"
            label="팀이름"
            required
            @blur="$v.teamname.$touch()"
          ></v-text-field>

          <v-row>
            <v-btn @click="duplicateName" type="button" color="blue" class="white--text duplicate">
              중복검사
            </v-btn>
          </v-row>

          <v-text-field
            name="team.leaderId"
            color="green"
            background-color="transparent"
            v-model="team.leaderId"
            label="리더ID"
            required
          ></v-text-field>

          <v-text-field
            name="team.leader"
            color="green"
            background-color="transparent"
            v-model="team.leader"
            label="리더이름"
            required
          ></v-text-field>

          <v-select
            v-model="team.sport"
            :items="sportList"
            label="종목"
            item-text="name"
            item-value="value"
            return-object
          ></v-select>

          <v-text-field
            name="team.introduction"
            color="green"
            background-color="transparent"
            v-model="team.introduction"
            label="팀소개"
            required
          ></v-text-field>

          <v-text-field
            name="team.imgPath"
            color="green"
            background-color="transparent"
            v-model="team.imgPath"
            label="이미지"
            required
          ></v-text-field>

          <v-file-input
            label="File input"
            filled
            prepend-icon="mdi-camera"
          ></v-file-input>

          <div class="buttons">
            <v-btn @click="submit" type="button" color="green" class="white--text"
              >생성하기</v-btn
            >
            <v-btn @click="clear">clear</v-btn>
          </div>
        </form>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { validationMixin } from "vuelidate";
import { createInstance } from "@/api/teamindex.js";
import {
  required,
  maxLength,
  email,
  minLength,
} from "vuelidate/lib/validators";
export default {
  mixins: [validationMixin],
  validations: {
    name: { required, maxLength: maxLength(20) },
    email: { required, email },
    body: { required, minLength: minLength(20) }
  },
  data() {
    return {
      sportList: [
        { name: "러닝", value: 0 },
        { name: "헬스", value: 1 },
        { name: "수영", value: 2 },
        { name: "탁구", value: 3 },
      ],
      name: "",
      email: "",
      body: "",
      sport: "",
      team: {
        name: "",
        sportId: "",
        introduction: "",
        imgPath: "",
        leader: "",
        leaderId: "",
        member: {
          memberId: Number(),
        },
        sportDto: {
          sportId: Number(),
        }
      },
    };
  },
  methods: {
    submit() {
      this.team.sport = this.sport.value;
      const instance = createInstance();
      instance
        .post("team/", JSON.stringify(this.team))
        .then((response) => {
          if (response.data.message === "success") {
            alert("팀생성완료 완료");
            this.$router.push("/"); // 생성성공했으면 자기 그룹영역(그룹피드/게시판/채팅/챌린지)으로 이동 => router children 설정 필요
          } else {
            alert("팀생성 실패");
          }
        })
        .catch(() => {
          alert("에러발생!");
        });
    },
    clear() {
      this.$v.$reset();
      this.team.name = "";
      this.team.introduction = "";
    },
    duplicateName(){
      const instance = createInstance();
      instance
        .get("/team"+this.team.name+"exist")
        .then(({ data }) => {
          if (data) {
            alert("이미 사용된 팀명입니다!")
          } else {
            alert("사용가능한 팀명입니다!")
          }
        });
    },
  },
  computed: {
    // teamnameErrors() {
    //   const errors = [];
    //   if (!this.$v.teamname.$dirty) return errors;
    //   !this.$v.teamname.maxLength &&
    //     errors.push("팀이름은 20글자 이내로 작성해야합니다.");
    //   !this.$v.teamname.required && errors.push("팀이름을 적어주세요.");
    //   return errors;
    // },
    // emailErrors() {
    //   const errors = [];
    //   if (!this.$v.email.$dirty) return errors;
    //   !this.$v.email.email && errors.push("Must be valid e-mail");
    //   !this.$v.email.required && errors.push("E-mail is required");
    //   return errors;
    // },
    // bodyErrors() {
    //   const errors = [];
    //   if (!this.$v.body.$dirty) return errors;
    //   !this.$v.body.minLength &&
    //     errors.push("Text must be at least 20 characters long");
    //   !this.$v.body.required && errors.push("Text is required");
    //   return errors;
    // }
  }
};
</script>

<style scoped>
.duplicate {
  position: relative;
  margin-left: 85%;
}
.buttons {
  position: relative;
  margin-left: 33%;
}
</style>
