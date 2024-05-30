<template>

        <h2>{{ listTitle }}</h2>
        <div class="list">
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
}
</script>

<style scoped>
h2 {
    text-align: center;
}
.list {
padding-left: 5px;
}
</style>