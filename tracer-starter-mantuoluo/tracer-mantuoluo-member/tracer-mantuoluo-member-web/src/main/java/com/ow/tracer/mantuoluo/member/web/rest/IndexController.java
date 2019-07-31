package com.ow.tracer.mantuoluo.member.web.rest;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ow.tracer.common.base.BaseEnums;
import com.ow.tracer.common.base.Result;
import com.ow.tracer.common.util.BirthDayUtils;
import com.ow.tracer.common.util.GetAssemble;
import com.ow.tracer.common.util.Results;
import com.ow.tracer.common.vo.NumberValue;
import com.ow.tracer.mantuoluo.member.dsm.dto.SiteSetting;
import com.ow.tracer.mantuoluo.member.dsm.dto.*;
import com.ow.tracer.mantuoluo.member.dsm.service.*;
import com.ow.tracer.mantuoluo.member.web.rest.wxpay.WxPayBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @auther: Easy
 * @date: 19-5-25 15:15
 * @description:
 */
@Controller
@RequestMapping("/wap")
public class IndexController {
    @Autowired
    private IConsultationService consultationService;
    @Autowired
    private INumCodeService iNumCodeService;
    @Autowired
    private IMemberUserService iMemberUserService;
    @Autowired
    private ITutorService tutorService;
    @Autowired
    private IConsultationInfoService iConsultationInfoService;
    @Autowired
    private IPayConsultaionService iPayConsultaionService;
    @Autowired
    private ISiteSettingService iSiteSettingService;
    @Autowired
    WxPayBean wxPayBean;


    @RequestMapping(value="/index")
    private ModelAndView index(Model model, HttpServletRequest request, HttpServletResponse response){
        System.out.println(wxPayBean.getAppId());
        model.addAttribute("baseUrl","http://127.0.0.1:18080/");
        request.getSession().setAttribute("userId", IdUtil.simpleUUID());
        return   new ModelAndView("index-1","baseModel",model);
    }
    @RequestMapping(value="my-jisuan")
    private ModelAndView myJisuan(Model model, HttpServletRequest request, HttpServletResponse response){
        model.addAttribute("baseUrl","http://127.0.0.1:18080/");
        request.getSession().setAttribute("userId", IdUtil.simpleUUID());
        return   new ModelAndView("index","baseModel",model);
    }
    @RequestMapping(value="/center")
    private ModelAndView center(Model model, HttpServletRequest request, HttpServletResponse response){
        model.addAttribute("baseUrl","http://127.0.0.1:18080/");
        request.getSession().setAttribute("userId", IdUtil.simpleUUID());
        return   new ModelAndView("my-center","baseModel",model);
    }
    @RequestMapping(value="/tutorPaySuccess")
    private ModelAndView tutorPaySuccess(String consultaId,Model model, HttpServletRequest request, HttpServletResponse response){
        Consultation consultation =new Consultation();
        consultation =consultationService.getById(consultaId);
        model.addAttribute("consultation",consultation);
        return   new ModelAndView("service","baseModel",model);
    }
    @RequestMapping(value="/daoshi")
    private ModelAndView daoshi(String id,Model model, HttpServletRequest request, HttpServletResponse response){
        ConsultationInfo consultation =new ConsultationInfo();
        consultation =iConsultationInfoService.getById(id);
        Tutor tutor =tutorService.getById(consultation.getTutorId());
        MemberUser memberUser = iMemberUserService.getById(tutor.getUserId());
        model.addAttribute("consultation",consultation);
        model.addAttribute("tutor",tutor);

        model.addAttribute("memberUser",memberUser);
        return   new ModelAndView("daoshi","baseModel",model);
    }
    @RequestMapping(value="/orderDaoshi")
    private ModelAndView orderDaoshi(String id,Model model, HttpServletRequest request, HttpServletResponse response){
        Consultation consultation =new Consultation();
        consultation =consultationService.getById(id);
        Tutor tutor =tutorService.getById(consultation.getTutorId());
        MemberUser memberUser = iMemberUserService.getById(tutor.getUserId());
        model.addAttribute("consultation",consultation);
        model.addAttribute("tutor",tutor);

        model.addAttribute("memberUser",memberUser);
        return   new ModelAndView("daoshi","baseModel",model);
    }
    @RequestMapping(value="/order")
    private ModelAndView order(Model model, HttpServletRequest request, HttpServletResponse response){
        //创建session对象
        HttpSession session = request.getSession();
        //把用户数据保存在session域对象中
        Consultation consultation = (Consultation) session.getAttribute("consultation");
        SiteSetting siteSetting =iSiteSettingService.getById("ad41d03c520c49deb10b69ba84d54098");
        model.addAttribute("siteSetting",siteSetting);

        model.addAttribute("consultation",consultation);

        return   new ModelAndView("order","baseModel",model);
    }
    @RequestMapping(value="/orderTopay")
    private ModelAndView orderTopay(Model model, HttpServletRequest request, HttpServletResponse response){
        //创建session对象
        HttpSession session = request.getSession();
        //把用户数据保存在session域对象中
        Consultation consultation = (Consultation) session.getAttribute("consultation");
        SiteSetting siteSetting =iSiteSettingService.getById("ad41d03c520c49deb10b69ba84d54098");
        model.addAttribute("siteSetting",siteSetting);
        MemberUser memberUser = (MemberUser) session.getAttribute("memberUser");
        System.out.println(memberUser.getId());
        PayConsultaion payConsultaion = new PayConsultaion();
        payConsultaion.setConsultationId(consultation.getId());
        payConsultaion.setCreatTime(new DateTime());
        payConsultaion.setMemberId(memberUser.getId());
        payConsultaion.setOrderName("导师推荐信息");
        payConsultaion.setOrderNo(IdUtil.simpleUUID());
        payConsultaion.setType(1);
        payConsultaion.setStatus(0);
        payConsultaion.setReaded(0);
        payConsultaion.setTotalFee(siteSetting.getTwoPrice());
        iPayConsultaionService.save(payConsultaion);

        //把用户数据保存在session域对象中
        session.setAttribute("memberUser", memberUser);
        model.addAttribute("consultation",consultation);
        model.addAttribute("memberUser",memberUser);
        model.addAttribute("payConsultaion",payConsultaion);

        return   new ModelAndView("order-topay","baseModel",model);
    }

