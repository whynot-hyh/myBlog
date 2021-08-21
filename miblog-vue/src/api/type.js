import axiosIstance from "../utils/request";

export function all() {
    return axiosIstance.get('type/all')
}

export function insert(type) {
    return axiosIstance.post('type/insert',type)
}

export function update(type) {
    return axiosIstance.post('type/update',type)
}

export function typeList(pageEntity) {
    return axiosIstance.post('type/list',pageEntity)
}
