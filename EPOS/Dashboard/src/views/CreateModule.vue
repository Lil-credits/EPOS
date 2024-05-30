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


      <div class="steps">
        <div step="1" v-if="step === 1">
          <v-form>
        <div class="image-upload">
        <input
          type="file"
          id="course-badge"
          @change="handleImageUpload"
          accept="image/*"
          hidden
        />
        <label for="course-badge" class="upload-label">
          <div v-if="formData.image" class="image-preview">
            <img
              :src="from"
              alt="Uploaded image preview"
              class="preview-image"
            />
          </div>
          <div v-else>Add Image</div>
        </label>
      </div>
      <div class="form-group">
        <v-text-field label="Credential title"></v-text-field>
      </div>
      <div class="description-container">
      <v-textarea no-resize label="Description" variant="solo-filled" rows="10"></v-textarea>

    </div>
    </v-form>
        </div>

        <div step="2" v-if="step === 2">
          <createStep2/>
        </div>

        <div step="3" v-if="step === 3">
          <createStep3/>
        </div>

        <div step="4" v-if="step === 4">
          <createStep4/>
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


import createStep2 from '@/components/page-components/module/form/createStep2.vue';
import createStep3 from '@/components/page-components/module/form/createStep3.vue';
import createStep4 from '@/components/page-components/module/form/createStep4.vue';

export default {
  name: 'MultiStepForm',
  components: {
    createStep2,
    createStep3,
    createStep4,
  },
  setup() {
    const step = ref(1);
    const formData = ref({
      title: '',
      image: '',
      description: '',
      attriburtes: {},
      skills: [],
      requiredAdmission: [],
    });

    const nextStep = () => {
      if (step.value < 5) step.value++;
      console.log('Step:', step.value);
    };

    const prevStep = () => {
      if (step.value > 1) step.value--;
      console.log('Step:', step.value);
    };

    const handleImageUpload = (event) => {
      const file = event.target.files[0];
      const reader = new FileReader();

      reader.onload = () => {
        formData.value.image = reader.result;
      };

      reader.readAsDataURL(file);
    };

    const submitForm = () => {
      console.log('Form Submitted', formData.value);
    };

    return {
      step,
      formData,
      nextStep,
      prevStep,
      submitForm,
      handleImageUpload
    };
  }
};
</script>

<style scoped>
.stepbutton {
  display: flex;
  justify-content: center;
  align-items: center;
  position: fixed;
  bottom: 25px;
  left: 50%;
  transform: translateX(-50%);
  font-size: 40px;
}

.btn {
  margin: 0 40%;
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


