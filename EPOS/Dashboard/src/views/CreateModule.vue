<template>
  <pageHeading title="Create"/>

  <v-container class="container">

    <v-stepper :elevation="0">
      <v-stepper-header class="elevation-0" >
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

      <!-- Step 1 -->
      <div class="steps">
        <div step="1" v-if="step === 1">
          <v-row class="mt-n5" wrap no-gutters>
        <v-col cols="3" class="text-center">
            <v-divider class="mt-4" />
        </v-col>
        <v-col cols="6" class="text-center text-h6">
          Basic Information
        </v-col>
        <v-col cols="3" class="text-center">
            <v-divider class="mt-4" />
        </v-col>
    </v-row>
          <v-form>
            <div class="image-upload">
              <input type="file" id="course-badge" @change="handleImageUpload" accept="image/*" hidden/>
              <label for="course-badge" class="upload-label">
                <div v-if="formData.image" class="image-preview">
                  <img :src="formData.image" alt="Uploaded image preview" class="preview-image"/>
                </div>
                <div v-else>Add Image</div>
              </label>
            </div>
            <div class="form-group">
              <v-text-field v-model="formData.title" label="Credential title"></v-text-field>
            </div>
            <div class="description-container">
              <v-textarea v-model="formData.description" no-resize label="Description" variant="solo-filled" rows="10"></v-textarea>
            </div>
          </v-form>
        </div>

        <!-- Step 2 -->
        <div step="2" v-if="step === 2">
          <v-row class="mt-n5" wrap no-gutters>
        <v-col cols="3" class="text-center">
            <v-divider class="mt-4" />
        </v-col>
        <v-col cols="6" class="text-center text-h6">
          Define Attributes
        </v-col>
        <v-col cols="3" class="text-center">
            <v-divider class="mt-4" />
        </v-col>
    </v-row>
          <v-form>
            <div class="dropdown">
              <v-select :items="['Nederlands', 'Deutsch', 'English', 'Español', 'Zhōngguó ren']" label="Select Language" required variant="outlined" v-model:model-value="formData.attributes['language']" @update:model-value="handleDropdown('language', $event)"></v-select>
              <v-select :items="[5, 10, 15, 20, 25, 30, 35, 40, 45, 50]" label="Select Study Load" required variant="outlined" v-model:model-value="formData.attributes['ects']" @update:model-value="handleDropdown('ects', $event)"></v-select>
              <v-select :items="[5, 7, 8, 9, 10]" label="Select EQF Level" required variant="outlined" v-model:model-value="formData.attributes['eqf']" @update:model-value="handleDropdown('eqf', $event)"></v-select>
            </div>
          </v-form>
        </div>

        <!-- Step 3 -->
        <div step="3" v-if="step === 3">
          <v-row class="mt-n5" wrap no-gutters>
        <v-col cols="3" class="text-center">
            <v-divider class="mt-4" />
        </v-col>
        <v-col cols="6" class="text-center text-h6">
          Specify Skills
        </v-col>
        <v-col cols="3" class="text-center">
            <v-divider class="mt-4" />
        </v-col>
    </v-row>
          <div class="list">
            <div class="skills-container">
              <h2>Skills</h2>
              <ul class="skills-list">
                <li v-for="(skill, index) in skills" :key="index" class="skill-item">
                  {{ skill }}
                  <v-btn icon="mdi-trash-can-outline" variant="text" @click="deleteSkill(index)"></v-btn>
                </li>
              </ul>
              <v-text-field type="text" v-model="newSkill" label="Add more skills..." @keyup.enter="addSkill"></v-text-field>
            </div>
          </div>
        </div>

        <!-- Step 4 -->
        <div step="4" v-if="step === 4">
          <v-row class="mt-n5" wrap no-gutters>
        <v-col cols="1.5" class="text-center">
            <v-divider class="mt-4" />
        </v-col>
        <v-col cols="9" class="text-center text-h6">
          Set Admission Requirements
        </v-col>
        <v-col cols="1.5" class="text-center">
            <v-divider class="mt-4" />
        </v-col>
    </v-row>
          <div class="list">
            <div class="requirements-container">
              <h2>Admission Requirements</h2>
              <ul class="requirements-list">
                <li v-for="(requirement, index) in requirements" :key="index" class="requirement-item">
                  {{ requirement }}
                  <v-btn icon="mdi-trash-can-outline" variant="text" @click="deleteRequirement(index)"></v-btn>
                </li>
              </ul>
              <v-text-field type="text" v-model="newRequirement" label="Add more Admission Requirements..." @keyup.enter="addRequirement"></v-text-field>
            </div>
          </div>
        </div>

        <!-- Step 5 -->
        <div step="5" v-if="step === 5">
          <v-row class="mt-n5" wrap no-gutters>
        <v-col cols="3" class="text-center">
            <v-divider class="mt-4" />
        </v-col>
        <v-col cols="6" class="text-center text-h6">
          Review and Confirm
        </v-col>
        <v-col cols="3" class="text-center">
            <v-divider class="mt-4" />
        </v-col>
    </v-row>
          <container class="detailcontainer">
            <div class="informationbox">
              <div class="image-container">
                <detailHeading :imageUrl="formData.image" :title="formData.title" />
              </div>
              <detailAttributes :attributes="formData.attributes" />
              <div class="info-section">
                <detailDescription :description="formData.description" />
              </div>
              <div class="info-section">
                <detailList listTitle="Skills" :list="formData.skills" />
              </div>
              <div class="info-sectionalt">
                <detailList listTitle="Admission Requirements" :list="formData.requiredAdmission" />
                <v-button></v-button>
              </div>
            </div>
          </container>
        </div>
      </div>
    </v-stepper>

  </v-container>

  <!-- stepbuttons -->    
  <div class="stepbutton" style="width: 100%;">
    <v-divider></v-divider>
    <v-container>
      <div class="px-8" style="width: 100%;">
        <div v-if="step===5" class="d-flex justify-space-between" style="width: 100%;">
          <div>
            <v-icon class="btn" @click="prevStep">mdi-arrow-left</v-icon>
          </div>
          <div>
            <v-icon class="btn" @click="submitForm">mdi-check</v-icon>
          </div>
        </div>
        <div v-else class="d-flex justify-space-between" style="width: 100%;">
          <div>
            <v-icon class="btn" @click="prevStep">mdi-arrow-left</v-icon>
          </div>
          <div>
            <v-icon class="btn" @click="nextStep">mdi-arrow-right</v-icon>
          </div>
        </div>
      </div>
    </v-container>
  </div>
