<template>
  <div class="step-three">
    <div class="header">
    <div class="header">
      <div class="image-container">
        <img :src="stepData[1]['badgeImage']" alt="Course Badge" class="badge-image"/>
      </div>
      <div class="course-title">{{ stepData[1]['courseTitle'] }}</div>
    </div>
    </div>
    <div class="description-container">
      <label for="course-description" class="description-label">Description</label>
      <textarea 
        id="course-description"
        rows="10"
        placeholder="Type here the description..."
        v-model="courseDescription"
      ></textarea>

    </div>

    <button class="next-button" @click="submitStep">Next</button>
  </div>
</template>

<script setup>
import { ref, defineProps, defineEmits } from 'vue';

defineProps({
  stepData: Object
});

const courseDescription = ref('');
const emit = defineEmits(['update-step-data', 'go-back']);

function submitStep() {
  // You can validate the description or perform any other operations here
  if (courseDescription.value) {
    emit('update-step-data', { step: 3, data: { description: courseDescription.value } });
  } else {
    // Handle the case where the description is empty
  }
}
</script>

<style scoped>
.step-three {
  max-width: 500px;
  margin: auto;
  text-align: center;
  display: flex;
  flex-direction: column;
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

.description-container {
  margin-bottom: 20px;
}

.description-label {
  display: block;
  font-size: 1.25rem;
  margin-bottom: 10px;
}

textarea {
  width: 100%;
  padding: 15px;
  border-radius: 25px;
  border: 2px solid #007bff;
  resize: none; /* Prevent resizing of the textarea */
  font-size: 1rem;
}

.next-button {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 15px 30px;
  border-radius: 25px;
  font-size: 1.25rem;
  cursor: pointer;
  width: 100%;
  position: fixed; /* Fixed positioning relative to the viewport */
  left: 0;
  bottom: 0; /* Anchored to the bottom of the viewport */
  box-sizing: border-box; /* Makes sure the padding doesn't affect the final width */
}

</style>
