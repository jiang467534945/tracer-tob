package com.ow.tracer.mantuoluo.member.admin.rest;

import com.ow.tracer.common.util.NumberUtils;
import com.ow.tracer.mantuoluo.member.dsm.dto.NumCode;
import com.ow.tracer.mantuoluo.member.dsm.service.INumCodeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ow.tracer.common.base.BaseController;
import com.ow.tracer.common.base.BaseEnums;
import com.ow.tracer.common.base.Result;
import com.ow.tracer.common.util.Results;
import com.ow.tracer.common.vo.UserVO;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 类描述:     [控制器]
 * 创建人:     [becky]
 * 创建时间:   [2019-05-28 11:05:44]
 * 版本:       [v1.0]
 */
@SuppressWarnings("ALL")
@RestController
@RequestMapping("/numCode")
public class NumCodeController extends BaseController {
    @Autowired
    private INumCodeService numCodeService;
    private Logger logger = LoggerFactory.getLogger(NumCodeController.class);

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
         page.setAsc("number_code");
        IPage<NumCode> numCodeIPage = numCodeService.page(page,new QueryWrapper<NumCode>().eq("parent","0"));
        return Results.successWithData(numCodeIPage , BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
    }
        /**
    	 * 全部list
    	 * @return List实体集合
    	 */
    @GetMapping(value="/allList")
    public Result allList(){
        List<NumCode> numCodeList =  numCodeService.list(new QueryWrapper<>());
        return Results.successWithData(numCodeList, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
    }

    /**
     * 通过ID查询
     * @param id ID
     * @return Dept
     */

    @GetMapping("/{id}")
    public Result get(@PathVariable String id) {
        NumCode numCode = new NumCode ();
        numCode=numCodeService.getById(id);
        return Results.successWithData(numCode, BaseEnums.SUCCESS.desc());
    }

    /**
     * 根据ID删除
     * @param id 编号
     * @return success/false
     */

    @DeleteMapping("/{id}")
    public Result del(@PathVariable String  id) {
        NumCode numCode = numCodeService.getById(id);
        boolean  boo = true;
        List<Integer> intList = NumberUtils.intToList(numCode.getNumberCode());
            for(Integer i:intList){
                  boo = numCodeService.remove(new QueryWrapper<NumCode>().eq("number_code",i));

            }
        return Results.successWithData(boo, BaseEnums.SUCCESS.desc());
    }

     /**
       * 添加{data.describe}
       *
       * @param numCode {data.describe}
       * @return success/false
       */

       @PostMapping("/add")
       public Result add(@RequestBody NumCode  numCode ,UserVO userVO) {
          int count = numCodeService.count(new QueryWrapper<NumCode>().eq("number_code",numCode.getNumberCode()));
          if(count>0){
              return Results.failure("生命数已存在");
          }
           List<Integer> intList = NumberUtils.intToList(numCode.getNumberCode());
           numCode.setParent("0");
           numCodeService.save(numCode,userVO) ;
           for(Integer i :intList){
               if(i==numCode.getNumberCode()){


               }else{
                   NumCode numCode1 = new NumCode();
                   numCode.setParent("0");
                   numCode1.setParent(numCode.getId());
                   numCode1.setNumberCode(i);
                   numCode1.setAdvantage(numCode.getAdvantage());
                   numCode1.setCause(numCode.getCause());
                   numCode1.setCharactert(numCode.getCharactert());
                   numCode1.setConnections(numCode.getConnections());
                   numCode1.setGrow(numCode.getGrow());
                   numCode1.setHealthy(numCode.getHealthy());
                   numCode1.setMarriage(numCode.getMarriage());
                   numCode1.setParenting(numCode.getParenting());
                   numCode1.setProposal(numCode.getProposal());
                   numCode1.setShortcoming(numCode.getShortcoming());
                   numCode1.setTalent(numCode.getTalent());
                   numCode1.setType(numCode.getType());
                   numCodeService.save(numCode1,userVO);
               }

           }
           return Results.success();
       }
     /**
       	 * 更新
         * @param numCode {data.describe} UserVO user操作用户
         * @return success/false
       	 */
        @PutMapping("/edit")
       public Result edit(@RequestBody NumCode   numCode ,UserVO userVO){
            numCodeService.updateById(numCode);
            NumCode numCode1 = new NumCode();
                numCode1.setAdvantage(numCode.getAdvantage());
                numCode1.setCause(numCode.getCause());
                numCode1.setCharactert(numCode.getCharactert());
                numCode1.setConnections(numCode.getConnections());
                numCode1.setGrow(numCode.getGrow());
                numCode1.setHealthy(numCode.getHealthy());
                numCode1.setMarriage(numCode.getMarriage());
                numCode1.setParenting(numCode.getParenting());
                numCode1.setProposal(numCode.getProposal());
                numCode1.setShortcoming(numCode.getShortcoming());
                numCode1.setTalent(numCode.getTalent());
                numCodeService.update(numCode1,new QueryWrapper<NumCode>().eq("parent",numCode.getId()));

            return Results.success();
    }

}
