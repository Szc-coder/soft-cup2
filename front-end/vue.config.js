const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,
  devServer: {
    proxy: {
      'api': {
        target: 'http://localhost:8080',  // 进行后台模拟请求，填写后端端口
        changeOrigin: true,
        pathRewrite: {
          '^/api': ''
        }
      }
    },
    host: 'localhost',  // IP地址
    port: 8081
  }
})
