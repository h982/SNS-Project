import Vue from "vue";
import Vuex from "vuex";

// import router from "../router";
import jwt_decode from "jwt-decode";
import { findById } from "@/api/user.js";

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        isLogin: false, // 로그인 여부
        memberInfo: null,
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
        setComments(state, payload) {
            state.comments = payload;
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
