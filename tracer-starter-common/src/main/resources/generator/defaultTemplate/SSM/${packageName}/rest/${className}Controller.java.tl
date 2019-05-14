package com.ow.tracer.${data.packageName}.${data.functionName}.rest;

import com.ow.tracer.${data.packageName}.${data.functionName}.dto.${data.className};
import com.ow.tracer.${data.packageName}.${data.functionName}.service.I${data.className}Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ow.tracer.common.base.BaseController;
import com.ow.tracer.common.base.BaseEnums;
import com.ow.tracer.common.base.Result;
import com.ow.tracer.common.constats.CommonConstant;
import com.ow.tracer.common.util.Results;
import com.ow.tracer.common.vo.UserVO;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

/**
 * 类描述:     [${data.describe}控制器]
 * 创建人:     [${data.author}]
 * 创建时间:   [${date(),"yyyy-MM-dd HH:mm:ss"}]
 * 版本:       [v1.0]
 */
\@SuppressWarnings("ALL")
\@RestController
\@RequestMapping("/${data.lowerClassName}")
public class ${data.className}Controller extends BaseController {
    \@Autowired
    private I${data.className}Service ${data.lowerClassName}Service;
    private Logger logger = LoggerFactory.getLogger(${data.className}Controller.class);

    /**
	 * 分页 PAGE
	 * \@param current 当前页数
	 * \@return 分页数据
	 */
	 \@ApiOperation(value="获取分页数据",notes = "获取分页数据")
     \@GetMapping(value="/pageList")
    public Result pageList(Integer current){
        Page page = new Page();
        page.setCurrent(current);
        page.setSize(20);
        IPage<${data.className}> ${data.lowerClassName}IPage = ${data.lowerClassName}Service.page(page,null);
        return Results.successWithData(${data.lowerClassName}IPage , BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
    }
        /**
    	 * 全部list
    	 * \@return List实体集合
    	 */
    \@GetMapping(value="/allList")
    public Result allList(){
        List<${data.className}> ${data.lowerClassName}List =  ${data.lowerClassName}Service.list(new QueryWrapper<>());
        return Results.successWithData(${data.lowerClassName}List, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
    }

    /**
     * 通过ID查询
     * \@param id ID
     * \@return Dept
     */

    \@GetMapping("/{id}")
    public Result get(\@PathVariable String id) {
        ${data.className} ${data.lowerClassName} = new ${data.className} ();
        ${data.lowerClassName}=${data.lowerClassName}Service.getById(id);
        return Results.successWithData(${data.lowerClassName}, BaseEnums.SUCCESS.desc());
    }

    /**
     * 根据ID删除${data.describe}
     * \@param id 编号
     * \@return success/false
     */

    \@DeleteMapping("/{id}")
    public Result del(\@PathVariable String  id) {
        boolean  boo = ${data.lowerClassName}Service.removeById(id);
        return Results.successWithData(boo, BaseEnums.SUCCESS.desc());
    }

     /**
       * 添加{data.describe}
       *
       * \@param ${data.lowerClassName} {data.describe}
       * \@return success/false
       */

       \@PostMapping("/add")
       public Result add(\@RequestBody ${data.className}  ${data.lowerClassName} ,UserVO userVO) {
           boolean  boo = ${data.lowerClassName}Service.save(${data.lowerClassName},userVO);
           return Results.successWithData(boo, BaseEnums.SUCCESS.desc());
       }
     /**
       	 * 更新${data.describe}
         * \@param ${data.lowerClassName} {data.describe} UserVO user操作用户
         * \@return success/false
       	 */
        \@PutMapping("/edit")
       public Result edit(\@RequestBody ${data.className}   ${data.lowerClassName} ,UserVO userVO){

        boolean boo = ${data.lowerClassName}Service.updateById( ${data.lowerClassName},userVO);
        return  Results.successWithData(boo,BaseEnums.SUCCESS.desc());
    }

}
