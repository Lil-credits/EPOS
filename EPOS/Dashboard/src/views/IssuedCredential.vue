<template>
    <PageHeading title="STUDENTS"/>
    <div class="container">
      <div class="image-container">
        <detailHeading :title="title" :imageUrl="imageUrl" />
      </div>
      <v-card color="#00bfff">
        <v-card-title class="white--text font-weight-medium d-flex justify-center">Students with this Micro-Credentials</v-card-title>
        <v-expansion-panels
          class="px-2 pb-2"
          v-model="panel"
          :readonly="readonly"
          multiple
        >
          <v-expansion-panel>
            <v-expansion-panel-title>2024/2025</v-expansion-panel-title>
            <v-expansion-panel-text>
              <v-list lines="one">
                <v-list-item
                v-for="(student, index) in students"
                :key="index"
                >
                <v-list-item-content>
                {{ student.name }}
                </v-list-item-content>
                </v-list-item>
                      </v-list>
            </v-expansion-panel-text>
          </v-expansion-panel>
  
          <v-expansion-panel>
            <v-expansion-panel-title>2023/2024</v-expansion-panel-title>
            <v-expansion-panel-text>
              Some content
            </v-expansion-panel-text>
          </v-expansion-panel>
  
          <v-expansion-panel>
            <v-expansion-panel-title>2022/2023</v-expansion-panel-title>
            <v-expansion-panel-text>
              Some content
            </v-expansion-panel-text>
          </v-expansion-panel>
        </v-expansion-panels>
      </v-card>
    </div>
  </template>
  
  <script>
  import { ref, onMounted } from 'vue';
  import { useRoute } from 'vue-router';
  import api from '@/api/api.js';
  
  import PageHeading from '@/components/pageHeading.vue';
  import detailHeading from '@/components/page-components/module/detailHeading.vue';
  
  export default {
    name: 'ComponentTest',
    components: {
      PageHeading,
      detailHeading,
    },
    setup() {
      const route = useRoute();
      const moduleId = route.params.id;
  
      const title = ref('');
      const imageUrl = ref('');
      const students = ref([]);
  
      onMounted(async () => {
        try {
          const moduleCall = await api.getModule(moduleId);
          const moduleData = moduleCall.data;
          title.value = moduleData.versions[0].name;
          imageUrl.value = moduleData.versions[0].imageData;
  
          const studentsCall = await fetch(`http://localhost:8080/api/v1/education-modules/${moduleId}/versions/${moduleId}/issued-credentials`);
          const studentsData = await studentsCall.json();
          console.log(studentsData);
          students.value = studentsData.map(credential => ({ name: credential.name }));
        } catch (error) {
          console.error("Error fetching data:", error);
        }
      });
  
      return {
        title,
        imageUrl,
        students,
      };
    },
    data: () => ({
      panel: [0],
      readonly: false,
    }),
  };
  </script>
  
  <style scoped>
  .container {
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
  }
  
  .image-container {
    display: flex;
    justify-content: center;
    margin-bottom: 20px;
  }
  </style>
  