import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify'
import { loadFonts } from './plugins/webfontloader'
import VueQrcode from '@chenfengyuan/vue-qrcode'

loadFonts()

createApp(App)
  .use(router)
  .use(vuetify)
  .mount('#app')
  .component('VueQrcode.name', VueQrcode)

