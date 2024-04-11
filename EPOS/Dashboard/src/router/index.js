import { createRouter, createWebHistory } from 'vue-router';

import StepOne from '../views/StepOne.vue';
import StepTwo from '../views/StepTwo.vue';
import StepThree from '../views/StepThree';
import StepFour from '../views/StepFour';
import StepFive from '../views/StepFive';
import SummaryPage from '../views/SummaryPage'

const routes = [
  {
    path: '/', // Corrected and changed to kebab-case
    name: 'StepOne',
    component: StepOne
  },
  {
    path: '/step-two', // Changed to kebab-case for consistency
    name: 'StepTwo',
    component: StepTwo,
    props: true // This allows route params to be passed as props to the component
  },
  {
  path: '/step-three', // Changed to kebab-case for consistency
  name: 'StepThree',
  component: StepThree,
  props: true // This allows route params to be passed as props to the component
},
{
  path: '/step-four', // Changed to kebab-case for consistency
  name: 'StepFour',
  component: StepFour,
  props: true // This allows route params to be passed as props to the component
},
{
  path: '/step-five', // Changed to kebab-case for consistency
  name: 'StepFive',
  component: StepFive,
  props: true // This allows route params to be passed as props to the component
},
{
  path: '/Summary-Page',
  name: 'SummaryPage',
  component: SummaryPage,
  props: true 
}
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});

export default router;
