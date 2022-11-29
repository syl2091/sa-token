package com.lege.up;

import cn.dev33.satoken.basic.SaBasicUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lege
 * @Description Http Basic认证
 * @create 2022-11-29 14:14
 */
@RestController
@RequestMapping("/basic/")
public class HttpBasicController {


    @RequestMapping("login")
    public SaResult login() {
        // 1、Http Basic 认证校验，账号=sa，密码=123456
        SaBasicUtil.check("sa:123456");

        // 2、返回数据
        String data = "这是通过 Http Basic 校验后才返回的数据";
        return SaResult.data(data);
    }
}
