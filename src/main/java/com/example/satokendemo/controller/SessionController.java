package com.example.satokendemo.controller;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Romic
 * @date 2024/3/5
 * @description
 * Account-Session: 指的是框架为每个 账号id 分配的 Session
 * Token-Session: 指的是框架为每个 token 分配的 Session
 *
 * token-session：是同一个账号多端登陆，允许共存。这个可以测试一下。
 * sa-token.is-share=false,配置这个配置项，同一个账号多端登陆生成多个不同的凭证
 */
@RestController
@RequestMapping("/session")
public class SessionController {
    //获取账户会话 http://localhost:8081/session/getAccountSession
    @GetMapping("/getAccountSession")
    public SaResult getAccountSession(){
        SaSession session = StpUtil.getSession();
        return SaResult.ok();
    }

    @GetMapping("/getTokenSession")
    public SaResult getTokenSession(){
        SaSession session = StpUtil.getTokenSession();
        return SaResult.ok();
    }

    @GetMapping("/getSessionList/{id}")
    public SaResult getSessionList(@PathVariable Long id){
        //获取指定账号id的所有session
        return SaResult.data(StpUtil.getSessionByLoginId(id));
    }
}
