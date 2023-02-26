<template>
  <i class="pi pi-search" />
  <InputText type="text" v-model="searchValue" placeholder="Search author" :style="{
          width: '180px',
          marginRight: '5px'
        }" @keydown="toggleSearch" />
  <OverlayPanel ref="op" appendTo="body" id="overlay_panel" style="width: 450px" :breakpoints="{'960px': '75vw'}">
    <DataTable :value="posts" :scrollable="true" scrollHeight="350px" :lazy="true" :row-style="{ cursor: 'pointer' }">
      <Column header="Results:">
        <template #body="slotProps">
          <div @click="getProfile(slotProps.data.author)">
            <Avatar class="mr-2" size="large" :style="{
                  background: 'url(\'' + slotProps.data.icon + '\') center no-repeat',
                  color: '#ffffff',
                  height: '50px',
                  width: '50px',
                  backgroundSize: '70px',
                  border: '3px solid #ffffff',
                  float: 'left'
                }" shape="circle"/>
            <p :style="{ width: '300px', textAlign: 'center' }">
              {{ slotProps.data.author + '123@gmail.com' }}
            </p>
          </div>
        </template>
      </Column>
    </DataTable>
  </OverlayPanel>
</template>

<script>
import OverlayPanel from "primevue/overlaypanel"
import DataTable from "primevue/datatable"
import Column from "primevue/column"
import Avatar from "primevue/avatar"
import posts from '../../posts.json'
import InputText from "primevue/inputtext"

export default {
  name: "search-element",
  data() {
    return {
      searchValue: '',
      posts
    }
  },
  methods: {
    getProfile(name) {
      document.location.href= `/${name}`
    },
    toggleSearch(event) {
      this.$refs.op.toggle(event)
    },
  },
  components: {
    OverlayPanel,
    DataTable,
    Column,
    Avatar,
    InputText
  }
}
</script>