<template>
    <div class="summary-container">
      <div class="header">
        <button @click="goBack" class="back-button">&lt;</button>
      </div>
  
      <div class="content">
        <div class="summary-section">
          <img v-if="uploadedImage" :src="uploadedImage" alt="Uploaded" class="summary-image"/>
          <div class="badge-banner">{{ subjectBanner }}</div>
  
          <div class="summary-details">
            <div class="detail-item">
              <h4>Study Load</h4>
              <p>{{ studyLoad }}</p>
            </div>
            <div class="detail-item">
              <h4>Language</h4>
              <p>{{ language }}</p>
            </div>
            <div class="detail-item">
              <h4>Indicative EQF</h4>
              <p>{{ eqf }}</p>
            </div>
          </div>
  
          <div class="summary-description">
            <h4>Description</h4>
            <p>{{ description }}</p>
          </div>
  
          <div class="summary-skills">
            <h4>Skills</h4>
            <ul>
              <li v-for="skill in skills" :key="skill">{{ skill }}</li>
            </ul>
          </div>
  
          <div class="summary-admission">
            <h4>Admission Requirements</h4>
            <ul>
              <li v-for="requirement in admissionRequirements" :key="requirement">{{ requirement }}</li>
            </ul>
          </div>
  
          <button @click="sendForReview" class="review-button">Send for review</button>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  // The script section remains the same as the previously provided example.
  </script>
  
  <style scoped>
.summary-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
  height: 100vh; /* Use full viewport height to center content */
  box-sizing: border-box;
}

.header .back-button {
  font-size: 2rem; /* Larger back arrow */
  background: none;
  border: none;
  cursor: pointer;
  margin-bottom: 20px;
}

.summary-section {
  text-align: center;
  width: 100%;
}

.summary-section img {
  max-width: 100px; /* Adjust to your design */
  border-radius: 50%;
  margin-bottom: 1rem;
}

.badge-banner {
  background-color: #d9534f; /* Your banner color */
  color: white;
  padding: 0.5rem 1rem;
  border-radius: 20px; /* Rounded corners for the banner */
  margin-bottom: 2rem;
  display: inline-block;
}

.summary-details {
  border-top: 2px solid #ccc;
  border-bottom: 2px solid #ccc;
  padding: 1rem;
  margin-bottom: 1rem;
}

.detail-item h4 {
  margin: 0.5rem 0;
  color: #333;
}

.detail-item p {
  background-color: #e7f3ff; /* Light blue background for items */
  padding: 0.5rem 1rem;
  border-radius: 10px; /* Rounded corners for items */
  margin-bottom: 0.5rem; /* Space between items */
}

.summary-description,
.summary-skills,
.summary-admission {
  text-align: center;
  margin-bottom: 1rem;
}

.summary-description h4,
.summary-skills h4,
.summary-admission h4 {
  margin-bottom: 0.5rem;
}

.review-button {
  background-color: #5cb85c; /* Button color */
  color: white;
  padding: 1rem 2rem;
  border-radius: 20px; /* Rounded corners for the button */
  font-size: 1rem;
  border: none;
  cursor: pointer;
  margin-top: 1rem;
  width: 100%; /* Full width button */
}
</style>
