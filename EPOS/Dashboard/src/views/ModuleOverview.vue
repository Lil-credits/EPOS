<template>
  <v-container>
    <v-row align="center" justify="space-around" class="overview">
      <v-col 
        v-for="module in educationModules" 
        :key="module.id" 
        class="module-item"
      >
        <overviewItem 
          :title="module.name" 
          :image="module.imageUrl" 
          :moduleId="module.id"
        />
      </v-col>
      <v-col cols="12" sm="6" md="4" lg="4" class="module-item">
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
.overview {
  margin-top: 20px;
}
.module-item {
  margin-bottom: 20px;
}
</style>
