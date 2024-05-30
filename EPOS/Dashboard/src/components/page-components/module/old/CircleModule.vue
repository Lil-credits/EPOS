<template>
  <div class="circle-container">
    <v-btn class="circle-button module" fab small @click="$router.push('/modules/' + moduleId)">
      <img :src="image" alt="" class="circle-image">
    </v-btn>
    <div class="circle-text">{{ title }}</div>
  </div>
</template>

<script>
export default {
  name: 'CircleModule',
  props: {
    title: {
      type: String,
      required: true,
    },
    image: {
      type: String,
      required: true
    },
    moduleId: {
      type: String,
      required: true
    }
  }
};
</script>

<style scoped>
.circle-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
}
.circle-button {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: auto;
  font-size: 14px;
  font-weight: bold;
  text-align: center;
  cursor: pointer;
  position: relative;
  box-shadow: none; /* Remove shadow */
  border-radius: 0; /* Remove rounded corners */
}
.circle-image {
  width: 100%;
  height: auto;
  object-fit: cover;
  border-radius: 0; /* Ensure no rounded corners */
  box-shadow: none; /* Remove shadow */
}
.circle-text {
  margin-top: 10px;
  font-size: 14px;
  text-align: center;
  max-width: 100px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>
