package com.ow.tracer.mantuoluo.member.admin.rest;

import com.ow.tracer.mantuoluo.member.dsm.dto.OrderTutor;
import com.ow.tracer.mantuoluo.member.dsm.service.IOrderTutorService;
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
 * 创建时间:   [2019-06-12 16:06:45]
 * 版本:       [v1.0]
 */
@SuppressWarnings("ALL")
@RestController
@RequestMapping("/orderTutor")
public class OrderTutorController extends BaseController {
    @Autowired
    private IOrderTutorService orderTutorService;
    private Logger logger = LoggerFactory.getLogger(OrderTutorController.class);

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
        IPage<OrderTutor> orderTutorIPage = orderTutorService.page(page,null);
        return Results.successWithData(orderTutorIPage , BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
    }
        /**
    	 * 全部list
    	 * @return List实体集合
    	 */
    @GetMapping(value="/allList")
    public Result allList(){
        List<OrderTutor> orderTutorList =  orderTutorService.list(new QueryWrapper<>());
        return Results.successWithData(orderTutorList, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
    }

    /**
     * 通过ID查询
     * @param id ID
     * @return Dept
     */

    @GetMapping("/{id}")
    public Result get(@PathVariable String id) {
        OrderTutor orderTutor = new OrderTutor ();
        orderTutor=orderTutorService.getById(id);
        return Results.successWithData(orderTutor, BaseEnums.SUCCESS.desc());
    }

    /**
     * 根据ID删除
     * @param id 编号
     * @return success/false
     */

    @DeleteMapping("/{id}")
    public Result del(@PathVariable String  id) {
        boolean  boo = orderTutorService.removeById(id);
        return Results.successWithData(boo, BaseEnums.SUCCESS.desc());
    }

     /**
       * 添加{data.describe}
       *
       * @param orderTutor {data.describe}
       * @return success/false
       */

       @PostMapping("/add")
       public Result add(@RequestBody OrderTutor  orderTutor ,UserVO userVO) {
           boolean  boo = orderTutorService.save(orderTutor,userVO);
           return Results.successWithData(boo, BaseEnums.SUCCESS.desc());
       }
     /**
       	 * 更新
         * @param orderTutor {data.describe} UserVO user操作用户
         * @return success/false
       	 */
        @PutMapping("/edit")
       public Result edit(@RequestBody OrderTutor   orderTutor ,UserVO userVO){

        boolean boo = orderTutorService.updateById( orderTutor,userVO);
        return  Results.successWithData(boo,BaseEnums.SUCCESS.desc());
    }

}
