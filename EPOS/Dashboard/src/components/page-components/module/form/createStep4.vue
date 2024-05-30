<template>
    <div class="list">
        <div class="requirements-container">
      <h2>Admission Requirements</h2>
      <ul class="requirements-list">
        <li v-for="(requirement, index) in requirements" :key="index">{{ requirement }}</li>
      </ul>
      <input type="text" v-model="newRequirement" placeholder="Type here more Admission Requirements..." @keyup.enter="addRequirement">
    </div>
  </div>
      </template>
      
      <script>
    
    export default {
        data() {
            return {
                requirements: [],
                newRequirement: ''
            };
        },
        methods: {
            addRequirement() {
                if (this.newRequirement.trim() !== '') {
                    this.requirements.push(this.newRequirement);
                    this.newRequirement = '';
                }
            },
            submitStep() {
                // Add your logic for submitting the step here
            }
        }
    }
    
      
      </script>
      
      <style scoped>
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
      