package com.ow.tracer.admin.account.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ow.tracer.common.base.BaseDTO;

/**
 * @auther: Easy
 * @Date: 18-9-25 21:04
 * @Description:
 */
@TableName("admin_role_dept")
@SuppressWarnings("serial")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleDept extends BaseDTO<RoleDept> {
    /**
     * 角色ID
     */
    @TableField("role_id")
    private String roleId;
    /**
     * 部门ID
     */
    @TableField("dept_id")
    private String deptId;
    @Override
    public String toString() {
        return "RoleDept{" +
                ", roleId=" + roleId +
                ", deptId=" + deptId +
                "}";
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
}
