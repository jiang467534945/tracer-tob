package com.ow.tracer.mantuoluo.member.web.rest;

import cn.hutool.http.HttpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther: Easy
 * @date: 19-7-4 15:29
 * @description:
 */
@Controller
@RequestMapping("/wxOauth")
public class WxOauthController {
    @GetMapping(value="/jscode2session")
    @ResponseBody
    public String jscode2session(String  code){
        System.out.println(code);
        Map map = new HashMap();
        map.put("appid","wx4767f75cd9752e25");
        map.put("secret","8c9ea02e94803ec6b83b798dff46b8cb");
        map.put("js_code",code);
        map.put("grant_type","authorization_code");
        String bbb= HttpUtil.get("https://api.weixin.qq.com/sns/jscode2session",map);
        System.out.println(bbb);
        return  bbb;

    }
}
