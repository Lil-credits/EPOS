<template>
  <div>
    <v-icon class="back-arrow" @click="goBack">mdi-arrow-left</v-icon>

    <div class="overview">
      <!-- Corrected attribute spacing and removed unnecessary comma -->
      <CircleModule title="Sales Engineer" image="" moduleId="4" />

      <div class="circle-container">
        <v-btn class="circle-button create-own" fab small @click="$router.push('/modules/create')">
          <v-icon>mdi-plus</v-icon>
        </v-btn>
        <div class="circle-text">Create your own</div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import CircleModule from '../components/module-components/CircleModule.vue';
import { onMounted } from 'vue';  // Corrected import

export default {
  name: 'ModuleOverview',  // Corrected component name
  components: {
    CircleModule
  },
  setup() {
    const fetchData = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/v1/education-modules");
        console.log(response);
      } catch {
        console.log("Can't get data");
      }
    };

    onMounted(fetchData);
  }
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
