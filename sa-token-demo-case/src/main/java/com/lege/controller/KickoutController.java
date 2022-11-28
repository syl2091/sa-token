package com.lege.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lege
 * @Description 踢人下线
 * @create 2022-11-24 13:25
 */
@RestController
@RequestMapping("/kickout/")
public class KickoutController {

    @RequestMapping("logout")
    public SaResult logout(long userId) {

        // 强制注销等价于对方主动调用了注销方法，再次访问会提示：Token无效。
        StpUtil.logout(userId);

        // 返回
        return SaResult.ok();
    }

    /**
     * 将指定账号踢下线
     */
    @RequestMapping("kickout")
    public SaResult kickout(long userId) {
        StpUtil.kickout(userId);
        return SaResult.ok();
    }

    /**
     * 通过token踢人下线
     * @param token
     * @return
     */
    @RequestMapping("kickoutByTokenValue")
    public SaResult kickoutByTokenValue(String token) {
        StpUtil.kickoutByTokenValue(token);
        return SaResult.ok();
    }
}
