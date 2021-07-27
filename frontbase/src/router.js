import Vue from "vue";
import Router from "vue-router";
import Home from "./views/Home.vue";
import Meta from 'vue-meta'
import Login from "@/views/user/Login.vue";


Vue.use(Router);
Vue.use(Meta);

export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      name: "home",
      component: Home
    },
    {
      path: "/signup",
      name: "signup",
      component: () =>
        import( /* webpackChunkName: "about" */ "./views/SignUp.vue")
    },
    {
      path: "/team",
      name: "team",
      component: () =>
        import( /* webpackChunkName: "about" */ "./views/Team.vue")
    },
    {
      path: "/teamlist",
      name: "teamlist",
      component: () =>
        import( /* webpackChunkName: "about" */ "./views/Teamlist.vue")
    },
    {
      path: "/resume",
      name: "resume",
      component: () =>
        import(/* webpackChunkName: "about" */ "./views/Resume.vue")
    },
    {
      path: "/blog",
      name: "blog",
      component: () =>
        import(/* webpackChunkName: "about" */ "./views/Blog.vue")
    },
    {
      path: "/blog/:id",
      name: "blogPost",
      component: () =>
        import(/* webpackChunkName: "about" */ "./views/BlogPost.vue")
    },
    {
      path: "/services",
      name: "services",
      component: () =>
        import(/* webpackChunkName: "about" */ "./views/Services.vue")
    },
    {
      path: "/portfolio",
      name: "portfolio",
      component: () =>
        import(
          /* webpackChunkName: "about" */ "./views/Portfolio/Portfolio.vue"
        )
    },
    {
      path: "/portfolio/video",
      name: "videoPortfolio",
      component: () =>
        import(/* webpackChunkName: "about" */ "./views/Portfolio/Video.vue")
    },
    {
      path: "/portfolio/graphic",
      name: "graphicPortfolio",
      component: () =>
        import(/* webpackChunkName: "about" */ "./views/Portfolio/Graphic.vue")
    },
    {
      path: "/portfolio/web",
      name: "webPortfolio",
      component: () =>
        import(/* webpackChunkName: "about" */ "./views/Portfolio/Web.vue")
    },
    {
      path: "/group",
      name: "group",
      component: () =>
        import(/* webpackChunkName: "about" */ "./views/Group.vue")
    },
    {
      path: "/writefeed",
      name: "writefeed",
      component: () =>
        import(/* webpackChunkName: "about" */ "./views/Feed/WriteFeed.vue")
    },
    {
      path: "/feed",
      name: "feed",
      component: () =>
        import(/* webpackChunkName: "about" */ "./views/Feed/Feed.vue")
    },
    {
      path: "/comment",
      name: "comment",
      component: () =>
        import(/* webpackChunkName: "about" */ "./views/Feed/Comment.vue")
    },
    {
      path: "*",
      name: "Error",
      component: () =>
      import( /* webpackChunkName: "about" */ "./views/Error.vue")
    },
    {
      path: "/login",
      name: "login",
      component: Login
    },
  ]
});
