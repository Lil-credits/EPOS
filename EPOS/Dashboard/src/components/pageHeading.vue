<template>
  <v-container class="page-header">
    <v-row align="center" justify="space-between">
      <v-col cols="auto">
        <v-icon class="backButton" @click="goBack">mdi-arrow-left</v-icon>
      </v-col>
      <v-col cols="auto">
        <div v-if="title != null">
          <v-breadcrumbs class="title"
                      :items="['HU', 'IDE', 'TBK', title]"                      
                      divider="/"/>
        </div>
        <div v-else>
          <v-breadcrumbs class="title"
                      :items="['HU', 'IDE', 'TBK']"
                      divider="/"/>
        </div>
      </v-col>
      <v-col cols="auto">
        <v-avatar size="40">
          <img :src="require('../assets/profile.jpg')" alt="User Avatar" style="max-width: 100%; max-height: 100%;" @click="$router.push('/')">
        </v-avatar>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: 'PageHeader',
  props: {
    title: {
      type: String,
      required: false,
    },
  },
  methods: {
    goBack() {
      this.$router.go(-1);
    },
  },
};
</script>

<style scoped>
.page-header {
  padding: 10px;
}

.backButton {
  cursor: pointer;
  color: black;
}

.title>ul>li {
  text-transform: capitalize;
}
</style>
