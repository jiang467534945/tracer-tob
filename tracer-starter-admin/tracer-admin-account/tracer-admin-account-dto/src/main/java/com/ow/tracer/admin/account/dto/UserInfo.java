package com.ow.tracer.admin.account.dto;

import java.io.Serializable;

/**
 * @auther: Easy
 * @Date: 18-9-25 21:00
 * @Description:
 */
public class UserInfo  implements Serializable {
    private User user;
    /**
     * 权限标识集合
     */
    private String[] permissions;

    /**
     * 角色集合
     */
    private String[] roles;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public void setPermissions(String[] permissions) {
        this.permissions = permissions;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }
}
