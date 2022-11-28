package com.lege.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lege
 * @Description 权限demo
 * @create 2022-11-24 10:21
 */
@RestController
@RequestMapping("/jur/")
public class JurAuthController {
    @RequestMapping("getPermission")
    public SaResult getPermission() {
        List<String> permissionList = StpUtil.getPermissionList();
        System.out.println("当前登录账号拥有的所有权限：" + permissionList);

        // 查询角色信息 ，如果当前会话未登录，会返回一个空集合
        List<String> roleList = StpUtil.getRoleList();
        System.out.println("当前登录账号拥有的所有角色：" + roleList);

        // 返回给前端
        return SaResult.ok()
                .set("roleList", roleList)
                .set("permissionList", permissionList);
    }

    // 角色校验  ---- http://localhost:8081/jur/checkRole
    @RequestMapping("checkRole")
    public SaResult checkRole() {

        // 判断：当前账号是否拥有一个角色，返回 true 或 false
        // 		如果当前账号未登录，则永远返回 false
        StpUtil.hasRole("admin");
        StpUtil.hasRoleAnd("admin", "ceo", "cfo");  // 指定多个，必须全部拥有才会返回 true
        StpUtil.hasRoleOr("admin", "ceo", "cfo");	  // 指定多个，只要拥有一个就会返回 true

        // 校验：当前账号是否拥有一个角色，校验不通过时会抛出 `NotRoleException` 异常
        // 		如果当前账号未登录，则永远校验失败
        StpUtil.checkRole("admin");
        StpUtil.checkRoleAnd("admin", "ceo", "cfo");  // 指定多个，必须全部拥有才会校验通过
        StpUtil.checkRoleOr("admin", "ceo", "cfo");  // 指定多个，只要拥有一个就会校验通过

        return SaResult.ok();
    }

    // 权限通配符  ---- http://localhost:8081/jur/wildcardPermission
    @RequestMapping("wildcardPermission")
    public SaResult wildcardPermission() {

        // 前提条件：在 StpInterface 实现类中，为账号返回了 "art.*" 泛权限
        StpUtil.hasPermission("art.add");  // 返回 true
        StpUtil.hasPermission("art.delete");  // 返回 true
        StpUtil.hasPermission("goods.add");  // 返回 false，因为前缀不符合

        // * 符合可以出现在任意位置，比如权限码的开头，当账号拥有 "*.delete" 时
        StpUtil.hasPermission("goods.add");        // false
        StpUtil.hasPermission("goods.delete");     // true
        StpUtil.hasPermission("art.delete");      // true

        // 也可以出现在权限码的中间，比如当账号拥有 "shop.*.user" 时
        StpUtil.hasPermission("shop.add.user");  // true
        StpUtil.hasPermission("shop.delete.user");  // true
        StpUtil.hasPermission("shop.delete.goods");  // false，因为后缀不符合

        // 注意点：
        // 1、上帝权限：当一个账号拥有 "*" 权限时，他可以验证通过任何权限码
        // 2、角色校验也可以加 * ，指定泛角色，例如： "*.admin"，暂不赘述

        return SaResult.ok();
    }
}
