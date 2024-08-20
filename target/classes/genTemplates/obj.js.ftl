import request from '@/utils/request'

export function getList(data) {
    return request({
        url: '${(objName)!}/list',
        method: 'POST',
        data,
    })
}

export function deleteData(data) {
    return request({
        url: '${(objName)!}/delete',
        method: 'POST',
        data,
    })
}

export function saveData(data) {
    return request({
        url: data.id ? `${(objName)!}/update` : `${(objName)!}/add`,
        method: 'POST',
        data,
    })
}





