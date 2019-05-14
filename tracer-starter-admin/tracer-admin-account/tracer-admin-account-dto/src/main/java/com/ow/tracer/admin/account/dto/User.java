package com.ow.tracer.admin.account.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ow.tracer.common.base.BaseDTO;

/**
 * @auther: Easy
 * @Date: 18-9-7 22:30
 * @Description:
 */
@TableName("admin_user")
@SuppressWarnings("serial")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends BaseDTO <User>{
    private static final long serialVersionUID = -7395431342743009038L;

    public String userName;
    public String password;
    public String nickName;
    public String tenantId;
    public String avatar;
    public String salt;
    public String deptId;
    public String phone;
    @TableField(exist = false)
    public String   deptName;

    @TableField(exist = false)
    public String  [] roleStr;
    @TableField(exist = false)
    public String      role;
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String[] getRoleStr() {
        return roleStr;
    }

    public void setRoleStr(String[] roleStr) {
        this.roleStr = roleStr;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
