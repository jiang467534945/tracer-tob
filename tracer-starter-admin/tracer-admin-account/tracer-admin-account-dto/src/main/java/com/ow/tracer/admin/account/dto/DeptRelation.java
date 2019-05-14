package com.ow.tracer.admin.account.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ow.tracer.common.base.BaseDTO;

import java.io.Serializable;

/**
 * @author : Easy
 * @Date: 18-9-25 21:29
 * @Description:
 */
@TableName("admin_dept_relation")

public class DeptRelation extends BaseDTO<DeptRelation> {

    private static final long serialVersionUID = 1L;

    /**
     * 祖先节点
     */
    private String ancestor;
    /**
     * 后代节点
     */
    private String descendant;


    @Override
    protected Serializable pkVal() {
        return this.ancestor;
    }

    @Override
    public String toString() {
        return "DeptRelation{" +
                ", ancestor=" + ancestor +
                ", descendant=" + descendant +
                "}";
    }

    public String getAncestor() {
        return ancestor;
    }

    public void setAncestor(String ancestor) {
        this.ancestor = ancestor;
    }

    public String getDescendant() {
        return descendant;
    }

    public void setDescendant(String descendant) {
        this.descendant = descendant;
    }
}