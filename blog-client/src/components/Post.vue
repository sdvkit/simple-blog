<template>
  <Card :id="`post-${item.id}`">
    <template #title>
      <Chip :label="item.author" icon="pi pi-check" class="mr-2 mb-2 custom-chip" />
      <br><br>
      {{ item.name }}
    </template>
    <template #subtitle>
      {{ item.date }}
    </template>
    <template #content>
      <p>{{ item.text }}</p>
      <Button v-if="isOwner" label="Delete" class="p-button-danger" icon="bx bxs-trash" @click="confirm()"></Button>
    </template>
  </Card>
</template>

<script>
import Button from "primevue/button"
import Card from "primevue/card"
import Chip from "primevue/chip"

export default {
  name: "post-card",
  props: [ 'item', 'isOwner' ],
  methods: {
    confirm() {
      this.$confirm.require({
        message: 'Do you want to delete this record?',
        header: 'Delete Confirmation',
        icon: 'pi pi-info-circle',
        acceptClass: 'p-button-danger',
        accept: () => {
          this.$toast.add({severity:'info', summary:'Confirmed', detail:'Record deleted', life: 3000})
        },
        reject: () => {}
      });
    },
  },
  components: {
    Button,
    Card,
    Chip
  },
}
</script>

<style scoped>
  .p-chip.custom-chip {
    background: var(--primary-color);
    color: var(--primary-color-text);
  }
</style>