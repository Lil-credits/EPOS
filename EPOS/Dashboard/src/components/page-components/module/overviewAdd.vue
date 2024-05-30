<template>
    <div class="circle-container">
      <v-btn class="circle-button module" fab small @click="$router.push('/modules/create')">
        <v-icon>mdi-plus</v-icon>
      </v-btn>
      <div class="circle-text">Create your own</div>
    </div>
  </template>
  
  <script>
  export default {
    name: 'overviewItem',
  };
  </script>
  
  <style scoped>
  .circle-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    text-align: center;
  }
  .circle-button {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 100px;
    height: 100px;
    border-radius: 50%;
    font-size: 14px;
    font-weight: bold;
    text-align: center;
    cursor: pointer;
    position: relative;
    background-color: #f5f5f5;
  }
  .circle-image {
    width: 100%;
    height: 100%;
    border-radius: 50%;
    object-fit: cover;
  }
  .circle-text {
    margin-top: 10px;
    font-size: 14px;
    text-align: center;
    max-width: 100px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }
  </style>
  