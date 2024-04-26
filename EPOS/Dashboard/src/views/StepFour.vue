<template>
    <div class="step-four-container">
      <div class="header">
        <button @click="goBack" class="back-button">&lt;</button>
      </div>
  
      <div class="content">
        <img v-if="uploadedImage" :src="uploadedImage" alt="Uploaded" class="uploaded-image"/>
        <div v-else class="no-image">No image uploaded</div>
        <div class="subject-banner">{{ subjectBanner }}</div>
  
        <div class="skills-section">
          <h3>Skills</h3>
          <ul class="skills-list">
            <li v-for="(skill, index) in skills" :key="index" class="skill-item">
              <input
                type="text"
                v-model="skills[index]"
                placeholder="Type here more Skills..."
                class="skill-input"
              />
              <button v-if="skills.length > 1" @click="removeSkill(index)" class="remove-skill-btn">
                &times;
              </button>
            </li>
          </ul>
          <button @click="addSkill" class="add-skill-btn">Add Skill +</button>
        </div>
  
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
        skills: [''], // Start with one empty skill input
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
      addSkill() {
        this.skills.push('');
      },
      removeSkill(index) {
        if (this.skills.length > 1) {
          this.skills.splice(index, 1);
        } else {
          alert("You must have at least one skill.");
        }
      },
      goToNextStep() {
        if (this.skills.some(skill => skill.trim() === '')) {
          alert("Please fill in all skill fields or remove unused ones.");
          return;
        }
        this.$router.push({
          name: 'StepFive', // The route name for the next step
          params: {
            uploadedImage: this.uploadedImage,
            subjectBanner: this.subjectBanner,
            skills: this.skills.filter(skill => skill.trim() !== '') // Only pass non-empty skills
          }
        });
      }
    }
  };
  </script>
  
  <style scoped>
.step-four-container {
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

.skills-section {
  width: 100%;
}

.skills-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.skill-item {
  display: flex;
  align-items: center;
  margin-bottom: 1rem;
}

.skill-input {
  flex-grow: 1;
  padding: 0.5rem;
  margin-right: 0.5rem;
  border: 2px solid #ccc;
  border-radius: 0.5rem;
}

.remove-skill-btn {
  background-color: #e74c3c;
  color: white;
  border: none;
  padding: 0.5rem;
  border-radius: 0.5rem;
  cursor: pointer;
}

.add-skill-btn {
  background-color: #3498db;
  color: white;
  padding: 0.5rem 1rem;
  border: none;
  border-radius: 0.5rem;
  cursor: pointer;
  width: 100%; /* Full width button */
  margin-top: 1rem; /* Space between the last skill item and the button */
}

.next-button {
  padding: 1rem 2rem;
  background-color: #3498db;
  color: white;
  border: none;
  border-radius: 0.5rem;
  cursor: pointer;
  font-size: 1rem;
  width: 100%; /* Full width button */
  margin-top: 2rem; /* Space between add skill button and next button */
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
