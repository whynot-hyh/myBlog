import axiosIstance from "../utils/request";

export function menus(type) {
    return axiosIstance.get('menus/list?type='+type)
}
