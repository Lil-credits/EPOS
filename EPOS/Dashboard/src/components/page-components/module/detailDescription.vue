<template>
      <div class="description">
        <h2>Description</h2>
        <p>{{ description }}</p>
      </div>
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
  color: #007bff; /* Blue color for section headers */
  margin-bottom: 10px;
}

.description p {
    color: #333; /* Dark text for content */
    text-align: left;
}


</style>