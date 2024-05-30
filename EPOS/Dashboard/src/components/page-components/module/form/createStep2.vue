<template>
    <v-form>
      <div class="header">
        <div class="image-container">
          <img :src="stepData.badgeImage" alt="Course Badge" class="badge-image"/>
        </div>
        <div class="course-title">{{ stepData.courseTitle }}</div>
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
    </v-form>
  </template>
  
  <script>
  import { ref, defineProps } from 'vue';
  
  export default {
    setup() {
      const props = defineProps({
        stepData: Object
      });
  
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
  
      return {
        props,
        dropdowns
      };
    }
  };
  </script>
  
  <style scoped>
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
  
  .dropdowns-container {
    margin-bottom: 20px;
  }
  
  .dropdown label {
    display: block;
    background-color: #007bff;
    color: white;
    padding: 8px;
    border-radius: 10px 10px 0 0;
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
    border-radius: 0 0 10px 10px;
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
    background-color: #007bff;
    color: white;
    padding: 10px 20px;
    border-radius: 10px;
    margin-top: 10px;
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
    position: fixed;
    left: 0;
    bottom: 0;
    box-sizing: border-box;
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
  