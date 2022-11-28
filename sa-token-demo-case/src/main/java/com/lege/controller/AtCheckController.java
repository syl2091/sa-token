package com.lege.controller;

import cn.dev33.satoken.annotation.*;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lege
 * @Description
 * @create 2022-11-24 15:08
 */
@RestController
@RequestMapping("/at-check/")
public class AtCheckController {

    /**
     * 登录鉴权
     * @return
     */
    @SaCheckLogin
    @RequestMapping("checkLogin")
    public SaResult checkLogin(){
        return SaResult.ok();
    }


    /**
     * 权限校验
     * @return
     */
    @SaCheckPermission("user.add")
    @RequestMapping("checkPermission")
    public SaResult checkPermission(){
        return SaResult.ok();
    }

    /**
     * 多个权限校验 必须拥有多个权限
     * @return
     */
    @SaCheckPermission(value = {"user.add", "user.delete", "user.update"}, mode = SaMode.AND)
    @RequestMapping("checkPermission2")
    public SaResult checkPermission2(){
        return SaResult.ok();
    }

    /**
     * 多个权限校验  只要拥有一个
     * @return
     */
    @SaCheckPermission(value = {"user.add", "user.delete", "user.update"},mode = SaMode.OR)
    @RequestMapping("checkPermission3")
    public SaResult checkPermission3(){
        return SaResult.ok();
    }


    // 角色校验   ---- http://localhost:8081/at-check/checkRole
    //		只有具有 super-admin 角色的账号才可以进入方法
    @SaCheckRole("super-admin")
    @RequestMapping("checkRole")
    public SaResult checkRole() {
        // ...
        return SaResult.ok();
    }

    // 角色权限双重 “or校验”   ---- http://localhost:8081/at-check/userAdd
    //		具备 "user.add"权限 或者 "admin"角色 即可通过校验
    @RequestMapping("userAdd")
    @SaCheckPermission(value = "user.add", orRole = "admin")
    public SaResult userAdd() {
        return SaResult.data("用户信息");
    }

    // 忽略校验 ---- http://localhost:8081/at-check/ignore
    //		使用 @SaIgnore 修饰的方法，无需任何校验即可进入，具体使用示例可参照在线文档
    @SaIgnore
    @SaCheckLogin
    @RequestMapping("ignore")
    public SaResult ignore() {
        return SaResult.ok();
    }




}
