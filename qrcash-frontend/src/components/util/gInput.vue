<template>
  <div>
    <q-field
      :icon="icone"
      :error="$v.dValue.$error"
      :error-label="mensagemErro"
      :count="tamanho"
      :disabled="desabilitar"
      :helper="mensagemAjuda"
    >
      <q-input 
        v-bind:value="value"
        :float-label="etiqueta"
        @input="atualizarModelo($event)"
        @blur="validar"
        :disable="desabilitar" 
        :type="tipo"
        no-pass-toggle="false"
        :max-length="tamanho"/>
    </q-field>
  </div>
</template>

<script>
import {
  required,
  minLength,
  maxLength,
  minValue,
  maxValue,
  between,
  alpha,
  alphaNum,
  numeric,
  email
} from 'vuelidate/lib/validators'

export default {
  name: 'g-input',
  props: {
    // value representa o objeto referenciado por v-model
    value: {
      type: [String, Number],
      default: ''
    },
    etiqueta: {
      type: String,
      default: ''
    },
    validacao: {
      type: String,
      default: ''
    },
    tamanho: {
      type: Number,
      default: 10
    },
    icone: {
      type: String,
      default: ''
    },
    desabilitar: {
      type: [Boolean, String],
      default: false
    },
    mensagemAjuda: {
      type: String,
      default: ''
    },
    tipo: {
      type: String,
      default: 'text'
    }
  },
  data () {
    return {
      dValue: this.value,
      mensagemErro: ''
    }
  },
  validations () {
    var dValue = {}
    // As regras de validação são separadas por '|'
    var validacoes = this.validacao.split('|')
    // Agregar a validação de acordo com o que é informado pelo usuário
    validacoes.forEach(v => {
      if (v.includes('required')) dValue.required = required
      if (v.includes('minValue')) dValue.minValue = minValue(v.substring(v.indexOf('(') + 1, v.indexOf(')')))
      if (v.includes('maxValue')) dValue.maxValue = maxValue(v.substring(v.indexOf('(') + 1, v.indexOf(')')))
      if (v.includes('minLength')) dValue.minLength = minLength(v.substring(v.indexOf('(') + 1, v.indexOf(')')))
      if (v.includes('maxLength')) dValue.maxLength = maxLength(v.substring(v.indexOf('(') + 1, v.indexOf(')')))
      if (v.includes('email')) dValue.email = email
      if (v.includes('alpha')) dValue.alpha = alpha
      if (v.includes('alphaNum')) dValue.alphaNum = alphaNum
      if (v.includes('numeric')) dValue.numeric = numeric
      if (v.includes('between')) {
        // Recuperando os parâmetros de dentro do parêntesi (ex. [10,20] de 'between(10,20)' )
        var params = v.match(/\((.*)\)/)[1].split(',')
        dValue.between = between(params[0], params[1])
      }
    })
    return {dValue}
  },
  methods: {
    atualizarModelo (value) {
      this.dValue = value
      // Emite o valor do número através do evento de input
      this.$emit('input', this.dValue)
    },
    validar () {
      // Alterar cor do campo e exibir mensagem de erro, caso campo inválido
      this.$v.dValue.$touch()
      if (this.$v.dValue.required === false) {
        this.mensagemErro = 'Campo obrigatório não informado'
      }
      if (this.$v.dValue.maxLength === false) {
        this.mensagemErro = 'Máximo permitido de ' + this.$v.dValue.$params.maxLength.max + ' caracteres'
      }
      if (this.$v.dValue.minLength === false) {
        this.mensagemErro = 'Mínimo permitido de ' + this.$v.dValue.$params.minLength.min + ' caracteres'
      }
      if (this.$v.dValue.alpha === false) {
        this.mensagemErro = 'Campo permite apenas letras'
      }
      if (this.$v.dValue.alphaNum === false) {
        this.mensagemErro = 'Campo permite apenas letras e números'
      }
      if (this.$v.dValue.numeric === false) {
        this.mensagemErro = 'Campo permite apenas números'
      }
      if (this.$v.dValue.maxValue === false) {
        this.mensagemErro = 'Valor ' + this.$v.dValue.$params.maxValue.max + ' é o máximo permitido'
      }
      if (this.$v.dValue.minValue === false) {
        this.mensagemErro = 'Valor ' + this.$v.dValue.$params.minValue.min + ' é o mínimo permitido'
      }
      if (this.$v.dValue.email === false) {
        this.mensagemErro = 'Email inválido'
      }
      if (this.$v.dValue.between === false) {
        this.mensagemErro = 'Valor permitido entre ' + this.$v.dValue.$params.between.min + ' e ' + this.$v.dValue.$params.between.max
      }

      return !this.$v.dValue.$invalid
    }
  }
}
</script>