import axiosIstance from "../utils/request";

export function upload(formData) {
    return axiosIstance.post('uploadFile',formData)
}