    @RequestMapping(value="/getConsulta")
    private  ModelAndView getConsulta(Model model, HttpServletRequest request, HttpServletResponse response){
        //创建session对象
        HttpSession session = request.getSession();
        //把用户数据保存在session域对象中
        Consultation consultation = (Consultation) session.getAttribute("consultation");
        String maxS="";
        String minS="";
        consultation.setCreatTime(new Date());
        QueryWrapper queryBhWrapper = new QueryWrapper();
        QueryWrapper queshuWrapper = new QueryWrapper();

        List<String> strList = new ArrayList<>();
        List<NumberValue> numberValueList=BirthDayUtils.birthDayAge(consultation.getBirthDay());
        List<NumberValue> maxNums=new ArrayList<>();
        for(NumberValue numberValue:numberValueList){
            if(numberValue.getSum()>0){
                System.out.println(numberValue.getNum());
                queryBhWrapper.or();

                queryBhWrapper.eq("number_code",numberValue.getNum());
                queryBhWrapper.eq("type",0);

            }else{
                queshuWrapper.or();

                queshuWrapper.eq("number_code",numberValue.getNum());
                queshuWrapper.eq("type",2);


                minS=minS+numberValue.getNum().toString()+",";
            }
            if(numberValue.getSum()>=3){
                maxS=maxS+numberValue.getNum().toString();
            }
        }

        List<NumCode>list = iNumCodeService.list(queryBhWrapper);
        List<NumCode>queshuList = iNumCodeService.list(queshuWrapper);

        QueryWrapper zuheBhWrapper = new QueryWrapper();

        Set<String> setVal= GetAssemble.zuhe(minS);
        for(String s :setVal){
            zuheBhWrapper.or();

            zuheBhWrapper.eq("number_code",Integer.parseInt(s));
            zuheBhWrapper.eq("type",4);

        }
        List<NumCode>zuheList = iNumCodeService.list(zuheBhWrapper);
        QueryWrapper lianxianWrapper = new QueryWrapper();

        Integer yearMonthSum = Integer.parseInt(BirthDayUtils.yearMonthDay(consultation.getBirthDay()));
        consultation.setBirthNum(yearMonthSum);
        lianxianWrapper.eq("number_code",yearMonthSum);


        if(maxS.contains("2")&&maxS.contains("5")&&maxS.contains("8")){
            lianxianWrapper.or();
            lianxianWrapper.eq("number_code",258);
            lianxianWrapper.eq("type",3);



        }
        if(maxS.contains("1")&&maxS.contains("4")&&maxS.contains("7")){
            lianxianWrapper.or();
            lianxianWrapper.eq("number_code",147);
            lianxianWrapper.eq("type",3);



        }
        if(maxS.contains("1")&&maxS.contains("2")&&maxS.contains("3")){
            lianxianWrapper.or();
            lianxianWrapper.eq("number_code",123);
            lianxianWrapper.eq("type",3);



        }
        if(maxS.contains("1")&&maxS.contains("6")&&maxS.contains("8")){

            lianxianWrapper.or();
            lianxianWrapper.eq("number_code",168);
            lianxianWrapper.eq("type",3);



        }
        if(maxS.contains("4")&&maxS.contains("5")&&maxS.contains("6")){

            lianxianWrapper.or();
            lianxianWrapper.eq("number_code",456);
            lianxianWrapper.eq("type",3);



        }
        if(maxS.contains("7")&&maxS.contains("8")&&maxS.contains("9")){

            lianxianWrapper.or();
            lianxianWrapper.eq("number_code",789);
            lianxianWrapper.eq("type",3);



        }
        if(maxS.contains("3")&&maxS.contains("6")&&maxS.contains("9")){

            lianxianWrapper.or();
            lianxianWrapper.eq("number_code",369);
            lianxianWrapper.eq("type",3);



        }
        if(maxS.contains("2")&&maxS.contains("4")){

            lianxianWrapper.or();
            lianxianWrapper.eq("number_code",24);
            lianxianWrapper.eq("type",3);



        }
        if(maxS.contains("4")&&maxS.contains("8")){

            lianxianWrapper.or();
            lianxianWrapper.eq("number_code",48);
            lianxianWrapper.eq("type",3);



        }
        if(maxS.contains("8")&&maxS.contains("6")){
            lianxianWrapper.or();
            lianxianWrapper.eq("number_code",86);
            lianxianWrapper.eq("type",3);



        }
        if(maxS.contains("2")&&maxS.contains("6")){

            lianxianWrapper.or();
            lianxianWrapper.eq("number_code",26);
            lianxianWrapper.eq("type",3);



        }
        List<NumCode>queshu = iNumCodeService.list(lianxianWrapper);
        list.addAll(queshu);
        list.addAll(zuheList);
        list.addAll(queshuList);
        model.addAttribute("list",list);
        return   new ModelAndView("detail","baseData",model);

    }

