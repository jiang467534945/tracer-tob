package com.ow.tracer.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @auther: Easy
 * @date: 19-6-13 13:45
 * @description:
 */
public class NumberUtils {
    public static List intToList(int m){
        List<Integer> intList = new ArrayList<>();
        String numStr= String.valueOf(m);

        Integer [] maxNum = new Integer[numStr.length()];
        Integer [] minNum = new Integer[numStr.length()];
        for(int i=0;i<numStr.length();i++){
            maxNum[i]=Integer.parseInt(String.valueOf(numStr.charAt(i)));
            minNum[i]=Integer.parseInt(String.valueOf(numStr.charAt(i)));
        }


        String [] bucunzai = new String[]{};
        String bczStr = new String();
        for(int i=0;i<minNum.length-1;i++){
            for(int j=0;j<minNum.length-1-i;j++){
                if(minNum[j]>minNum[j+1]){
                    int temp=minNum[j];
                    minNum[j]=minNum[j+1];
                    minNum[j+1]=temp;
                }
            }
        }
        for(int i=maxNum.length-1;i>=0;i--){
            for(int j=maxNum.length-1;j>0;j--){
                if(maxNum[j]>maxNum[j-1]){
                    int temp=maxNum[j];
                    maxNum[j]=maxNum[j-1];
                    maxNum[j-1]=temp;
                }
            }
        }
        String min = "";
        String max = "";
        for(int num:minNum){
            min=min+num;


        }
        for(int num:maxNum){
            max = max+num;



        }

        for(int  i =0;i<10;i++){
            if(max.indexOf(i+"")<0){
                bczStr=i+","+bczStr;
            }
        }
        bucunzai= bczStr.split(","); // 用,分割

        Integer minInt = Integer.parseInt(min);
        Integer maxInt = Integer.parseInt(max);
        for(Integer i=Integer.parseInt(min);i<=Integer.parseInt(max);i++){

            if(isNotNum(i.toString(),bucunzai,numStr,maxNum)){
                intList.add(i);
            }
        }
        return intList;
    }

    private static boolean isNotNum(String str,String [] bcz,String num,Integer [] max){

        for(Integer  arr :max){
            //方法1：替换法
         String str1=num.replace(arr.toString(),""); //将字符串中i替换为空,创建新的字符串
         int cishu =num.length()-str1.length();
            //方法2：替换法
            String shengchengstr=str.replace(arr.toString(),""); //将字符串中i替换为空,创建新的字符串
            int shengcheng =str.length()-shengchengstr.length();
            if(cishu!=shengcheng){
                return false;
            }

        }
                for(String notStr :bcz){
                    if(str.indexOf(notStr)>=0){
                        return false;
                    }
                }
                if(str.charAt(0)=='0'){
                    return false;
                }
                 if(str.length()<2){
                        return false;
                    }
                 if(str.length()<num.length()){
                     return  false;
                 }
                return true;
    }

    public static void main(String[] args) {
        String id = "133333";
        //方法1：替换法
             String str1=id.replace("3",""); //将字符串中i替换为空,创建新的字符串
             System.out.println("i出现的次数为："+(id.length()-str1.length()));
    }
}
