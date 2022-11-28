package com.lege.up;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lege
 * @Description 二级认证
 * @create 2022-11-28 14:41
 */
@RestController
@RequestMapping("/safe/")
public class SafeAuthController {


    @RequestMapping("deleteProject")
    public SaResult deleteProject(String projectId) {
        if (!StpUtil.isSafe()) {
            return SaResult.error("仓库删除失败,请完成二级认证");
        }
        return SaResult.ok("删除成功");
    }


    //提供密码二级认证
    @RequestMapping("openSafe")
    public SaResult openSafe(String password) {
        if ("123456".equals(password)) {
            StpUtil.openSafe(120);
            return SaResult.ok("二级认证成功");
        }
        return SaResult.error("二级认证失败");
    }

    //手动关闭认证
    @RequestMapping("closeSafe")
    public SaResult closeSafe() {
        StpUtil.closeSafe();
        return SaResult.ok();
    }


    //获取应用密钥
    @RequestMapping("getClientSecret")
    public SaResult getClientSecret(){
        StpUtil.checkSafe("client");
        // 第2步，如果已完成二级认证，则返回数据
        return SaResult.data("aaaa-bbbb-cccc-dddd-eeee");
    }

    @RequestMapping("openClientSafe")
    public SaResult openClientSafe(String gesture){
        if ("123456".equals(gesture)) {
            StpUtil.openSafe("client",600);
            return SaResult.ok("二级认证成功");
        }
        return SaResult.error("二级认证失败");
    }

    @RequestMapping("isClientSafe")
    public SaResult isClientSafe(){
        return SaResult.ok("当前是否已完成 client 二级认证：" + StpUtil.isSafe("client"));
    }




}
