<template>
  <div class="header">
    <div class="image-container">
      <img
        :src="imageUrl"
        alt="Course Badge"
        class="badge-image"
      />
    </div>
    <div class="course-title">{{ title }}</div>
  </div>
</template>

<script>
export default {
  name: 'DetailHeading',
  props: {
    imageUrl: {
      type: String,
      required: true
    },
    title: {
      type: String,
      required: true
    }
  }
}
</script>

<style scoped>
.header {
  margin-bottom: 20px;
}

.image-container {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

.badge-image {
  max-width: 100px;
  border-radius: 50%;
  height: 100px;
}

.course-title {
  background-color: red; /* Adjust to match your brand color */
  color: white;
  padding: 5px 15px;
  border-radius: 20px;
  text-align: center; /* Add this line to center the text */
  margin: 0 auto; /* Add this line to center the box */
}
</style>