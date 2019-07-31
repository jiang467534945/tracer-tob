package com.ow.tracer.mantuoluo.member.dsm.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ow.tracer.common.base.BaseDTO;

@SuppressWarnings("serial")
@TableName("v_tutor")
public class Tutor extends BaseDTO<Tutor> {

    private String userId;

    private String name;

    private String message;

    private String phone;

    private String typeId;

    private String image;

    /**
     * 0.未审核
     * 1.已审核
     */
    private int status;

    /**
     * 0.未通过
     * 1.已通过
     */
    private int pass;
    @TableField(exist = false)
private String [] type;

    private Double userLat;
    private Double userLng;
    public Tutor() {
    }

    public Tutor(String userId, String name, String message, String phone, String image, int status, int pass) {
        this.userId = userId;
        this.name = name;
        this.message = message;
        this.phone = phone;
        this.image = image;
        this.status = status;
        this.pass = pass;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPass() {
        return pass;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
    }

    public Double getUserLat() {
        return userLat;
    }

    public void setUserLat(Double userLat) {
        this.userLat = userLat;
    }

    public Double getUserLng() {
        return userLng;
    }

    public void setUserLng(Double userLng) {
        this.userLng = userLng;
    }

    @Override
    public String toString() {
        return "Tutor{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", message='" + message + '\'' +
                ", phone='" + phone + '\'' +
                ", image='" + image + '\'' +
                ", status=" + status +
                ", pass=" + pass +
                '}';
    }
}
