<template>
  <div class="step-two">
    <div class="header">
      <div class="image-container">
        <img :src="stepData[1]['badgeImage']" alt="Course Badge" class="badge-image"/>
      </div>
      <div class="course-title">{{ stepData[1]['courseTitle'] }}</div>
    </div>


    <div class="dropdowns-container">
      <div class="dropdown" v-for="(dropdown, index) in dropdowns" :key="index">
        <label :for="'dropdown-' + index">{{ dropdown.label }}</label>
        <div class="select-wrapper">
          <select :id="'dropdown-' + index" v-model="dropdown.selectedValue">
            <option disabled value="">{{ dropdown.placeholder }}</option>
            <option v-for="option in dropdown.options" :key="option" :value="option">{{ option }}</option>
          </select>
        </div>
      </div>
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
  {
    label: 'language',
    placeholder: '- Select -',
    options: ['English', 'Dutch', 'German'],
    selectedValue: ''
  },
  {
    label: 'EC',
    placeholder: '- Select -',
    options: [1, 2, 3, 5, 10, 15, 20, 30],
    selectedValue: ''
  },
  {
    label: 'EQF',
    placeholder: '- Select -',
    options: [5, 6, 7],
    selectedValue: ''
  }
]);

function submitStep() {
  const formData = dropdowns.value.reduce((acc, dropdown) => {
    acc[dropdown.label] = dropdown.selectedValue; // Assign the selectedValue to the label as the key
    return acc;
  }, {});

  emit('update-step-data', { step: 2, data: formData });
}

// OUDE CODE
/* const showNewDropdownFields = ref(false);
const newDropdownLabel = ref('');
const newDropdownOptions = ref('');

const showDropdownFields = () => {
  showNewDropdownFields.value = true;
};

const confirmAddDropdown = () => {
  if (newDropdownLabel.value && newDropdownOptions.value) {
    dropdowns.value.push({
      label: newDropdownLabel.value,
      placeholder: 'Enter Value',
      selectedValue: '',
      isTextInput: true
    });
    showNewDropdownFields.value = false;
    newDropdownLabel.value = '';
  } else {
    alert('Please fill in the dropdown label and options.');
  }*/

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

.dropdown label{
  display: block;
  background-color: #007bff; /* Blue background */
  color: white;
  padding: 8px;
  border-radius: 10px 10px 0 0; /* Rounded top corners */
  font-size: 18px;
}

.input-title {
  display: block;
  background-color: #007bff; /* Blue background */
  color: white;
  padding: 8px;
  border-radius: 10px 10px 0 0; /* Rounded top corners */
  font-size: 18px;
}

.select-wrapper {
  margin-bottom: 1rem;
}

.select-wrapper select {
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  padding: 12px;
  width: 100%;
  border-radius: 0 0 10px 10px; /* Rounded bottom corners */
  border: 1px solid #007bff;
  background-color: white;
  color: black;
  font-size: 20px;
}

.select-wrapper::after {
  content: '⌵';
  color: black;
  position: absolute;
  right: 15px;
  top: 50%;
  transform: translateY(-50%);
  pointer-events: none;
  font-size: 12px;
}

.add-dropdown-button {
  background-color: #007bff; /* Blue background */
  color: white;
  padding: 10px 20px;
  border-radius: 10px;
  margin-top: 10px; /* Spacing between the last dropdown and the button */
  border: none;
  cursor: pointer;
  font-size: 18px;
  width: auto;
  display: inline-block;
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


.back-button {
  background-color: transparent;
  border: none;
  cursor: pointer;
  color: #007bff;
  padding: 10px;
  font-size: 24px;
  position: absolute;
  left: 0;
}
</style>
