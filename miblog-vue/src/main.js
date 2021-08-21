import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import utils from './utils'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

//markdown
import VMdpreview from '@kangc/v-md-editor/lib/preview'
import   '@kangc/v-md-editor/lib/style/preview.css'
import githubTheme from  '@kangc/v-md-editor/lib/theme/github'
VMdpreview.use(githubTheme)
Vue.use(VMdpreview);
import VueMarkdownEditor from '@kangc/v-md-editor';
import '@kangc/v-md-editor/lib/style/base-editor.css';
import vuepressTheme from '@kangc/v-md-editor/lib/theme/vuepress.js';
VueMarkdownEditor.use(vuepressTheme);
Vue.use(VueMarkdownEditor);

Vue.prototype.utils=utils
Vue.use(ElementUI);

new Vue({
  router,
  store,
  render: function (h) { return h(App) }
}).$mount('#app')
