import axiosIstance from "../utils/request";

export function list(pageEntity) {
    return axiosIstance.post('photo/getMyself',pageEntity)
}

export function photoList(pageEntity) {
    return axiosIstance.post('photo/getPhotoList',pageEntity)
}

export function insert(photo) {
    return axiosIstance.post('photo/insert',photo)
}

export function del(id) {
    return axiosIstance.get('photo/delete?userId='+id)
}
