<template>
  <div class="step-two-container">
    <div class="header">
      <button @click="goBack" class="back-button">&lt;</button>
    </div>

    <div class="content">
      <img v-if="uploadedImage" :src="uploadedImage" alt="Uploaded" class="uploaded-image"/>
      <div v-else class="no-image">No image uploaded</div>
      <div class="subject-banner">{{ subjectBanner }}</div>

      <form @submit.prevent="submitForm">
        <!-- Replace with your actual options -->
        <select v-model="selectedLanguage" required>
          <option disabled value="">Language</option>
          <option>English</option>
          <option>Dutch</option>
        </select>

        <select v-model="selectedStudyLoad" required>
          <option disabled value="">Study Load</option>
          <option>Full-time</option>
          <option>Part-time</option>
        </select>

        <select v-model="selectedEQF" required>
          <option disabled value="">Indicative EQF</option>
          <option>Level 4</option>
          <option>Level 5</option>
        </select>

        <!-- More selects can be added here -->

        <button @click="goToNextStep" class="next-button">Next</button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    uploadedImage: String,
    subjectBanner: String
  },
  methods: {
    goBack() {
      this.$router.go(-1);
    },
    goToNextStep() {
      // Add your logic to go to the next step, e.g., validation or state management
      this.$router.push('/step-three'); // Replace with your actual step 3 route
    }
  }
};
</script>

<style scoped>
.step-two-container {
  padding: 20px;
  max-width: 768px; /* Match the width of the device in the image */
  margin: auto;
  display: flex;
  flex-direction: column;
}

.header .back-button {
  font-size: 32px; /* Larger back arrow */
  color: #333; /* Dark grey color */
  padding: 10px;
  margin-bottom: 20px;
  background: none;
  border: none;
  cursor: pointer;
}

.content {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.content .uploaded-image {
  width: 120px; /* Match your design */
  height: 120px; /* Match your design */
  border-radius: 50%; /* Make it round */
  margin-bottom: 20px;
}

.subject-banner {
  background-color: #d9534f; /* Red background color */
  color: white;
  padding: 5px 20px;
  border-radius: 20px; /* Rounded edges for banner */
  font-size: 16px;
  margin-bottom: 30px; /* Space below the banner */
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  max-width: 90%;
}

form {
  width: 100%;
}

form select {
  width: 100%;
  padding: 15px;
  margin-bottom: 15px;
  background-color: #3498db; /* Blue background color */
  color: white;
  border: none;
  border-radius: 25px; /* Roundness of select inputs */
  -webkit-appearance: none; /* Removes default chrome and safari style */
  -moz-appearance: none; /* Removes default style Firefox */
  appearance: none; /* Removes default style */
  position: relative;
}

form .next-button {
  width: 100%;
  padding: 15px;
  background-color: #3498db; /* Blue background color */
  color: white;
  border: none;
  border-radius: 25px; /* Roundness of the button */
  cursor: pointer;
}

/* Style for when no image is uploaded */
.no-image {
  width: 120px; /* Match your design */
  height: 120px; /* Match your design */
  border-radius: 50%; /* Make it round */
  background-color: #ecf0f1; /* Light grey color */
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 20px;
  font-size: 24px;
  color: #3498db; /* Blue color for the '+' icon */
}

/* Custom arrow icon */
form select {
  background-image: url('data:image/svg+xml;utf8,<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="white"><path d="M7 10l5 5 5-5z"/></svg>');
  background-repeat: no-repeat;
  background-position: right 10px top 50%;
  background-size: 24px 24px;
}

/* Additional styles for the 'Add +' button if needed */
.add-button {
  background-color: white;
  color: #3498db; /* Blue text color */
  border: 2px solid #3498db; /* Blue border */
  padding: 15px;
  margin-bottom: 15px;
  border-radius: 25px; /* Roundness of the button */
  font-size: 18px;
}
</style>
