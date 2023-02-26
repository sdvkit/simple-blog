import { createStore } from "vuex"
import posts from '../../posts.json'

const index = createStore({
    state () {
        return {
            posts,
            user: {
                username: 'John the Monkey',
                email: 'john.the.monkey@gmail.com',
                about: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. A aut eaque, est modi natus nesciunt sapiente sed similique soluta suscipit? Accusamus ea eaque eligendi eum expedita minus nihil recusandae temporibus!',
                userPosts: [ posts[0] ]
            }
        }
    },
    getters: {
        getPosts(state) {
            return state.posts
        },
        getUser(state) {
            return state.user
        }
    },
    mutations: {
        SET_USERNAME(state, username) {
            state.user.username = username
        },
        SET_EMAIL(state, email) {
            state.user.email = email
        },
        SET_ABOUT(state, about) {
            state.user.about = about
        }
    },
    actions: {
        async changeUser( { commit } , payload) {
            commit('SET_USERNAME', payload[0])
            commit('SET_EMAIL', payload[1])
            commit('SET_ABOUT', payload[2])
        }
    }
})

export default index