<template>
<div class="container">
    <div class="summary-page">
        <div class="header">
        <div class="image-container">
          <img :src="responseData['imageUrl']" alt="Course Badge" class="badge-image" />
        </div>
        <div class="course-title">{{ responseData['name'] }}</div>
      </div>
    </div>

    <v-form @submit.prevent="issueCredential" class="issue-credential-form">
        <v-text-field
            v-model="userId"
            label="Student ID"
            type="number"
            required
        ></v-text-field>
        <v-btn type="submit" color="primary">Reward</v-btn>
    </v-form>
</div>
</template>

<script>
import { ref, onMounted } from "vue";
import axios from "axios";
import { useRoute } from "vue-router";

export default {
  name: 'IssueModule',
  setup() {
    const route = useRoute();
    const id = route.params.id;
    const responseData = ref({});

    const userId = ref("");
    const issueCredential = async () => {
        console.log("Entered id " + userId.value)
    }

    const fetchData = async () => {
      const response = await axios.get("http://localhost:8080/api/v1/education-modules/" + id);
      responseData.value = response.data;
    };

    onMounted(fetchData);

    return {
        userId,
        issueCredential,
        responseData
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

  .badge-container {
    display: flex;
    justify-content: center;
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
  
  
</style>