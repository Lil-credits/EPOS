<template>
  <div class="step-one-container">
    <div class="image-uploader" @click="triggerFileUpload">
      <input type="file" ref="fileInput" @change="handleFileUpload" style="display: none;" accept="image/*" />
      <div v-if="uploadedImage">
        <img :src="uploadedImage" alt="Uploaded Image Preview" class="uploaded-image-preview"/>
      </div>
      <div v-else>Add Image</div>
    </div>

    <input type="text" placeholder="Type your Credential Title here" class="credential-input" v-model="credentialTitle"/>

    <button @click="goToNextStep" class="get-started-btn">Get Started</button>
  </div>
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
        reader.onload = e => {
          this.uploadedImage = e.target.result;
        };
        reader.readAsDataURL(file);
      }
    },
    goToNextStep() {
      if (this.uploadedImage && this.credentialTitle) {
        this.$router.push({
          name: 'StepTwo',
          params: {
            uploadedImage: this.uploadedImage,
            subjectBanner: this.credentialTitle
          }
        });
      } else {
        alert('Please upload an image and enter a credential title.');
      }
    }
  }
};
</script>

<style scoped>
.step-one-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 20px;
  height: 100vh; /* Full height */
  box-sizing: border-box;
}

.image-uploader {
  background-color: #3498db; /* Blue background */
  border-radius: 50%; /* Circular shape */
  width: 150px; /* Circle size */
  height: 150px; /* Circle size */
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 30px; /* Space below the circle */
  position: relative; /* Needed for absolute positioning of '+' icon */
  cursor: pointer;
}

.upload-icon {
  color: white;
  font-size: 2rem; /* Larger '+' sign */
  position: absolute;
}

.uploaded-image-preview {
  width: 100%; /* Full width of the circle */
  height: 100%; /* Full height of the circle */
  border-radius: 50%; /* Circular shape */
  object-fit: cover; /* Cover the circle area with the image */
}

.credential-input {
  border: 2px solid red; /* Red border */
  border-radius: 25px; /* Rounded corners */
  padding: 10px 20px;
  font-size: 1rem;
  width: calc(100% - 40px); /* Full width minus padding */
  box-sizing: border-box;
}

.get-started-btn {
  background-color: #e74c3c; /* Red background */
  color: white;
  padding: 15px 30px;
  border: none;
  border-radius: 25px; /* Rounded corners */
  font-size: 1rem;
  font-weight: bold;
  cursor: pointer;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Subtle shadow */
  transition: background-color 0.3s ease; /* Smooth background color change on hover */
}

.get-started-btn:hover {
  background-color: #c0392b; /* Darker red on hover */
}

/* If you have a specific font to be used, add it here */
</style>
