<template>
    <div>
        <v-container style="padding: 0;">
        <v-row align="start" class="scrollable-row">
          <v-col
            cols="5"
            md="4"
            v-for="(value, key) in attributes"
            :key="key"
          >
            <div class="info-section">
              <h2>{{ key }}</h2>
              <p>{{ value }}</p>
            </div>
          </v-col>
        </v-row>
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
.info-section h2{
  background-color: #f0f0f0; /* Light grey background */
  border-radius: 10px;
  margin-bottom: 15px;
  padding: 15px;
}</style>