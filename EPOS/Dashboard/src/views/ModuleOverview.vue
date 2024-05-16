<template>
    <div>
        <v-icon class="back-arrow" @click="goBack">mdi-arrow-left</v-icon>
        <div class="overview">
            <v-btn class="circle-button sales-engineer" fab small>
                <img src="picture1.png" alt="Image" class="circle-image">
                <div class="circle-text">Sales Engineer</div>
            </v-btn>
            <v-btn class="circle-button process-engineer" fab small>
                <v-icon class="icon-placeholder">mdi-image</v-icon>
                <div class="circle-text">Process Engineer</div>
            </v-btn>
            <v-btn class="circle-button create-own" fab small>
                <v-icon>mdi-plus</v-icon>
                <div class="circle-text">Create your own</div>
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
        goBack() {
            // Logic to go back to the previous page
        }
    },
    mounted() {
        // Add any initialization logic here
    }
};
</script>

<style scoped>
.back-arrow {
    margin: 10px;
    cursor: pointer;
    font-size: 24px;
}
.overview {
    display: flex;
    justify-content: space-around;
    align-items: center;
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
    font-size: 14px;
    font-weight: bold;
    text-align: center;
    cursor: pointer;
}
.sales-engineer {
    background-color: #f44336;
    color: white;
}
.process-engineer {
    background-color: #f44336;
    color: white;
}
.create-own {
    background-color: #90a4ae;
    color: black;
}
.icon-placeholder {
    font-size: 40px;
    margin-bottom: 10px;
}
.circle-text {
    margin-top: 10px;
}
</style>
