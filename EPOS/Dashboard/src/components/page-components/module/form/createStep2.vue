<template>
    <v-form>
<div class="dropdown">
    <v-select
        :items="['Nederlands', 'Deutsch', 'English', 'Español', 'Zhōngguó ren']"
        label="Select Language"
        required
        variant="outlined"
        ></v-select>
    <v-select
        :items="[5, 10, 15, 20, 25, 30, 35, 40, 45, 50]"
        label="Select Study Load"
        required
        variant="outlined"
        ></v-select>
    <v-select
        :items="[5, 7, 8, 9, 10]"
        label="Select EQF Level"
        required
        variant="outlined"
        ></v-select>
</div>
    </v-form>
  </template>
  
  <script>
  
  </script>
  
  <style scoped>
  .dropdown {
    margin-top: 10px;
  }
  .v-select label{
    margin: 10px;
  }
  
  </style>
  