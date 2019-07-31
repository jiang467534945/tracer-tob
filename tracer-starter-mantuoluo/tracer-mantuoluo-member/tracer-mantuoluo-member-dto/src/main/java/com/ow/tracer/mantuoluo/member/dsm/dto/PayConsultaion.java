package com.ow.tracer.mantuoluo.member.dsm.dto;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ow.tracer.common.base.BaseDTO;

import java.util.Date;

/**
 * Explain:     [实体类]
 * Date:        [2019-06-01 15:54:34]
 * Coder:       [becky]
 * Version:     [1.0]
 */
 @SuppressWarnings("serial")
  @TableName("pay_consultaion")
public class PayConsultaion extends BaseDTO<PayConsultaion> {

    private String orderNo;

    private String orderName;

    private int type;

    private int status;

    private String consultationId;

    private String memberId;

    /**创建时间*/
    private Date creatTime;

    /**是否已读  0 未读,  1已读 ,  默认 0未读*/
    private int readed;

    private Double totalFee;

    private String prepayId;

    public Double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Double totalFee) {
        this.totalFee = totalFee;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderName() {
        return this.orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getConsultationId() {
        return this.consultationId;
    }

    public void setConsultationId(String consultationId) {
        this.consultationId = consultationId;
    }

    public String getMemberId() {
        return this.memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Date getCreatTime() {
        return this.creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public int getReaded() {
        return this.readed;
    }

    public void setReaded(int readed) {
        this.readed = readed;
    }

    public String toString(){
        final StringBuilder strBuf = new StringBuilder("PayConsultaion{\n");
        strBuf.append("orderNo:").append(this.orderNo).append(",\n");
        strBuf.append("orderName:").append(this.orderName).append(",\n");
        strBuf.append("type:").append(this.type).append(",\n");
        strBuf.append("status:").append(this.status).append(",\n");
        strBuf.append("consultationId:").append(this.consultationId).append(",\n");
        strBuf.append("memberId:").append(this.memberId).append(",\n");
        strBuf.append("creatTime:").append(this.creatTime).append(",\n");
        strBuf.append("readed:").append(this.readed).append("\n");
        strBuf.append("}");
        return strBuf.toString();
    }
}
