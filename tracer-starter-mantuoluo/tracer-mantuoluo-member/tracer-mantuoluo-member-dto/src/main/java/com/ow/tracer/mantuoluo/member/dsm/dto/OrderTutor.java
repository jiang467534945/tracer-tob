package com.ow.tracer.mantuoluo.member.dsm.dto;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ow.tracer.common.base.BaseDTO;

/**
 * Explain:     [实体类]
 * Date:        [2019-06-12 16:06:45]
 * Coder:       [becky]
 * Version:     [1.0]
 */
 @SuppressWarnings("serial")
  @TableName("order_tutor")
public class OrderTutor extends BaseDTO<OrderTutor> {

    /**导师ID*/
    private String tutorId;

    /**用户ID*/
    private String memberId;

    /**金额*/
    private double tofee;

    /**分润关联*/
    private String conId;

    /**分润类型*/
    private int type;

    private String payId;

    public String getTutorId() {
        return this.tutorId;
    }

    public void setTutorId(String tutorId) {
        this.tutorId = tutorId;
    }

    public String getMemberId() {
        return this.memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public double getTofee() {
        return this.tofee;
    }

    public void setTofee(double tofee) {
        this.tofee = tofee;
    }

    public String getConId() {
        return this.conId;
    }

    public void setConId(String conId) {
        this.conId = conId;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPayId() {
        return this.payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public String toString(){
        final StringBuilder strBuf = new StringBuilder("OrderTutor{\n");
        strBuf.append("tutorId:").append(this.tutorId).append(",\n");
        strBuf.append("memberId:").append(this.memberId).append(",\n");
        strBuf.append("tofee:").append(this.tofee).append(",\n");
        strBuf.append("conId:").append(this.conId).append(",\n");
        strBuf.append("type:").append(this.type).append(",\n");
        strBuf.append("payId:").append(this.payId).append("\n");
        strBuf.append("}");
        return strBuf.toString();
    }
}
