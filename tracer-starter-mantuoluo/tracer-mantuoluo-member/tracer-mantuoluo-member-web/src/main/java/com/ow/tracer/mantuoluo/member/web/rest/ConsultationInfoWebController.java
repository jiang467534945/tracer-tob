package com.ow.tracer.mantuoluo.member.web.rest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ow.tracer.common.base.BaseEnums;
import com.ow.tracer.common.base.Result;
import com.ow.tracer.common.util.Results;
import com.ow.tracer.mantuoluo.member.dsm.dto.Consultation;
import com.ow.tracer.mantuoluo.member.dsm.dto.ConsultationInfo;
import com.ow.tracer.mantuoluo.member.dsm.dto.MemberUser;
import com.ow.tracer.mantuoluo.member.dsm.dto.Tutor;
import com.ow.tracer.mantuoluo.member.dsm.service.IConsultationInfoService;
import com.ow.tracer.mantuoluo.member.dsm.service.IConsultationService;
import com.ow.tracer.mantuoluo.member.dsm.service.IMemberUserService;
import com.ow.tracer.mantuoluo.member.dsm.service.ITutorService;
import io.swagger.annotations.ApiOperation;
import org.abego.treelayout.internal.util.java.util.IteratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 类描述:     [控制器]
 * 创建人:     [xy]
 * 创建时间:   [2019-05-14 10:09:36]
 * 版本:       [v1.0]
 */
@RestController
@RequestMapping("/app/consultationInfo")
public class ConsultationInfoWebController {

    @Autowired
    private IConsultationInfoService consultationInfoService;

    @Autowired
    private ITutorService iTutorService;
    @Autowired
    private IMemberUserService memberUserService;
    @Autowired
    private IConsultationService iConsultationService;
    /**
     * 保存详细咨询记录
     * @param consultationInfo 详细咨询记录
     * @return 保存成功
     */
    @PostMapping("/add")
    public Result save(@RequestBody ConsultationInfo consultationInfo) {
        boolean save = consultationInfoService.save(consultationInfo);
        return Results.successWithData(save, BaseEnums.SUCCESS.desc());
    }

    /**
     * 删除详细记录
     * @param id 详细记录id
     * @return 是否删除成功
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id){
        boolean b = consultationInfoService.removeById(id);
        return Results.successWithData(b, BaseEnums.SUCCESS.desc());
    }

    /**
     * 删除详细记录
     * @param consultationInfo 详细记录信息
     * @return 是否更新成功
     */
    @PutMapping("/edit")
    public Result update(@RequestBody ConsultationInfo consultationInfo){
        boolean b = consultationInfoService.updateById(consultationInfo);
        return Results.successWithData(b,BaseEnums.SUCCESS.desc());
    }

    /**
     * 全部list
     * @return List实体集合
     */
    @GetMapping(value = "/allList")
    public Result allList() {
        List<ConsultationInfo> consultationInfoList = consultationInfoService.list(new QueryWrapper<>());
        return Results.successWithData(consultationInfoList, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
    }

    /**
     * 根据id查询
     * @param id 记录id
     * @return 单条记录信息
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable String id){
        ConsultationInfo consultationInfo = consultationInfoService.getById(id);
        Consultation consultation  = iConsultationService.getById(consultationInfo.getConId());
        consultation.setBirthDay(consultation.getBirthDay().replace("-",""));

        consultationInfo.setConsultation(consultation);
        return  Results.successWithData(consultationInfo, BaseEnums.SUCCESS.desc());
    }
    /**
     * 根据id查询
     * @param id 记录id
     * @return 单条记录信息
     */
    @GetMapping("/toure/{id}")
    public Result findByToureId(@PathVariable String id){

        Tutor tutor = iTutorService.getOne(new QueryWrapper<Tutor>().eq("user_id",id));
        if(tutor==null){
            return  Results.failure("您还不是导师，快去注册成为导师吧");

        }
        if(tutor.getStatus()==1){
            Calendar calendar = Calendar.getInstance();
            String year = String.valueOf(calendar.get(Calendar.YEAR));
            List<ConsultationInfo> consultationInfoList = consultationInfoService.list(new QueryWrapper<ConsultationInfo>().eq("tutor_id",tutor.getId()));
            for(ConsultationInfo consultationInfo :consultationInfoList) {
                Integer age=0;
                String type="";
                String sex="";
                Consultation consultation = iConsultationService.getById(consultationInfo.getConId());
                age = Integer.parseInt(year) - Integer.parseInt(consultation.getBirthDay().substring(0, 4));

                if (consultationInfo.getMemberSex() == 0) {
                    sex="男";
                }else{
                    sex="女";

                }
                if (consultationInfo.getMarriage() == 0) {
                    type="未婚";
                }else{
                    type="已婚";

                }
                consultationInfo.setConsultation(consultation);
                consultationInfo.setMessage("性别："+sex+"；年龄："+age+";"+type);

            }

            List <List<ConsultationInfo>>list = new ArrayList();
            list.add(consultationInfoList);
            return  Results.successWithData(list, BaseEnums.SUCCESS.desc());
        }else{
            return  Results.failure("您还不是导师，快去注册成为导师吧");
        }

    }
    /**
     * 分页 PAGE
     *
     * @param current 当前页数
     * @return 分页数据
     */
    @ApiOperation(value = "获取分页数据", notes = "获取分页数据")
    @GetMapping(value = "/info/pageList")
    public Result infoPageList(@RequestParam(value = "id", required = false) String id, @RequestParam(value = "current", required = false) Integer current) {
        Tutor tutor = iTutorService.getOne(new QueryWrapper<Tutor>().eq("user_id", id));
        Calendar calendar = Calendar.getInstance();
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("tutor_id", tutor.getId());
        Page page = new Page();
        page.setCurrent(current);
        page.setSize(10);
        IPage<ConsultationInfo> memberUserIPage = consultationInfoService.page(page, queryWrapper);
        for (ConsultationInfo consultationInfo : memberUserIPage.getRecords()) {
            Integer age = 0;
            String type = "";
            String sex = "";
            Consultation consultation = iConsultationService.getById(consultationInfo.getConId());
            age = Integer.parseInt(year) - Integer.parseInt(consultation.getBirthDay().substring(0, 4));

            if (consultationInfo.getMemberSex() == 0) {
                sex = "男";
            } else {
                sex = "女";

            }
            if (consultationInfo.getMarriage() == 0) {
                type = "未婚";
            } else {
                type = "已婚";

            }
            consultationInfo.setConsultation(consultation);
            consultationInfo.setMessage("性别：" + sex + "；年龄：" + age + ";" + type);

        }

        return Results.successWithData(memberUserIPage, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
    }
}
