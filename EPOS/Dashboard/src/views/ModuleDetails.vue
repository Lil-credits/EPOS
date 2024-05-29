<template>
    <div>
        <detailHeading :title="title" :image="imageUrl" />
        <detailAttributes :attributes="attributes" />
        <detailDescription :description="description" />
        <detailList listTitle="Skills" :list="skills" />
        <detailList listTitle="Required Achievements" :list="requiredAchievements" />
    </div>
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
        title.value = data.name;
        imageUrl.value = data.imageUrl;
        attributes.value = data.attributes;
        description.value = data.description;
        skills.value = data.skills;
        requiredAchievements.value = data.requiredAchievements;
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
.info-section {
  background-color: #f0f0f0; /* Light grey background */
  border-radius: 10px;
  margin-bottom: 15px;
  padding: 15px;
}
</style>
