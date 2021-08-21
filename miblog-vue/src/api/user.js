import axiosIstance from "../utils/request";

export function login(user) {
    return axiosIstance.post('admin/login',user)
}

export function list(user) {
    return axiosIstance.post('user/list',user)
}

export function search(user) {
    return axiosIstance.post('user/search',user)
}

export function register(user) {
    return axiosIstance.post('admin/register',user)
}

export function update(user) {
    return axiosIstance.post('user/update',user)
}

export function isToken(token) {
    return axiosIstance.get('admin/isToken?token='+token)
}


export function detail(id) {
    return axiosIstance.get('user/detail?userId='+id)
}

export function del(id) {
    return axiosIstance.get('user/delete?id='+id)
}



