import '@mdi/font/css/materialdesignicons.css'
import 'vuetify/styles'
import { createVuetify } from 'vuetify'
  

export default createVuetify({
  theme: {
    defaultTheme: 'light',
    themes: {
      light: {
        primary: '#000000', // Replace with your desired primary color
      },
    },
  },
})
