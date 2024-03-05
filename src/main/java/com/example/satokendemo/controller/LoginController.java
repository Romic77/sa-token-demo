package com.example.satokendemo.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录测试
 */
@RestController
@RequestMapping("/acc/")
public class LoginController {

    // 测试登录  ---- @Configuration
    //public class SaTokenConfigure implements WebMvcConfigurer {
    //    // 注册拦截器
    //    @Override
    //    public void addInterceptors(InterceptorRegistry registry) {
    //        // 注册 Sa-Token 拦截器，校验规则为 StpUtil.checkLogin() 登录校验。
    //        registry.addInterceptor(new SaInterceptor(handle -> StpUtil.checkLogin()))
    //                .addPathPatterns("/**")
    //                .excludePathPatterns("/user/doLogin");
    //    }
    //}
    @RequestMapping("doLogin")
    public SaResult doLogin(String name, String pwd, HttpServletRequest request) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对 
        if ("zhang".equals(name) && "123456".equals(pwd)) {
            StpUtil.login(10001, getOpenSystem(request));
            System.out.println(StpUtil.getLoginDevice());
            return SaResult.ok("登录成功");
        }
        return SaResult.error("登录失败");
    }


    /**
     * 获取操作系统名称
     *
     * @return
     */
    public String getOpenSystem(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        return getDevice(userAgent);
    }


    /**
     * 解析 用户代理(User-Agent)
     *
     * @param userAgent 用户代理User-Agent ,UA
     * @return "操作系统:%s
     * @author ghl
     */
    private static String getDevice(String userAgent) {
        //解析agent字符串
        UserAgent ua = UserAgent.parseUserAgentString(userAgent);
        //获取操作系统对象
        OperatingSystem os = ua.getOperatingSystem();
        return String.format("%s", os.getName());
    }


    // 查询登录状态  ---- http://localhost:8081/acc/isLogin
    @RequestMapping("isLogin")
    public SaResult isLogin() {
        return SaResult.ok("是否登录：" + StpUtil.isLogin());
    }

    // 查询 Token 信息  ---- http://localhost:8081/acc/tokenInfo
    @RequestMapping("tokenInfo")
    public SaResult tokenInfo() {
        return SaResult.data(StpUtil.getTokenInfo());
    }

    // 测试注销  ---- http://localhost:8081/acc/logout
    @RequestMapping("logout")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok();
    }

}
