package com.ow.tracer.admin.account.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ow.tracer.admin.account.dto.RoleMenu;
import com.ow.tracer.admin.account.mapper.RoleMenuMapper;
import com.ow.tracer.admin.account.service.IRoleMenuService;
import com.ow.tracer.common.base.BaseServiceImpl;
import com.ow.tracer.common.vo.UserVO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @auther: Easy
 * @date: 18-11-1 00:24
 * @description:
 */
@CacheConfig(cacheNames = "roleMenu")
@Service
public class RoleMenuServiceImpl  extends BaseServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {
    @Override
    public Boolean insertRoleMenus(String role, String roleId, String menuIds, UserVO userVO) {
        RoleMenu condition = new RoleMenu();
        condition.setRoleId(roleId);
        this.remove(new QueryWrapper<>(condition));
        if(StrUtil.isBlank(menuIds)){
            return true;
        }
        List<RoleMenu> roleMenuList = new ArrayList<>();
        List<String> menuidList= Arrays.asList(menuIds.split(","));
        for(String menuId: menuidList){
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenuList.add(roleMenu);
        }
        return  this.saveBatch(roleMenuList,userVO);
    }
}
