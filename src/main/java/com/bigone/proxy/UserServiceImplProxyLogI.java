package com.bigone.proxy;

import com.bigone.entity.UserInfo;
import com.bigone.service.UserService;
import com.bigone.service.impl.UserServiceImpl;

import java.util.List;

/**
 * 静态代理类
 * 代理目标对象：UserServiceImpl
 * 通过接口聚合实现代理
 * 将目标对象注入，调用实现代理
 *
 * 缺点：也需要创建大量物理java文件
 *
 * */
public class UserServiceImplProxyLogI implements UserService {

    private UserService userService;

    /**
     * 构造传入目标对象
     * */
    public UserServiceImplProxyLogI(UserService userService){
        this.userService = userService;
    }

    @Override
    public List<UserInfo> findUsersByage(int age) {
        System.out.println("执行代理逻辑----log-------");
        return userService.findUsersByage(age);
    }

    @Override
    public UserInfo getUserInfoByNo(String userNo) {
        System.out.println("执行代理逻辑----log-------");
        return userService.getUserInfoByNo(userNo);
    }

    @Override
    public void updateUserInfo(UserInfo user) {
        System.out.println("执行代理逻辑----log-------");
        userService.updateUserInfo(user);
    }
}
