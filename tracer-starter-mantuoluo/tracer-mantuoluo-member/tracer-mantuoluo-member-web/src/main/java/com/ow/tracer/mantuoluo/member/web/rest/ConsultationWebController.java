package com.ow.tracer.mantuoluo.member.web.rest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ow.tracer.common.base.BaseController;
import com.ow.tracer.common.base.BaseEnums;
import com.ow.tracer.common.base.Result;
import com.ow.tracer.common.util.BirthDayUtils;
import com.ow.tracer.common.util.Results;
import com.ow.tracer.common.vo.NumberValue;
import com.ow.tracer.mantuoluo.member.TestCvas;
import com.ow.tracer.mantuoluo.member.dsm.dto.Consultation;
import com.ow.tracer.mantuoluo.member.dsm.dto.MemberUser;
import com.ow.tracer.mantuoluo.member.dsm.dto.Tutor;
import com.ow.tracer.mantuoluo.member.dsm.service.IConsultationService;
import com.ow.tracer.mantuoluo.member.dsm.service.IMemberUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 类描述:     [控制器]
 * 创建人:     [xy]
 * 创建时间:   [2019-05-14 10:09:36]
 * 版本:       [v1.0]
 */
@RestController
@RequestMapping("/app/consulation")
public class ConsultationWebController extends BaseController {

    @Autowired
    private IConsultationService consultationService;
  @Autowired
  private IMemberUserService iMemberUserService;

    /***
     * 保存咨询记录
     * @param consultation 咨询记录
     *
     */
    @PostMapping("/add")
    public Result save(@RequestBody Consultation consultation) {
        MemberUser memberUser = new MemberUser();
        int count = iMemberUserService.count(new QueryWrapper<MemberUser>().eq("open_id",consultation.getPhone()));
        if(count>0){

        }else{
            memberUser.setUserType(0);
            memberUser.setNickName(consultation.getPhone());
            memberUser.setOpenId(consultation.getPhone());
            memberUser.setAvatarUrl("../static/gerenzhongxin/images/gdsgf4.jpg");
            iMemberUserService.save(memberUser);
        }
        consultation.setCreatTime(new Date());
        consultation.setMemberId(memberUser.getId());
        boolean save = consultationService.save(consultation);
        return Results.successWithData(consultation, BaseEnums.SUCCESS.desc());
    }

    /***
     * 保存咨询记录
     * @param consultation 咨询记录
     *
     */
    @PostMapping("/wxApp/add")
    public Result wxAppAdd(@RequestBody Consultation consultation) throws  Exception{
        List<NumberValue> numberValueList= BirthDayUtils.birthDayAge(consultation.getBirthDay());
        String url = TestCvas.ImageTo(numberValueList);
        consultation.setImage(url);
        consultation.setCreatTime(new Date());
        boolean save = consultationService.save(consultation);
        return Results.successWithData(consultation, BaseEnums.SUCCESS.desc());
    }
    /**
     * 更新记录
     *
     * @param consultation 记录信息
     * @return 是否更新成功
     */
    @PutMapping("/edit")
    public Result update(@RequestBody Consultation consultation) {
        Consultation consultation1 = consultationService.getById(consultation.getId());
        consultation1.setName(consultation.getName());
        consultation1.setSex(consultation.getSex());
        consultation1.setZinv(consultation.getZinv());
        consultation1.setImage(consultation.getImage());
        consultation1.setXingbie(consultation.getXingbie());
        System.out.println(consultation.getXingbie());
        boolean b = consultationService.updateById(consultation1);
        return Results.successWithData(b, BaseEnums.SUCCESS.desc());
    }


    /**
     * 删除记录
     *
     * @param id 记录表id
     * @return 是否删除成功
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        boolean boo = consultationService.removeById(id);
        return Results.successWithData(boo, BaseEnums.SUCCESS.desc());
    }

    /**
     * 全部list
     *
     * @return List实体集合
     */
    @GetMapping(value = "/allList")
    public Result allList() {
        List<Consultation> consultationList = consultationService.list(new QueryWrapper<>());
        return Results.successWithData(consultationList, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
    }

    /**
     * 根据id查询
     *
     * @param id 记录id
     * @return 单条记录信息
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {
        Consultation consultation = consultationService.getById(id);
        consultation.setYear(consultation.getBirthDay().substring(0,4));
        consultation.setMonth(consultation.getBirthDay().substring(4,6));
        consultation.setDay(consultation.getBirthDay().substring(6,8));

        return Results.successWithData(consultation, BaseEnums.SUCCESS.desc());
    }


    /**
     * 根据导师id查询
     *
     * @param tutorId 导师id
     * @param readed  是否已读
     * @return 记录集合
     */
    @GetMapping("/selectByTutorId")
    public Result findByTutorId(@RequestParam(value = "tutorId", required = false) String tutorId, @RequestParam(value = "readed", required = false) Long readed) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("tutor_id", tutorId);
        if (null != readed && !"".equals(readed))
            queryWrapper.eq("readed", readed);
        queryWrapper.orderByDesc("creat_time");
        List<Consultation> consultationList = consultationService.list(queryWrapper);
        return Results.successWithData(consultationList, BaseEnums.SUCCESS.desc());
    }
    /**
     * 根据导师id查询
     *

     * @return 记录集合
     */
    @GetMapping("/findByMemberId/{id}")
    public Result findByMemberId(@PathVariable String id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("tutor_id", id);
        queryWrapper.orderByDesc("creat_time");
        List<Consultation> consultationList = consultationService.list(queryWrapper);
        List<List<Consultation>>list = new ArrayList<>();
        list.add(consultationList);
        return Results.successWithData(list, BaseEnums.SUCCESS.desc());
    }
    /**
     * 分页 PAGE
     *
     * @param current 当前页数
     * @return 分页数据
     */
    @ApiOperation(value = "获取分页数据", notes = "获取分页数据")
    @GetMapping(value = "/pageList")
    public Result pageList(@RequestParam(value = "id", required = false) String id,@RequestParam(value = "name", required = false) String name,  @RequestParam(value = "birthDay", required = false) String birthDay, @RequestParam(value = "shengmingshu", required = false) Integer shengmingshu, @RequestParam(value = "current", required = false) Integer current) {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("tutor_id", id);
        if(name!=null&&name!=""){
            queryWrapper.like("name", name);
        }
        if(birthDay!=null&&birthDay!=""){
            queryWrapper.like("birth_day", birthDay);

        }
        if(null!=shengmingshu){
            queryWrapper.eq("birth_num", shengmingshu);

        }
        queryWrapper.orderByDesc("creat_time");
        Page page = new Page();
        page.setCurrent(current);
        page.setSize(10);
        IPage<Consultation> memberUserIPage = consultationService.page(page, queryWrapper);
        return Results.successWithData(memberUserIPage, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
    }
}
