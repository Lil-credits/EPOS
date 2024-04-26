<template>
    <div class="step-three-container">
      <div class="header">
        <button @click="goBack" class="back-button">&lt;</button>
      </div>
  
      <div class="content">
        <img v-if="uploadedImage" :src="uploadedImage" alt="Uploaded" class="uploaded-image"/>
        <div v-else class="no-image">No image uploaded</div>
        <div class="subject-banner">{{ subjectBanner }}</div>
  
        <textarea v-model="description" placeholder="Type here the description..." class="description-textarea"></textarea>
  
        <button @click="goToNextStep" class="next-button">Next</button>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        uploadedImage: null,
        subjectBanner: '',
        description: ''
      };
    },
    beforeRouteEnter(to, from, next) {
      next(vm => {
        vm.uploadedImage = to.params.uploadedImage;
        vm.subjectBanner = to.params.subjectBanner;
      });
    },
    methods: {
      goBack() {
        this.$router.go(-1);
      },
      goToNextStep() {
        if (this.description) {
          this.$router.push({
            name: 'StepFour', // Assuming the next step is named 'StepFour'
            params: {
              // Pass all the necessary data
              uploadedImage: this.uploadedImage,
              subjectBanner: this.subjectBanner,
              description: this.description
            }
          });
        } else {
          alert('Please enter a description.');
        }
      }
    }
  };
  </script>
  
  <style scoped>
.step-three-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 20px;
  height: 100vh; /* Full height */
  box-sizing: border-box;
}

.header .back-button {
  margin-bottom: 20px;
  font-size: 2rem; /* Larger back arrow */
  background: none;
  border: none;
  cursor: pointer;
}

.content {
  width: 100%;
  max-width: 400px; /* Maximum width */
  display: flex;
  flex-direction: column;
  align-items: center;
}

.content .uploaded-image {
  width: 100px; /* Adjust based on your design */
  height: 100px; /* Adjust based on your design */
  border-radius: 50%; /* Make it round */
  margin-bottom: 1rem;
}

.subject-banner {
  background-color: #d9534f; /* Red background color */
  color: white;
  padding: 0.5rem 1rem;
  border-radius: 1rem; /* Rounded edges */
  margin-bottom: 1rem; /* Space below the banner */
  font-size: 0.8rem;
}

.content .description-textarea {
  width: 100%;
  height: 150px; /* Adjust based on your design */
  margin-bottom: 1rem;
  padding: 0.5rem;
  border: 2px solid #ccc; /* Border color */
  border-radius: 0.5rem; /* Border roundness */
  resize: vertical; /* Allow vertical resizing */
}

.next-button {
  padding: 1rem 2rem;
  background-color: #3498db; /* Blue background color */
  color: white;
  border: none;
  border-radius: 0.5rem; /* Border roundness */
  cursor: pointer;
  font-size: 1rem;
  width: 100%; /* Full width button */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Subtle shadow for depth */
  transition: background-color 0.3s ease; /* Smooth transition for hover effect */
}

.next-button:hover {
  background-color: #2980b9; /* Slightly darker blue on hover */
}

/* If a no-image state needs styling: */
.no-image {
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 1rem;
  width: 100px; /* Same as the image */
  height: 100px; /* Same as the image */
  border-radius: 50%;
  background-color: #ecf0f1; /* Placeholder background color */
  color: #3498db; /* Placeholder text/icon color */
  margin-bottom: 1rem;
}
</style>

  