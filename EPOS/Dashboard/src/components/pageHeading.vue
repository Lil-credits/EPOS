<template>
<v-container class="page-header">
            <v-row align="center" justify="space-between">
                <v-col cols="auto">
                    <v-icon class="backButton" @click="goBack">mdi-arrow-left</v-icon>
          
        </v-col>
        <v-col cols="auto">
            <v-breadcrumbs class="title"
                :items="[{
                    text: 'HU',
                    disabled: false,
                    href: '/modules'
                }, {
                    text: title,
                    disabled: true
                }]"
                divider="/"/>
        </v-col>
        <v-col cols="auto">
          <v-avatar size="40">
            <img src="../assets/profile.jpg" alt="User Avatar">
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
        required: true,
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
    background-color: white;
    color: #ffffff;
    padding: 10px;
  }
  .title {
    color: white;
    text-align: center;
  }
    .backButton {
        cursor: pointer;
        color: black;
    }
  </style>
  