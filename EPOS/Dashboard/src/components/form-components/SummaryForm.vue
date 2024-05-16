<template>
  <div class="summary-page">
    <div class="header">
      <div class="image-container">
        <img
          :src="stepData[1]['badgeImage']"
          alt="Course Badge"
          class="badge-image"
        />
      </div>
      <div class="course-title">{{ stepData[1]["courseTitle"] }}</div>
    </div>

    <div class="info-sections">
      <v-container style="padding: 0;">
        <v-row align="start" class="scrollable-row">
          <v-col
            cols="5"
            md="4"
            v-for="(value, key) in stepData[2]"
            :key="key"
          >
            <div class="info-section">
              <h2>{{ key }}</h2>
              <p>{{ value }}</p>
            </div>
          </v-col>
        </v-row>
      </v-container>

      <div class="info-section">
        <h2>Description</h2>
        <p>{{ stepData[3]["description"] }}</p>
      </div>

      <div class="info-section">
        <h2>Skills</h2>
        <ul>
          <li v-for="skill in stepData[4]" :key="skill">
            {{ skill }}
          </li>
        </ul>
      </div>

      <div class="info-section">
        <h2>Admission Requirements</h2>
        <ul>
          <li v-for="requirement in stepData[5]" :key="requirement">
            {{ requirement }}
          </li>
        </ul>
      </div>
    </div>

    <button class="send-button" @click="submitForm">Send for review</button>
  </div>
</template>

  
  <script setup>
import { ref, defineProps, defineEmits } from "vue";
import axios from "axios";

const props = defineProps({
  stepData: Object,
});


const emit = defineEmits(["send-for-review"]);


const loading = ref(false);
const submitForm = async () => {
  console.log('Submit button is pressed');

  const payload = {
    courseName: props.stepData[1]["courseTitle"],
    studyYear: new Date().getFullYear(),
    description: props.stepData[3]["description"],
    imageUrl: props.stepData[1]["badgeImage"],
    requiredAchievements: props.stepData[5],
    skills: props.stepData[4],
    attributes: props.stepData[2]
  };

  try {
    loading.value = true;
    const response = await axios.post(
      "http://localhost:8080/api/v1/education-modules",
      payload
    );
    const invitationUrl = response.data.invitationUrl;
    emit("review-sent", invitationUrl); // Notify parent component
    loading.value = false;
  } catch (error) {
    console.error("Submission failed: ", error);
    loading.value = false;
  }
};
/*
  {
  "courseName": "Nederlands",
  "studyYear": 2021,
  "description": "Dutch language course",
  "imageUrl": "google.nl",
  "requiredAchievements": ["A2", "B1"],
  "skills": ["listening", "speaking", "reading", "1"],
  "attributes": {
    "EC": 1,
    "language": "Dutch",
    "EQF": 1
  }
}
 
  */
</script>
  
  
 <style scoped>
.scrollable-row {
  display: flex;
  flex-wrap: nowrap; /* Prevents the columns from wrapping */
  overflow-x:scroll; /* Enables horizontal scrolling */
  -webkit-overflow-scrolling: touch; /* Smooth scrolling on touch devices */
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
.summary-page {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  max-width: 500px;
  margin: auto;
  padding: 20px;

}

.header {
  text-align: center;
}

.back-button {
  border: none;
  background: none;
  cursor: pointer;
  color: #007bff;
  font-size: 2rem;
  position: absolute;
  top: 20px;
  left: 20px;
}

.badge-container {
  display: flex;
  justify-content: center;
  margin-top: 20px; /* Spacing from the top */
}

.badge-image {
  width: 100px; /* Adjust size to match design */
  height: 100px; /* Adjust size to match design */
  border-radius: 50%;
}

.course-title {
  margin: 10px 0;
  font-size: 1.5rem;
  color: #333; /* Dark text color for title */
}

.info-section {
  background-color: #f0f0f0; /* Light grey background */
  border-radius: 10px;
  margin-bottom: 15px;
  padding: 15px;
}

.info-section h2 {
  color: #007bff; /* Blue color for section headers */
  margin-bottom: 10px;
}

.info-section p,
.info-section ul {
  color: #333; /* Dark text for content */
  text-align: left;
}

.info-section ul {
  list-style: inside circle; /* Styled bullets */
  padding-left: 0;
}

.send-button {
  background-color: #007bff; /* Blue background */
  color: white;
  border: none;
  padding: 15px;
  border-radius: 20px;
  font-size: 1rem;
  cursor: pointer;
  width: 100%;
  position: fixed; /* Fixed positioning relative to the viewport */
  bottom: 0; /* Anchored to the bottom of the viewport */
  left: 0;
  right: 0; /* Ensure full width */
}
</style>

  