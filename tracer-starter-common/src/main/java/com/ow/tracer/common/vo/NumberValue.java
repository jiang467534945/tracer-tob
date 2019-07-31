package com.ow.tracer.common.vo;

/**
 * @auther: Easy
 * @date: 19-5-28 10:55
 * @description:
 */
public class NumberValue {
    Integer num;
    Integer sum;

    public NumberValue() {
        super();
    }

    public NumberValue(Integer num, Integer sum) {
        this.num = num;
        this.sum = sum;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }
}
