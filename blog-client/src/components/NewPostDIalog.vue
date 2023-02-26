<template>
  <Dialog header="Create new post" :breakpoints="{'960px': '75vw', '640px': '90vw'}" :style="{ width: '40vw' }">
    <h3>Post title</h3>
    <InputText id="post-title" type="text" v-model="postTitle" :style="{ width: '100%' }" placeholder="Your Title" />
    <p>
      <InlineMessage v-if="showTitleError">Length of Title must be more than 5</InlineMessage>
    </p>

    <h3>Post description</h3>
    <Textarea v-model="postDescription" :autoResize="true" rows="5" :style="{ width: '100%' }" placeholder="Your Description" />
    <p>
      <InlineMessage v-if="showDescriptionError">Length of Description must be more than 5</InlineMessage>
    </p>

    <template #footer>
      <Button label="Save post" icon="pi pi-check" @click="savePost" autofocus />
    </template>
  </Dialog>
</template>

<script>
import Dialog from "primevue/dialog"
import Textarea from "primevue/textarea"
import InlineMessage from "primevue/inlinemessage"
import Button from "primevue/button"
import InputText from "primevue/inputtext"

export default {
  name: "new-post-dialog",
  data() {
    return {
      postTitle: '',
      postDescription: '',
      showTitleError: false,
      showDescriptionError: false,
    }
  },
  methods: {
    savePost() {
      if (this.postTitle.length < 5) {
        this.showTitleError = true
        return
      }
      this.showTitleError = false

      if (this.postDescription.length < 5) {
        this.showDescriptionError = true
        return
      }
      this.showDescriptionError = false

      this.displayModal = false
      this.postTitle = ''
      this.postDescription = ''
      this.$toast.add({severity:'success', summary: 'Successfully saved', detail:'New post has been created', life: 6000})
    }
  },
  components: {
    Dialog,
    Textarea,
    InlineMessage,
    Button,
    InputText
  }
}
</script>