package com.ow.tracer.admin.account.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ow.tracer.admin.account.dto.Role;
import com.ow.tracer.common.base.BaseService;

/**
 * @auther: Easy
 * @Date: 18-10-17 00:04
 * @Description:
 */
public interface IRoleService  extends BaseService<Role> {
     IPage<Role> selectPage(Page page);
     boolean delRoleById(String id);
}
