<template>
    <v-container>
      <v-row justify="center">
        <v-col cols="12" sm="8" md="6">
          <!-- Image uploader circle -->
          <v-row justify="center">
            <v-avatar size="200px" class="blue lighten-2">
              <v-icon size="100">mdi-plus</v-icon>
            </v-avatar>
          </v-row>
  
          <!-- Credential title input -->
          <v-text-field
            outlined
            dense
            single-line
            label="Your Credential Title"
            class="red lighten-1 white--text"
          ></v-text-field>
  
          <!-- Get Started button -->
          <v-btn
            large
            color="blue"
            class="white--text"
            @click="goToNextStep"
          >
            Get Started
          </v-btn>
        </v-col>
      </v-row>
    </v-container>
  </template>
  
  <script>
  export default {
    methods: {
      goToNextStep() {
        // Implement logic to save data and go to the next step
        // For now, let's just navigate to the next step
        this.$router.push('/step-2');
      },
    },
  };
  </script>
  
  <style scoped>
    .blue--text.text--lighten-2 {
      color: #64B5F6 !important;
    }
    .blue.lighten-2 {
      background-color: #90CAF9 !important;
    }
    .red--text.text--lighten-1 {
      color: #FFCDD2 !important;
    }
    .red.lighten-1 {
      background-color: #EF9A9A !important;
    }
  </style>
  