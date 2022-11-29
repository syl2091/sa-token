package com.lege.up;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lege
 * @Description 同端互斥登录
 * @create 2022-11-29 10:10
 */
@RestController
@RequestMapping("/mutex/")
public class MutexLoginController {


    @RequestMapping("login")
    public SaResult login(long userId,String device){
        StpUtil.login(userId,device);
        return SaResult.ok("登录成功");
    }


    @RequestMapping("isLogin")
    public SaResult isLogin(){
        boolean login = StpUtil.isLogin();
        return SaResult.ok("当前客户端是否登录：" + login + "，登录的设备是：" + StpUtil.getLoginDevice());
    }
}
