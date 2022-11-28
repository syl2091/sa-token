package com.lege.up;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lege
 * @Description 后台返回token给前台
 * @create 2022-11-28 14:07
 */
@RestController
@RequestMapping("/NotCookie/")
public class NotCookieController {


    @RequestMapping("dologin")
    public SaResult dologin(String username,String pwd){
        if ("zhangsan".equals(username)&&"123456".equals(pwd)){
            //会话登录
            StpUtil.login(10001);
            return SaResult.ok();
        }
        return SaResult.error();
    }


    @RequestMapping("dologin2")
    public SaResult dologin2(String username,String pwd){
        if ("zhangsan".equals(username)&&"123456".equals(pwd)) {
            //会话登录
            StpUtil.login(10001);
            //获取token
            SaTokenInfo token = StpUtil.getTokenInfo();
            return SaResult.data(token);
        }
        return SaResult.error();
    }



}
