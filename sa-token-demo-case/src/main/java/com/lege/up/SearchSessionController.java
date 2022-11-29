package com.lege.up;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.lege.model.SysUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lege
 * @Description
 * @create 2022-11-29 14:00
 */
@RestController
@RequestMapping("/search/")
public class SearchSessionController {
    // 会话登录接口  ---- http://localhost:8081/search/login?userId=10001&张三&age=18
    @RequestMapping("login")
    public SaResult login(Integer userId, String name, int age) {
        // 先登录上
        StpUtil.login(userId);

        // 再把 User 对象存储在 SaSession 上
        SysUser user = new SysUser();
        user.setId(userId);
        user.setName(name);
        user.setAge(age);
        StpUtil.getSession().set("user", user);

        // 返回
        return SaResult.ok("账号登录成功");
    }


    // 会话查询接口  ---- http://localhost:8081/search/getList?start=0&size=10
    @RequestMapping("getList")
    public SaResult getList(int start, int size) {
        // 创建集合
        List<SaSession> sessionList = new ArrayList<>();

        // 分页查询数据
        List<String> sessionIdList = StpUtil.searchSessionId("", start, size, false);
        for (String sessionId: sessionIdList) {
            SaSession session = StpUtil.getSessionBySessionId(sessionId);
            sessionList.add(session);
        }

        // 返回
        return SaResult.data(sessionList);
    }

    // 会话查询接口  ---- http://localhost:8081/disable/logout
    @RequestMapping("logout")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok("账号退出成功");
    }
}
