<template>
  <div class="hello">
    <v-data-table
          v-bind:headers="headers"
          :items="users"
          hide-actions
          class="elevation-5"
        >
        <template slot="items" slot-scope="props">
          <td class="text-xs-right">{{ props.item.id }}</td>
          <td class="text-xs-right">{{ props.item.email }}</td>
          <td class="text-xs-right">{{ props.item.password }}</td>
        </template>
    </v-data-table>    
  </div>
</template>

<script>
import api from './util/api.js'

export default {
  name: 'UserCrud',
  data () {
    return {
      headers: [
        { text: 'Código', value: 'id' },
        { text: 'Nome do Usuário', value: 'email' },
        { text: 'Senha', value: 'password' }
      ],
      users: []
    }
  },
  methods: {
    findUserAll () {
      var url = api.API_USER
      this.axios.get(url).then((response) => {
        console.log(response.data)
        this.users = response.data
      }).catch(error => {
        console.log(error)
      })
    }
  },
  mounted () {
    this.findUserAll()
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
