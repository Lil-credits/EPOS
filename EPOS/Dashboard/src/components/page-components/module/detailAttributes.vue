<template>
  <div>
    <v-container style="padding: 0;">
      <v-sheet class="mx-auto">
        <v-slide-group
          id="slide-group"
          v-model="model"
          class="py-2"
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
              style="border: 1px solid black;"
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

<style>
.info-sectionSlider {
  color: white;
  background-color: #00A4E7;
  border-radius: 10px;
  padding: 15px;
  text-align: center;
  width: 100%;
}

.slidegroup > .v-slide-group__next,
.slidegroup > .v-slide-group__prev {
  max-width: 30px !important;
  margin: 0 -10px !important;
}
</style>
