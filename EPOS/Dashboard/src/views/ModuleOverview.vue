<template>
  <pageHeading />
  <v-container>
    <div class="empty" v-if="!educationModules.length">
      <h2>No Modules Yet</h2>
      <p>Create your own module by clicking the button below.</p>
    </div>
    <div v-else>
      <v-row wrap no-gutters>
        <v-col cols="3" class="text-center">
            <v-divider class="mt-4" />
        </v-col>
        <v-col cols="6" class="text-center text-h6">
            Modules overview
        </v-col>
        <v-col cols="3" class="text-center">
            <v-divider class="mt-4" />
        </v-col>
    </v-row>
      <v-row align="center" justify="space-around" class="overview">
        <v-col v-for="module in educationModules" :key="module.id" class="module-item">
          <overviewItem :title="module.versions[0].name" :image="module.versions[0].imageData" :moduleId="module.id" />
        </v-col>
      </v-row>
    </div>
    <div class="add">
      <v-btn prepend-icon="mdi-plus" size="x-large" class="addButton" color="#007bff"  @click="$router.push('/modules/create')">
        Create your own
      </v-btn>
    </div>
  </v-container>
</template>

<script>
import { ref, onMounted } from 'vue';
import overviewItem from '@/components/page-components/module/overviewItem.vue';
import pageHeading from '@/components/pageHeading.vue';
import api from '@/api/api.js';

export default {
  name: 'ModuleOverview',
  components: {
    overviewItem,
    pageHeading
  },
  setup() {
    const educationModules = ref([]);

    const fetchData = async () => {
      console.log('fetchData called');
      try {
        const response = await api.getModules();
        educationModules.value = response.data.educationModules;
      } catch (error) {
        console.log("Can't get data", error);
      }
    };

    onMounted(fetchData);

    const goBack = () => {
      this.$router.go(-1);
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
  bottom: 4em;
  left: 50%;
  transform: translateX(-50%);
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
