package com.ow.tracer.mantuoluo.member.web.rest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ow.tracer.common.base.BaseController;
import com.ow.tracer.common.base.BaseEnums;
import com.ow.tracer.common.base.Result;
import com.ow.tracer.common.util.Results;
import com.ow.tracer.common.vo.UserVO;
import com.ow.tracer.mantuoluo.member.dsm.dto.MemberUser;
import com.ow.tracer.mantuoluo.member.dsm.dto.Tutor;
import com.ow.tracer.mantuoluo.member.dsm.service.IMemberUserService;
import com.ow.tracer.mantuoluo.member.dsm.service.ITutorService;
import com.ow.tracer.mantuoluo.member.dsm.vo.WxObject;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@SuppressWarnings("ALL")
@RestController
@RequestMapping("/app/tutor")
public class TutorWebController extends BaseController {
    @Autowired
    private ITutorService tutorService;
    @Autowired
    private IMemberUserService memberUserService;
    private Logger logger = LoggerFactory.getLogger(TutorWebController.class);

    /**
     * 分页 PAGE
     *
     * @param current 当前页数
     * @return 分页数据
     */
    @ApiOperation(value = "获取分页数据", notes = "获取分页数据")
    @GetMapping(value = "/pageList")
    public Result pageList(Integer current) {
        Page page = new Page();
        page.setCurrent(current);
        page.setSize(20);
        IPage<MemberUser> memberUserIPage = tutorService.page(page, null);
        return Results.successWithData(memberUserIPage, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
    }

    /**
     * 全部list
     *
     * @return List实体集合
     */
    @GetMapping(value = "/allList")
    public Result allList() {
        List<Tutor> tutorList = tutorService.list(new QueryWrapper<>());
        return Results.successWithData(tutorList, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
    }

    /**
     * 通过ID查询
     *
     * @param id ID
     * @return success/false
     */

    @GetMapping("/{id}")
    public Result get(@PathVariable String id) {
        Tutor tutor = new Tutor();
        tutor = tutorService.getById(id);
        return Results.successWithData(tutor, BaseEnums.SUCCESS.desc());
    }

    /**
     * 根据ID删除
     *
     * @param id 编号
     * @return success/false
     */

    @DeleteMapping("/{id}")
    public Result del(@PathVariable String id) {
        boolean boo = tutorService.removeById(id);
        return Results.successWithData(boo, BaseEnums.SUCCESS.desc());
    }

    /**
     * 添加导师
     *
     * @param tutor 导师对象
     * @return success/false
     */

    @PostMapping("/add")
    public Result add(@RequestBody Tutor tutor) {
        int count =tutorService.count(new QueryWrapper<Tutor>().eq("user_id",tutor.getUserId()));
      if(count>0){
          return Results.failure(BaseEnums.OPERATION_FAILURE.code(),"您已经提交过申请，请耐心等待后台审核");

      }
        for(String str:tutor.getType()){
            if(tutor.getTypeId()!=null){
                tutor.setTypeId(tutor.getTypeId()+","+str);
            }else{
                tutor.setTypeId(str);
            }
        }
        boolean boo = tutorService.save(tutor);
        MemberUser memberUser = new MemberUser();
        memberUser = memberUserService.getById(tutor.getUserId());
        memberUser.setUserType(1);
        memberUserService.updateById(memberUser);
        return Results.successWithData(memberUser,BaseEnums.SUCCESS.desc());
    }

    /**
     * 更新
     *
     * @param tutor 导师对象
     * @return success/false
     */
    @PutMapping("/edit")
    public Result edit(@RequestBody Tutor tutor) {
        boolean boo = tutorService.updateById(tutor);
        return Results.successWithData(boo, BaseEnums.SUCCESS.desc());
    }

    /**
     * 管理员审核是否通过
     * @param tutorId 导师ID
     * @param flag 是否通过审核
     * @return 成功状态
     */
    @PutMapping("/pass")
    public Result pass(@RequestBody String tutorId,@RequestBody int flag) {
        Tutor tutor = tutorService.getById(tutorId);
        //设置为已审核
        tutor.setStatus(1);
        //设置是否通过审核
        tutor.setPass(flag);
        boolean boo = tutorService.updateById(tutor);
        return Results.successWithData(boo, BaseEnums.SUCCESS.desc());
    }

}
