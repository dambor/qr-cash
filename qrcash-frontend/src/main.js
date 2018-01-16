// === DEFAULT / CUSTOM STYLE ===
// WARNING! always comment out ONE of the two require() calls below.
// 1. use next line to activate CUSTOM STYLE (./src/themes)
require(`./themes/app.${__THEME}.styl`)
// 2. or, use next line to activate DEFAULT QUASAR STYLE
// require(`quasar/dist/quasar.${__THEME}.css`)
// ==============================

// Uncomment the following lines if you need IE11/Edge support
// require(`quasar/dist/quasar.ie`)
// require(`quasar/dist/quasar.ie.${__THEME}.css`)

import Vue from 'vue'
import router from './router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import VueI18n from 'vue-i18n'
import Quasar, {
  dom,
  event,
  openURL,
  QLayout,
  QToolbar,
  QToolbarTitle,
  QBtn,
  QIcon,
  QList,
  QListHeader,
  QItem,
  QItemSide,
  QItemMain,
  QCard,
  QCardTitle,
  QCardSeparator,
  QCardMain,
  QInput
} from 'quasar'

Vue.config.productionTip = false
Vue.use(Quasar, {
  components: {
    dom,
    event,
    openURL,
    QLayout,
    QToolbar,
    QToolbarTitle,
    QBtn,
    QIcon,
    QList,
    QListHeader,
    QItem,
    QItemSide,
    QItemMain,
    QCard,
    QCardTitle,
    QCardSeparator,
    QCardMain,
    QInput
  }
})

Vue.use(VueAxios, axios)
Vue.use(VueI18n)

if (__THEME === 'mat') {
  require('quasar-extras/roboto-font')
}
import 'quasar-extras/material-icons'
// import 'quasar-extras/ionicons'
import 'quasar-extras/fontawesome'
// import 'quasar-extras/animate'
import {messages} from 'components/MessageResources'

// Create VueI18n instance with options
const i18n = new VueI18n({
  locale: 'pt_BR', // set locale
  messages // set locale messages
})

Quasar.start(() => {
  /* eslint-disable no-new */
  new Vue({
    el: '#q-app',
    router,
    i18n,
    render: h => h(require('./App').default)
  })
})
