<template>
  <div id="app">
    <v-app>
      <v-toolbar class="indigo darken-1" dark>
          <v-toolbar-title>Cartão Solidário - <small>Aplicativo para Desbancarizados</small></v-toolbar-title>
          <v-spacer></v-spacer>
          <v-toolbar-side-icon class="hidden-md-and-up"></v-toolbar-side-icon>
          <v-toolbar-items class="hidden-sm-and-down">
            <v-btn flat>Cadastro de Usuário</v-btn>
          </v-toolbar-items>
        </v-toolbar>
      <v-content>
        <v-container fluid>
          <router-view></router-view>
        </v-container>
      </v-content>
      <v-footer app></v-footer>
    </v-app>
  </div>
</template>

<script>
export default {
  name: 'app'
}
</script>

<style>
</style>
