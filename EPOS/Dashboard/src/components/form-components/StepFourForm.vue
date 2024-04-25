<template>
  <div class="step-four">
    <div class="header">
      <div class="image-container">
        <img :src="stepData[1]['badgeImage']" alt="Course Badge" class="badge-image"/>
      </div>
      <div class="course-title">{{ stepData[1]['courseTitle'] }}</div>
    </div>

    <div class="skills-container">
      <h2>Skills</h2>
      <ul class="skills-list">
        <li v-for="(skill, index) in skills" :key="index">{{ skill }}</li>
      </ul>
      <input type="text" v-model="newSkill" placeholder="Type here more Skills..." @keyup.enter="addSkill">
    </div>

    <button class="next-button" @click="submitStep">Next</button>
  </div>
</template>

<script setup>
import { ref, defineProps, defineEmits } from 'vue';

defineProps({
  stepData: Object
});


const skills = ref([]); // Initial skills
const newSkill = ref('');

const emit = defineEmits(['update-step-data', 'go-back']);

const addSkill = () => {
  if (newSkill.value.trim) {
    skills.value.push(newSkill.value);
    newSkill.value = ''; // Reset the input field
  }
};

function submitStep() {
  if (skills.value.length > 0) {
    emit('update-step-data', { step: 4, data: skills });
  } else {
    alert('Please enter at least one skill.');
  }
}
</script>

<style scoped>
.step-four {
  max-width: 500px;
  margin: auto;
  text-align: center;
  display: flex;
  flex-direction: column;
}

.header {
  margin-bottom: 20px;
}
.badge-container {
  display: flex;
  justify-content: center;
}
.image-container {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

.badge-image {
  max-width: 100px;
  border-radius: 50%;
}

.course-title {
  background-color: red; /* Adjust to match your brand color */
  color: white;
  padding: 5px 15px;
  border-radius: 20px;
  display: inline-block;
  margin-bottom: 20px;
}

.skills-container {
  text-align: left;
  margin: 20px;
}

.skills-list {
  list-style: inside; /* Position the bullets inside the list */
  padding-left: 0; /* Remove default padding */
}

.skills-list li {
  background-color: #007bff; /* Blue background */
  color: white;
  padding: 10px;
  margin-bottom: 10px;
  border-radius: 10px; /* Rounded corners */
}

input[type="text"] {
  width: calc(100% - 22px); /* Input width accounting for padding */
  padding: 10px;
  margin-top: 10px;
  border: 2px solid #007bff; /* Blue border */
  border-radius: 10px; /* Rounded corners */
  font-size: 1rem;
}

.next-button {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 15px 30px;
  border-radius: 25px;
  font-size: 1.25rem;
  cursor: pointer;
  width: 100%;
  position: fixed; /* Fixed positioning relative to the viewport */
  left: 0;
  bottom: 0; /* Anchored to the bottom of the viewport */
  box-sizing: border-box; /* Makes sure the padding doesn't affect the final width */
}
</style>
