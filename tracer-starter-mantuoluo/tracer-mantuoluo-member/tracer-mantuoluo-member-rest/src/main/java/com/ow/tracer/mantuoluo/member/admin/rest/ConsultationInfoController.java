package com.ow.tracer.mantuoluo.member.admin.rest;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ow.tracer.common.base.BaseController;
import com.ow.tracer.common.base.BaseEnums;
import com.ow.tracer.common.base.Result;
import com.ow.tracer.common.constats.CommonConstant;
import com.ow.tracer.common.util.Results;
import com.ow.tracer.common.vo.UserVO;
import com.ow.tracer.mantuoluo.member.dsm.dto.Consultation;
import com.ow.tracer.mantuoluo.member.dsm.dto.ConsultationInfo;
import com.ow.tracer.mantuoluo.member.dsm.service.IConsultationInfoService;
import com.ow.tracer.mantuoluo.member.dsm.service.IConsultationService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

/**
 * 类描述:     [详细咨询记录表控制器]
 * 创建人:     [becky]
 * 创建时间:   [2019-06-07 10:16:53]
 * 版本:       [v1.0]
 */
@SuppressWarnings("ALL")
@RestController
@RequestMapping("/consultationInfo")
public class ConsultationInfoController extends BaseController {
    @Autowired
    private IConsultationInfoService consultationInfoService;
    @Autowired
    private IConsultationService consultationService;
    private Logger logger = LoggerFactory.getLogger(ConsultationInfoController.class);

    /**
	 * 分页 PAGE
	 * @param current 当前页数
	 * @return 分页数据
	 */
	 @ApiOperation(value="获取分页数据",notes = "获取分页数据")
     @GetMapping(value="/pageList")
    public Result pageList(Integer current) {
         Page page = new Page();
         page.setCurrent(current);
         page.setSize(20);
         IPage<ConsultationInfo> consultationInfoIPage = consultationInfoService.page(page, null);
         for (ConsultationInfo consultationInfo : consultationInfoIPage.getRecords()) {
             Consultation consultation = consultationService.getById(consultationInfo.getConId());
             consultationInfo.setConsultation(consultation);
             if(consultation!=null){
                 consultationInfo.setBirthNum(consultation.getBirthDay());
                 consultationInfo.setBirthDay(consultation.getBirthDay());

             }
             String typeMessage = "";
             String[] num = consultationInfo.getCtype().split(",");
             for (String s : num) {
                 if (s.equals("0")) {
                     typeMessage = typeMessage + "情感" + ",";
                 } else if (s.equals("1")) {
                     typeMessage = typeMessage + "事业" + ",";

                 } else if (s.equals("2")) {
                     typeMessage = typeMessage + "生命" + ",";

                 } else if (s.equals("3")) {
                     typeMessage = typeMessage + "健康" + ",";

                 } else if (s.equals("4")) {
                     typeMessage = typeMessage + "气运" + ",";
                 }
             }
             consultationInfo.setCtype(typeMessage);

         }
             return Results.successWithData(consultationInfoIPage, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
         }

        /**
    	 * 全部list
    	 * @return List实体集合
    	 */
    @GetMapping(value="/allList")
    public Result allList(){
        List<ConsultationInfo> consultationInfoList =  consultationInfoService.list(new QueryWrapper<>());
        return Results.successWithData(consultationInfoList, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
    }

    /**
     * 通过ID查询
     * @param id ID
     * @return Dept
     */

    @GetMapping("/{id}")
    public Result get(@PathVariable String id) {
        ConsultationInfo consultationInfo = new ConsultationInfo ();
        consultationInfo=consultationInfoService.getById(id);
        return Results.successWithData(consultationInfo, BaseEnums.SUCCESS.desc());
    }

    /**
     * 根据ID删除详细咨询记录表
     * @param id 编号
     * @return success/false
     */

    @DeleteMapping("/{id}")
    public Result del(@PathVariable String  id) {
        boolean  boo = consultationInfoService.removeById(id);
        return Results.successWithData(boo, BaseEnums.SUCCESS.desc());
    }

     /**
       * 添加{data.describe}
       *
       * @param consultationInfo {data.describe}
       * @return success/false
       */

       @PostMapping("/add")
       public Result add(@RequestBody ConsultationInfo  consultationInfo ,UserVO userVO) {
           boolean  boo = consultationInfoService.save(consultationInfo,userVO);
           return Results.successWithData(boo, BaseEnums.SUCCESS.desc());
       }
     /**
       	 * 更新详细咨询记录表
         * @param consultationInfo {data.describe} UserVO user操作用户
         * @return success/false
       	 */
        @PutMapping("/edit")
       public Result edit(@RequestBody ConsultationInfo   consultationInfo ,UserVO userVO){

        boolean boo = consultationInfoService.updateById( consultationInfo,userVO);
        return  Results.successWithData(boo,BaseEnums.SUCCESS.desc());
    }

}
