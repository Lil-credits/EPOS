<template>
  <div class="container">
    <button class="back-button" @click="prevStep" :hidden="currentStep === 1">&larr;</button>
    <div v-if="currentStep <= totalSteps">
      <!-- Dynamic component based on current step -->
      <component
          :is="currentComponent"
          :step-data="formData"
          @update-step-data="handleUpdateStepData"
          @validate-step="validateStep"
      ></component>

    </div>
    <div v-else>
      <h2>Form Submitted Successfully!</h2>
      <p>{{ formData }}</p>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue';
import StepOne from '../components/form-components/StepOneForm.vue';
import StepTwo from '../components/form-components/StepTwoFrom.vue';
import StepThree from '../components/form-components/StepThreeFrom.vue';
import StepFour from '../components/form-components/StepFourForm.vue';
import StepFive from '../components/form-components/StepFiveForm.vue';

const currentStep = ref(1);
const formData = ref({
  1: {courseTitle: '', badgeImage: ''},
  2: {},
  3: {},
  4: {},
  5: {},

});
const isStepValid = ref(true);

const componentsMap = {
  1: StepOne,
  2: StepTwo,
  3: StepThree,
  4: StepFour,
  5: StepFive,
};

const currentComponent = computed(() => componentsMap[currentStep.value]);
const totalSteps = 5;

function nextStep() {
  if (isStepValid.value && currentStep.value < totalSteps) {
    console.log(formData.value)
    currentStep.value++;
  } else if (isStepValid.value && currentStep.value === totalSteps) {
    submitForm();
  }
}

function prevStep() {
  if (currentStep.value > 1) {
    currentStep.value--;
  }
}

function validateStep(valid) {
  isStepValid.value = valid;
}

const handleUpdateStepData = ({ step, data }) => {
  formData.value[step] = data;
  nextStep();
};

function submitForm() {
  console.log('Submitting form data:', formData.value);
  // Move to a success message or handle the form submission to the server
}
</script>
  
  <style>
  .container {
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
  }
  input, button {
    display: block;
    margin: 10px 0;
  }
  .submit-button {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 10px 15px;
  border-radius: 4px;
  cursor: pointer;
  width: 100%;
}
.back-button {
  background-color: transparent;
  border: none;
  cursor: pointer;
  font-size: 1.5em;
  margin-right: -40px; /* Adjust based on your layout */
}
  </style>
  