<template>
<div style=" display: flex;
   flex-direction: column;
   min-height: 100vh;">
  <g-cabecalho-privado />
  <div class="pagina" style=" flex-grow: 1;">

    <g-toast ref="toast"></g-toast>
    <g-dialog-alert ref="dialogAlert"></g-dialog-alert>

    <form @submit.prevent='login()'>
      <v-container grid-list-md text-xs-center>
        <v-flex xs7 offset-xs2>
          <v-card>
            <v-layout row wrap>
              <!-- Usuário -->
              <v-flex>
                <span class="md-display-1">Autenticação</span>
              </v-flex>
            </v-layout>
            <v-layout row wrap>
              <!-- Usuário -->
              <v-flex xs8 offset-xs2>
                <g-input 
                  etiqueta="Usuário" 
                  nome="usuario" 
                  icone="fa fa-user"
                  v-model="usuario.usuarionome" 
                  validacao="required"
                  tamanho="12">
                </g-input>
              </v-flex>
            </v-layout>
            <v-layout row wrap>
              <!-- Senha -->            
              <v-flex xs8 offset-xs2>
                <md-field :md-toggle-password="false">
                  <md-icon>security</md-icon>
                  <label>Senha</label>
                  <md-input type="password" v-model="usuario.senha"></md-input>
                </md-field>
              </v-flex>
            </v-layout>
            <v-layout row wrap>
              <!-- Senha -->            
              <v-flex>
                <md-button type="submit" class="md-raised md-primary"><md-icon class="">send</md-icon> Entrar</md-button>
              </v-flex>
            </v-layout>
          </v-card>
        </v-flex>
      </v-container>
    </form>
  </div>  
  
 <g-rodape-publico />  
</div>
</template>

<script>
  import * as types from '../../store/mutation-types'
  export default {
    data () {
      return {
        usuario: {
          usuarionome: '',
          senha: ''
        }
      }
    },
    methods: {
      login () {
        const dialogAlert = this.$refs['dialogAlert']
        const toast = this.$refs['toast']
        const self = this
        this.$store.dispatch(types.LOGIN, {
          username: this.usuario.usuarionome,
          password: this.usuario.senha
        }).then((response) => {
          if (response.status === 200) {
            response.json().then(json => {
              this.$store.commit(types.LOGIN_SUCCESS, {
                token: json.access_token,
                username: self.usuario.usuarionome
              })
              this.$router.push('/indexPrivado')
            })
          } else if (response.status === 400) {
            response.json().then(json => {
              if (json.error_description === 'Bad credentials') {
                toast.e('Usuário ou senha incorretos')
              }
            })
            this.$store.commit(types.LOGIN_WRONG_CREDENTIALS)
          } else if (response.status === 401) {
            response.json().then(json => {
              dialogAlert.exibir('Erro ao se autenticar - ' + response.status, json)
            })
            this.$store.commit(types.LOGIN_WRONG_CREDENTIALS)
          } else {
            this.log = 'else: ' + response.status
            response.json().then(json => {
              dialogAlert.exibir('Erro ao se autenticar - ' + response.status, json)
            })
            this.$store.commit(types.LOGIN_ERROR)
          }
        }).catch(json => {
          dialogAlert.exibir('Erro ao enviar requisição', 'Servidor backend inacessível.' + json)
        })
      }
    },
    mounted () {
      this.$store.commit(types.LOGOUT)
    }
  }
</script>
