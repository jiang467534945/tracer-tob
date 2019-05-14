package com.ow.tracer.admin.account.service;

import com.ow.tracer.admin.account.dto.Menu;
import com.ow.tracer.admin.account.dto.RoleMenu;
import com.ow.tracer.common.base.BaseService;
import com.ow.tracer.common.vo.UserVO;

/**
 * @auther: Easy
 * @date: 18-11-1 00:23
 * @description:
 */
public interface IRoleMenuService extends BaseService<RoleMenu> {
    /**
     * 更新角色菜单
     *
     *
     * @param role
     * @param roleId  角色
     * @param menuIds 菜单列表
     * @return
     */
    Boolean insertRoleMenus(String role, String roleId, String menuIds, UserVO userVO);
}
