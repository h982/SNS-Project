

import Login from './views/user/Login.vue'
import Join from './views/user/Join.vue'
import FeedMain from './views/feed/IndexFeed.vue'
import Components from './views/Components.vue'
import Main from './views/Main.vue'
import ModifyPassword from './views/user/ModifyPassword.vue'
import PageNotFound from './views/PageNotFound.vue'
import Error from './views/Error.vue'

export default [


    {
        path : '/',
        name : 'Login',
        component : Login
    },
    {
        path : '/user/join',
        name : 'Join',
        component : Join
    },
    {
        path : '/feed/main',
        name : 'FeedMain',
        component : FeedMain
    },
    {
        path : '/main',
        name : 'Main',
        component : Main
    },
    {
        path : '/modifypassword',
        name : 'ModifyPassword',
        component : ModifyPassword
    },
    {
        path : '/components',
        name : 'Components',
        component : Components
    },
    {
        path: "*",
        name: "PageNotFound",
        component: PageNotFound
    },
    {
        path: "/error",
        name: "Error",
        component: Error
    },
]
