<template>
    <div class="form-container">
      <div class="image-upload-container">
        <div class="image-upload-placeholder">
          <div class="image-upload-icon">+</div>
          <div>Add Image</div>
        </div>
        <input type="file" @change="handleImageUpload" hidden>
      </div>
      <input type="text" class="text-input" placeholder="Type your Credential Title">
      <button class="get-started-button">Get Started</button>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  
  const selectedImage = ref(null);
  
  function handleImageUpload(event) {
    const files = event.target.files;
    if (files.length > 0) {
      const fileReader = new FileReader();
      fileReader.readAsDataURL(files[0]);
      fileReader.onload = (e) => {
        selectedImage.value = e.target.result; // this is the base64 encoded string of the image
        // You can emit this value to the parent component or handle it as needed
      };
    }
  }
  </script>
  
  <style scoped>
  .form-container {
    max-width: 400px;
    margin: auto;
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  
  .image-upload-container {
    margin-top: 40px; /* adjust as needed */
    width: 150px;
    height: 150px;
    border-radius: 50%;
    background-color: #007BFF; /* the blue background */
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
  }
  
  .image-upload-placeholder {
    text-align: center;
  }
  
  .image-upload-icon {
    font-size: 24px; /* adjust as needed */
    color: #fff;
  }
  
  .text-input {
    width: 100%;
    padding: 10px;
    margin-top: 20px; /* adjust as needed */
    border: 1px solid #ccc;
    border-radius: 5px;
  }
  
  .get-started-button {
    background-color: #007BFF; /* matching the blue background */
    color: #fff;
    padding: 10px 20px;
    margin-top: 20px; /* adjust as needed */
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }
  </style>
  