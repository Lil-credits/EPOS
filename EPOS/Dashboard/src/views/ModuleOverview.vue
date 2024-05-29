<template>
  <v-container>
    <v-row align="center" justify="space-around">
      <overviewItem 
        v-for="module in educationModules" 
        :key="module.id" 
        :title="module.name" 
        :image="module.imageUrl" 
        :moduleId="module.id" 
        :cols="4"
        :md="4"
      />
      <v-col cols="4" md="4">
        <overviewAdd />
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { ref, onMounted } from 'vue';
import overviewAdd from '@/components/page-components/module/overviewAdd.vue';
import overviewItem from '@/components/page-components/module/overviewItem.vue';

import api from '@/api/api.js'; // Ensure the correct path

export default {
  name: 'ModuleOverview',
  components: {
    overviewAdd,
    overviewItem,
  },
  setup() {
    const educationModules = ref([]); // Reactive variable to store module data

    const fetchData = async () => {
      console.log('fetchData called'); // Debugging statement
      try {
        const response = await api.getModules(); // Await the response of the API call
        educationModules.value = response.data.educationModules; // Set the data
      } catch (error) {
        console.log("Can't get data", error); // Debugging statement
      }
    };

    onMounted(() => {
      fetchData();
    });

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
  color: black;
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
