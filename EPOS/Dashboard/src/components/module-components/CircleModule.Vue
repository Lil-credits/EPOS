<template>
  <div class="circle-container">
    <v-btn :class="buttonClass" fab small>
      <img v-if="imageSrc" :src="imageSrc" class="circle-image" :alt="altText">
      <v-icon v-else>{{ icon }}</v-icon>
    </v-btn>
    <div class="circle-text">{{ text }}</div>
  </div>
</template>

<script>
export default {
  name: 'CircleButton',
  props: {
    buttonClass: {
      type: String,
      required: true
    },
    imageSrc: {
      type: String,
      default: ''
    },
    altText: {
      type: String,
      default: 'Circle Image'
    },
    text: {
      type: String,
      required: true
    },
    icon: {
      type: String,
      default: 'mdi-plus'
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
