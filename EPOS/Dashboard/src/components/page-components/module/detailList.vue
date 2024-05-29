<template>
    <div class="info-section">
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
}
</script>

<style scoped>
/* Your component's CSS styles go here */
</style>