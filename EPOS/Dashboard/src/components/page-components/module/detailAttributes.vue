<template>
  <div>
    <v-container style="padding: 0;">
      <v-sheet
  class="mx-auto"
>
  <v-slide-group
    v-model="model"
    class="pa-2"
    mandatory
    show-arrows
  >
    <v-slide-group-item
      v-for="(value, key) in attributes"
      :key="key"
    >
      <v-card 
        height="100"
        width="150"
        class="info-sectionSlider"
      >
        <h2>{{ key }}</h2>
        <p>{{ value }}</p>
      </v-card>
    </v-slide-group-item>
  </v-slide-group>
</v-sheet>
    </v-container>
  </div>
</template>

<script>
export default {
  name: 'DetailAttributes',
  props: {
    attributes: {
      type: Object,
      required: false
    }
  }
}
</script>

<style scoped>
.info-sectionSlider {
  color: white;
  background-color: #00A4E7;
  border-radius: 10px;
  padding: 15px;
  text-align: center;
  width: 100%; /* Set the width to 100% */
}

</style>
