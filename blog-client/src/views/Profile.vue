<template>
  <div class="wrapper">

    <Card class="profile-card">
      <template #content>
        <h3>Email</h3>
        <span v-if="!editStatus">
          {{ email }}
        </span>
        <span v-else>
          <InputText type="text" v-model="email" :style="{
            width: '100%'
          }"/>
        </span>

        <h3>Posts</h3>
        {{ posts.length }}

        <h3>About me</h3>
        <span v-if="!editStatus">
          {{ about }}
        </span>
        <span v-else>
          <Textarea v-model="about" :autoResize="true" rows="5" :style="{
            width: '100%'
          }"/>
        </span>

        <br><br>
        <Button label="Edit profile" @click="editStatus = true" v-if="!editStatus"/>
        <Button label="Save changes" @click="saveChanges" v-else/>
      </template>
      <template #title>
        <div class="info">
          <Avatar class="mr-2" size="large" :style="{
          background: 'url(\'https://images.unsplash.com/photo-1675475794189-cf76fe3f58cf?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80\') center no-repeat',
          color: '#ffffff',
          height: '140px',
          width: '140px',
          backgroundSize: '200px',
          border: '3px solid #ffffff',
          marginLeft: 'auto',
          marginRight: 'auto',
          display: 'block',
          marginBottom: '10px'
      }" shape="circle" />
          {{ username }}
        </div>
      </template>
    </Card>

    <div class="content">
      <div v-for="item in posts" :key="item">
        <Post :item="item" :is-owner="true" />
        <br>
      </div>
    </div>

  </div>
</template>

<script>
import Card from 'primevue/card'
import Avatar from 'primevue/avatar'
import Button from "primevue/button"
import InputText from 'primevue/inputtext'
import Textarea from 'primevue/textarea'
import Post from "@/components/Post.vue"
import {mapGetters} from "vuex";

export default {
  name: "profile-view",
  data() {
    return {
      ...mapGetters([ 'getUser' ]),
      editStatus: false,
    }
  },
  computed: {
    username() {
      return this.getUser().username
    },
    email() {
      return this.getUser().email
    },
    about() {
      return this.getUser().about
    },
    posts() {
      return this.getUser().userPosts
    },
  },
  methods: {
    saveChanges() {
      this.editStatus = false
      this.$toast.add({severity:'success', summary: 'Successfully updated', detail:'Your profile has been updated', life: 6000})
    }
  },
  components: {
    Card,
    Avatar,
    Button,
    InputText,
    Textarea,
    Post
  }
}
</script>

<style lang="scss" scoped>

  .wrapper {
    padding-top: 70px;
    padding-bottom: 20px;
    background: var(--primary-color);
    min-height: 100vh;
  }

  .profile-card {
    display: block;
    max-width: 600px;
    min-height: 460px;
    margin: 50px auto 70px;
    padding-left: 50px;
    padding-right: 50px;
  }

  .info {
    text-align: center;
  }

  .content {
    max-width: 600px;
    display: block;
    margin: 0 auto 0;
  }

  ::v-deep(.customized-timeline) {
    .p-timeline-event:nth-child(even) {
      flex-direction: row !important;

      .p-timeline-event-content {
        text-align: left !important;
      }
    }

    .p-timeline-event-opposite {
      flex: 0;
    }

    .p-card {
      margin-top: 1rem;
    }
  }

  .p-chip.custom-chip {
    background: var(--primary-color);
    color: var(--primary-color-text);
  }

  .custom-marker {
    display: flex;
    width: 2rem;
    height: 2rem;
    align-items: center;
    justify-content: center;
    color: #ffffff;
    border-radius: 50%;
    z-index: 1;
  }
</style>