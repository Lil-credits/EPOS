<template>
  <v-app>
    <v-main>
      <pageHeading/>
      <router-view/>
    </v-main>
  </v-app>
</template>

<script>
import pageHeading from '@/components/pageHeading.vue';
export default {
  name: 'App',
  components: {
    pageHeading
  }
};
</script>

<style>
  v-app {
    background-color: white;
  }
</style>