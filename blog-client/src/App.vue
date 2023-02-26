<template>
  <Toast />
  <ConfirmDialog></ConfirmDialog>
  <NewPostDialog v-model:visible="displayModal" />

  <Menubar :model="items" :style="{
    position: 'fixed',
    width: '100%',
    zIndex: '100'
  }">
    <template #start>
      <img alt="logo" src="./assets/logo.png" :style="{ height: '40px' }" class="mr-2">
    </template>
    <template #end>
      <span class="p-input-icon-left">
        <Search />
      </span>
      <Button label="" icon="bx bx-plus" iconPos="left" @click="openModal" />
    </template>
  </Menubar>

  <router-view />
</template>

<script>
import Menubar from 'primevue/menubar'
import Button from "primevue/button"
import Toast from 'primevue/toast'
import ConfirmDialog from 'primevue/confirmdialog'
import NewPostDialog from "@/components/NewPostDIalog.vue"
import Search from "@/components/Search.vue"

export default {
  name: 'App',
  data() {
    return {
      displayModal: false,
      items: [
        { label: 'Home', icon: 'bx bx-home-alt', to: '/' },
        { label: 'Profile', icon: 'bx bx-user', to: '/profile' },
        { label: 'Login', to: '/login' },
        { label: 'Registration', to: '/registration' }
      ]
    }
  },
  methods: {
    openModal() {
      this.displayModal = true
    }
  },
  components: {
    Menubar,
    Button,
    Toast,
    ConfirmDialog,
    NewPostDialog,
    Search
  }
}
</script>

<style>
  body {
    margin: 0 auto;
    padding: 0;
  }

  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    color: #2c3e50;
  }

  .p-menubar-start {
    margin-left: 25px;
    margin-right: 20px;
  }
</style>
