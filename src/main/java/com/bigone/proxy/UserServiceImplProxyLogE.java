package com.bigone.proxy;

import com.bigone.entity.UserInfo;
import com.bigone.service.impl.UserServiceImpl;

import java.util.List;

/**
 * 静态代理类
 * 代理目标对象：UserServiceImpl
 * 通过继承实现代理
 * 对方法进行重写，织入新的代码逻辑
 *
 * 缺点：需要创建大量的代理文件类进行单一功能的代理，如果需要进行两种功能的代理，
 * 则需要连续继承
 * */
public class UserServiceImplProxyLogE extends UserServiceImpl {

    @Override
    public List<UserInfo> findUsersByage(int age) {
        System.out.println("执行代理逻辑----log-------");
        return super.findUsersByage(age);
    }

    @Override
    public UserInfo getUserInfoByNo(String userNo) {
        System.out.println("执行代理逻辑----log-------");
        return super.getUserInfoByNo(userNo);
    }

    @Override
    public void updateUserInfo(UserInfo user) {
        System.out.println("执行代理逻辑----log-------");
        super.updateUserInfo(user);
    }

}