</template>

<script>
import { ref } from 'vue';

import detailHeading from '@/components/page-components/module/detailHeading.vue';
import detailAttributes from '@/components/page-components/module/detailAttributes.vue';
import detailList from '@/components/page-components/module/detailList.vue';  
import detailDescription from '@/components/page-components/module/detailDescription.vue';

import pageHeading from '@/components/pageHeading.vue';

import api from '@/api/api.js'; // Ensure the correct path
import router from '@/router';

export default {
  name: 'MultiStepForm',
  components: {
    detailHeading,
    detailAttributes,
    detailList,
    detailDescription,
    pageHeading
  },
  setup() {
    const step = ref(1);
    const formData = ref({
      title: '',
      image: '',
      description: '',
      attributes: { language: null, ects: null, eqf: null },
      skills: [],
      requiredAdmission: [],
    });

    const nextStep = () => {
      if (step.value < 5) step.value++;
      console.log('Step:', step.value);
      console.log('Form Data:', formData.value);
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
      let requestBody = {
        name: formData.value.title,
        organisationalUnitId: 1,
        imageData: formData.value.image,
        baseCredential: {
          courseName: formData.value.title,
          attributes: {
            language: formData.value.attributes.language,
            EC: formData.value.attributes.ects,
            EQF: formData.value.attributes.eqf
          },
          description: formData.value.description,
          skills: formData.value.skills,
          requiredAchievements: formData.value.requiredAdmission,
          imageUrl: formData.value.image,
          studyYear: 2024,
        }
      };
      api.createModule(requestBody)
        .then((response) => {
          console.log(response);
          // go to module overview
          router.push("/modules");
        })
        .catch((error) => {
          console.error(error);
        });
    };

    const requirements = ref([]);
    const newRequirement = ref('');

    const addRequirement = () => {
      if (newRequirement.value.trim() !== '') {
        requirements.value.push(newRequirement.value);
        newRequirement.value = '';
        formData.value.requiredAdmission = requirements.value;
      }
    };

    const deleteRequirement = (index) => {
      requirements.value.splice(index, 1);
      formData.value.requiredAdmission = requirements.value;
    };

    const skills = ref([]);
    const newSkill = ref('');

    const addSkill = () => {
      if (newSkill.value.trim() !== '') {
        skills.value.push(newSkill.value);
        newSkill.value = '';
        formData.value.skills = skills.value;
      }
    };

    const deleteSkill = (index) => {
      skills.value.splice(index, 1);
      formData.value.skills = skills.value;
    };

    const handleDropdown = (label, value) => {
      formData.value.attributes[label] = value;
    };

    return {
      step,
      formData,
      nextStep,
      prevStep,
      submitForm,
      handleImageUpload,
      requirements,
      newRequirement,
      addRequirement,
      deleteRequirement,
      skills,
      newSkill,
      addSkill,
      deleteSkill,
      handleDropdown,
    };
  },
};
</script>

