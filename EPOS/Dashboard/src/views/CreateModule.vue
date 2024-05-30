<template>
  <v-container class="container">
    <v-row align="center" justify="center" class="fill-height">
      <v-col cols="12" class="fill-height">
    <v-stepper>
      <v-stepper-header>
        <v-stepper-item :complete="step > 1" step="1" value="1" @click="step = 1"/>
        <v-divider></v-divider>
        <v-stepper-item :complete="step > 2" step="2" value="2" @click="step = 2"/>
        <v-divider></v-divider>
        <v-stepper-item :complete="step > 3" step="3" value="3" @click="step = 3"/>
        <v-divider></v-divider>
        <v-stepper-item :complete="step > 4" step="4" value="4" @click="step = 4"/>
        <v-divider></v-divider>
        <v-stepper-item :complete="step > 5" step="5" value="5" @click="step = 5"/>
      </v-stepper-header>


      <div>
        <div step="1" v-if="step === 1">
          <createStep1/>
        </div>

        <div step="2" v-if="step === 2">
          <createStep2/>
        </div>

        <div step="3" v-if="step === 3">
          <createStep3/>
        </div>

        <div step="4" v-if="step === 4">
          <v-form>
            <v-text-field label="State" v-model="formData.state"></v-text-field>
            <v-text-field label="Zip Code" v-model="formData.zipCode"></v-text-field>
            <v-btn color="primary" @click="prevStep">Previous</v-btn>
            <v-btn color="primary" @click="nextStep">Next</v-btn>
          </v-form>
        </div>

        <div step="5" v-if="step === 5">
          <v-form>
            <v-text-field label="Country" v-model="formData.country"></v-text-field>
            <v-btn color="primary" @click="prevStep">Previous</v-btn>
            <v-btn color="success" @click="submitForm">Submit</v-btn>
          </v-form>
        </div>
      </div>
    </v-stepper>
    <div class="stepbutton">
      <v-icon class="btn" @click="prevStep">mdi-arrow-left</v-icon>
      <v-icon class="btn" @click="nextStep">mdi-arrow-right</v-icon>
      <!-- <v-btn color="primary" @click="prevStep">Previous</v-btn>
      <v-btn color="primary" @click="nextStep">Next</v-btn> -->
    </div>
  </v-col>
  </v-row>
  </v-container>
</template>

<script>
import { ref } from 'vue';

import createStep1 from '@/components/page-components/module/form/createStep1.vue';
import createStep2 from '@/components/page-components/module/form/createStep2.vue';
import createStep3 from '@/components/page-components/module/form/createStep3.vue';

export default {
  name: 'MultiStepForm',
  components: {
    createStep1,
    createStep2,
    createStep3,
  },
  setup() {
    const step = ref(1);
    const formData = ref({
      firstName: '',
      lastName: '',
      email: '',
      phone: '',
      address: '',
      city: '',
      state: '',
      zipCode: '',
      country: ''
    });

    const nextStep = () => {
      if (step.value < 5) step.value++;
      console.log('Step:', step.value);
    };

    const prevStep = () => {
      if (step.value > 1) step.value--;
      console.log('Step:', step.value);
    };

    const submitForm = () => {
      console.log('Form Submitted', formData.value);
    };

    return {
      step,
      formData,
      nextStep,
      prevStep,
      submitForm
    };
  }
};
</script>

<style scoped>
.stepbutton {
  margin-left: 100px;
  position: fixed;
  bottom: 25px;
  font-size: 40px;
}

.container {
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.fill-height {
  height: 100%;
}

.v-stepper {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.v-stepper-header {
  justify-content: space-between;
}
</style>

