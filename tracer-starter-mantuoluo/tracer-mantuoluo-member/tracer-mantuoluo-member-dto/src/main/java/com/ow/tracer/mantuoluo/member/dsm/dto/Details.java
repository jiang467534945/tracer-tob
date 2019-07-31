package com.ow.tracer.mantuoluo.member.dsm.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ow.tracer.common.base.BaseDTO;


import java.util.Date;

@SuppressWarnings("serial")
@TableName("d_details")
public class Details extends BaseDTO<Details> {
    private String id;

    private String memberId;

    private String tutorId;

    private String payment;

    private String consultationId;

    private Date createTime;


    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
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

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(String consultationId) {
        this.consultationId = consultationId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Details(String id, String memberId, String tutorId, String payment, String consultationId, Date createTime) {
        this.id = id;
        this.memberId = memberId;
        this.tutorId = tutorId;
        this.payment = payment;
        this.consultationId = consultationId;
        this.createTime = createTime;
    }

    public Details() {
    }
}
