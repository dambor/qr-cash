<template>
  <div class="row">
    <div class="col-xs-12 offset-sm-1 col-sm-10 offset-md-2 col-md-8">
      <q-card>
        <q-card-title>
          <strong>{{ $t("user.signup") }}</strong>
        </q-card-title>
        <q-card-separator />
        <q-card-main>
          <div class="row sm-gutter">
            <div class="col-xs-6">
              <g-input 
                :etiqueta="$t('Email')"
                tipo="text"
                tamanho="10"
                validacao="required"
                icone="fa-user"
                :desabilitar="false"
                mensagem-ajuda=""
                v-model="user.email"
              />
            </div>
            <div class="col-xs-6">
              <g-input 
                :etiqueta="$t('Password')"
                tipo="password"
                tamanho="10"
                validacao="required"
                icone="fa-user"
                :desabilitar="false"
                mensagem-ajuda=""
                v-model="user.password"
              />
            </div>
          </div>
        </q-card-main>
      </q-card> 
    </div>
  </div>
</template>

<script>

export default {
  data () {
    return {
      user: {
        email: '',
        password: ''
      }
    }
  }
}
</script>
