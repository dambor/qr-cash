<template>
  <div class="row">
    <div class="offset-xs-1 col-xs-10 
                offset-sm-2 col-sm-8
                offset-md-3 col-md-6
                self-center" style="margin-top: 10px">
      <q-card>
        <q-card-title style="background: #595959;" >
          
          <h5 style="color: white"><q-icon name="fa-lock" /> <strong>Autenticação</strong></h5>
        </q-card-title>
        <q-card-separator />
        <q-card-main>

          <!-- Usuário -->
          <div class="row">
            <div class="offset-sm-2 col-sm-8 offset-sm-2 col-xs-12">
              <q-field
                icon="fa-user"
                :error="mailHasError"
                error-label="We need a valid email"
                :count="10"
              >
                <q-input v-model="text" float-label="Usuário" />
              </q-field>
            </div>
          </div>

          <!-- Senha -->
          <div class="row">
            <div class="offset-sm-2 col-sm-8 offset-sm-2 col-xs-12">
              <q-field
                icon="fa-lock"
                :error="mailHasError"
                error-label="We need a valid email"
                :count="10"
              >
                <q-input v-model="text" float-label="Senha" />
              </q-field>
            </div>
          </div>

          <!-- Entrar -->
          <div class="row text-center" style="height: 80px">
            <div class="col-xs-12 self-center" >
              <q-btn icon="fa-sign-in" color="primary">
                Entrar
              </q-btn>
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
      text: ''
    }
  }
}
</script>
