package com.ow.tracer.admin.account.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ow.tracer.admin.account.dto.Role;
import com.ow.tracer.admin.account.dto.User;
import com.ow.tracer.admin.account.dto.UserInfo;
import com.ow.tracer.admin.account.dto.UserRole;
import com.ow.tracer.admin.account.mapper.UserMapper;
import com.ow.tracer.admin.account.service.IMenuService;
import com.ow.tracer.admin.account.service.IRoleService;
import com.ow.tracer.admin.account.service.IUserRoleService;
import com.ow.tracer.admin.account.service.IUserService;
import com.ow.tracer.common.base.BaseServiceImpl;
import com.ow.tracer.common.constats.SecurityConstants;
import com.ow.tracer.common.vo.AdminRole;
import com.ow.tracer.common.vo.MenuVO;
import com.ow.tracer.common.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @auther: Easy
 * @Date: 18-9-10 21:56
 * @Description:
 */
@Service("iUserService")
public class UserServiceImpl extends BaseServiceImpl<UserMapper,User> implements IUserService {
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private IRoleService roleService;
    @Override
    //cacheManager = "cacheManager"可以不指定
    public UserInfo getUserInfo(UserVO userVO) {
        User usertion = new User();
        usertion.setUserName(userVO.getUserName());
        User user =  this.getOne(new QueryWrapper<>(usertion));
        UserInfo userInfo = new UserInfo();
        userInfo.setUser(user);
        List<AdminRole> roleList = userVO.getRoleList();
        List<String> roleNames = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(roleList)) {
            for (AdminRole sysRole : roleList) {
                if (!StrUtil.equals(SecurityConstants.BASE_ROLE, sysRole.getRoleName())) {
                    roleNames.add(sysRole.getRoleName());
                }
            }
        }
        String[] roles = roleNames.toArray(new String[roleNames.size()]);
        userInfo.setRoles(roles);
        Set<MenuVO> menuVoSet = new HashSet<>();
        for (String role : roles) {
            List<MenuVO> menuVos = menuService.findMenuByRoleName(role,null);
            menuVoSet.addAll(menuVos);
        }
        Set<String> permissions = new HashSet<>();
        for (MenuVO menuVo : menuVoSet) {
            if (StringUtils.isNotEmpty(menuVo.getPermission())) {
                String permission = menuVo.getPermission();
                permissions.add(permission);
            }
        }
        userInfo.setPermissions(permissions.toArray(new String[permissions.size()]));
        return userInfo;
    }

    @Override
    public IPage<User> selectUserPage(Page<User> page) {
        IPage<User> iPage = this.baseMapper.selectPageVo(page);
        for(User user :iPage.getRecords()){
            QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id",user.getId());
            UserRole userRoles = userRoleService.getOne(queryWrapper);

            user.setRole(userRoles.getRoleId());
        }
        return iPage;
    }
    @Override
    public boolean installUser(User user,UserVO userVO) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        this.save(user,userVO);
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(user.getRole());
        userRole.setCreateDate(new Date());
        userRoleService.save(userRole,userVO);
        return true;
    }
    @Override
    public boolean updateUser(User user,UserVO userVO) {
        user.setUpdateDate(new Date());
        this.updateById(user,userVO);
        UserRole contion = new UserRole();
        contion.setUserId(user.getId());
        UserRole userRole = userRoleService.getOne(new QueryWrapper<>(contion));
        userRole.setRoleId(user.getRole());
        userRole.setUpdateDate(new Date());
        userRoleService.saveOrUpdate(userRole,userVO);
        return true;
    }
}
