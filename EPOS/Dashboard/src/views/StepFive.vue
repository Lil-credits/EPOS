<template>
    <div class="step-five-container">
      <div class="header">
        <button @click="goBack" class="back-button">&lt;</button>
      </div>
  
      <div class="content">
        <img v-if="uploadedImage" :src="uploadedImage" alt="Uploaded" class="uploaded-image"/>
        <div v-else class="no-image">No image uploaded</div>
        <div class="subject-banner">{{ subjectBanner }}</div>
  
        <div class="admission-requirements-section">
          <h3>Admission Requirements</h3>
          <ul class="requirements-list">
            <li v-for="(requirement, index) in admissionRequirements" :key="index" class="requirement-item">
              <input
                type="text"
                v-model="admissionRequirements[index]"
                placeholder="Type here more Admission Requirements..."
                class="requirement-input"
              />
              <button v-if="admissionRequirements.length > 1" @click="removeRequirement(index)" class="remove-requirement-btn">
                &times;
              </button>
            </li>
          </ul>
          <button @click="addRequirement" class="add-requirement-btn">Add Requirement +</button>
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
        admissionRequirements: [''], // Start with one empty admission requirement input
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
      addRequirement() {
        this.admissionRequirements.push('');
      },
      removeRequirement(index) {
        this.admissionRequirements.splice(index, 1);
      },
      goToNextStep() {
        // Proceed only if all requirement fields have been filled out
        if (this.admissionRequirements.every(requirement => requirement.trim())) {
          this.$router.push({
            name: 'SummaryPage', // The route name for the next step
            params: {
              uploadedImage: this.uploadedImage,
              subjectBanner: this.subjectBanner,
              admissionRequirements: this.admissionRequirements,
            }
          });
        } else {
          alert("Please fill in all admission requirement fields before proceeding.");
        }
      }
    }
  };
  </script>
  
  <style scoped>
.step-five-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
  height: 100vh; /* Full height of the viewport */
  box-sizing: border-box;
}

.header .back-button {
  font-size: 2rem; /* Larger size for the back arrow */
  background: none;
  border: none;
  cursor: pointer;
  margin-bottom: 20px;
}

.content .uploaded-image {
  width: 120px; /* Size of the badge */
  height: 120px; /* Size of the badge */
  border-radius: 50%; /* Circular shape */
  margin-bottom: 1rem;
}

.content .no-image {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 120px;
  height: 120px;
  border-radius: 50%;
  background-color: #ecf0f1; /* Light gray background for placeholder */
  color: #3498db; /* Icon color */
  font-size: 1rem;
  margin-bottom: 1rem;
}

.subject-banner {
  background-color: #d9534f; /* Red color for the banner */
  color: white;
  padding: 0.5rem 1rem;
  border-radius: 20px; /* Rounded corners for the banner */
  margin-bottom: 2rem;
  font-size: 1rem;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.admission-requirements-section h3 {
  color: #337ab7; /* Blue color for the section title */
  margin-bottom: 1rem;
}

.requirements-list {
  list-style-type: none;
  padding: 0;
  width: 100%;
}

.requirement-item {
  display: flex;
  align-items: center;
  margin-bottom: 1rem;
}

.requirement-input {
  flex-grow: 1;
  padding: 0.5rem;
  border: 2px solid #ccc;
  border-radius: 0.5rem;
  margin-right: 0.5rem;
}

.remove-requirement-btn {
  padding: 0.5rem;
  border: none;
  background-color: #e74c3c; /* Red color for delete button */
  color: white;
  border-radius: 0.5rem;
  cursor: pointer;
}

.add-requirement-btn {
  padding: 1rem 2rem;
  background-color: #3498db; /* Blue color for the add button */
  color: white;
  border: none;
  border-radius: 0.5rem;
  cursor: pointer;
  margin-top: 1rem; /* Margin above the add button */
  width: 100%; /* Full width button */
}

.next-button {
  padding: 1rem 2rem;
  background-color: #3498db; /* Blue color for the next button */
  color: white;
  border: none;
  border-radius: 0.5rem;
  cursor: pointer;
  font-size: 1rem;
  width: 100%; /* Full width button */
  margin-top: 2rem; /* Margin above the next button */
}
</style>