<style scoped>
.container {
  height: 100%;
}
.stepbutton {
  background: linear-gradient(to bottom, rgba(128, 128, 128, 0.5), rgba(128, 128, 128, 0.5));
  position: fixed;
  bottom: 0px;
  left: 50%;
  transform: translateX(-50%);
  font-size: 40px;
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

.step-title {
  margin-bottom: 2em;
  font-size: 2em;
}

/* ------------------------------------styling of step 1------------------------------------ */
.step-one {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  text-align: center;
}

.form-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.image-upload {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 30px;
  margin-top: 30px;
  box-shadow: none;
}

.upload-label {
  background-color: #007bff;
  border-radius: 50%;
  width: 120px;
  height: 120px;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.add-image-icon {
  color: white;
  font-size: 24px;
  position: absolute;
}

.upload-label div {
  color: white;
  font-size: 14px;
}

.title-input {
  border: 1px solid #ccc;
  border-radius: 20px;
  padding: 10px;
  width: 100%;
  font-size: 16px;
  text-align: center;
  margin-bottom: 20px;
}

.submit-button {
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 20px;
  padding: 15px 30px;
  font-size: 16px;
  width: calc(100% - 60px); /* Account for padding */
  cursor: pointer;
}

.image-preview {
  background-color: white;
  width: 120px;
  height: 120px;
  border-radius: 50%;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
}

.preview-image {
  max-width: 100%;
  max-height: 100%;
}

.description-container {
  margin-bottom: 20px;
}

.description-label {
  display: block;
  font-size: 1.25rem;
  margin-bottom: 10px;
}

textarea {
  width: 100%;
  padding: 15px;
  border-radius: 25px;
  border: 2px solid #007bff;
  resize: none; /* Prevent resizing of the textarea */
  font-size: 1rem;
}

/* ------------------------------------styling of step 2------------------------------------ */

.dropdown {
  margin-top: 10px;
}

.v-select label {
  margin: 10px;
}

/* ------------------------------------styling of step 3------------------------------------ */

.skills-container {
  text-align: left;
  margin: 20px;
}

.skills-list {
  list-style: inside; /* Position the bullets inside the list */
  padding-left: 0; /* Remove default padding */
}

.skills-list .skill-item {
  background-color: #007bff; /* Blue background */
  color: white;
  padding: 10px;
  margin-bottom: 10px;
  border-radius: 10px; /* Rounded corners */
  display: flex;
  justify-content: space-between;
  align-items: center;
}

input[type="text"] {
  width: calc(100% - 22px); /* Input width accounting for padding */
  padding: 10px;
  margin-top: 10px;
  border: 2px solid #007bff; /* Blue border */
  border-radius: 10px; /* Rounded corners */
  font-size: 1rem;
}

/* ------------------------------------styling of step 4------------------------------------ */

.requirements-container {
  text-align: left;
  margin: 20px;
}

.requirements-list {
  list-style: inside; /* Position the bullets inside the list */
  padding-left: 0; /* Remove default padding */
}

.requirements-list .requirement-item {
  background-color: #007bff; /* Blue background */
  color: white;
  padding: 10px;
  margin-bottom: 10px;
  border-radius: 10px; /* Rounded corners */
  display: flex;
  justify-content: space-between;
  align-items: center;
}

input[type="text"] {
  width: calc(100% - 22px); /* Input width accounting for padding */
  padding: 10px;
  margin-top: 10px;
  border: 2px solid #007bff; /* Blue border */
  border-radius: 10px; /* Rounded corners */
  font-size: 1rem;
}

/* ------------------------------------styling of step 5------------------------------------ */
.informationbox {
  width: 100%;
  margin: 0 20px;
}

.info-section {
  background-color: #f0f0f0;
  border-radius: 10px;
  margin-bottom: 15px;
  padding: 15px;
}

.image-container {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

.detailcontainer {
  margin-top: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.info-sectionalt {
  color: white;
  background-color: #00A4E7;
  border-radius: 10px;
  margin-bottom: 15px;
  padding: 15px;
}

.info-section h2 {
  text-align: center;
}
</style>
