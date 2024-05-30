<template>
<v-expansion-panels>
  <v-expansion-panel
    title="Description"
   :text=description
  >
  </v-expansion-panel>
</v-expansion-panels>
</template>

<script>
export default {
    name: 'DetailDescription',
    props: {
        description: {
            type: String,
            required: true
        }
    }
}
</script>

<style scoped>
.description h2 {
  color: #333; /* Blue color for section headers */
  margin-bottom: 10px;
  text-align: center;
}

.description p {
  color: #333; /* Dark text for content */
  text-align: center;
  font-size: 16px;
  max-height: 100px;
  text-overflow: ellipsis;
  overflow: hidden;
  transition: max-height 0.3s ease;
}

.description.folded p {
  max-height: none;
}

.description.folded::after {
  content: '...';
  color: #333;
  cursor: pointer;
  display: block;
  text-align: center;
  margin-top: 10px;
}

.description.folded::after:hover {
  text-decoration: underline;
}

.description.unfolded::after {
  content: '';
  display: none;
}
</style>