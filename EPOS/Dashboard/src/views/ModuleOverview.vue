<template>
  <v-container>
      <div class="empty" v-if="!educationModules.length">
        <h2>No Modules Yet</h2>
        <p>Create your own module by clicking the button below.</p>
      </div>
      <div>
        <v-row align="center" justify="space-around" class="overview">
        <v-col 
        v-for="module in educationModules" 
        :key="module.id" 
        class="module-item"
      >
        <overviewItem 
          :title="module.versions[0].name" 
          :image="module.versions[0].imageData" 
          :moduleId="module.id"
        />
      </v-col>
    </v-row>

      </div>
    <div class="add">
      <v-btn prepend-icon="mdi-plus"  size="x-large" class="addButton" @click="$router.push('/modules/create')">
        Create your own
      </v-btn>
    </div>
  </v-container>
</template>

<script>
import { ref, onMounted } from 'vue';
import overviewItem from '@/components/page-components/module/overviewItem.vue';

import api from '@/api/api.js'; // Ensure the correct path

export default {
  name: 'ModuleOverview',
  components: {
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

.add {
  position: fixed;
  bottom: 20px;
  left: 50%;
  transform: translateX(-50%);
  position: fixed;
  bottom: 4em;
}

.empty {
  text-align: center;
  margin-top: 10em;
}

.empty h2 {
  padding-bottom: 0;
  font-size: 3em;
  margin-bottom: 1em;
}

.empty p {
  margin-top: 0;
  font-size: 1.5em;
}
</style>
