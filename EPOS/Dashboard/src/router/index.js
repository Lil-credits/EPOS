import { createRouter, createWebHistory } from 'vue-router';
import ModuleDetails from '../views/ModuleDetails.vue';
import ModuleOverview from '../views/ModuleOverview.vue';
import CreateModule from '../views/CreateModule.vue';
import IssueModule from '../views/IssueModule.vue';
import ComponentTest from '../views/ComponentTest.vue';
import Personalwallet from '../views/PersonalWallet.vue';
import IssuedCredential from '@/views/IssuedCredential.vue';


const routes = [
  {
    path: '/',
    name: 'YourWallet',
    component: Personalwallet,
  },
  {
    path: '/modules/',
    name: 'ModuleOveriew',
    component: ModuleOverview,
  },
  {
    path: '/modules/:id',
    name: 'ModuleDetails',
    component: ModuleDetails,
    props: true
  },
  {
    path: '/modules/create',
    name: 'CreateModule',
    component: CreateModule,
  },
  {
    path: '/modules/:id/issue',
    name: 'IssueModule',
    component: IssueModule,
    props: true
  },
  {
    path: '/modules/:id/issued',
    name: 'IssuedCredential',
    component: IssuedCredential,
    props: true
  },
  {
    path: '/test',
    name: 'Test',
    component: ComponentTest
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});

export default router;
