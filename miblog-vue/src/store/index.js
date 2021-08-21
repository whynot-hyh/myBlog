import Vue from 'vue'
import Vuex from 'vuex'

import {login} from "../api/user";

import token from  '../../public/js/token'

Vue.use(Vuex);
const store = new Vuex.Store({
  state:{
    token:token.get()
  },
  mutations:{
    setToken(state,paramToken){
      state.token = paramToken
      token.set(paramToken)
    },
    logout(){
      token.clear()
    }
  },
  actions:{
    login({commit},user){
      return new Promise((resolve,reject)=>{
        console.log('bbbb')
        login(user).then(data=>{
          console.log('aaaa')
          console.log(data)
          console.log(data)
          console.log(data)
          commit('setToken',data.data.token)
          token.setUser(data.data.user)
          //处理token 写入vuex状态管理
          resolve()
        }).catch(error=>{
          reject(error)
        })
      } )
    }
  }

});

export default store
