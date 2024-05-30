<template>
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
</template>

<script>
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router'; // Import useRoute from vue-router

import detailHeading from '@/components/page-components/module/detailHeading.vue';
import detailAttributes from '@/components/page-components/module/detailAttributes.vue';
import detailDescription from '@/components/page-components/module/detailDescription.vue';
import detailList from '@/components/page-components/module/detailList.vue'; // Ensure the correct path

import api from '@/api/api.js'; // Ensure the correct path

export default {
  name: 'ModuleDetails',
  components: {
    detailHeading,
    detailAttributes,
    detailDescription,
    detailList
  },
  setup() {
    const route = useRoute(); // Use the useRoute hook to get route information
    const moduleId = route.params.id; // Get the module ID from the route params

    const title = ref('');
    const imageUrl = ref('');
    const attributes = ref({});
    const description = ref('');
    const skills = ref([]);
    const requiredAchievements = ref([]);

    onMounted(async () => {
      try {
        const call = await api.getModule(moduleId); // Use the module ID from the route
        const data = call.data;
        console.log(data);
        console.log(data.versions)
        console.log(data.versions[0].name)
        console.log(data.versions[0].attributes)
        title.value = data.versions[0].name;
        imageUrl.value = data.versions[0].baseCredential.imageData;
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
      requiredAchievements
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
  background-color: #f0f0f0; /* Light grey background */
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
  padding: 0 20px; /* Add padding of 20px on both sides */
}

.info-sectionalt {
  color: white;
  background-color:  #00A4E7;
  border-radius: 10px;
  margin-bottom: 15px;
  padding: 15px;
}

.info-section h2 {
  text-align: center;
}
</style>
