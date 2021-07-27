import Vue from "vue";
import Vuex from "vuex";

// import router from "../router";
import jwt_decode from "jwt-decode";
import { findById } from "@/api/user.js";
import { createInstance } from "../api/teamindex";

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        isLogin: false, // 로그인 여부
        memberInfo: null,
        teamLists: [], // 팀 정보
        groupInfo: [], // 각 그룹 정보
        feeds: [],
        comments: []
    },
    getters: {
        feeds(state) {
            return state.feeds;
        },
        comments(state) {
            return state.comments;
        },
        teamLists(state) {
            return state.teamLists;
        },

        // groupInfo(state) {
        //     return state.groupInfo;
        // }
    },
    mutations: {
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
        setComments(state, payload) {
            state.comments = payload;
        },
        setTeamLists(state, payload) {
            state.teamLists = payload;
        }
    },
    actions: {
        async GET_MEMBER_INFO({ commit }, token) {
            let decode = jwt_decode(token);
                console.log(decode);
            await findById(
            decode.memberEmail,
            (response) => {
                if (response.data.message === "success") {
                commit("setMemberInfo", response.data.memberInfo);
                // router.push("/");
                // router.go(router.currentRoute);
                } else {
                    console.log("유저 정보 없음!!");
                }
            },
            (error) => {
                console.log(error);
            }
        );
        },

        LOGOUT({ commit }) {
            commit("logout");
            localStorage.removeItem("access-token");
            },
        getTeamLists({ commit }) {
            const instance = createInstance();
            instance
            .get("/team")
            .then((response) => {
                console.log(response.data.object);
                commit("setTeamLists", response.data.object);
            })
            .catch(() => {
                console.log("에러");
            });
            },
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
