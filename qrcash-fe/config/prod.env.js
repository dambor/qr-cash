'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"production"',
  API_HOST: '"https://qrcash-user-service.cfapps.io"'
})

//module.exports = {
//  NODE_ENV: '"production"',
  // API_HOST: '"https://qrcash-user-service.cfapps.io"'
//  API_HOST: '"http://localhost:8090"'
//}
