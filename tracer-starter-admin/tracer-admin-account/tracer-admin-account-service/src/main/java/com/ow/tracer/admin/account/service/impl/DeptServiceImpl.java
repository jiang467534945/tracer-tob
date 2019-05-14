package com.ow.tracer.admin.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ow.tracer.admin.account.dto.Dept;
import com.ow.tracer.admin.account.dto.DeptRelation;
import com.ow.tracer.admin.account.dto.User;
import com.ow.tracer.admin.account.mapper.DeptMapper;
import com.ow.tracer.admin.account.mapper.DeptRelationMapper;
import com.ow.tracer.admin.account.mapper.UserMapper;
import com.ow.tracer.admin.account.service.IDeptService;
import com.ow.tracer.admin.account.vo.DeptTree;
import com.ow.tracer.common.base.BaseServiceImpl;
import com.ow.tracer.common.util.TreeUtil;
import com.ow.tracer.common.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther: Easy
 * @Date: 18-10-18 23:12
 * @Description:部门管理Impl
 */
@Service
public class DeptServiceImpl extends BaseServiceImpl<DeptMapper, Dept> implements IDeptService {
    @Autowired
    DeptRelationMapper deptRelationMapper;
    @Autowired
    UserMapper userMapper;
    @Override
    public List<DeptTree> selectListTree(QueryWrapper<Dept> sysDeptEntityWrapper,String deptId) {
        Dept dept =  this.getById(deptId);
        if(dept.getId()==null){
            return getDeptTree(this.list(sysDeptEntityWrapper),deptId);
        }else{
            return getDeptTree(this.list(sysDeptEntityWrapper),dept.getParentId());

        }
    }
    /**
     * 构建部门树
     *
     * @param depts 部门
     * @param root  根节点
     * @return
     */
    private List<DeptTree> getDeptTree(List<Dept> depts, String root) {
        List<DeptTree> trees = new ArrayList<>();
        DeptTree node;
        for (Dept dept : depts) {
            if (dept.getParentId().equals(dept.getId())) {
                continue;
            }
            node = new DeptTree();
            node.setId(dept.getId());
            node.setParentId(dept.getParentId());
            node.setName(dept.getName());
            trees.add(node);
        }

        return TreeUtil.bulid(trees, root);
    }

    @Override
    public Boolean insertDept(Dept dept, UserVO userVO) {
        this.saveOrUpdate(dept,userVO);
        insertDeptRelation(dept);
        return     true;
    }
    /**
     * 维护部门关系
     * @param sysDept 部门
     */
    private void insertDeptRelation(Dept sysDept) {
        //增加部门关系表
        DeptRelation deptRelation = new DeptRelation();
        deptRelation.setDescendant(sysDept.getParentId());
        List<DeptRelation> deptRelationList = deptRelationMapper.selectList(new QueryWrapper<>(deptRelation));
        for (DeptRelation deptR : deptRelationList) {
            deptR.setDescendant(sysDept.getId());
            deptRelationMapper.insert(deptR);
        }
        //自己也要维护到关系表中
        DeptRelation own = new DeptRelation();
        own.setDescendant(sysDept.getParentId());
        own.setAncestor(sysDept.getId());
        deptRelationMapper.insert(own);
    }

    @Override
    public Boolean delDept(String id) {
        User user = new User();
        user.setDeptId(id);
        List<User> userList = userMapper.selectList(new QueryWrapper<>(user));
        if(userList.size()>0){
            return  false;
        }
        Dept condition = new Dept();
        condition.setParentId(id);
        List<Dept> list =  this.list(new QueryWrapper<>(condition));

        if(list.size()>0){
            for(Dept dept : list){
                User userCondition = new User();
                userCondition.setDeptId(dept.getId());
                List<User> users = userMapper.selectList(new QueryWrapper<>(userCondition));
                if(users.size()>0){
                    return  false;
                }
            }
        }
        QueryWrapper <Dept> queryWrapper  = new QueryWrapper();
         queryWrapper.eq("id",id).or().eq("parent_id",id);
         this.remove(queryWrapper);
          QueryWrapper<DeptRelation>deptRelationQueryWrapper = new QueryWrapper<>();
          deptRelationQueryWrapper.eq("ancestor",id).or().eq("descendant",id);
          deptRelationMapper.delete(deptRelationQueryWrapper);
          return true;
    }


}
