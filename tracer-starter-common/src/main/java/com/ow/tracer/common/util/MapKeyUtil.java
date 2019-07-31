package com.ow.tracer.common.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.Map;

/**
 * @auther: Easy
 * @date: 19-6-12 10:19
 * @description:
 */
public class MapKeyUtil<T> {

    public  QueryWrapper<T> getWrapper(Map map){
        System.out.println(map.size());
        QueryWrapper<T>queryWrapper = new QueryWrapper<T>();
        for(Object key : map.keySet()){
            System.out.println(key.toString());
            String value = map.get(key.toString()).toString();
            queryWrapper.eq(key.toString(),value);

        }
        return queryWrapper;
    }
}
