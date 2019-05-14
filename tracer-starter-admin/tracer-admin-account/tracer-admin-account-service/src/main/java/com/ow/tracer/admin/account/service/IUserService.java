package com.ow.tracer.admin.account.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ow.tracer.admin.account.dto.User;
import com.ow.tracer.admin.account.dto.UserInfo;
import com.ow.tracer.common.base.BaseService;
import com.ow.tracer.common.vo.UserVO;

/**
 * @auther: Easy
 * @Date: 18-9-10 21:53
 * @Description:
 */
public interface  IUserService extends BaseService<User> {
    public UserInfo getUserInfo(UserVO userVO);
    public IPage<User> selectUserPage(Page<User> page);
    public boolean installUser(User user,UserVO userVO);
    public boolean updateUser(User user,UserVO userVO);

}
