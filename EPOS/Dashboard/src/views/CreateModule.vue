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

<!-- Step 1-->
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
<!-- Step 2 -->
        <div step="2" v-if="step === 2">
        <v-form>
<div class="dropdown">
    <v-select
        :items="['Nederlands', 'Deutsch', 'English', 'Español', 'Zhōngguó ren']"
        label="Select Language"
        required
        variant="outlined"
        ></v-select>
    <v-select
        :items="[5, 10, 15, 20, 25, 30, 35, 40, 45, 50]"
        label="Select Study Load"
        required
        variant="outlined"
        ></v-select>
    <v-select
        :items="[5, 7, 8, 9, 10]"
        label="Select EQF Level"
        required
        variant="outlined"
        ></v-select>
</div>
    </v-form>
        </div>
<!-- Step 3 -->
        <div step="3" v-if="step === 3">
          <div class="list">
    <div class="skills-container">
      <h2>Skills</h2>
      <ul class="skills-list">
        <li v-for="(skill, index) in skills" :key="index">{{ skill }}</li>
      </ul>
      <input type="text" v-model="newSkill" placeholder="Type here more Skills..." @keyup.enter="addSkill">
      <v-text-field v-modal="newSkill" label="Add more skills..." @keyup.enter="addSkill"></v-text-field>
    </div>
</div>
        </div>

<!-- Step 4 -->
        <div step="4" v-if="step === 4">
          <div class="list">
        <div class="requirements-container">
      <h2>Admission Requirements</h2>
      <ul class="requirements-list">
        <li v-for="(requirement, index) in requirements" :key="index">{{ requirement }}</li>
      </ul>
      <input type="text" v-model="newRequirement" placeholder="Type here more Admission Requirements..." @keyup.enter="addRequirement">
    </div>
  </div>
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
    </div>
  </v-col>
  </v-row>
  </v-container>
</template>

<script>
import { ref } from 'vue';




export default {
  name: 'MultiStepForm',
  components: {
  
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

/* styling of step 1 */
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

/* styling of step 2 */

.dropdown {
    margin-top: 10px;
  }
  .v-select label{
    margin: 10px;
  }


/* styling of step 3 */

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

/* styling of step 4 */

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

</style>


