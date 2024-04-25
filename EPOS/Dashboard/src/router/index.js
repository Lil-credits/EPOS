import { createRouter, createWebHistory } from 'vue-router';
import Form from '../views/FormPage.vue';

const routes = [
  {
    path: '/', // Corrected and changed to kebab-case
    name: 'FormPage',
    component: Form,
}
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});

export default router;
