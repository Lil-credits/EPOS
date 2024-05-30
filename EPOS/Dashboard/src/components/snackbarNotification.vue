<template>
    <div>
        <v-snackbar
        v-model="snackbar">
            {{ message }}
        </v-snackbar>
    </div>
</template>

<script>
export default {
    name: 'SnackbarNotification',
    props {
        message: {
            type: String,
            required: true
        },
        color: {
            type: String,
            required: false,
            default: 'success'
        }
    
    }
}
</script>

<style scoped>
/* Your component styles go here */
</style>