    @RequestMapping(value="/detail")
    private ModelAndView detail(Model model, String consultaId,HttpServletRequest request, HttpServletResponse response){
        String maxS="";
        String minS="";
        Consultation consultation =new Consultation();
        consultation =consultationService.getById(consultaId);

        QueryWrapper queryBhWrapper = new QueryWrapper();
        QueryWrapper queshuWrapper = new QueryWrapper();

        List<String> strList = new ArrayList<>();
        List<NumberValue> numberValueList=BirthDayUtils.birthDayAge(consultation.getBirthDay());
        List<NumberValue> maxNums=new ArrayList<>();
        for(NumberValue numberValue:numberValueList){
            if(numberValue.getSum()>0){
                System.out.println(numberValue.getNum());
                queryBhWrapper.or();

                queryBhWrapper.eq("number_code",numberValue.getNum());
                queryBhWrapper.eq("type",0);

            }else{
                queshuWrapper.or();

                queshuWrapper.eq("number_code",numberValue.getNum());
                queshuWrapper.eq("type",2);


                minS=minS+numberValue.getNum().toString()+",";
            }
            if(numberValue.getSum()>=3){
                maxS=maxS+numberValue.getNum().toString();
            }
        }

        List<NumCode>list = iNumCodeService.list(queryBhWrapper);
        List<NumCode>queshuList = iNumCodeService.list(queshuWrapper);

        QueryWrapper zuheBhWrapper = new QueryWrapper();

        Set<String> setVal= GetAssemble.zuhe(minS);
        for(String s :setVal){
            zuheBhWrapper.or();

            zuheBhWrapper.eq("number_code",Integer.parseInt(s));
            zuheBhWrapper.eq("type",4);

        }
        List<NumCode>zuheList = iNumCodeService.list(zuheBhWrapper);
        QueryWrapper lianxianWrapper = new QueryWrapper();

        Integer yearMonthSum = Integer.parseInt(BirthDayUtils.yearMonthDay(consultation.getBirthDay()));
        consultation.setBirthNum(yearMonthSum);
        lianxianWrapper.eq("number_code",yearMonthSum);


        if(maxS.contains("2")&&maxS.contains("5")&&maxS.contains("8")){
            lianxianWrapper.or();
            lianxianWrapper.eq("number_code",258);
            lianxianWrapper.eq("type",3);



        }
        if(maxS.contains("1")&&maxS.contains("4")&&maxS.contains("7")){
            lianxianWrapper.or();
            lianxianWrapper.eq("number_code",147);
            lianxianWrapper.eq("type",3);



        }
        if(maxS.contains("1")&&maxS.contains("2")&&maxS.contains("3")){
            lianxianWrapper.or();
            lianxianWrapper.eq("number_code",123);
            lianxianWrapper.eq("type",3);



        }
        if(maxS.contains("1")&&maxS.contains("6")&&maxS.contains("8")){

            lianxianWrapper.or();
            lianxianWrapper.eq("number_code",168);
            lianxianWrapper.eq("type",3);



        }
        if(maxS.contains("4")&&maxS.contains("5")&&maxS.contains("6")){

            lianxianWrapper.or();
            lianxianWrapper.eq("number_code",456);
            lianxianWrapper.eq("type",3);



        }
        if(maxS.contains("7")&&maxS.contains("8")&&maxS.contains("9")){

            lianxianWrapper.or();
            lianxianWrapper.eq("number_code",789);
            lianxianWrapper.eq("type",3);



        }
        if(maxS.contains("3")&&maxS.contains("6")&&maxS.contains("9")){

            lianxianWrapper.or();
            lianxianWrapper.eq("number_code",369);
            lianxianWrapper.eq("type",3);



        }
        if(maxS.contains("2")&&maxS.contains("4")){

            lianxianWrapper.or();
            lianxianWrapper.eq("number_code",24);
            lianxianWrapper.eq("type",3);



        }
        if(maxS.contains("4")&&maxS.contains("8")){

            lianxianWrapper.or();
            lianxianWrapper.eq("number_code",48);
            lianxianWrapper.eq("type",3);



        }
        if(maxS.contains("8")&&maxS.contains("6")){
            lianxianWrapper.or();
            lianxianWrapper.eq("number_code",86);
            lianxianWrapper.eq("type",3);



        }
        if(maxS.contains("2")&&maxS.contains("6")){

            lianxianWrapper.or();
            lianxianWrapper.eq("number_code",26);
            lianxianWrapper.eq("type",3);



        }
        List<NumCode>queshu = iNumCodeService.list(lianxianWrapper);
        list.addAll(queshu);
        list.addAll(zuheList);
        list.addAll(queshuList);
        MemberUser memberUser = iMemberUserService.getById(consultation.getMemberId());
        model.addAttribute("list",list);
        model.addAttribute("consultation",consultation);
        model.addAttribute("memberUser",memberUser);
        if(consultation.getTutorId()!=null){
            return   new ModelAndView("orderDetail","jisuanList",model);
        }else{
            return   new ModelAndView("detail-1","jisuanList",model);

        }
    }
    @RequestMapping(value="/orderDetail")
    private ModelAndView orderDetail(Model model, String consultaId,HttpServletRequest request, HttpServletResponse response){
        String maxS="";
        String minS="";
        Consultation consultation =new Consultation();
        consultation =consultationService.getById(consultaId);
        QueryWrapper queryWrapper = new QueryWrapper();
        List<String> strList = new ArrayList<>();
        List<NumberValue> numberValueList=BirthDayUtils.birthDayAge(consultation.getBirthDay());
        List<NumberValue> maxNums=new ArrayList<>();
        for(NumberValue numberValue:numberValueList){
            if(numberValue.getSum()>0){
                queryWrapper.eq("number_code",numberValue.getNum());
                queryWrapper.or();
            }else{
                minS=minS+numberValue.getNum().toString()+",";
            }
            if(numberValue.getSum()>=3){
                maxS=maxS+numberValue.getNum().toString();
            }

        }
        Set<String> setVal= GetAssemble.zuhe(minS);
        for(String s :setVal){
            queryWrapper.eq("number_code",Integer.parseInt(s));


            queryWrapper.or();
        }


        Integer yearMonthSum = Integer.parseInt(BirthDayUtils.yearMonthDay(consultation.getBirthDay()));
        queryWrapper.eq("number_code",yearMonthSum);


        if(maxS.contains("2")&&maxS.contains("5")&&maxS.contains("8")){
            queryWrapper.or();
            queryWrapper.eq("number_code",258);




        }
        if(maxS.contains("1")&&maxS.contains("4")&&maxS.contains("7")){
            queryWrapper.or();
            queryWrapper.eq("number_code",147);


        }
        if(maxS.contains("1")&&maxS.contains("2")&&maxS.contains("3")){

            queryWrapper.or();
            queryWrapper.eq("number_code",123);


        }
        if(maxS.contains("1")&&maxS.contains("6")&&maxS.contains("8")){

            queryWrapper.or();
            queryWrapper.eq("number_code",168);


        }
        if(maxS.contains("4")&&maxS.contains("5")&&maxS.contains("6")){

            queryWrapper.or();
            queryWrapper.eq("number_code",456);


        }
        if(maxS.contains("7")&&maxS.contains("8")&&maxS.contains("9")){

            queryWrapper.or();
            queryWrapper.eq("number_code",789);


        }
        if(maxS.contains("3")&&maxS.contains("6")&&maxS.contains("9")){

            queryWrapper.or();
            queryWrapper.eq("number_code",369);


        }
        if(maxS.contains("2")&&maxS.contains("4")){

            queryWrapper.or();
            queryWrapper.eq("number_code",24);


        }
        if(maxS.contains("4")&&maxS.contains("8")){

            queryWrapper.or();
            queryWrapper.eq("number_code",48);


        }
        if(maxS.contains("8")&&maxS.contains("6")){
            queryWrapper.or();
            queryWrapper.eq("number_code",86);


        }
        if(maxS.contains("2")&&maxS.contains("6")){

            queryWrapper.or();
            queryWrapper.eq("number_code",26);


        }
        List<NumCode>list = iNumCodeService.list(queryWrapper);
        MemberUser memberUser = iMemberUserService.getById(consultation.getMemberId());
        model.addAttribute("list",list);
        model.addAttribute("consultation",consultation);
        model.addAttribute("memberUser",memberUser);

        return   new ModelAndView("orderDetail","jisuanList",model);
    }
    @PostMapping("/consulta/add")
    @ResponseBody
    public Result save(@RequestBody Consultation consultation,HttpServletRequest request) {
        //创建session对象
        HttpSession session = request.getSession();
        //把用户数据保存在session域对象中
        session.setAttribute("consultation", consultation);

        return Results.success(BaseEnums.SUCCESS.desc());
    }
    @PostMapping("/his/checkPhone")
    @ResponseBody
    public Result checkPhone(@RequestBody String phone,HttpServletRequest request, HttpServletResponse response) {
        MemberUser  memberUser=  iMemberUserService.getOne(new QueryWrapper<MemberUser>().eq("open_id",phone));
        if(memberUser!=null && memberUser.getId()!=null){
            List<Consultation> consultations = new ArrayList<>();
            consultations  = consultationService.list(new QueryWrapper<Consultation>().eq("member_id",memberUser.getId()));
            if(consultations!=null && !consultations.isEmpty()){
                return Results.success("1");
            }else{
                return Results.failure();
            }
        }else{
            return Results.failure();
        }


    }
        /***
         * 保存咨询记录
         * @param phone 咨询记录
         *
         */
    @PostMapping("/consultation/add")
    @ResponseBody
    public Result conAdd(@RequestBody String phone,HttpServletRequest request, HttpServletResponse response) {
        //创建session对象
        HttpSession session = request.getSession();
        //把用户数据保存在session域对象中
        Consultation consultation = (Consultation) session.getAttribute("consultation");

        MemberUser memberUser = new MemberUser();
        int count = iMemberUserService.count(new QueryWrapper<MemberUser>().eq("open_id",phone));
        if(count>0){
            memberUser=iMemberUserService.getOne(new QueryWrapper<MemberUser>().eq("open_id",phone));
        }else{
            memberUser.setLat(Double.valueOf(consultation.getLat()));
            memberUser.setLng(Double.valueOf(consultation.getLng()));
            memberUser.setUserType(0);
            memberUser.setNickName(consultation.getName());
            memberUser.setOpenId(phone);
            memberUser.setAvatarUrl("../static/gerenzhongxin/images/gdsgf4.jpg");
            iMemberUserService.save(memberUser);
        }
        consultation.setPhone(phone);
        consultation.setCreatTime(new Date());
        consultation.setMemberId(memberUser.getId());
        boolean save = consultationService.save(consultation);
        SiteSetting siteSetting =iSiteSettingService.getById("ad41d03c520c49deb10b69ba84d54098");
        PayConsultaion payConsultaion = new PayConsultaion();
        payConsultaion.setConsultationId(consultation.getId());
        payConsultaion.setCreatTime(new DateTime());
        payConsultaion.setMemberId(memberUser.getId());
        payConsultaion.setOrderName("天赋才能+性格剖析+婚姻情感+事业财富+人脉关系+身体康健+成长方案");
        payConsultaion.setOrderNo(IdUtil.simpleUUID());
        payConsultaion.setType(0);
        payConsultaion.setStatus(0);
        payConsultaion.setReaded(0);
        payConsultaion.setTotalFee(siteSetting.getOnePrice());

        iPayConsultaionService.save(payConsultaion);
        //把用户数据保存在session域对象中
        session.setAttribute("memberUser", memberUser);

        return Results.successWithData(consultation.getId(), BaseEnums.SUCCESS.desc());
    }
    /***
     * 保存咨询记录
     * @param phone 咨询记录
     *
     */
    @PostMapping("/consultation/tuTurPay/add")
    @ResponseBody
    public Result tuTurPayAdd(@RequestBody String phone,HttpServletRequest request, HttpServletResponse response) {
        //创建session对象
        HttpSession session = request.getSession();
        //把用户数据保存在session域对象中
        Consultation consultation = (Consultation) session.getAttribute("consultation");

        MemberUser memberUser = (MemberUser) session.getAttribute("memberUser");

        SiteSetting siteSetting =iSiteSettingService.getById("ad41d03c520c49deb10b69ba84d54098");
        PayConsultaion payConsultaion = new PayConsultaion();
        payConsultaion.setConsultationId(consultation.getId());
        payConsultaion.setCreatTime(new DateTime());
        payConsultaion.setMemberId(memberUser.getId());
        payConsultaion.setOrderName("导师选择");
        payConsultaion.setOrderNo(IdUtil.simpleUUID());
        payConsultaion.setType(0);
        payConsultaion.setStatus(0);
        payConsultaion.setReaded(0);
        payConsultaion.setTotalFee(siteSetting.getOnePrice());

        iPayConsultaionService.save(payConsultaion);
        //把用户数据保存在session域对象中
        session.setAttribute("memberUser", memberUser);

        return Results.successWithData(consultation.getId(), BaseEnums.SUCCESS.desc());
    }
    @PostMapping("/consultationInfo/add")
    @ResponseBody
    public Result conInfoAdd(@RequestBody ConsultationInfo consultationInfo,Model model, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        MemberUser memberUser =(MemberUser)session.getAttribute("memberUser");
        consultationInfo.setMemberId(memberUser.getId());
        Tutor tutor = tutorService.selectByGps(memberUser.getLat(),memberUser.getLng());
        consultationInfo.setTutorId(tutor.getId());
        iConsultationInfoService.save(consultationInfo);
        Consultation consultation =new Consultation();
        consultation=consultationService.getById(consultationInfo.getConId());
        consultation.setTutorId(tutor.getId());
        consultationService.updateById(consultation);
        return Results.successWithData(consultationInfo.getId(), BaseEnums.SUCCESS.desc());

    }
    @RequestMapping(value="/hisOrder")
    public ModelAndView hisOrder(Model model){
        model.addAttribute("type",0);
        return   new ModelAndView("lishi","baseModel",model);
    }
    @RequestMapping(value="/hisGetOrder")
    public ModelAndView hisGetOrder(Model model,String phone,HttpServletRequest request){
        MemberUser  memberUser=  iMemberUserService.getOne(new QueryWrapper<MemberUser>().eq("open_id",phone));
        List<Consultation> consultations = consultationService.list(new QueryWrapper<Consultation>().eq("member_id",memberUser.getId()));
        for(Consultation consultation :consultations){
            PayConsultaion payConsultaion = iPayConsultaionService.getOne(new QueryWrapper<PayConsultaion>().eq("consultation_id",consultation.getId()));
            consultation.setPayConsultaion(payConsultaion);
        }
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("memberUser", memberUser);
        model.addAttribute("list",consultations);
        model.addAttribute("type",1);
        return   new ModelAndView("lishi-1","baseModel",model);
    }
    }
