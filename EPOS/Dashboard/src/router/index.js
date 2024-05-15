import { createRouter, createWebHistory } from 'vue-router';
import Form from '../views/FormPage.vue';

const routes = [
  {
    path: '/modules/',
    name: 'ModuleOveriew',
    component: ModuleOveriew
  },
  {
    path: '/modules/create', // Corrected and changed to kebab-case
    name: 'CreateModule',
    component: Form,
  },
  {
    path: '/modules/:id',
    name: 'ModuleDetails',
    component: ModuleDetails,
    props: true // This allows route params to be passed as props to the component
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});

export default router;
