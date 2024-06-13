<template>
  <div>
    <img :src="generateQRCode(url)" alt="QR Code">
  </div>
</template>

<script>
import QRCode from 'qrcode'

export default {
  props: {
    url: {
      type: String,
      required: true
    }
  },
  methods: {
    generateQRCode(url) {
      const canvas = document.createElement('canvas')
      QRCode.toCanvas(canvas, url, { width: 200, height: 200 })
      return canvas.toDataURL()
    }
  }
}
</script>

<style scoped>
div {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}

img {
  width: 200px;
  height: 200px;
}
</style>