import Vue from "vue";
import Vuex from "vuex";
import http from "@/util/http-common";
import VueRouter from "vue-router";

Vue.use(Vuex);
Vue.use(VueRouter);
export default new Vuex.Store({
  state: {
    feeds: [],
    comments: []
  },
  getter: {
    feeds(state) {
      return state.feeds;
    },
    comments(state) {
      return state.comments;
    }
  },
  mutations: {
    setFeeds(state, payload) {
      state.feeds = payload;
    },
    setComments(state, payload) {
      state.comments = payload;
    }
  },
  actions: {
    getFeeds(context) {
      http
        .get("")
        .then(({ data }) => {
          context.commit("setFeeds", data);
        })
        .catch(() => {
          alert("에러발생");
        });
    },
    getComments(context) {
      http
        .get("")
        .then(({ data }) => {
          context.commit("setComments", data);
        })
        .catch(() => {
          alert("에러발생");
        });
    }
  }
});
