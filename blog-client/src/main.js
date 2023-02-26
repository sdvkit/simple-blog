import { createApp } from 'vue'
import App from './App.vue'

import router from "@/router"
import index from "@/store"

import PrimeVue from 'primevue/config'
import ToastService from 'primevue/toastservice'
import ConfirmationService from 'primevue/confirmationservice'
import 'primevue/resources/themes/saga-blue/theme.css'
import 'primevue/resources/primevue.min.css'
import 'primeicons/primeicons.css'

import 'boxicons'
import VueAnimateOnScroll from 'vue3-animate-onscroll'

createApp(App)
    .use(router)
    .use(index)
    .use(VueAnimateOnScroll)
    .use(PrimeVue)
    .use(ToastService)
    .use(ConfirmationService)
    .mount('#app')
