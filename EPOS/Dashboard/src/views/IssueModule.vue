<template>
  <pageHeading title="ISSUE" />
  <div class="container">

    <v-dialog v-model="isDialogVisible" max-width="500px">
      <v-card>
        <v-card-title>
          <span class="headline">Collect Credential</span>
        </v-card-title>
        <v-card-text>
          <qrcodeGenerator :url=invitationUrl />
        </v-card-text>
        <v-card-actions>
          <v-btn color="#00A4E7" text @click="closeDialog">Close</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <div class="summary-page">
      <div class="header">
        <div class="image-container">
          <img :src=imageUrl alt="Course Badge" class="badge-image" />
        </div>
        <div class="course-title">{{ title }}</div>
      </div>
    </div>

    <v-form @submit.prevent="issueCredential" class="issue-credential-form">
      <v-text-field v-model="userId" label="Student ID" type="number" required></v-text-field>
      <v-btn type="submit" color="#00A4E7">Reward</v-btn>
    </v-form>
  </div>

  <v-snackbar
    v-model="snackbar"
    class="snackbar">
    Error issuing credential

    <template v-slot:actions> 
      <v-btn 
      color="red"
      variant="text"
      @click="snackbar = false">
      Close
    </v-btn>
    </template>
  </v-snackbar>
</template>

<script>
import { ref, onMounted } from "vue";
// import axios from "axios";
import { useRoute } from "vue-router";
// import ModalComponent from "../components/page-components/module/old/ModalComponent.vue";
import qrcodeGenerator from "@/components/qrcodeGenerator.vue";
import pageHeading from "@/components/pageHeading.vue";
import api from "@/api/api";

export default {
  name: 'IssueModule',
  components: {
    // ModalComponent,
    pageHeading,
    qrcodeGenerator,
  },
  setup() {
    const route = useRoute();
    const id = route.params.id;
    const responseData = ref({});
    const invitationUrl = ref("");
    const userId = ref("");
    const title = ref("");
    const imageUrl = ref("");
    const isDialogVisible = ref(false);
    const snackbar = ref(false);


    const issueCredential = async () => {
      const payload = {
        subjectId: parseInt(userId.value),
        issuerId: 1,
        educationModuleVersionId: responseData.value.versions[0].id
      };
      try {
        const response = await api.issueCredential(payload);
        // const response = await axios.post('http://localhost:8080/api/v1/micro-credentials/issue', payload);
        invitationUrl.value = response.data.invitationLink;
        isDialogVisible.value = true;
      } catch (error) {
        console.log("Submission failed: " + error);
        snackbar.value = true;
      }

      };

    const closeDialog = () => {
      isDialogVisible.value = false;
    };

    const fetchData = async () => {
      const response = await api.getModule(id);
      // const response = await axios.get(`http://localhost:8080/api/v1/education-modules/${id}`);
      responseData.value = response.data;
      title.value = response.data['versions'][0]['name'];
      imageUrl.value = response.data['versions'][0]['imageData'];
    };

    onMounted(fetchData);

    return {
      invitationUrl,
      userId,
      issueCredential,
      responseData,
      title,
      imageUrl,
      isDialogVisible,
      closeDialog,
      snackbar,
    };
  }
};
</script>

<style scoped>
.summary-page {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  max-width: 500px;
  margin: auto;
  padding: 20px;
  text-align: center;
}

.header {
  margin-bottom: 20px;
}

.image-container {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

.badge-image {
  max-width: 100px;
  border-radius: 50%;
}

.course-title {
  background-color: red;
  color: white;
  margin: 0 auto;
  padding: 5px 15px;
  border-radius: 20px;
  display: inline-block;
  margin-bottom: 20px;
}

.issue-credential-form {
  display: flex;
  flex-direction: column;
  align-items: center;
  max-width: 400px;
  width: 100%;
  margin: 0 auto;
}

.modal {
  z-index: 99;
  position: absolute;
}

.url-box {
  display: flex;
  justify-content: space-between;
  align-items: center;
  cursor: pointer;
  color: blue;
  text-decoration: underline;
  border: 2px solid gray;
  border-radius: 15px;
  padding: 10px;
  background-color: rgb(224, 224, 224);
  position: relative;
  overflow: hidden;
}

.copy-text-hint {
  font-size: 0.8em;
  margin-left: 10px;
  color: gray;
  text-align: right;
}

.url-box.copied {
  border-color: green;
  background-color: rgb(133, 255, 133);
}

.snackbar {
  margin-bottom: 2em;
  display: flex;
  align-content: center;
}
</style>
