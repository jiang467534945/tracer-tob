package com.ow.tracer.mantuoluo.member.web.rest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ow.tracer.common.base.BaseController;
import com.ow.tracer.common.base.BaseEnums;
import com.ow.tracer.common.base.Result;
import com.ow.tracer.common.util.Results;
import com.ow.tracer.mantuoluo.member.dsm.dto.Details;
import com.ow.tracer.mantuoluo.member.dsm.service.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/details")
public class DetailsController extends BaseController {
    @Autowired
    private DetailsService detailsService;

    /***
     * 添加
     * @param details
     * @return 成功或失败
     */
    @PostMapping("/add")
    public Result add(@RequestBody Details details) {
        boolean boo = detailsService.save(details);
        return Results.successWithData(boo, BaseEnums.SUCCESS.desc());
    }

    /**
     * 通过条件查询
     *
     * @param id,memberId,tutorId,createTime
     * @return 成功或失败
     */

    @GetMapping("/getByAll")
    public Result getByAll(@RequestParam(value="id",required=false) String id, @RequestParam(value="memberId",required=false) String memberId, @RequestParam(value="tutorId",required=false) String tutorId, @RequestParam(value="createTime",required=false) String createTime,String startTime,String lastTime) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if(null!=id&&!"".equals(id)){
            queryWrapper.eq("id",id);
        }
        if(null!=memberId&&!"".equals(memberId)){
            queryWrapper.eq("member_id",memberId);
        }
        if(null!=tutorId&&!"".equals(tutorId)){
            queryWrapper.eq("tutor_id",tutorId);
        }
        if(null!=createTime&&!"".equals(createTime)){
            queryWrapper.between("create_time",startTime,lastTime);
        }
        try {
            List<Details> detailsList = detailsService.list(queryWrapper);
            return Results.successWithData(detailsList, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
        } catch (Exception e) {
            return Results.failure();
        }
    }

    /**
     *  查询全部
     * @return 成功或失败
     */
    @GetMapping(value="/allList")
    public Result allList(){
        List<Details> detailsList =  detailsService.list(new QueryWrapper<>());
        return Results.successWithData(detailsList, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
    }

}
