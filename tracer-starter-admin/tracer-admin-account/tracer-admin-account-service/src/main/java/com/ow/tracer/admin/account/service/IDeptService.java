package com.ow.tracer.admin.account.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ow.tracer.admin.account.dto.Dept;
import com.ow.tracer.admin.account.vo.DeptTree;
import com.ow.tracer.common.base.BaseService;
import com.ow.tracer.common.vo.UserVO;

import java.util.List;

/**
 * @auther: Easy
 * @Date: 18-10-18 23:12
 * @Description:
 */
public interface IDeptService extends BaseService<Dept> {
    /**
     * 查询部门树菜单
     * @param sysDeptEntityWrapper
     * @return 树
     */
    List<DeptTree> selectListTree(QueryWrapper<Dept> sysDeptEntityWrapper,String deptId);
    /**
     * 新增部门及其关联关系
     * @param dept
     * @return boolean状态
     */
    Boolean insertDept (Dept dept, UserVO userVO);
    /**
     * 删除部门及其关联关系
     * @param id
     * @return boolean状态
     */
    Boolean delDept (String id);
}
