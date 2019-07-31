package com.ow.tracer.mantuoluo.member.dsm.dto;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ow.tracer.common.base.BaseDTO;

/**
 * Explain:     [实体类]
 * Date:        [2019-06-12 15:24:06]
 * Coder:       [becky]
 * Version:     [1.0]
 */
 @SuppressWarnings("serial")
  @TableName("site_setting")
public class SiteSetting extends BaseDTO<SiteSetting> {

    /**计算付款*/
    private double onePrice;

    /**导师*/
    private double twoPrice;

    /**计算分成*/
    private double oneProportion;

    /**咨询分成*/
    private double twoProportion;

    public double getOnePrice() {
        return this.onePrice;
    }

    public void setOnePrice(double onePrice) {
        this.onePrice = onePrice;
    }

    public double getTwoPrice() {
        return this.twoPrice;
    }

    public void setTwoPrice(double twoPrice) {
        this.twoPrice = twoPrice;
    }

    public double getOneProportion() {
        return this.oneProportion;
    }

    public void setOneProportion(double oneProportion) {
        this.oneProportion = oneProportion;
    }

    public double getTwoProportion() {
        return this.twoProportion;
    }

    public void setTwoProportion(double twoProportion) {
        this.twoProportion = twoProportion;
    }

    public String toString(){
        final StringBuilder strBuf = new StringBuilder("SiteSetting{\n");
        strBuf.append("onePrice:").append(this.onePrice).append(",\n");
        strBuf.append("twoPrice:").append(this.twoPrice).append(",\n");
        strBuf.append("oneProportion:").append(this.oneProportion).append(",\n");
        strBuf.append("twoProportion:").append(this.twoProportion).append("\n");
        strBuf.append("}");
        return strBuf.toString();
    }
}
