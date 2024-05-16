<template>
  <div class="circle-container">
    <v-btn class="circle-btton module" fab small @click="$router.push('modules/' + moduleId)">
      <img :src="image" alt="" class="circle-image">
    </v-btn>
    <div class="circle-text">{{ title }}</div>
  </div>
</template>

<script>
export default {
  name: 'CircleButton',
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
}
.circle-button {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100px;
  height: 100px;
  border-radius: 50%;
  font-size: 14px;
  font-weight: bold;
  text-align: center;
  cursor: pointer;
  position: relative;
}
.circle-image {
  width: 60%;
  height: 60%;
  border-radius: 50%;
}
.circle-text {
  margin-top: 10px;
  font-size: 14px;
  text-align: center;
}
</style>
