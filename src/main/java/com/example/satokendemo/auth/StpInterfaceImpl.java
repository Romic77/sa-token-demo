package com.example.satokendemo.auth;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Romic
 * @date 2024/3/5
 * @description 权限处理器 每次都会动态查询用户的权限
 */
@Component
public class StpInterfaceImpl implements StpInterface {
    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 本 list1 仅做模拟，实际项目中要根据具体业务逻辑来查询权限
        List<String> list1 = new ArrayList<String>();
        list1.add("101");
        list1.add("user.add");
        list1.add("user.update");
        list1.add("user.get");
        // list1.add("user.delete");
        list1.add("art.*");

        List<String> list2 = new ArrayList<>();
        list2.add("admin");

        if (Objects.equals(1L,loginId)) {
            return list1;
        }else if (Objects.equals(2L,loginId)){
            return list2;
        }
        return list1;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 本 list 仅做模拟，实际项目中要根据具体业务逻辑来查询角色
        List<String> list = new ArrayList<String>();
        list.add("admin");
        list.add("super-admin");
        return list;
    }
}
