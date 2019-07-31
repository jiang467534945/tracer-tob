package com.ow.tracer.mantuoluo.member.web.rest;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ow.tracer.common.base.BaseController;
import com.ow.tracer.common.base.BaseEnums;
import com.ow.tracer.common.base.Result;
import com.ow.tracer.common.util.Results;
import com.ow.tracer.common.vo.UserVO;
import com.ow.tracer.mantuoluo.member.dsm.dto.SiteSetting;
import com.ow.tracer.mantuoluo.member.dsm.service.ISiteSettingService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

/**
 * 类描述:     [控制器]
 * 创建人:     [becky]
 * 创建时间:   [2019-06-12 15:24:06]
 * 版本:       [v1.0]
 */
@SuppressWarnings("ALL")
@RestController
@RequestMapping("/siteSetting")
public class SiteSettingWebController extends BaseController {
    @Autowired
    private ISiteSettingService siteSettingService;
    private Logger logger = LoggerFactory.getLogger(SiteSettingWebController.class);

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
        IPage<SiteSetting> siteSettingIPage = siteSettingService.page(page,null);
        return Results.successWithData(siteSettingIPage , BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
    }
        /**
    	 * 全部list
    	 * @return List实体集合
    	 */
    @GetMapping(value="/allList")
    public Result allList(){
        List<SiteSetting> siteSettingList =  siteSettingService.list(new QueryWrapper<>());
        return Results.successWithData(siteSettingList, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
    }

    /**
     * 通过ID查询
     * @param id ID
     * @return Dept
     */

    @GetMapping("/{id}")
    public Result get(@PathVariable String id) {
        SiteSetting siteSetting = new SiteSetting ();
        siteSetting=siteSettingService.getById(id);
        return Results.successWithData(siteSetting, BaseEnums.SUCCESS.desc());
    }

    /**
     * 根据ID删除
     * @param id 编号
     * @return success/false
     */

    @DeleteMapping("/{id}")
    public Result del(@PathVariable String  id) {
        boolean  boo = siteSettingService.removeById(id);
        return Results.successWithData(boo, BaseEnums.SUCCESS.desc());
    }

     /**
       * 添加{data.describe}
       *
       * @param siteSetting {data.describe}
       * @return success/false
       */

       @PostMapping("/add")
       public Result add(@RequestBody SiteSetting  siteSetting ) {
           boolean  boo = siteSettingService.save(siteSetting);
           return Results.successWithData(boo, BaseEnums.SUCCESS.desc());
       }
     /**
       	 * 更新
         * @param siteSetting {data.describe} UserVO user操作用户
         * @return success/false
       	 */
        @PutMapping("/edit")
       public Result edit(@RequestBody SiteSetting   siteSetting ,UserVO userVO){
        siteSetting .setUpdateDate(new Date());
        siteSetting .setUpdateBy(userVO.getId());
        siteSetting .setUpdater(userVO.getUserName());
        boolean boo = siteSettingService.updateById( siteSetting);
        return  Results.successWithData(boo,BaseEnums.SUCCESS.desc());
    }

}
