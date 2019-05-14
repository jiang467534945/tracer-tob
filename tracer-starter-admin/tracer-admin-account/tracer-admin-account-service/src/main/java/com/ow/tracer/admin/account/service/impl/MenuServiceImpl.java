package com.ow.tracer.admin.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ow.tracer.admin.account.dto.Menu;
import com.ow.tracer.admin.account.dto.RoleMenu;
import com.ow.tracer.admin.account.mapper.MenuMapper;
import com.ow.tracer.admin.account.service.IMenuService;
import com.ow.tracer.admin.account.service.IRoleMenuService;
import com.ow.tracer.common.base.BaseServiceImpl;
import com.ow.tracer.common.vo.MenuVO;
import com.ow.tracer.common.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther: Easy
 * @Date: 18-9-25 21:46
 * @Description:
 */
//@CacheConfig(cacheNames = "menu")
@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper, Menu> implements IMenuService {
    @Autowired
    MenuMapper sysMenuMapper;

    @Autowired
    IRoleMenuService roleMenuService;

    @Override
    public List<MenuVO> findMenuByRoleName(String role, String systemType) {
        return sysMenuMapper.findMenuByRoleName(role, systemType);
    }

    @Override
    public boolean insertMenu(Menu menu, UserVO userVO) {
        this.save(menu,userVO);
        RoleMenu roleMenu = new RoleMenu();
        roleMenu.setMenuId(menu.getId());
        roleMenu.setRoleId("1");
        roleMenuService.save(roleMenu,userVO);
        return true;
    }

    @Override
    public boolean removeByIdAndRoleMenu(String id) {
        boolean success;
        Menu menuContion = new Menu();
        menuContion.setParentId(id);
        List<Menu> remove = sysMenuMapper.selectList(new QueryWrapper<>(menuContion));
        sysMenuMapper.deleteById(id);
        RoleMenu contion = new RoleMenu();
        contion.setMenuId(id);
        roleMenuService.remove(new QueryWrapper<>(contion));
        if (remove.size() >0) {
            remove.forEach((menu -> {
                        sysMenuMapper.deleteById(menu.getId());
                        RoleMenu roleMenucontion = new RoleMenu();
                roleMenucontion.setMenuId(menu.getId());
                        roleMenuService.remove(new QueryWrapper<>(roleMenucontion));
                    })
            );
        }
        return true;
    }
}