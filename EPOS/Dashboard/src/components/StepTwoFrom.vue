<template>
  <div class="step-two">
    <div class="header">
      <div class="image-container">
        <!-- Received from step one -->
        <img src="/micro.png" class="badge-image"/>
        <!-- <img :src="stepData.badgeImage" alt="Course Badge" class="badge-image" /> -->
      </div>
      <div class="course-title">Test <!-- {{ stepData.courseTitle }} --></div>
    </div>

    <div class="dropdowns-container">
      <div class="dropdown" v-for="(dropdown, index) in dropdowns" :key="index">
        <div class="select-wrapper">
          <select v-model="dropdown.selectedValue">
            <option disabled value="">{{ dropdown.placeholder }}</option>
            <option v-for="option in dropdown.options" :key="option" :value="option">{{ option }}</option>
          </select>
        </div>
      </div>
      <button class="add-button" @click="addDropdown">ADD +</button>
    </div>

    <button class="next-button" @click="submitStep">Next</button>
  </div>
</template>

<script setup>
import { ref, defineProps, defineEmits } from 'vue';

// Directly define stepData as a prop
defineProps({
  stepData: Object
});
const emit = defineEmits(['update-step-data', 'go-back']);

const dropdowns = ref([
  // Initial dropdown configuration
]);

function addDropdown() {
  dropdowns.value.push({
    label: `New Dropdown ${dropdowns.value.length + 1}`,
    placeholder: 'Select Option',
    options: ['Option 1', 'Option 2', 'Option 3'], // Replace with actual options
    selectedValue: ''
  });
}


function submitStep() {
  // Emit the selected values from the dropdowns
  emit('update-step-data', { step: 2, data: dropdowns.value.map(d => d.selectedValue) });
}
</script>

<style scoped>
.step-two {
  max-width: 500px;
  margin: 0 auto;
  text-align: center;
}

.header {
  margin-bottom: 20px;
}

.back-button {
  background-color: transparent;
  border: none;
  cursor: pointer;
  font-size: 1.5em;
  margin-right: -40px; /* Adjust based on your layout */
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

.dropdowns-container {
  margin-bottom: 20px;
}

.select-wrapper {
  position: relative;
  margin-bottom: 10px;
}

.select-wrapper::after {
  content: '▼';
  position: absolute;
  right: 10px;
  top: 50%;
  transform: translateY(-50%);
  pointer-events: none;
}

.dropdown select {
  width: 100%;
  padding: 10px;
  border-radius: 25px;
  border: 1px solid #ccc;
  appearance: none; /* Removes default styling from select elements */
  -webkit-appearance: none;
  -moz-appearance: none;
}

.add-button {
  background-color: blue;
  border: none;
  color: white;
  padding: 10px 20px;
  border-radius: 25px;
  cursor: pointer;
}

.next-button {
  background-color: blue;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 25px;
  cursor: pointer;
  width: 100%;
}
</style>
