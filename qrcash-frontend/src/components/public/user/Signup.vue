<template>
  <div>
    <q-card>
      <q-card-title>
        {{ $t("user.signup") }}
      </q-card-title>
      <q-card-separator />
      <q-card-main>
        <div class="row">
          <div class="col-12">
            <q-input v-model="text" float-label="Float Label" placeholder="Gigi" />
          </div>
        </div>
      
      </q-card-main>
    </q-card> 
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
