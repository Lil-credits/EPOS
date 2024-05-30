<template>
  <div>
    <div class="description" :class="{folded: folded}"  @click="toggleFold" >
      <h2>Description</h2>
      <p>
        {{ description }}
      </p>
    </div>
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
    },
  data() {
  return {
    folded: false
  };
},
methods: {
  toggleFold() {
    this.folded = !this.folded;
    console.log(this.folded);
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
  color: #333;
  text-align: center;
  font-size: 16px;
  max-height: 100px;
  overflow: hidden;
  text-overflow: ellipsis;
  transition: max-height 0.3s ease;
}

.description.folded p {
  max-height: none;
}

.description.folded::after {
  color: #333;
  cursor: pointer;
  display: block;
  text-align: center;
  margin-top: 10px;
}

</style>