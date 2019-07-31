package com.ow.tracer.mantuoluo.member.dsm.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ow.tracer.common.base.BaseDTO;

import java.util.Date;

/**
　　* @author :辛余
　　* @description: 记录实体类
　　* @date 19-5-14 上午9:41
　　*/
@TableName("consultation")
public class Consultation extends BaseDTO<Consultation> {


    private String birthDay;
    private int type;
    private int birthNum;
    private String memberId;
    private String tutorId;
    private Date creatTime;
    private int readed;
    private String name;
    private String sex;
    private String zinv;
    private String image;
    private String xingbie;

    @TableField(exist = false)
    private String lat;
    @TableField(exist = false)
    private String lng;
    @TableField(exist = false)
    private String phone;
    @TableField(exist = false)
    private PayConsultaion payConsultaion;
    @TableField(exist = false)
    private String year;
    @TableField(exist = false)
    private String month;
    @TableField(exist = false)
    private String day;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getReaded() {
        return readed;
    }

    public void setReaded(int readed) {
        this.readed = readed;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getBirthNum() {
        return birthNum;
    }

    public void setBirthNum(int birthNum) {
        this.birthNum = birthNum;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getTutorId() {
        return tutorId;
    }

    public void setTutorId(String tutorId) {
        this.tutorId = tutorId;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Consultation(String birthDay, int type, int birthNum, String memberId, String tutorId, Date creatTime, int readed) {
        this.birthDay = birthDay;
        this.type = type;
        this.birthNum = birthNum;
        this.memberId = memberId;
        this.tutorId = tutorId;
        this.creatTime = creatTime;
        this.readed = readed;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Consultation() {
    }

    public PayConsultaion getPayConsultaion() {
        return payConsultaion;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setPayConsultaion(PayConsultaion payConsultaion) {
        this.payConsultaion = payConsultaion;
    }

    public String getZinv() {
        return zinv;
    }

    public void setZinv(String zinv) {
        this.zinv = zinv;
    }

    public String getXingbie() {
        return xingbie;
    }

    public void setXingbie(String xingbie) {
        this.xingbie = xingbie;
    }
}
