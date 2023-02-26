<template>
  <div class="wrapper">
    <div class="content">
      <Timeline :value="posts" align="alternate" class="customized-timeline">
        <template #marker="slotProps">
        <span class="custom-marker shadow-2" :style="{
          background: 'url(\'' + slotProps.item.icon + '\') center no-repeat',
          backgroundSize: '70px',
          padding: '22px',
          cursor: 'pointer'
        }" @click="getProfile(slotProps.item.author)">
        </span>
        </template>
        <template #content="slotProps">
          <Post :item="slotProps.item" :is-owner="false" v-animate-onscroll="{ down: 'animated zoomInUp' }"/>
        </template>
      </Timeline>
      <br>
    </div>
  </div>
</template>

<script>
import Timeline from 'primevue/timeline'
import Post from "@/components/Post.vue"
import { mapGetters } from "vuex"

export default {
  name: "home-view",
  computed: {
    ...mapGetters([ 'getPosts' ]),
    posts() {
      return this.getPosts
    }
  },
  components: {
    Timeline,
    Post
  },
  methods: {
    getProfile(name) {
      document.location.href= `/${name}`
    }
  }
}
</script>

<style lang="scss" scoped>

  .wrapper {
    padding-top: 100px;
    background-color: #fcfcfc;
    min-height: 100vh;
  }

  .content {
    width: 90%;
    margin-left: 5%;
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

  ::v-deep(.p-timeline-event-content),
  ::v-deep(.p-timeline-event-opposite) {
    line-height: 1;
  }

  @media screen and (max-width: 960px) {

    .content {
      width: 100%;
      margin-left: 0;
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
  }
</style>