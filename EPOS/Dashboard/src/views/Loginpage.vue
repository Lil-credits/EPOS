<template>
    <v-container>
        <v-row justify="center">
            <v-col cols="12" sm="8" md="6">
                <v-card>
                    <v-card-title class="text-center">
                        <h2>Login</h2>
                    </v-card-title>
                    <v-card-text>
                        <v-form @submit.prevent="login">
                            <v-text-field v-model="username" label="Username"></v-text-field>
                            <v-text-field v-model="password" label="Password" type="password"></v-text-field>
                            <v-btn type="submit" color="primary">Login</v-btn>
                        </v-form>
                    </v-card-text>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
export default {
    data() {
        return {
            username: '',
            password: ''
        }
    },
    methods: {
        login() {
            // Make a POST request to the login endpoint with the username and password
            // You can use a library like axios to make the request
            // Example:
            // axios.post('/auth/login', {
            //   username: this.username,
            //   password: this.password
            // }).then(response => {
            //   // Handle successful login
            // }).catch(error => {
            //   // Handle login error
            // });
        }
    }
}
</script>

<style scoped>
/* Add custom styles here */
</style>