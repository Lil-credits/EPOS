<template>
    <div class="list" :class="{folded: folded}" @click="toggleFold">
        <h2>{{ listTitle }}</h2>
        <ul>
            <li v-for="requirement in list" :key="requirement">
              {{ requirement }}
            </li>
        </ul>
    </div>
</template>

<script>
export default {
    name: 'DetailList',
    props: {
        listTitle: {
            type: String,
            required: true
        },
        list: {
            type: Array,
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
.list h2 {
    text-align: center;
    margin-bottom: 10px;
}
.list ul {
    padding-left: 10px;
    overflow: hidden;
    max-height: 100px;
    text-overflow: ellipsis;
    transition: max-height 0.3s ease;
}

.list.folded ul {
    max-height: none;
}

.list.folded::after {
    cursor: pointer;
    display: block;
    text-align: center;
}
</style>