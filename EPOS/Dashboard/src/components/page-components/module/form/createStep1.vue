<template>
    <v-form>
        <div class="image-upload">
        <input
          type="file"
          id="course-badge"
          @change="handleImageUpload"
          accept="image/*"
          hidden
        />
        <label for="course-badge" class="upload-label">
          <div v-if="courseDetails.badgeImage" class="image-preview">
            <img
              :src="courseDetails.badgeImage"
              alt="Uploaded image preview"
              class="preview-image"
            />
          </div>
          <div v-else>Add Image</div>
        </label>
      </div>
      <div class="form-group">
        <input
          type="text"
          id="course-title"
          v-model="courseDetails.courseTitle"
          required
          placeholder="Type you here Your Credential Title"
          class="title-input"
        />
      </div>
    </v-form>
</template>

<script>
export default {
    data() {
        return {
            courseDetails: {
                badgeImage: '',
                courseTitle: ''
            }
        }
    },
    methods: {
        handleImageUpload(event) {
            const file = event.target.files[0];
            const reader = new FileReader();

            reader.onload = () => {
                this.courseDetails.badgeImage = reader.result;
            };

            reader.readAsDataURL(file);
        }
    }
}
</script>

<style scoped>
.step-one {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  text-align: center;
}

.form-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.image-upload {
  margin-bottom: 30px;
}

.upload-label {
  background-color: #007bff;
  border-radius: 50%;
  width: 120px;
  height: 120px;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.add-image-icon {
  color: white;
  font-size: 24px;
  position: absolute;
}

.upload-label div {
  color: white;
  font-size: 14px;
}

.title-input {
  border: 1px solid #ccc;
  border-radius: 20px;
  padding: 10px;
  width: 100%;
  font-size: 16px;
  text-align: center;
  margin-bottom: 20px;
}

.submit-button {
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 20px;
  padding: 15px 30px;
  font-size: 16px;
  width: calc(100% - 60px); /* Account for padding */
  cursor: pointer;
}

.image-preview {
  background-color: white;
  width: 120px;
  height: 120px;
  border-radius: 50%;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
}

.preview-image {
  max-width: 100%;
  max-height: 100%;
}
</style>