import Vue from 'vue'
import Vuex from 'vuex'
import actions from './actions'
import * as getters from './getters'
import mutations from './mutations'
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)

const state = {
  usuario: {
    autenticado: false,
    token: null,
    usuario: null
  },
  cliente: {}
}

const options = {
  state,
  mutations,
  actions,
  getters,
  plugins: [createPersistedState()] // adicionar state no localStorage
}

const store = new Vuex.Store(options)

export default store
