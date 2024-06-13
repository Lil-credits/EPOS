<template>
  <pageHeading title="DETAIL" />
  <container class="detailcontainer">
    <div class="informationbox">
      <div class="image-container">
      <detailHeading :title="title" :imageUrl="imageUrl" />
      </div>
      <detailAttributes :attributes="attributes" />
      <div class="info-section">
      <detailDescription :description="description" />
      </div>
      <div class="info-section">
      <detailList listTitle="Skills" :list="skills" />
      </div>
      <div class="info-sectionalt">
      <detailList listTitle="Required Achievements" :list="requiredAchievements" />
      </div>
    </div>
  </container>
  <div class="StudentsButton">
      <v-row align="center" justify="center">
        <v-col cols="auto">
        <v-btn @click="goToAwardedStudents" class="actionButton">Awarded students</v-btn>
        </v-col>
        <v-col cols="auto">
        <v-btn @click="goToAwardStudent" class="actionButton">Issue credential</v-btn>
        </v-col>
      </v-row>
      </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';

import detailHeading from '@/components/page-components/module/detailHeading.vue';
import detailAttributes from '@/components/page-components/module/detailAttributes.vue';
import detailDescription from '@/components/page-components/module/detailDescription.vue';
import detailList from '@/components/page-components/module/detailList.vue';
import pageHeading from '@/components/pageHeading.vue';
import api from '@/api/api.js';

export default {
  name: 'ModuleDetails',
  components: {
    detailHeading,
    detailAttributes,
    detailDescription,
    detailList,
    pageHeading,
  },
  setup() {
    const route = useRoute();
    const router = useRouter();
    const moduleId = route.params.id;

    const title = ref('');
    const imageUrl = ref('');
    const attributes = ref({});
    const description = ref('');
    const skills = ref([]);
    const requiredAchievements = ref([]);

    const goToAwardedStudents = () => {
      router.push(`/modules/${moduleId}/issued`);
    };

    const goToAwardStudent = () => {
      router.push(`/modules/${moduleId}/issue`);
    };

    onMounted(async () => {
      try {
        const call = await api.getModule(moduleId);
        const data = call.data;
        title.value = data.versions[0].name;
        imageUrl.value = data.versions[0].imageData;
        attributes.value = data.versions[0].baseCredential.attributes;
        description.value = data.versions[0].baseCredential.description;
        skills.value = data.versions[0].baseCredential.skills;
        requiredAchievements.value = data.versions[0].baseCredential.requiredAchievements;
      } catch (error) {
        console.error("Error fetching module data:", error);
      }
    });

    return {
      title,
      imageUrl,
      attributes,
      description,
      skills,
      requiredAchievements,
      goToAwardedStudents,
      goToAwardStudent
    };
  }
};
</script>

<style scoped>

.informationbox {
  width: 100%;
  margin: 0 20px;
}

.info-section {
  background-color: #f0f0f0;
  border-radius: 10px;
  margin-bottom: 15px;
  padding: 15px;
}

.image-container {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

.detailcontainer {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0 20px;
}

.info-sectionalt {
  color: white;
  background-color: #00A4E7;
  border-radius: 10px;
  margin-bottom: 15px;
  padding: 15px;
}

.info-section h2 {
  text-align: center;
}
.actionButton {
  background-color: #00A4E7;
  color: white;
  width: 185px;
}

.StudentsButton {
  
  background: linear-gradient(to bottom, rgba(128, 128, 128, 0.5), rgba(128, 128, 128, 0.5));
  position: fixed;
  bottom: 0px;
width: 100%;
  font-size: 60px;
}

</style>
