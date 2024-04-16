<template>
  <v-container class="step-one-container" fluid>
    <v-row justify="center">
      <v-col cols="12" sm="8" md="6" lg="4" class="text-center">
        <!-- Image uploader avatar -->
        <v-avatar size="150" color="grey lighten-4" class="image-uploader" @click="triggerFileUpload">
          <input ref="fileInput" type="file" @change="handleFileUpload" hidden accept="image/*" />
          <v-img v-if="uploadedImage" :src="uploadedImage" class="white--text" />
          <v-icon v-else size="55">mdi-plus</v-icon>
        </v-avatar>

        <!-- Credential title text field -->
        <v-text-field
          v-model="credentialTitle"
          label="Type your Credential Title here"
          solo
          class="my-4"
        ></v-text-field>

        <!-- Get started button -->
        <v-btn color="primary" class="white--text" @click="goToNextStep">
          Get Started
        </v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      uploadedImage: null,
      credentialTitle: '',
    };
  },
  methods: {
    triggerFileUpload() {
      this.$refs.fileInput.click();
    },
    handleFileUpload(event) {
      const file = event.target.files[0];
      if (file && file.type.startsWith('image/')) {
        const reader = new FileReader();
        reader.onload = (e) => {
          this.uploadedImage = e.target.result;
        };
        reader.readAsDataURL(file);
      }
    },
    goToNextStep() {
      if (this.uploadedImage && this.credentialTitle) {
        // Navigate to the next step with the necessary params
        this.$router.push({
          name: 'StepTwo',
          params: {
            uploadedImage: this.uploadedImage,
            subjectBanner: this.credentialTitle
          }
        });
      } else {
        // Notify the user to upload an image and enter a title
        this.$toast.error('Please upload an image and enter a credential title.');
      }
    }
  }
};
</script>

<style scoped>
.step-one-container {
  height: 100vh; /* Full height */
}

.image-uploader {
  cursor: pointer;
  margin-bottom: 20px; /* Space below the uploader */
}

.white--text {
  color: white;
}
</style>
