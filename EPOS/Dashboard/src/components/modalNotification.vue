<template>
    <div>
        <v-dialog v-model="dialogVisible" max-width="500px" isActive>
            <v-card>
                <v-card-title>
                    {{ title }}
                </v-card-title>
                <v-card-text>
                    <slot></slot>
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>

                    <v-btn
                        text="Close Dialog"
                        @click="isActive.value = false">
                    </v-btn>                
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