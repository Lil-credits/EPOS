<template>
  <div class="issue-credential-container">
    <v-dialog v-model="dialog" max-width="600px">
      <v-card>
        <v-card-title class="headline">Invitation Link</v-card-title>
        <v-card-text>{{ invitationLink }}</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary" text @click="dialog = false">Close</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    
    <v-form @submit.prevent="issueCredential" class="issue-credential-form">
      <v-text-field
        v-model="userId"
        label="User ID"
        type="number"
        required
      ></v-text-field>
      <v-text-field
        v-model="moduleId"
        label="Module ID"
        type="number"
        required
      ></v-text-field>
      <v-btn type="submit" color="primary">Submit</v-btn>
    </v-form>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';

const userId = ref('');
const moduleId = ref('');
const dialog = ref(false);
const invitationLink = ref('');

const issueCredential = async () => {
  try {
    const response = await axios.post('http://localhost:8080/api/v1/micro-credentials/issue', {
      userId: userId.value,
      educationModuleId: moduleId.value
    });
    invitationLink.value = response.data.invitationLink;
    dialog.value = true;
  } catch (error) {
    console.error('Error issuing credential:', error);
    // Handle error appropriately
  }
};
</script>

<style scoped>
.issue-credential-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 20px;
}

.issue-credential-form {
  display: flex;
  flex-direction: column;
  align-items: center;
  max-width: 400px;
  width: 100%;
  margin: 0 auto;
}

.v-text-field {
  margin-bottom: 20px;
  width: 100%;
}

.v-btn {
  width: 100%;
}
</style>
