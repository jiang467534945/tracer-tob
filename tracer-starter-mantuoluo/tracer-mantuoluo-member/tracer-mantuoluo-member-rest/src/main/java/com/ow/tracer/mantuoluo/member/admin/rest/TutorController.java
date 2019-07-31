package com.ow.tracer.mantuoluo.member.admin.rest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ow.tracer.common.base.BaseController;
import com.ow.tracer.common.base.BaseEnums;
import com.ow.tracer.common.base.Result;
import com.ow.tracer.common.util.Results;
import com.ow.tracer.common.vo.UserVO;
import com.ow.tracer.mantuoluo.member.dsm.dto.MemberUser;
import com.ow.tracer.mantuoluo.member.dsm.dto.Tutor;
import com.ow.tracer.mantuoluo.member.dsm.service.ITutorService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@SuppressWarnings("ALL")
@RestController
@RequestMapping("/tutor")
public class TutorController extends BaseController {
    @Autowired
    private ITutorService tutorService;
    private Logger logger = LoggerFactory.getLogger(TutorController.class);

    /**
     * 分页 PAGE
     *
     * @param current 当前页数
     * @return 分页数据
     */
    @ApiOperation(value = "获取分页数据", notes = "获取分页数据")
    @GetMapping(value = "/pageList")
    public Result pageList(Integer current) {
        Page page = new Page();
        page.setCurrent(current);
        page.setSize(20);
        IPage<Tutor> memberUserIPage = tutorService.page(page, null);
        for(Tutor tutor:memberUserIPage.getRecords()){
            String typeMessage ="";
            String [] num = tutor.getTypeId().split(",");
            for(String s:num){
                System.out.println(s);
                if(s.equals("0")){
                    typeMessage=typeMessage+"情感"+",";
                }else if(s.equals("1")){
                    typeMessage=typeMessage+"事业"+",";

                }else if(s.equals("2")){
                    typeMessage=typeMessage+"生命"+",";

                }else if(s.equals("3")){
                    typeMessage=typeMessage+"健康"+",";

                }
                else if(s.equals("4")){
                    typeMessage=typeMessage+"气运"+",";
                }
            }
            tutor.setTypeId(typeMessage);
        }
        return Results.successWithData(memberUserIPage, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
    }

    /**
     * 全部list
     *
     * @return List实体集合
     */
    @GetMapping(value = "/allList")
    public List allList() {
        List<Tutor> tutorList = tutorService.list(new QueryWrapper<>());
        return tutorList;
    }

    /**
     * 通过ID查询
     *
     * @param id ID
     * @return success/false
     */

    @GetMapping("/{id}")
    public Result get(@PathVariable String id) {
        Tutor tutor = new Tutor();
        tutor = tutorService.getById(id);
        return Results.successWithData(tutor, BaseEnums.SUCCESS.desc());
    }

    /**
     * 根据ID删除
     *
     * @param id 编号
     * @return success/false
     */

    @DeleteMapping("/{id}")
    public Result del(@PathVariable String id) {
        boolean boo = tutorService.removeById(id);
        return Results.successWithData(boo, BaseEnums.SUCCESS.desc());
    }

    /**
     * 添加导师
     *
     * @param tutor 导师对象
     * @return success/false
     */

    @PostMapping("/add")
    public Result add(@RequestBody Tutor tutor, UserVO userVO) {
        boolean boo = tutorService.save(tutor, userVO);
        return Results.successWithData(boo, BaseEnums.SUCCESS.desc());
    }

    /**
     * 更新
     *
     * @param tutor 导师对象 UserVO user操作用户
     * @return success/false
     */
    @PutMapping("/edit")
    public Result edit(@RequestBody Tutor tutor, UserVO userVO) {

        boolean boo = tutorService.updateById(tutor, userVO);
        return Results.successWithData(boo, BaseEnums.SUCCESS.desc());
    }

}
