import Vue from 'vue'
import fetch from 'isomorphic-fetch'
import * as types from './mutation-types'
import router from '../router'
import VueResource from 'vue-resource'
import comum from '../comum'

Vue.use(VueResource)

const login = ({ commit }, creds) => {
  commit(types.LOGIN) // show spinner
  return fetch(process.env.API + '/oauth/token', {
    method: 'POST',
    headers: {
      Authorization: 'Basic YXRvYWRtaW5pc3RyYXRpdm86QHQwQGRtMW4xM3RyQHQxdjA=',
      'Content-Type': 'application/x-www-form-urlencoded'
    },
    body: 'username=' + creds.username + '&password=' + creds.password + '&grant_type=password'
  })
}

const logout = ({ commit }) => {
  commit(types.LOGOUT)
  localStorage.removeItem('JWT')
  router.push('/login')
}

const recuperarCliente = ({ getters }) => {
  console.info('Recuperando cliente com a URL: ' + comum.URL_CLIENTE)
  return fetch(comum.URL_CLIENTE)
}

export default {
  [types.LOGIN]: login,
  [types.LOGOUT]: logout,
  [types.CLIENTE_RECUPERAR]: recuperarCliente
}
