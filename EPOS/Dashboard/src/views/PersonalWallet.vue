<template>
  <v-container class="pa-5">
    <v-row class="justify-center">
      <v-col cols="12">
        <!-- Profile Header -->
        <v-card :elevation="0" color="#ffffff">
          <div class="d-flex justify-center">
            <v-avatar size="120" class="mx-auto">
              <img :src="require('../assets/profile1.jpg')" alt="User Avatar" class="mx-auto" style="max-height: 100%; max-width: 100%;">
            </v-avatar>
          </div>
          <v-card-text class="text-center">
            <div class="font-weight-bold text-h5">Mischa Van Oranje</div>
          </v-card-text>
        </v-card>

        <!-- Personal Profile Section -->
        <v-card color="#00bfff">
          <v-card-title class="white--text font-weight-bold text-h6 d-flex justify-center">Personal profile</v-card-title>
          <v-card-text class="ma-3 mt-0 white--text" style="background-color: white; border-radius: 20px;">
            <ul class="px-3">
              <li>Bachelor leraar lichamelijke opvoeding</li>
              <li>1e-graads onderwijsbevoegd</li>
              <li>Basis kwalificatie examinering</li>
              <li>Web3 expert</li>
              <li>...</li>
            </ul>
          </v-card-text>
        </v-card>

        <!-- Memberships Section -->
        <v-card class="mt-2" color="secondary">
          <v-card-title class="white--text font-weight-bold text-h6 d-flex justify-center">Memberships</v-card-title>
          <v-card-text class="d-flex justify-center">
            <v-avatar size="80" class="mx-3" @click="$router.push('/modules')">
              <img :src="require('../assets/HULogo.png')" alt="Hogeschool Utrecht" class="mx-auto" style="object-fit: contain; max-height: 100%; max-width: 100%;">
            </v-avatar>
            <v-avatar size="80" class="mx-3" @click="$router.push('/modules')">
              <img src="https://via.placeholder.com/80" alt="Hogeschool van Amsterdam">
            </v-avatar>
            <v-avatar size="80" class="mx-3" @click="$router.push('/modules')">
              <img src="https://via.placeholder.com/80" alt="Saxion">
            </v-avatar>
          </v-card-text>
        </v-card>

        <!-- Tasks Section -->
        <v-card class="mt-2" color="#00bfff">
          <v-card-title class="white--text font-weight-bold text-h6 d-flex justify-center">Tasks</v-card-title>
          <v-card-text>
            <v-card class="mb-2" link>
              <v-card-text>
                <v-list-item-icon>
                  <v-icon>mdi-file-document</v-icon>
                </v-list-item-icon>
                Review the new education module from Peter
              </v-card-text>
            </v-card>
            <v-card class="mb-2" link>
              <v-card-text>
                <v-list-item-icon>
                  <v-icon>mdi-pencil</v-icon>
                </v-list-item-icon>
                Review the changes of the MC Bachelor ICT
              </v-card-text>
            </v-card>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: "PersonalWallet",
};
</script>

<style scoped>
.v-card {
  border-radius: 20px;
  
}
.v-card-title {
  border-radius: 20px 20px 0 0;
  padding-top: 16px;
  padding-bottom: 16px;
}
.v-card-text {
  padding: 16px;
}
</style>
