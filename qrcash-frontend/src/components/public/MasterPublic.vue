<template>
  <q-layout
    ref="layout"
    view="lHh Lpr fff"
  >
    <q-toolbar slot="header" >
      <router-link to="/"><img src="~assets/logo-qrcash.png" style="width:80px;"/></router-link>
      <q-toolbar-title >
        <big @click="$router.push('/')" style="cursor: pointer;"><strong>{{$t('app.title')}}</strong></big>
        <div slot="subtitle">{{$t('app.subtitle')}}</div>
      </q-toolbar-title>
      
      <q-btn @click="$router.push('/signup')" color="primary" >
        {{$t('account.create')}}
      </q-btn>
      <!-- Autenticar -->
      <q-btn round flat @click="$router.push('/login')">
        <q-icon name="fa-sign-in" color="secondary" />
      </q-btn>
    </q-toolbar>

    <router-view />

    <!-- Footer -->
    <q-toolbar slot="footer" class="rodape">
      <q-toolbar-title>
      </q-toolbar-title>
      <small>&copy; {{new Date().getFullYear() }} DAMBOR - Todos os direitos reservados (versão 18.01.15) </small>
    </q-toolbar>    
  </q-layout>
</template>
