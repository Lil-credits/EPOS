<template>
    <div>
        <v-dialog v-model="dialogVisible" max-width="500px">
            <v-card>
                <v-card-title>
                    <!-- Your dialog title goes here -->
                </v-card-title>
                <v-card-text>
                    <!-- Your dialog content goes here -->
                </v-card-text>
                <v-card-actions>
                    <!-- Your dialog actions go here -->
                </v-card-actions>
            </v-card>
        </v-dialog>
    </div>
</template>

<script>
export default {
    name: 'ModalNotification',
    props: {
        title: {
            type: String,
            required: true
        },
        content: {
            type: String,
            required: true
        },
    }
}
</script>

<style scoped>
.container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

.dialog-title {
    font-size: 20px;
    font-weight: bold;
}

.dialog-content {
    margin-top: 20px;
}

.dialog-actions {
    margin-top: 20px;
    display: flex;
    justify-content: flex-end;
}
</style>