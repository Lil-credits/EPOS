<template>
    <div class="container">
        <DetailDescription 
        class="info-section"
        description="This is a test description"/>
    </div>
</template>

<script>
import DetailDescription from '@/components/page-components/module/detailDescription.vue';

export default {
    name: 'ComponentTest',
    components: {
        DetailDescription
    },
}
</script>

<style scoped>
  .container {
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
  }

  .info-section {
  background-color: #f0f0f0; /* Light grey background */
  border-radius: 10px;
  margin-bottom: 15px;
  padding: 15px;
}
  
  </style>