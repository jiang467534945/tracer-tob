package com.ow.tracer.common.util;

import com.ow.tracer.common.vo.NumberValue;
import io.swagger.models.auth.In;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther: Easy
 * @date: 19-5-28 10:22
 * @description:
 */
public class BirthDayUtils {
    public static List birthDayAge(String val){
        String birthDay = val.replace("-","");

        ArrayList<Integer> a = new ArrayList<>();//存数组中的数字
        ArrayList<Integer> b = new ArrayList<>();//存数组出现的次数
        List<NumberValue> numberValues = new ArrayList<>();
        birthDay=birthDay+yearMonthDay(birthDay);
        int[]   intArray = new int[birthDay.length()];
        for (int i = 0; i < birthDay.length(); i++) {
            Character ch = birthDay.charAt(i);
            intArray[i] = Integer.parseInt(ch.toString());
        }
        numberValues.add(new NumberValue(0,0));
        numberValues.add(new NumberValue(1,0));
        numberValues.add(new NumberValue(2,0));
        numberValues.add(new NumberValue(3,0));
        numberValues.add(new NumberValue(4,0));
        numberValues.add(new NumberValue(5,0));
        numberValues.add(new NumberValue(6,0));
        numberValues.add(new NumberValue(7,0));
        numberValues.add(new NumberValue(8,0));
        numberValues.add(new NumberValue(9,0));


        for (int i = 0; i < intArray.length; i++) {
            for (NumberValue numberValue:numberValues) {
                if (intArray[i] == numberValue.getNum()) {
                    numberValue.setSum(numberValue.getSum()+1);
                }
            }
        }

        return  numberValues;
    }

    public static String yearMonthDay(String val){
        String birthDay = val.replace("-","");


        Integer birthSumNum =0;
        Integer brithPayNum=0;
        Integer endNum =0;
        ArrayList<Integer> a = new ArrayList<>();//存数组中的数字
        ArrayList<Integer> b = new ArrayList<>();//存数组出现的次数
        List<NumberValue> numberValues = new ArrayList<>();
        int[] intArray = new int[birthDay.length()];// 新建一个数组用来保存num每一位的数字
        for (int i = 0; i < birthDay.length(); i++) {
            Character ch = birthDay.charAt(i);
            intArray[i] = Integer.parseInt(ch.toString());
        }
        for (Integer i :intArray){
            birthSumNum=birthSumNum+i;
        }
        String brithSumNumStr = birthSumNum.toString();
        int shiwei = birthSumNum/10;
        int gewei = birthSumNum%10;

        for(int i=0;i<1000;i++){
            int jisuan = shiwei+gewei;
            if(jisuan>10){
                shiwei=jisuan/10;
                gewei=jisuan%10;
                brithSumNumStr=brithSumNumStr+jisuan;
            }else{
                brithSumNumStr=brithSumNumStr+jisuan;
                break;
            }
        }




        return brithSumNumStr;
    }

    public static void main(String[] args) {
        yearMonthDay("1988-06-15");
    }
}
