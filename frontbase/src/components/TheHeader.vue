<template>
  <div>
    <v-navigation-drawer v-model="drawer" absolute temporary app width="150" height="340">
      <v-list class="pt-4">
        <v-list-tile active-class="green--text" to="/">
          <v-list-tile-content>
            <v-list-tile-title>HOME</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
        <v-list-tile active-class="green--text" to="/resume">
          <v-list-tile-content>
            <v-list-tile-title>RESUME</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
        <v-list-tile active-class="green--text" to="/services">
          <v-list-tile-content>
            <v-list-tile-title>SERVICES</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
        <v-list-tile active-class="green--text" to="/teamChallenge">
          <v-list-tile-content>
            <v-list-tile-title>TEAMCHALLENGE</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
        <v-list-tile active-class="green--text" to="/blog">
          <v-list-tile-content>
            <v-list-tile-title>BLOG</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
        <v-list-tile active-class="green--text" to="/signup">
            <v-list-tile-content>
              <v-list-tile-title>SignUp</v-list-tile-title>
            </v-list-tile-content>
            <v-list-tile active-class="green--text" to="/login">
            <v-list-tile-content>
              <v-list-tile-title>Login</v-list-tile-title>
            </v-list-tile-content>
          </v-list-tile>
        </v-list-tile>
        <v-list-tile active-class="green--text" to="/team">
          <v-list-tile-content>
            <v-list-tile-title>TEAM</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
        <v-list-tile active-class="green--text" to="/teamlist">
          <v-list-tile-content>
            <v-list-tile-title>TEAMLIST</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
      </v-list>
    </v-navigation-drawer>
    <v-toolbar flat dense color="transparent" scroll-off-screen>
      <v-toolbar-side-icon class="hidden-md-and-up" @click.stop="drawer = !drawer"></v-toolbar-side-icon>
      <v-toolbar-title class="headline">
        <span class="font-weight-light">혼자</span>
        <span class="green--text">뭐하니?</span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn @click="changeTheme" depressed small icon class="hidden-md-and-up">
        <v-icon v-if="goDark==true">fas fa-sun</v-icon>
        <v-icon v-else>fas fa-moon</v-icon>
      </v-btn>

      <v-toolbar-items class="hidden-sm-and-down" v-if="memberInfo === null">
        <v-btn flat to="/" active-class="green--text headline">Home</v-btn>
        <v-btn flat to="/signup" active-class="green--text headline">SignUp</v-btn>
        <v-btn flat to="/login" active-class="green--text headline">Login</v-btn>
        <v-btn @click="changeTheme" depressed small icon>
          <v-icon v-if="goDark==true">fas fa-sun</v-icon>
          <v-icon v-else>fas fa-moon</v-icon>
        </v-btn>
      </v-toolbar-items>

      <v-toolbar-items class="hidden-sm-and-down" v-else>
        <v-btn flat to="/" active-class="green--text headline">Home</v-btn>
        <v-btn flat to="/teamChallenge" active-class="green--text headline">TeamChallenge</v-btn>
        <v-btn flat to="/challenge" active-class="green--text headline">Challenges</v-btn>
        <v-btn flat to="/team" active-class="green--text headline">Team</v-btn>
        <v-btn flat to="/teamlist" active-class="green--text headline">Teamlist</v-btn>
        <v-btn flat to="/" active-class="" @click.prevent="onClickLogout">Logout</v-btn>

        <v-btn @click="changeTheme" depressed small icon>
          <v-icon v-if="goDark==true">fas fa-sun</v-icon>
          <v-icon v-else>fas fa-moon</v-icon>
        </v-btn>
      </v-toolbar-items>
    </v-toolbar>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  props: {
    goDark: {
      type: Boolean
    }
  },
  data() {
    return {
      drawer: null
    };
  },
  computed: {
    ...mapState(["memberInfo", "isLogin"])
  },
  methods: {
    changeTheme() {
      this.$emit("changeTheme", this.goDark);
    },
    onClickLogout() {
      alert("로그아웃 하시겠습니까?");
      this.$store
        .dispatch("LOGOUT")
        .then(() => {
          if (this.$route.path !== "/") this.$router.replace("/");
          
        })
        .catch(() => {
          console.log("로그아웃 에러입니다.");
        });
    },
  }
};
</script>

<style >
</style>