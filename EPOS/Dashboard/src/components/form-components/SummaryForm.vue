<template>
    <div class="summary-page">
    <div class="header">
      <div class="image-container">
        <img :src="stepData[1]['badgeImage']" alt="Course Badge" class="badge-image"/>
      </div>
      <div class="course-title">{{ stepData[1]['courseTitle'] }}</div>
    </div>
  
      <div class="info-sections">
        <v-container>
    <v-row align="start">
      <v-col cols="12" md="4">
        <div class="info-section">
          <h2>Study Load</h2>
          <p>{{ formData.studyLoad }} ECTS</p>
        </div>
      </v-col>
      <v-col cols="12" md="4">
        <div class="info-section">
          <h2>Language</h2>
          <p>{{ formData.language }}</p>
        </div>
      </v-col>
      <v-col cols="12" md="4">
        <div class="info-section">
          <h2>Indicative EQF</h2>
          <p>EQF {{ formData.eqfLevel }}</p>
        </div>
      </v-col>
    </v-row>
  </v-container>
  
        <div class="info-section">
          <h2>Skills</h2>
          <ul>
            <li v-for="(skill, index) in formData.skills" :key="index">{{ skill }}</li>
          </ul>
        </div>
  
        <div class="info-section">
          <h2>Admission Requirements</h2>
          <ul>
            <li v-for="(requirement, index) in formData.requirements" :key="index">{{ requirement }}</li>
          </ul>
        </div>
      </div>
  
      <button class="send-button" @click="submitForm">Send for review</button>
    </div>
  </template>
  
  <script setup>
  import { ref, defineProps, defineEmits } from 'vue';
  import axios from "axios";

  defineProps({
  stepData: Object  
  });
  
  // Mock data, replace with actual data collection logic from previous steps
  const formData = ref({
    courseTitle: 'Fullstack Webdevelopment',
    studyLoad: '11', // Example data
    language: 'Dutch',
    eqfLevel: '5',
    description: 'In-depth course to become a Fullstack Developer.',
    skills: ['Responsive Design', 'RESTful Services', 'Backend Integration'],
    requirements: ['Completed Application', 'Passed Interview']
  });
  
  const emit = defineEmits(['send-for-review']);
  
  /*function sendForReview() {
    // Here you would handle the submission of the summary for review
    // For example, send formData to a server or transition to a "review sent" state
    console.log('Sending for review:', formData.value);
    emit('send-for-review', formData.value);
  } */
  const loading = ref(false); 
  const submitForm = async() => {
    if (!formData.value.courseTitle || !formData.value.skills.length) {
      alert('Error missing fields');
      return;
    }

    const payload = {
    courseName: formData.value.courseTitle,
    studyYear: new Date().getFullYear(), 
    description: formData.value.description,
    imageUrl: formData.value.badgeImage, 
    requiredAchievements: formData.value.requirements,
    skills: formData.value.skills,
    attributes: {
      EC: parseInt(formData.value.studyLoad),
      language: formData.value.language,
      EQF: parseInt(formData.value.eqfLevel)
    }
  };
    
    try {
    loading.value = true;
    const response = await axios.post(
      "http://localhost:8080/api/v1/education-modules",
      payload
    );
    const invitationUrl = response.data.invitationUrl;
    emit('review-sent', invitationUrl); // Notify parent component
    loading.value = false;
  } catch (error) {
    console.error('Submission failed: ', error);
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
  height: 100vh; /* Use the full height to allow for fixed button at the bottom */
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

.info-section p, .info-section ul {
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

  