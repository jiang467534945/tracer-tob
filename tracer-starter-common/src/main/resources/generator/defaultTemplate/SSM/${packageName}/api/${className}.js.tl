import request from '\@/router/axios'

/**
 * 部门分页查询
 * \@param query
 * \@constructor
 */
export function getPageList(query) {
    return request({
        url: '/${data.packageName}/${data.lowerClassName}/pageList',
        method: 'get',
        params: query
    })
}
/**
 * 查询所有
 *
 */
export function getAll() {
    return request({
        url: '/${data.packageName}/${data.lowerClassName}/allList' ,
        method: 'get'
    })
}
/**
 * 查询详情
 * \@param id
 */
export function getObj(id) {
    return request({
        url: '/${data.packageName}/${data.lowerClassName}/' + id,
        method: 'get'
    })
}

/**
 * 新增
 * \@param params
 */
export function add(params) {
    return request({
        url: '/${data.packageName}/${data.lowerClassName}/add',
        method: 'post',
        data: params
    })
}

/**
 * 删除部门
 * \@param id
 */
export function del(id) {
    return request({
        url: '/${data.packageName}/${data.lowerClassName}/' + id,
        method: 'delete'
    })
}

/**
 * 修改
 * \@param params
 */
export function edit(params) {
    return request({
        url: '/${data.packageName}/${data.lowerClassName}/edit',
        method: 'put',
        data: params
    })
}

