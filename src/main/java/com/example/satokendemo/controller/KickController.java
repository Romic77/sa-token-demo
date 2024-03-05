package com.example.satokendemo.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Romic
 * @date 2024/3/5
 * @description
 */
@RestController
@RequestMapping("/kick")
public class KickController {

    //强制指定账号注销下线 http://localhost:8081/logout/{id}
    @GetMapping("/logout/{id}")
    public SaResult logout(@PathVariable Long id){
        StpUtil.logout(id);                    // 强制指定账号注销下线
        return SaResult.ok();
    }

    //将指定账号踢下线  http://localhost:8081/kick/{id}
    @GetMapping("/kickout/{id}")
    public SaResult kickout(@PathVariable Long id){
        StpUtil.kickout(id);                    // 将指定账号踢下线
        return SaResult.ok();
    }

}
