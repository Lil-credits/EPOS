<template>
<div class="list">
    <div class="skills-container">
      <h2>Skills</h2>
      <ul class="skills-list">
        <li v-for="(skill, index) in skills" :key="index">{{ skill }}</li>
      </ul>
      <input type="text" v-model="newSkill" placeholder="Type here more Skills..." @keyup.enter="addSkill">
    </div>
</div>
  </template>
  
  <script>

export default {
    data() {
        return {
            skills: [],
            newSkill: ''
        };
    },
    methods: {
        addSkill() {
            if (this.newSkill.trim() !== '') {
                this.skills.push(this.newSkill);
                this.newSkill = '';
            }
        }
    }
}

  
  </script>
  
  <style scoped>
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
  
  </style>
  