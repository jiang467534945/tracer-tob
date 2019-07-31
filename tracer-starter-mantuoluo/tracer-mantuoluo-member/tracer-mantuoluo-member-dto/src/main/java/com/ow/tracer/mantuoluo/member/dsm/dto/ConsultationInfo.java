package com.ow.tracer.mantuoluo.member.dsm.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ow.tracer.common.base.BaseDTO;

/**
　　* @author :辛余
　　* @description: 详细咨询实体类
　　* @date 19-5-14 上午9:41
　　*/
@TableName("consultationInfo")
public class ConsultationInfo extends BaseDTO<ConsultationInfo> {


    private String ctype;
    private String memberName;
    private String memberPhone;
    private int memberSex;
    private int marriage;
    private String childNum;
    private String context;
    private String conId;
    private String memberId;
    private String tutorId;
    private String birthDay;
    private String birthNum;
    @TableField(exist = false)

    private String message;
    @TableField(exist = false)

    private Consultation consultation;

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public int getMemberSex() {
        return memberSex;
    }

    public void setMemberSex(int memberSex) {
        this.memberSex = memberSex;
    }

    public int getMarriage() {
        return marriage;
    }

    public void setMarriage(int marriage) {
        this.marriage = marriage;
    }

    public String getChildNum() {
        return childNum;
    }

    public void setChildNum(String childNum) {
        this.childNum = childNum;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getConId() {
        return conId;
    }

    public void setConId(String conId) {
        this.conId = conId;
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

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getBirthNum() {
        return birthNum;
    }

    public void setBirthNum(String birthNum) {
        this.birthNum = birthNum;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    public ConsultationInfo(String ctype, String memberName, String memberPhone, int memberSex, int marriage, String childNum, String context, String conId) {
        this.ctype = ctype;
        this.memberName = memberName;
        this.memberPhone = memberPhone;
        this.memberSex = memberSex;
        this.marriage = marriage;
        this.childNum = childNum;
        this.context = context;
        this.conId = conId;
    }

    public ConsultationInfo() {
    }
}
