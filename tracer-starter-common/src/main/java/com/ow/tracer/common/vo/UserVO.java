package com.ow.tracer.common.vo;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @auther: Easy
 * @Date: 18-9-13 17:30
 * @Description:
 */
/**
 * @author easy
 * @date 2018/09/28
 */
@TableName("admin_user")

public class UserVO implements Serializable {
        private static final long serialVersionUID = 1L;

        /**
         * 主键ID
         */
        private String id;
        /**
         * 用户名
         */
        private String userName;
        /**
         * 用户名
         */
        private String nickName;
        /**
         * 密码
         */
        private String password;
        /**
         * 随机盐
         */
        private String salt;
        /**
         * 创建时间
         */
        private Date createTime;
        /**
         * 修改时间
         */
        private Date updateTime;
        /**
         * 0-正常，1-删除
         */
        private String delFlag;
        /**
         * 简介
         */
        private String phone;
        /**
         * 头像
         */
        private String avatar;

        /**
         * 部门ID
         */
        private String deptId;
        /**
         * 部门名称
         */
        private String deptName;

        /**
         * 角色列表
         */
        private List<AdminRole> roleList;

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getUserName() {
                return userName;
        }

        public void setUserName(String userName) {
                this.userName = userName;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getSalt() {
                return salt;
        }

        public void setSalt(String salt) {
                this.salt = salt;
        }

        public Date getCreateTime() {
                return createTime;
        }

        public void setCreateTime(Date createTime) {
                this.createTime = createTime;
        }

        public Date getUpdateTime() {
                return updateTime;
        }

        public void setUpdateTime(Date updateTime) {
                this.updateTime = updateTime;
        }

        public String getDelFlag() {
                return delFlag;
        }

        public void setDelFlag(String delFlag) {
                this.delFlag = delFlag;
        }

        public String getPhone() {
                return phone;
        }

        public void setPhone(String phone) {
                this.phone = phone;
        }

        public String getAvatar() {
                return avatar;
        }

        public void setAvatar(String avatar) {
                this.avatar = avatar;
        }

        public String getDeptId() {
                return deptId;
        }

        public void setDeptId(String deptId) {
                this.deptId = deptId;
        }

        public String getDeptName() {
                return deptName;
        }

        public void setDeptName(String deptName) {
                this.deptName = deptName;
        }

        public List<AdminRole> getRoleList() {
                return roleList;
        }

        public void setRoleList(List<AdminRole> roleList) {
                this.roleList = roleList;
        }

        public String getNickName() {
                return nickName;
        }

        public void setNickName(String nickName) {
                this.nickName = nickName;
        }

        @Override
        public String toString() {
                return "UserVO{" +
                        "id='" + id + '\'' +
                        ", userName='" + userName + '\'' +
                        ", nickName='" + nickName + '\'' +
                        ", password='" + password + '\'' +
                        ", salt='" + salt + '\'' +
                        ", createTime=" + createTime +
                        ", updateTime=" + updateTime +
                        ", delFlag='" + delFlag + '\'' +
                        ", phone='" + phone + '\'' +
                        ", avatar='" + avatar + '\'' +
                        ", deptId='" + deptId + '\'' +
                        ", deptName='" + deptName + '\'' +
                        ", roleList=" + roleList +
                        '}';
        }
}


