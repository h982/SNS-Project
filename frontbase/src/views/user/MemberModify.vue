<template>
  <v-container grid-list-xl>
    <v-layout row justify-center align-center wrap class="mt-4 pt-2">
      <v-flex xs12 sm12 md6 lg6 xl6>
        <h2 class="pb-4 mb-4">
          <span>Modify</span>
          <span class="green--text">Form</span>
        </h2>

        <form method="POST">
          
          <v-text-field
            id="user.userpwd"
            color="green"
            background-color="transparent"
            v-model="user.userpwd"
            label="Password"
            required
          ></v-text-field>
          <v-text-field
            name="user.username"
            color="green"
            background-color="transparent"
            v-model="user.username"
            :error-messages="nameErrors"
            label="Name"
            required
          ></v-text-field>
          <v-text-field
            type="email"
            color="green"
            background-color="transparent"
            name="user.email"
            v-model="user.email"
            :error-messages="emailErrors"
            label="E-mail"
            required
          ></v-text-field>
          <v-text-field
            address="address"
            color="green"
            background-color="transparent"
            v-model="user.address"
            :error-messages="nameErrors"
            label="Address"
            required
          ></v-text-field>
          <b-button
            type="button"
            color="green"
            class="white--text"
            @click="modify"
          >SEND MESSAGE</b-button>
          <v-btn @click="clear">clear</v-btn>
        </form>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { validationMixin } from "vuelidate";
import { createInstance } from "@/api/index.js";
import {
  required,
  maxLength,
  email,
} from "vuelidate/lib/validators";
import { mapState } from "vuex";
export default {
  metaInfo: {
    title: "",
    titleTemplate: "ApartFinder",
  },
  mixins: [validationMixin],
  validations: {
    name: { required, maxLength: maxLength(20) },
    email: { required, email },
  },
  data() {
    return {
      user: {
        userid:"",
        userpwd:"",
        username: "",
        email: "",
        address: ""
      },
    };
  },
  methods: {
    clear() {
      this.$v.$reset();
      this.user.userid="";
      this.user.userpwd="";
      this.user.username = "";
      this.user.email = "";
      this.user.address = "";
    },
    modify() {
      const instance = createInstance();
      this.user.userid=this.userInfo.userid;
      instance.post("/user/confirm/modify", JSON.stringify(this.user))
      .then(
        (response) => {
          if (response.data.message === "success") {
            alert("회원변경 완료");
            this.userInfo.userpwd= this.user.userpwd;
            this.userInfo.username= this.user.username;
            this.userInfo.email= this.user.email;
            this.userInfo.address= this.user.address;
            this.$router.push("/");
          } else {
            alert("회원변경 실패");
          }
        }
      )
      .catch();
    },
  },
  created() {
    this.user.userpwd=userInfo.userpwd;
  },
  computed: {
    ...mapState(["userInfo"]),
    nameErrors() {
      const errors = [];
      if (!this.$v.name.$dirty) return errors;
      !this.$v.name.maxLength &&
        errors.push("Name must be at most 20 characters long");
      !this.$v.name.required && errors.push("Name is required.");
      return errors;
    },
    emailErrors() {
      const errors = [];
      if (!this.$v.email.$dirty) return errors;
      !this.$v.email.email && errors.push("Must be valid e-mail");
      !this.$v.email.required && errors.push("E-mail is required");
      return errors;
    },
    bodyErrors() {
      const errors = [];
      if (!this.$v.mbti.$dirty) return errors;
      !this.$v.mbti.minLength &&
        errors.push("mbti 4글자 입력");
      !this.$v.mbti.required && errors.push("MBTI를 입력해주세요.");
      return errors;
    }
  }
};
</script>

<style lang="scss" scoped>
</style>
