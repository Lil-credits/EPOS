import { createRouter, createWebHistory } from 'vue-router';
import ModuleDetails from '../views/ModuleDetails.vue';
import ModuleOverview from '../views/ModuleOverview.vue';
import CreateModule from '../views/CreateModule.vue';

const routes = [
  {
    path: '/modules/',
    name: 'ModuleOveriew',
    component: ModuleOverview
  },
  {
    path: '/modules/:id',
    name: 'ModuleDetails',
    component: ModuleDetails,
    props: true // This allows route params to be passed as props to the component
  },
  {
    path: '/modules/create', // Corrected and changed to kebab-case
    name: 'CreateModule',
    component: CreateModule,
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});

export default router;
