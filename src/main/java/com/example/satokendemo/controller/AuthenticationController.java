package com.example.satokendemo.controller;

import cn.dev33.satoken.annotation.SaCheckBasic;
import cn.dev33.satoken.annotation.SaCheckDisable;
import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaCheckSafe;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Romic
 * @date 2024/3/5
 * @description
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    //获取所有权限 http://localhost:8081/auth/getPermissionList
    @GetMapping("/getPermissionList")
    public List<String> getPermissionList(){
        return StpUtil.getPermissionList();
    }

    //获取所有权限 http://localhost:8081/auth/getPermissionList
    @GetMapping("/getPermissionList/{id}")
    public List<String> getPermissionList(@PathVariable Long id){
        return StpUtil.getPermissionList(id);
    }

    //判断是否有权限 http://localhost:8081/auth/checkPermission/{auth}
    @GetMapping("/checkPermission/{auth}")
    public boolean checkPermission(@PathVariable String auth){
        return StpUtil.hasPermission(auth);
    }

    // 登录校验：只有登录之后才能进入该方法
    @SaCheckLogin
    @RequestMapping("info")
    public String info() {
        return "查询用户信息";
    }

    // 角色校验：必须具有指定角色才能进入该方法
    @SaCheckRole("super-admin")
    @RequestMapping("addSaCheckRole")
    public String addSaCheckRole() {
        return "用户增加";
    }

    // 权限校验：必须具有指定权限才能进入该方法
    @SaCheckPermission("user-add")
    @RequestMapping("addSaCheckPermission")
    public String addSaCheckPermission() {
        return "用户增加";
    }

    // 二级认证校验：必须二级认证之后才能进入该方法
    @SaCheckSafe()
    @RequestMapping("addSaCheckSafe")
    public String addSaCheckSafe() {
        return "用户增加";
    }

    // Http Basic 校验：只有通过 Basic 认证后才能进入该方法
    @SaCheckBasic(account = "sa:123456")
    @RequestMapping("basicAdd")
    public String basicAdd() {
        return "用户增加";
    }

    // 校验当前账号是否被封禁 comment 服务，如果已被封禁会抛出异常，无法进入方法
    @SaCheckDisable("comment")
    @RequestMapping("send")
    public String send() {
        return "查询用户信息";
    }

    // 注解式鉴权：只要具有其中一个权限即可通过校验
    @RequestMapping("atJurOr")
    @SaCheckPermission(value = {"user-add", "user-all", "user-delete"}, mode = SaMode.OR)
    public SaResult atJurOr() {
        return SaResult.data("用户信息");
    }

}
