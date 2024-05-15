<template>
    <header>Modules Overview</header>
    <div>
        <div class="overview">
            <v-btn class="circle-button" fab small color="primary">
            <v-icon>mdi-circle</v-icon>
            <img src="../assets/Picture 1.png" alt="Image" class="circle-image">
            </v-btn>
            <v-btn class="circle-button" fab small color="primary">
            <v-icon>mdi-circle</v-icon>
            <img src="your-image-url" alt="Image" class="circle-image">
            </v-btn>
            <v-btn class="circle-button" fab small color="primary">
            <v-icon>mdi-circle</v-icon>
            <img src="your-image-url" alt="Image" class="circle-image">
            </v-btn>
        </div>
        </div>
</template>


<script>
export default {
    name: 'ModuleOverview',
    data() {
        return {
            // Add your data properties here
        };
    },
    methods: {
        // Add your methods here
    },
    mounted() {
        // Add any initialization logic here
    }
};
</script>

<style scoped>
header {
    margin-top: 20px;
    display: flex;
    justify-content: center;
    font-size: 24px;
    font-weight: bold;
    margin-bottom: 20px;
}
.overview {
    display: flex;
    justify-content: space-around;
    margin-top: 20px;
}
</style>