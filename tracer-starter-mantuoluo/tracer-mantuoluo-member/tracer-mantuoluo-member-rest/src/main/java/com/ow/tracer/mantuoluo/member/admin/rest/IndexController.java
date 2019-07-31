package com.ow.tracer.mantuoluo.member.admin.rest;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ow.tracer.common.base.BaseController;
import com.ow.tracer.common.base.BaseEnums;
import com.ow.tracer.common.base.Result;
import com.ow.tracer.common.util.Results;
import com.ow.tracer.mantuoluo.member.dsm.dto.AdminNotice;
import com.ow.tracer.mantuoluo.member.dsm.dto.ConsultationInfo;
import com.ow.tracer.mantuoluo.member.dsm.dto.MemberUser;
import com.ow.tracer.mantuoluo.member.dsm.dto.PayConsultaion;
import com.ow.tracer.mantuoluo.member.dsm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther: Easy
 * @date: 19-6-13 08:49
 * @description:
 */
@SuppressWarnings("ALL")
@RestController
@RequestMapping("/index")
public class IndexController  extends BaseController {
    @Autowired
    private IMemberUserService memberUserService;
    @Autowired
    private IPayConsultaionService payConsultaionService;
    @Autowired
    private IConsultationService consultationService;
    @Autowired
    private IConsultationInfoService iConsultationInfoService;
    @Autowired
    private ITutorService tutorService;
    @Autowired
    private IAdminNoticeService adminNoticeService;
    /**
     * 全部list
     * @return List实体集合
     */
    @GetMapping(value="/webSite")
    public Result webSite(){
        int count =memberUserService.count(new QueryWrapper<>());
        String today= DateUtil.today();
        String lingdian = today+"  00:00:00";
        String enddian = today+"  23:59:59";
        QueryWrapper<MemberUser> queryWrapper =new QueryWrapper();
        int dayCount =memberUserService.count(queryWrapper.between("create_date",lingdian,enddian));
        int oldPayCount = payConsultaionService.count(new QueryWrapper<PayConsultaion>().eq("status ",1));
        int payCount =   payConsultaionService.count(new QueryWrapper<PayConsultaion>().eq("status ",1).between("create_date",lingdian,enddian));
        int  contionCount = consultationService.count(new QueryWrapper<>());
        int  contionInfoCount = iConsultationInfoService.count(new QueryWrapper<>());
        int tutorCount = tutorService.count(new QueryWrapper<>());
        List<AdminNotice> adminNotices = adminNoticeService.list(new QueryWrapper<>());
        Map map =new HashMap();
        map.put("dayCount",dayCount);
        map.put("sumCount",count);
        map.put("dayPayCount",payCount);
        map.put("sumPayCount",oldPayCount);
        map.put("contionCount",contionCount);
        map.put("contionInfoCount",contionInfoCount);
        map.put("tutorCount",tutorCount);
        map.put("adminNotices",adminNotices);

        return Results.successWithData(map, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
    }
}
