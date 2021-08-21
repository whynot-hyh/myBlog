import axiosIstance from "../utils/request";

export function insert(article) {
    return axiosIstance.post('article/insert',article)
}

export function articleUpdate(article) {
    return axiosIstance.post('article/update',article)
}

export function list(pageEntity) {
    return axiosIstance.post('article/userList',pageEntity)
}

export function adminList(pageEntity) {
    return axiosIstance.post('article/list',pageEntity)
}

export function getArticleView() {
    return axiosIstance.get('article/getArticleView')
}

export function count() {
    return axiosIstance.get('article/count')
}

export function get10Article() {
    return axiosIstance.get('article/get10Article')
}


export function search(pageEntity) {
    return axiosIstance.post('article/search',pageEntity)
}
export function detail(id) {
    return axiosIstance.get('article/detail?id='+id)
}

export function cat(id) {
    return axiosIstance.get('article/cat?id='+id)
}

export function del(id) {
    return axiosIstance.get('article/delete?id='+id)
}

