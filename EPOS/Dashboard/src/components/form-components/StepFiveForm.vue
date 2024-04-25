<template>
  <div class="step-five">
    <div class="header">
      <div class="image-container">
        <!-- Received from step one -->
        <img src="/micro.png" class="badge-image"/>
        <!-- <img :src="stepData.badgeImage" alt="Course Badge" class="badge-image" /> -->
      </div>
      <div class="course-title">Test <!-- {{ stepData.courseTitle }} --></div>
    </div>
      <div class="requirements-container">
      <h2>Admission Requirements</h2>
      <ul class="requirements-list">
        <li v-for="(requirement, index) in requirements" :key="index">{{ requirement }}</li>
      </ul>
      <input type="text" v-model="newRequirement" placeholder="Type here more Admission Requirements..." @keyup.enter="addRequirement">
    </div>

    <button class="next-button" @click="submitStep">Next</button>
  </div>
</template>

<script setup>
import { ref, defineEmits } from 'vue';

const requirements = ref([]); // Start with an empty array for requirements
const newRequirement = ref('');
const emit = defineEmits(['update-step-data', 'go-back']);

const addRequirement = () => {
  if (newRequirement.value.trim()) {
    requirements.value.push(newRequirement.value);
    newRequirement.value = ''; // Reset the input field
  }
};

function submitStep() {
  if (requirements.value.length > 0) {
    emit('update-step-data', { step: 5, data: { requirements: requirements.value } });
  } else {
    alert('Please enter at least one admission requirement.');
  }
}
</script>

<style scoped>
.step-five {
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

.requirements-container {
  text-align: left;
  margin: 20px;
}

.requirements-list {
  list-style: inside; /* Position the bullets inside the list */
  padding-left: 0; /* Remove default padding */
}

.requirements-list li {
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
