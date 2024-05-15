<template>
    <header>Modules Overview</header>
    <div>
        <div class="overview">
            <v-btn class="circle-button" fab small color="primary">
                <v-icon>mdi-circle</v-icon>
                <img src="../assets/Picture1.png" alt="Image" class="circle-image">
                <span>Button 1</span>
            </v-btn>
            <v-btn class="circle-button" fab small color="primary">
                <v-icon>mdi-circle</v-icon>
                <img src="../assets/Picture15.png" alt="Image" class="circle-image">
                <span>Button 2</span>
            </v-btn>
            <v-btn class="circle-button" fab small color="primary">
                <v-icon>mdi-plus</v-icon>
                <span>Button 3</span>
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
.circle-button {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    width: 100px;
    height: 100px;
    border-radius: 50%;
    background-color: #2196f3;
    color: white;
    font-size: 14px;
    font-weight: bold;
    text-align: center;
    cursor: pointer;
}
.circle-image {
    width: 50px;
    height: 50px;
    margin-bottom: 10px;
}
</style>