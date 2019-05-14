package com.ow.tracer.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ow.tracer.auth.mapper.UserServiceMapper;
import com.ow.tracer.common.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther: Easy
 * @Date: 18-9-21 22:38
 * @Description:
 */
@Service
public class UserServiceImpl  extends ServiceImpl<UserServiceMapper, UserVO> implements UserService{
    @Autowired
    public UserServiceMapper userServiceMapper;
    @Override

    public UserVO findUserByUsername(String username) {
        return userServiceMapper.selectUserVoByUsername(username);
    }
}
