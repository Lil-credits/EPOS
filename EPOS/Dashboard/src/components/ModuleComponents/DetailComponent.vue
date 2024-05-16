<template>
  <div class="summary-page">
    <div v-if="loading" class="loading-spinner"></div>
    <div v-else>
      <div class="header">
        <div class="image-container">
          <img :src="stepData[1]['badgeImage']" alt="Course Badge" class="badge-image" />
        </div>
        <div class="course-title">{{ stepData[1]["courseTitle"] }}</div>
      </div>
      <div class="info-sections">
        <v-container>
          <v-row align="start" class="scrollable-row">
            <v-col cols="5" md="4" v-for="(value, key) in stepData[2]" :key="key">
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
            <li v-for="skill in stepData[4]" :key="skill">{{ skill }}</li>
          </ul>
        </div>
        <div class="info-section">
          <h2>Admission Requirements</h2>
          <ul>
            <li v-for="requirement in stepData[5]" :key="requirement">{{ requirement }}</li>
          </ul>
        </div>
      </div>
      <button class="send-button" @click="submitForm">Send for review</button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";
import { useRoute } from "vue-router";

// Mock data
const mockData = {
  1: {
    badgeImage: "../assets/Picture1.png",
    courseTitle: "Logics enigneer",
    imageUrl: "../assets/Picture1.png",
  },
  2: {
    EC: 5,
    language: "English",
    EQF: 2,
  },
  3: {
    description: "This is a sample course description.",
  },
  4: ["Listening", "Speaking", "Reading", "Writing"],
  5: ["Requirement 1", "Requirement 2"],
};

const route = useRoute();
const stepData = ref({});
const loading = ref(true);
const useMockData = ref(false); // Set this to true to use mock data instead of fetching

const fetchData = async () => {
  if (useMockData.value) {
    stepData.value = mockData;
    loading.value = false;
    return;
  }
  try {
    const response = await axios.get(`http://localhost:8080/api/v1/education-modules/${route.params.id}`);
    stepData.value = response.data;
  } catch (error) {
    console.error("Failed to fetch data:", error);
    stepData.value = mockData;
  } finally {
    loading.value = false;
  }
};

onMounted(fetchData);

const submitForm = () => {
  console.log("Sending for review:", stepData.value);
  // emit('send-for-review', stepData.value);
};
</script>

<style scoped>
.scrollable-row {
  display: flex;
  flex-wrap: nowrap;
  overflow-x: auto;
  -webkit-overflow-scrolling: touch;
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
  font-size: 1.5rem;
  font-weight: bold;
  background-color: red;
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
  height: 100vh;
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
  margin-top: 20px;
}

.badge-image {
  width: 100px;
  height: 100px;
  border-radius: 50%;
}



.info-section {
  background-color: #f0f0f0;
  border-radius: 10px;
  margin-bottom: 15px;
  padding: 15px;
}

.info-section h2 {
  color: #007bff;
  margin-bottom: 10px;
}

.info-section p,
.info-section ul {
  color: #333;
  text-align: left;
}

.info-section ul {
  list-style: inside circle;
  padding-left: 0;
}

.send-button {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 15px;
  border-radius: 20px;
  font-size: 1rem;
  cursor: pointer;
  width: 100%;
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
}

.loading-spinner {
  border: 16px solid #f3f3f3;
  border-top: 16px solid #007bff;
  border-radius: 50%;
  width: 120px;
  height: 120px;
  animation: spin 2s linear infinite;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

@keyframes spin {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}
</style>
