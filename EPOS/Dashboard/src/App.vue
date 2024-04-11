<template>
  <v-app>
    <!-- App Bar -->
    <v-app-bar app color="indigo" dark>
      <v-toolbar-title>Form Wizard</v-toolbar-title>
    </v-app-bar>

    <!-- Main content -->
    <v-main>
      <v-container>
        <!-- Where your page components will be rendered -->
        <router-view></router-view>
      </v-container>
    </v-main>

    <!-- Footer -->
    <v-footer app color="indigo" dark>
      <span class="white--text">&copy; {{ new Date().getFullYear() }}</span>
    </v-footer>
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
