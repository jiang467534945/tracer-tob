package com.ow.tracer.admin.account.rest;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ow.tracer.admin.account.dto.User;
import com.ow.tracer.admin.account.dto.UserInfo;
import com.ow.tracer.admin.account.service.IUserService;
import com.ow.tracer.common.base.BaseController;
import com.ow.tracer.common.base.BaseEnums;
import com.ow.tracer.common.base.Result;
import com.ow.tracer.common.util.Results;
import com.ow.tracer.common.vo.UserVO;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


import org.slf4j.Logger;

/**
 * @auther: Easy
 * @Date: 18-9-7 22:31
 * @Description:
 */
@RestController
@RequestMapping("/user")

public class UserController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService iUserService;

    @Cacheable(key ="'cache_user_current'+#userVO.id",value = "userIdCache",cacheManager = "cacheManager")
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    @ResponseBody
    public Result userInfo(UserVO userVO){
        logger.info("get by id from db");
        QueryWrapper<User> ew = new QueryWrapper<User>();
        ew.eq("user_name",userVO.getUserName());
        UserInfo user = iUserService.getUserInfo(userVO);

        return Results.successWithData(user, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
    }
    /**
     * 用户信息校验
     * @param authentication 信息
     * @return 用户信息
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Object user() {
            // 获取当前用户资源
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.info("authentication:{}",authentication);
        return "order id : " + 12;
    }
    @Cacheable(key ="'cache_user_allUser'+#userVO.id+#current",value = "user",cacheManager = "cacheManager")
    @GetMapping(value="/allUser")
    public Result allUser(Integer current,Integer size,UserVO userVO){
        logger.info("get by id from allUser db");

        Page page = new Page();
        page.setCurrent(current);
        page.setSize(size);
        IPage<UserVO> userVOIPage = iUserService.selectUserPage(page);
        return Results.successWithData(userVOIPage, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
    }
    @PostMapping(value="/add")
    public Result add(@RequestBody  User user,UserVO userVO){
        iUserService.installUser(user,userVO);

        return  Results.success();
    }
    @PostMapping(value="/edit")
    public Result edit(@RequestBody  User user,UserVO userVO){
        iUserService.updateUser(user,userVO);

        return  Results.success();
    }
}