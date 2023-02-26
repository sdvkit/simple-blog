import { createRouter, createWebHashHistory } from "vue-router"
import Home from "@/views/Home.vue"
import Profile from "@/views/Profile.vue"
import Login from "@/views/Login.vue"
import Registration from "@/views/Registration.vue"

const routes = [
    {
        name: 'Home',
        path: '/',
        component: Home
    },
    {
        name: 'Profile',
        path: '/profile',
        component: Profile
    },
    {
        name: 'Login',
        path: '/login',
        component: Login
    },
    {
        name: 'Registration',
        path: '/registration',
        component: Registration
    }
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router