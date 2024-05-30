// src/api/api.js
import axios from 'axios';

// Create an axios instance
const apiClient = axios.create({
  baseURL: 'http://localhost:8080/api/v1', // Replace with your API base URL
  headers: {
    'Content-Type': 'application/json',
  },
});

export default {
  getModule(id) {
    return apiClient.get(`/education-modules/${id}`);
  },

  getModules() {
    return apiClient.get('/education-modules/');
  },
  
  createModule(payload) {
    return apiClient.post('/education-modules', payload);
  },

  issueCredential(payload) {
    return apiClient.post('/micro-credentials/issue', payload);
  },
};
