package com.ow.tracer.common.util;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class GetAssemble {

    public static Set<String> set = new TreeSet<String>();

    public static void doSet(String start, String[] sourceList, int max) {
        String[] olds = start.split("_");
        if (olds.length == max) {
            set.add(start.replaceAll("_", "").trim());
        }
        else {
            for (int s = 0; s < sourceList.length; s++) {
                if (Arrays.asList(olds).contains(sourceList[s])) {
                    continue;
                }
                else {
                    doSet(start + "_" + sourceList[s], sourceList, max);
                }
            }
        }
    }

    public static void doSet(String[] sourceList, int max) {
        for (int start = 0; start < sourceList.length; start++) {
            doSet(sourceList[start], sourceList, max);
        }

    }

    public static void print() {
        System.out.println("Total:" + set.size());
        int cols = 10;
        for (String s : set) {
            System.out.print(s + " ");
            if (cols-- == 1) {
                System.out.println();
                cols = 10;
            }
        }
        set.clear();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method s tub
        String[] haha = new String[] { "2","3","4","5","6" };

    }
    public static  Set zuhe(String val){
        System.out.println(val);
        String[] value = val.split(","); // 用,分割
             Set<String> strSet = new TreeSet<String>();
for(int i=0;i<value.length;i++){

    for(int j=i+1;j<value.length;j++){
        strSet.add(value[i]+value[j]);
        for(int b=j+1;b<value.length;b++){
            strSet.add(value[i]+value[j]+value[b]);
            for(int c=b+1;c<value.length;c++){
                strSet.add(value[i]+value[j]+value[b]+value[c]);
                for(int d=c+1;d<value.length;d++){
                    strSet.add(value[i]+value[j]+value[b]+value[c]+value[d]);

                    for(int e=d+1;e<value.length;e++){
                        strSet.add(value[i]+value[j]+value[b]+value[c]+value[d]+value[e]);

                        for(int f=e+1;f<value.length;f++){
                            strSet.add(value[i]+value[j]+value[b]+value[c]+value[d]+value[e]+value[f]);
                        }
                    }
                }
            }
        }
    }

}
        for(String s:strSet){
            System.out.println(s);
        }
    return  strSet;
    }

}