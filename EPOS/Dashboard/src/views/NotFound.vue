<template>
  <v-container class="text-center">
    <v-row>
      <v-col>
        <h1>404 - Page Not Found</h1>
        <p>Sorry, the page you are looking for does not exist.</p>
        <v-btn color="primary" @click="goHome">Go Home</v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: 'NotFound',
  methods: {
    goHome() {
      this.$router.push('/');
    }
  }
};
</script>

<style scoped>
h1 {
  margin-top: 20px;
  font-size: 3rem;
}
p {
  font-size: 1.5rem;
}
</style>
