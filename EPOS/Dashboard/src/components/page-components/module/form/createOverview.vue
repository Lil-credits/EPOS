<template>
    <div>
        <detailHeading title="title"/>
        <detailDescription/>
        <detailAttributes/>
        <DetailList/>
    </div>
</template>

<script>
import detailHeading from '../detailHeading.vue';
import detailDescription from '../detailDescription.vue';
import detailAttributes from '../detailAttributes.vue';
import detailAttributes from '../detailAttributes.vue';
import DetailList from '../detailList.vue';

export default {
    name: 'YourComponentName',
    components: {
        detailHeading,
        detailDescription,
        detailAttributes,
        DetailList
    },
    data() {
        return {
            // Your component's data goes here
        };
    },
    methods: {
        // Your component's methods go here
    },
    mounted() {
        // Code to run when the component is mounted goes here
    },
};
</script>

<style scoped>
/* Your component's styles go here */
</style>