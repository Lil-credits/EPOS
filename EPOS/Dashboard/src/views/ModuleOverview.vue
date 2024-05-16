<template>
  <div>
      <v-icon class="back-arrow" @click="goBack">mdi-arrow-left</v-icon>
      <div class="overview">
          <div class="circle-container">
          <v-btn class="circle-button module" fab small @click="$router.push('modules/1')">
          <img src="../assets/Picture1.png" class="circle-image" alt="Sales Engineer">
          </v-btn>
          <div class="circle-text">Sales Engineer</div>
          </div>
          <div class="circle-container">
          <v-btn class="circle-button module" fab small @click="$router.push('modules/2')">
          <img src="../assets/Picture15.png" class="circle-image" alt="Process Engineer">
          </v-btn>
          <div class="circle-text">Process Engineer</div>
          </div>
          <div class="circle-container">
          <v-btn class="circle-button create-own" fab small @click="$router.push('modules/create')">
          <v-icon>mdi-plus</v-icon>
          </v-btn>
          <div class="circle-text">Create your own</div>
          </div>
      </div>
      </div>

      <CircleModule/>
</template>

<script>
import CircleModule from "../components/module-components/CircleModule.vue";

export default {
  name: 'CircleModule',
    components: {
        CircleModule
    },

  name: 'ModuleOverview',
  methods: {
      goBack() {
          // Logic to go back to the previous page
      }
  },
  
};
</script>

<style scoped>
.back-arrow {
  margin: 10px;
  cursor: pointer;
  font-size: 24px;
}
.overview {
  display: flex;
  justify-content: space-around;
  align-items: center;
  margin-top: 20px;
}
.circle-container {
  display: flex;
  flex-direction: column;
  align-items: center;
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
}
.module {
  background-color: #FB0017;
  color: Black;
}

.create-own {
  background-color: #00A4E7;
  color: white;
}
.circle-image {
  width: 60%;
  height: 60%;
  border-radius: 50%;
}
.circle-text {
  margin-top: 10px;
  font-size: 14px;
  text-align: center;
}
</style>
