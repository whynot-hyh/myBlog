import axios from 'axios'
import store from '../store'
// import token from '../../public/js/token'
const axiosIstance=axios.create({
    baseURL: 'http://localhost:9527/api/',
    timeout: 100000,
});

// 添加请求拦截器
axiosIstance.interceptors.request.use(config => {
    if (store.state.token){
        config.headers['token'] =store.state.token
    }
    return config;
}, error=>  {
    console.log('请求他拦截器')
    return Promise.reject(error);
});


// 添加响应拦截器
axiosIstance.interceptors.response.use(response =>{

    let token =response.headers['token']
    if (token){
        store.commit('setToken',token)
    }

    //获取后端返回的对象
    const  res = response.data
    if(response.status == 200){
        //数据正常响应
            return res;
        }else {
        return Promise.reject(res.message)
    }
}, error=> {

    return Promise.reject(error);
});

export default axiosIstance
