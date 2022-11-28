package com.lege.test;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lege
 * @Description
 * @create 2022-11-28 11:17
 */
@RestController
@RequestMapping("/test/")
public class TestController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @RequestMapping("login")
    public AjaxJson login(@RequestParam(defaultValue = "10001") String id) {
        System.out.println("--------------- 测试Sa-Token缓存");
        StpUtil.login(id);
        return AjaxJson.getSuccess();
    }

    @RequestMapping("test")
    public AjaxJson test() {
        System.out.println("--------------- 测试业务缓存");
        stringRedisTemplate.opsForValue().set("hello", "hello word");
        return AjaxJson.getSuccess();

    }

}
