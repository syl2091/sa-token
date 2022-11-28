package com.lege.up;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lege
 * @Description 记住我模式
 * @create 2022-11-28 14:22
 */
@RestController
@RequestMapping("/Remember/")
public class RememberMeController {
    //记住我登录
    @RequestMapping("dologin")
    public SaResult dologin(String username,String pwd){
        if ("zhangsan".equals(username)&&"123456".equals(pwd)) {
            StpUtil.login(10001,true);
            return SaResult.ok();
        }
        return SaResult.error();
    }
    //不记住我登录
    @RequestMapping("dologin2")
    public SaResult dologin2(String username,String pwd){
        if ("zhangsan".equals(username)&&"123456".equals(pwd)) {
            StpUtil.login(10001,false);
            return SaResult.ok();
        }
        return SaResult.error();
    }
    //七天免登录
    @RequestMapping("dologin3")
    public SaResult dologin3(String username,String pwd){
        if ("zhangsan".equals(username)&&"123456".equals(pwd)) {
            StpUtil.login(10001,60*60*24*7);
            return SaResult.ok();
        }
        return SaResult.error();
    }



}
