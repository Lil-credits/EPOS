<template>
  <v-container>
    <v-row align="center">
      <!-- Loop through educationModules and create CircleModule components -->
      <v-col cols="4" md="6" v-for="module in educationModules" :key="module.id">
        <CircleModule :title="module.name" :image="module.imageUrl" :moduleId="module.id" />
      </v-col>
      <v-col :key="'create-own'">
        <div class="circle-container">
          <v-btn class="circle-button create-own" fab small @click="$router.push('/modules/create')">
            <v-icon>mdi-plus</v-icon>
          </v-btn>
          <div class="circle-text">Create your own</div>
        </div>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from 'axios';
import { ref, onMounted } from 'vue'; // Use ref to create reactive variables
import CircleModule from '../components/module-components/CircleModule.vue';

export default {
  name: 'ModuleOverview', // Corrected component name
  components: {
    CircleModule
  },
  setup() {
    const educationModules = ref([]); // Reactive variable to store module data

    const fetchData = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/v1/education-modules");
        educationModules.value = response.data.educationModules; // Update the reactive variable
        console.log(response.data);
      } catch {
        console.log("Can't get data");
      }
    };

    onMounted(fetchData); // Fetch data when the component is mounted

    const goBack = () => {
      this.$router.go(-1); // Logic to go back to the previous page
    };

    return {
      educationModules,
      goBack,
    };
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
