import Vue from "vue";
import Vuex from "vuex";
import jwt_decode from "jwt-decode";
import { findById } from "@/api/user.js";
import { createInstance } from "../api/teamindex";
import http from "@/util/http-common";
Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    isLogin: false, // 로그인 여부
    memberInfo: null,
    teamLists: [], // 팀 정보
    feeds: [],
    teamFeeds: [],
    comments: [],
    whole_challenges: [],
    team_challenges: [],
    team: [],
    teamInfo: null,
    myTeamList: [],
    managingTeam: {},
    book: {},
    books: [],
    joinRequests: [],
    selectTeam: {},
    feedid: {},
    team_challenging: [], //내가 진행중인 챌린지
    entire_challenge: [], // 공통 챌린지
    feed_challenging: [], //feed 입력시 넣는 챌린지 목록

    // 공지사항
    noticeItems: [],
    noticeItem: {},
  },

  getters: {
    team_challenges(state) {
      return state.team_challenges;
    },
    whole_challenges(state) {
      return state.whole_challenges;
    },
    myTeamList(state) {
      return state.myTeamList;
    },
    feeds(state) {
      return state.feeds;
    },
    teamFeeds(state) {
      return state.teamFeeds;
    },
    comments(state) {
      return state.comments;
    },
    teamLists(state) {
      return state.teamLists;
    },
    books(state) {
      return state.books;
    },
    book(state) {
      return state.book;
    },
    memberInfo(state) {
      return state.memberInfo;
    },
    joinRequests(state) {
      return state.joinRequests;
    },
    selectTeam(state) {
      return state.selectTeam;
    },
    feedid(state) {
      return state.feedid;
    },
    team_challenging(state) {
      return state.team_challenging;
    },
    entire_challenge(state) {
      return state.entire_challenge;
      },
    managingTeam(state) {
      return state.managingTeam;
    },
    feed_challenging(state) {
      return state.feed_challenging;
    },
    noticeItems(state) {
      return state.noticeItems;
    },
    noticeItem(state) {
      return state.noticeItem;
    },
  },
  mutations: {
    setIsLogined(state, isLogin) {
      state.isLogin = isLogin;
    },
    setMemberInfo(state, memberInfo) {
      state.isLogin = true;
      state.memberInfo = memberInfo;
    },
    logout(state) {
      state.isLogin = false;
      state.memberInfo = null;
    },
    setFeeds(state, payload) {
      state.feeds = payload;
    },
    setTeamFeeds(state, payload) {
      state.teamFeeds = payload;
    },
    setComments(state, payload) {
      state.comments = payload;
    },
    SET_WHOLECHALLENGE(state, whole_projects) {
      state.whole_projects = whole_projects;
    },
    SET_TEAMCHALLENGE(state, data) {
      state.team_challenges.length = 0;
      data.forEach(element => {
        state.team_challenges.push({ value: element, text: element });
      });
    },
    SET_TEAMCHALLENGER(state, data) {
      state.team_challenging.length = 0;
      data.forEach(element => {
        state.team_challenging.push({ value: element });
      });
    },
    SET_WHOLETEAMCHALLENGE(state, data) {
      state.whole_challenges.length = 0;
      data.forEach(element => {
        state.whole_challenges.push({ value: element, text: element });
      });
    },
    SET_MY_TEAMLIST(state, data) {
      state.myTeamList.length = 0;
      data.forEach(element => {
        state.myTeamList.push({ value: element, text: element });
      });
    },
    SET_MANAGING_TEAM(state, data) {
      state.managingTeam = data;
    },
    setTeamLists(state, payload) {
      state.teamLists = payload;
    },
    setBooks(state, payload) {
      state.books = payload;
    },
    setBook(state, payload) {
      state.book = payload;
    },
    SET_REQUESTS(state, payload) {
      state.joinRequests = payload;
    },
    SET_SELECT_TEAM(state, data) {
      state.selectTeam = data;
    },
    SET_COMMENTS(state, payload) {
      state.comments = payload;
    },
    SET_FEEDID(state, payload) {
      state.feedid = payload;
    },
    SET_ENTIRECHALLEGE(state, payload) {
      state.entire_challenge = payload;
    },
    SET_TEAMCHALLENGING(state, data) {
      state.feed_challenging.length = 0;
      data.forEach(element => {
        state.feed_challenging.push({ value: element, text: element });
      });
    },
    setNoticeItems(state, payload) {
      state.noticeItems = payload;
    },
    setNoticeItem(state, payload) {
      state.noticeItem = payload;
    },
  },
  actions: {
    async GET_MEMBER_INFO({ commit }, token) {
      let decode = jwt_decode(token);
      await findById(
        decode.memberEmail,
        response => {
          if (response.data.message === "success") {
            commit("setMemberInfo", response.data.memberInfo);
          } else {
            console.log("유저 정보 없음!!");
          }
        },
        error => {
          console.log(error);
        }
      );
    },

    LOGOUT({ commit }) {
      commit("logout");
      this.state.myTeamList.length = 0;
      localStorage.removeItem("access-token");
    },
    SET_SELECT_TEAM(context, payload) {
      this.state.selectTeam = {};
      context.commit("SET_SELECT_TEAM", payload);
    },


    GET_TEAMCHALLENGE_INFO(context, payload) {
      http
        .get("/my_teamchallenge_list/" + "{member_id}?member_id=" + payload)
        .then(data => {
          context.commit("SET_TEAMCHALLENGE", data.data.object);
        })
        .catch(() => {});
    },

    GET_TEAMCHALLENGER_INFO(context, payload) {
      http
        .get(
          "/my_teamchallenger_list?" +
            "member_id=" +
            payload.memberId +
            "&team_id=" +
            payload.teamId
        )
        .then(response => {
          context.commit("SET_TEAMCHALLENGER", response.data.object);
        })
        .catch(() => {});
    },

    GET_TEAMCHALLENGEING_INFO(context, payload) {
      http
        .get(
          "/my_teamchalleging_list?" +"member_id=" +payload
        )
        .then(response => {
          console.log(response);
          context.commit("SET_TEAMCHALLENGING", response.data.object);
        })
        .catch(() => {});
    },

    async GET_WHOLECHALLENGE_INFO({ commit }) {
      await http
        .get("/challenge/whole_challenge_list")
        .then(data => {
          commit("SET_WHOLETEAMCHALLENGE", data.data);
        })
        .catch(() => {
          //alert("에러발생!");
        });
    },

    async GET_MY_TEAM_INFO({ commit, dispatch, state }, payload) {
      await http
        .get("/team/my_team_list/" + payload)
        .then(data => {
          commit("SET_MY_TEAMLIST", data.data.object);

          data.data.object.forEach(element => {
            let managerId = element.member.memberId;
            if (managerId === state.memberInfo.memberId) {
                commit("SET_MANAGING_TEAM", element);
                dispatch("getRequests", element.teamId);
            }
          });
        })
        .catch(() => {
          //alert("에러발생!");
        });
    },

    getTeamLists({ commit }) {
      const instance = createInstance();
      instance
        .get("/team")
        .then(response => {
          commit("setTeamLists", response.data.object);
        })
        .catch(() => {
          console.log("에러");
        });
    },
    getFeeds({ commit }) {
      const instance = createInstance();
      instance
        .get("/feed")
        .then(response => {
          console.log(response);
          commit("setFeeds", response.data.object);
        })
        .catch(() => {
          //alert("에러발생");
        });
    },
    getMyTeamFeeds({ commit }, teamId) {
      const instance = createInstance();
      instance
        .get("/feed/team/" + teamId)
        .then(response => {
          console.log(response);
          commit("setTeamFeeds", response.data.object);
        })
        .catch(() => {
          //alert("에러발생");
        });
    },
    getComments(context) {
      http
        .get("")
        .then(({ data }) => {
          context.commit("setComments", data);
        })
        .catch(() => {
          //alert("에러발생");
        });
    },
    getRequests(context, teamId) {
      http
        .get("/request/" + teamId)
        .then(({ data }) => {
          console.log("request send");
          context.commit("SET_REQUESTS", data);
        })
        .catch(() => {
          console.log("index.js getrequest error");
        });
    },
    GET_COMMENTS(context, payload) {
      http
        .get("/comment/" + payload)
        .then(data => {
          context.commit("SET_COMMENTS", data.data.object);
        })
        .catch(() => {});
    },
    SET_FEEDID(context, payload) {
      this.state.feedid = {};
      context.commit("SET_FEEDID", payload);
    },
    GET_ENTIRECHALLENGE_INFO(context, memberId) {
      http
        .get("/member/challenge/" + memberId)
        .then(({ data }) => {
          context.commit("SET_ENTIRECHALLEGE", data);
        })
        .catch(() => {
          console.log("에러발생");
        });
    },
    getNoticeItems({ commit },teamId) {
      http.get("/board/list/"+teamId).then(({ data }) => {
        commit("setNoticeItems", data.object);
      });
    },
    getNoticeItem({ commit }, boardid) {
      http.get("/board"+boardid).then(({ data }) => {
        //console.log("getItem : " + data)
        commit("setNoticeItem", data.object);
      });
    },
  }
});
