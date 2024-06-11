<template>
  <div class="step-five">
    <div class="header">
      <div class="image-container">
        <img :src="stepData[1].badgeImage" alt="Course Badge" class="badge-image"/>
      </div>
      <div class="course-title">{{ stepData[1].courseTitle }}</div>
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
import { ref, defineProps, defineEmits } from 'vue';

defineProps({
  stepData: Object
});

const requirements = ref([]);
const newRequirement = ref('');
const emit = defineEmits(['update-step-data', 'go-back']);

const addRequirement = () => {
  if (newRequirement.value.trim()) {
    requirements.value.push(newRequirement.value);
    newRequirement.value = '';
  }
};

function submitStep() {
  if (requirements.value.length > 0) {
    emit('update-step-data', { step: 5, data: requirements.value });
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
  background-color: red;
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
  list-style: inside;
  padding-left: 0;
}

.requirements-list li {
  background-color: #007bff;
  color: white;
  padding: 10px;
  margin-bottom: 10px;
  border-radius: 10px;
}

input[type="text"] {
  width: calc(100% - 22px);
  padding: 10px;
  margin-top: 10px;
  border: 2px solid #007bff;
  border-radius: 10px;
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
  position: fixed;
  left: 0;
  bottom: 0;
  box-sizing: border-box;
}
</style>
