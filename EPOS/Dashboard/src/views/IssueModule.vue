<template>
  <div class="container">
    <ModalComponent
      :visible="isModalVisible"
      title="Invite URL"
      @close="closeModal"
      class="modal"
    >
      <p class="url-box" :class="{ copied: isCopied }" @click="copyToClipboard">
        <span class="url-text">{{ invitationUrl }}</span>
        <span class="copy-text">Copy</span>
      </p>
    </ModalComponent>

    <div class="summary-page">
      <div class="header">
        <div class="image-container">
          <img :src="responseData.imageUrl" alt="Course Badge" class="badge-image" />
        </div>
        <div class="course-title">{{ responseData.name }}</div>
      </div>
    </div>

    <v-form @submit.prevent="issueCredential" class="issue-credential-form">
      <v-text-field v-model="userId" label="Student ID" type="number" required></v-text-field>
      <v-btn type="submit" color="primary">Reward</v-btn>
    </v-form>
  </div>
</template>

<script>
import { ref, onMounted } from "vue";
import axios from "axios";
import { useRoute } from "vue-router";
import ModalComponent from "../components/page-components/module/old/ModalComponent.vue";

export default {
  name: 'IssueModule',
  components: {
    ModalComponent
  },
  setup() {
    const route = useRoute();
    const id = route.params.id;
    const responseData = ref({});
    const invitationUrl = ref("");
    const userId = ref("");
    const isModalVisible = ref(false);
    const isCopied = ref(false);

    const showModal = () => {
      isModalVisible.value = true;
    };

    const closeModal = () => {
      isModalVisible.value = false;
    };

    const issueCredential = async () => {
      const payload = {
        userId: parseInt(userId.value),
        educationModuleId: parseInt(id)
      };
      try {
        const response = await axios.post('http://localhost:8080/api/v1/micro-credentials/issue', payload);
        invitationUrl.value = response.data.invitationLink;
      } catch (error) {
        console.log("Submission failed: " + error);
      }
      showModal();
    };

    const copyToClipboard = async () => {
      try {
        await navigator.clipboard.writeText(invitationUrl.value);
        isCopied.value = true;
        setTimeout(() => {
          isCopied.value = false;
        }, 2000);
      } catch (err) {
        console.error('Failed to copy: ', err);
      }
    };

    const fetchData = async () => {
      const response = await axios.get(`http://localhost:8080/api/v1/education-modules/${id}`);
      responseData.value = response.data;
    };

    onMounted(fetchData);

    return {
      invitationUrl,
      userId,
      issueCredential,
      responseData,
      isModalVisible,
      showModal,
      closeModal,
      copyToClipboard,
      isCopied
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
</style>
