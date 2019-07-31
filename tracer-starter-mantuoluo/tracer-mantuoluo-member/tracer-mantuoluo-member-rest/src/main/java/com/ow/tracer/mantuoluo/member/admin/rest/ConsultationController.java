package com.ow.tracer.mantuoluo.member.admin.rest;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ow.tracer.common.base.BaseController;
import com.ow.tracer.common.base.BaseEnums;
import com.ow.tracer.common.base.Result;
import com.ow.tracer.common.constats.CommonConstant;
import com.ow.tracer.common.util.MapKeyUtil;
import com.ow.tracer.common.util.MapUtil;
import com.ow.tracer.common.util.Results;
import com.ow.tracer.common.vo.GetParam;
import com.ow.tracer.common.vo.UserVO;
import com.ow.tracer.mantuoluo.member.dsm.dto.Consultation;
import com.ow.tracer.mantuoluo.member.dsm.service.IConsultationService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 类描述:     [记录表控制器]
 * 创建人:     [becky]
 * 创建时间:   [2019-06-07 10:16:53]
 * 版本:       [v1.0]
 */
@SuppressWarnings("ALL")
@RestController
@RequestMapping("/consultation")
public class ConsultationController extends BaseController {
    @Autowired
    private IConsultationService consultationService;
    private MapKeyUtil<Consultation> mapKeyUtil;
    private Logger logger = LoggerFactory.getLogger(ConsultationController.class);

    /**
	 * 分页 PAGE
	 * @param current 当前页数
	 * @return 分页数据
	 */
	 @ApiOperation(value="获取分页数据",notes = "获取分页数据")
     @PutMapping(value="/pageList")
    public Result pageList(@RequestBody GetParam getParam){
         Map map  = MapUtil.mapIsNotNull((Map)getParam.getSearchForm()  );
         QueryWrapper sql = MapUtil.getWrapper(map);

         Integer current =getParam.getCurrent();

         Page page = new Page();
         page.setCurrent(current);
         page.setSize(20);

         IPage<Consultation> consultationIPage = consultationService.page(page,sql);
         return Results.successWithData(consultationIPage , BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
    }
    /**
     * 分页 PAGE
     * @param current 当前页数
     * @return 分页数据
     */
    @ApiOperation(value="获取分页数据",notes = "获取分页数据")
    @PutMapping(value="/searchPageList")
    public Result searchPageList(@RequestBody GetParam getParam){
        Integer current =getParam.getCurrent();
        Page page = new Page();
        page.setCurrent(current);
        page.setSize(20);
        IPage<Consultation> consultationIPage = consultationService.page(page,new QueryWrapper(getParam.getSearchForm()));
        return Results.successWithData(BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
    }
        /**
    	 * 全部list
    	 * @return List实体集合
    	 */
    @GetMapping(value="/allList")
    public Result allList(){
        List<Consultation> consultationList =  consultationService.list(new QueryWrapper<>());
        return Results.successWithData(consultationList, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
    }

    /**
     * 通过ID查询
     * @param id ID
     * @return Dept
     */

    @GetMapping("/{id}")
    public Result get(@PathVariable String id) {
        Consultation consultation = new Consultation ();
        consultation=consultationService.getById(id);
        return Results.successWithData(consultation, BaseEnums.SUCCESS.desc());
    }

    /**
     * 根据ID删除记录表
     * @param id 编号
     * @return success/false
     */

    @DeleteMapping("/{id}")
    public Result del(@PathVariable String  id) {
        boolean  boo = consultationService.removeById(id);
        return Results.successWithData(boo, BaseEnums.SUCCESS.desc());
    }

     /**
       * 添加{data.describe}
       *
       * @param consultation {data.describe}
       * @return success/false
       */

       @PostMapping("/add")
       public Result add(@RequestBody Consultation  consultation ,UserVO userVO) {
           boolean  boo = consultationService.save(consultation,userVO);
           return Results.successWithData(boo, BaseEnums.SUCCESS.desc());
       }
     /**
       	 * 更新记录表
         * @param consultation {data.describe} UserVO user操作用户
         * @return success/false
       	 */
        @PutMapping("/edit")
       public Result edit(@RequestBody Consultation   consultation ,UserVO userVO){

        boolean boo = consultationService.updateById( consultation,userVO);
        return  Results.successWithData(boo,BaseEnums.SUCCESS.desc());
    }

}
