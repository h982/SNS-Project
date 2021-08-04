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
        team_challenging:[], //내가 진행중인 챌린지
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
        team_challenging(state) {
            return state.team_challenging;
        }
    },
    mutations: {
        setIsLogined(state, isLogin) {
            state.isLogin = isLogin;
        },
        setMemberInfo(state, memberInfo) {
            state.isLogin = true;
            state.memberInfo = memberInfo;
            console.log(memberInfo);
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
        SET_WHOLECHALLENGE(state, whole_projects) {
            state.whole_projects = whole_projects;
        },
        SET_TEAMCHALLENGE(state, data) {
            state.team_challenges.length = 0;
            console.log(data);
            data.forEach(element => {
                state.team_challenges.push({ value: element, text: element });
            });
        },
        SET_TEAMCHALLENGER(state, data) {
            state.team_challenging.length = 0;
            console.log(data);
            data.forEach(element => {
                state.team_challenging.push({ value: element});
            });
        },
        SET_WHOLETEAMCHALLENGE(state, data) {
            state.whole_challenges.length = 0;
            console.log(data);
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
            this.state.myTeamList.length = 0;
            localStorage.removeItem("access-token");
        },
        SET_SELECT_TEAM(context, payload) {
            this.state.selectTeam = {};
            context.commit("SET_SELECT_TEAM",payload);
        },

        getBooks(context) {
            http
            .get("/book")
            .then(({ data }) => {
                context.commit("setBooks", data);
            })
            .catch(() => {
                //alert("에러발생!");
            });
        },

        getBook(context, payload) {
            http.get("/book/"+payload).then(({ data }) => {
            context.commit("setBook", data);
            });
        },

        GET_TEAMCHALLENGE_INFO( context, payload) {
            http
                .get("/my_teamchallenge_list/"+"{member_id}?member_id="+payload)
                .then((data) => {
                    console.log(data.data.object);
                    context.commit("SET_TEAMCHALLENGE", data.data.object);
                })
                .catch(() => {
                    
                });
        },

        GET_TEAMCHALLENGER_INFO( context, payload) {
            http
                .get("/my_teamchallenger_list?"+"member_id="+payload)
                .then((response) => {
                    console.log(response.data.object);
                    context.commit("SET_TEAMCHALLENGER", response.data.object);
                })
                .catch(() => {
                    
                });
        },

        async GET_WHOLECHALLENGE_INFO({ commit }) {
            await http
                .get("/challenge/whole_challenge_list")
                .then((data) => {
                    console.log(data.data);
                    commit("SET_WHOLETEAMCHALLENGE", data.data);
                })
                .catch(() => {
                    //alert("에러발생!");
                });
        },

        async GET_MY_TEAM_INFO({ commit, dispatch, state },payload) {
            await http
                .get("/team/my_team_list/" + payload)
                .then((data) => {
                    commit("SET_MY_TEAMLIST", data.data.object);

                    data.data.object.forEach(element => {
                        let managerId = element.member.memberId;
                        if (managerId === state.memberInfo.memberId) {
                            commit('SET_MANAGING_TEAM', element);
                            dispatch('getRequests', element.teamId);
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
            .then((response) => {
                console.log(response.data.object);
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
                .then((response) => {
                    console.log(response);
                    commit("setFeeds", response.data.object);
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
                console.log("index.js getrequest error")
            });
        },  
    }
});
