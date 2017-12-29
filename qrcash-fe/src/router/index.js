import Vue from 'vue'
import Router from 'vue-router'
import UserCrud from '@/components/UserCrud'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'UserCrud',
      component: UserCrud
    }
  ]
})
