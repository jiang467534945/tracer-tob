package com.ow.tracer.mantuoluo.member.web.rest;

import cn.hutool.Hutool;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.http.HttpUtil;
import com.ow.tracer.mantuoluo.member.dsm.dto.Consultation;
import com.ow.tracer.mantuoluo.member.dsm.dto.MemberUser;
import com.ow.tracer.mantuoluo.member.dsm.service.IConsultationService;
import com.ow.tracer.mantuoluo.member.dsm.service.IMemberUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ow.tracer.common.base.BaseController;
import com.ow.tracer.common.base.BaseEnums;
import com.ow.tracer.common.base.Result;
import com.ow.tracer.common.util.Results;
import com.ow.tracer.common.vo.UserVO;
import com.ow.tracer.mantuoluo.member.dsm.vo.WxObject;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 类描述:     [控制器]
 * 创建人:     [becky]
 * 创建时间:   [2019-05-11 15:36:36]
 * 版本:       [v1.0]
 */
@SuppressWarnings("ALL")
@RestController
@RequestMapping("/app/memberUser")
public class MemberUserWebController extends BaseController {
    @Autowired
    private IMemberUserService memberUserService;
    @Autowired
    private IConsultationService iConsultationService;
    private Logger logger = LoggerFactory.getLogger(MemberUserWebController.class);

    /**
	 * 分页 PAGE
	 * @param current 当前页数
	 * @return 分页数据
	 */
	 @ApiOperation(value="获取分页数据",notes = "获取分页数据")
     @GetMapping(value="/pageList")
    public Result pageList(Integer current){
        Page page = new Page();
        page.setCurrent(current);
        page.setSize(20);
        IPage<MemberUser> memberUserIPage = memberUserService.page(page,null);
        return Results.successWithData(memberUserIPage , BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
    }

    /**
     * 添加{data.describe}
     *
     * @param memberUser {data.describe}
     * @return success/false
     */

    @PostMapping("/addWxUserinfo")
    public Result addWxUserinfo(@RequestBody WxObject wxObject ) {
        MemberUser member = memberUserService.getOne(new QueryWrapper<MemberUser>().eq("open_id",wxObject.getOpenId()));
        if(member!=null){
            return Results.successWithData(member,"登录成功,历史用户");
        }else{
            MemberUser memberUser = new MemberUser();
            memberUser.setAvatarUrl(wxObject.getWxUserInfo().getAvatarUrl());
            memberUser.setGender(wxObject.getWxUserInfo().getGender());
            memberUser.setNickName(wxObject.getWxUserInfo().getNickName());
            memberUser.setOpenId(wxObject.getOpenId());
            memberUser.setUserType(0);
            boolean boo= memberUserService.save(memberUser);
            return Results.successWithData(memberUser,"登录成功");
        }

    }

        /**
    	 * 全部list
    	 * @return List实体集合
    	 */
    @GetMapping(value="/allList")
    public Result allList(){
        List<MemberUser> memberUserList =  memberUserService.list(new QueryWrapper<>());
        return Results.successWithData(memberUserList, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
    }

    /**
     * 通过ID查询
     * @param id ID
     * @return Dept
     */

    @GetMapping("/{id}")
    public Result get(@PathVariable String id) {

        Long random = UUID.randomUUID().getMostSignificantBits();
        Long UID = Math.abs(random);
        System.out.println(UID.toString());
        MemberUser memberUser = new MemberUser ();
        memberUser=memberUserService.getById(id);
        return Results.successWithData(memberUser, BaseEnums.SUCCESS.desc());
    }

    /**
     * 根据ID删除
     * @param id 编号
     * @return success/false
     */

    @DeleteMapping("/{id}")
    public Result del(@PathVariable String  id) {
        boolean  boo = memberUserService.removeById(id);
        return Results.successWithData(boo, BaseEnums.SUCCESS.desc());
    }

     /**
       * 添加{data.describe}
       *
       * @param memberUser {data.describe}
       * @return success/false
       */

       @PostMapping("/add")
       public Result add(@RequestBody MemberUser  memberUser ) {
           boolean  boo = memberUserService.save(memberUser);
           return Results.successWithData(boo, BaseEnums.SUCCESS.desc());
       }

     /**
       	 * 更新
         * @param memberUser {data.describe} UserVO user操作用户
         * @return success/false
       	 */
        @PutMapping("/edit")
       public Result edit(@RequestBody MemberUser   memberUser ,UserVO userVO){
        memberUser .setUpdateDate(new Date());
        memberUser .setUpdateBy(userVO.getId());
        memberUser .setUpdater(userVO.getUserName());
        boolean boo = memberUserService.updateById( memberUser);
        return  Results.successWithData(boo,BaseEnums.SUCCESS.desc());
    }

}
