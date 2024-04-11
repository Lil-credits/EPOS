<template>
  <v-app>
    <!-- Main content -->
    <v-main>
      <v-container>
        <!-- Where your page components will be rendered -->
        <router-view></router-view>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: 'App'
  // If you have any global components, they can be registered here
};
</script>

<style>
/* Add global styles here */
</style>
