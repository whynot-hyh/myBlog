import axiosIstance from "../utils/request";

export function getMySelfComments(pageEntity) {
    return axiosIstance.post('comment/getMySelfComments',pageEntity)
}

export function insert(comment) {
    return axiosIstance.post('comment/insert',comment)
}

export function commentList(pageEntity) {
    return axiosIstance.post('comment/list',pageEntity)
}

export function getArticle(articleId) {
    return axiosIstance.get('comment/getArticle?articleId='+articleId)
}

export function getReceiverComments(pageEntity) {
    return axiosIstance.post('comment/getReceiverComments',pageEntity)
}

export function search(pageEntity) {
    return axiosIstance.post('comment/search',pageEntity)
}
