package com.bigone.service.impl;

import com.bigone.entity.UserInfo;
import com.bigone.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public List<UserInfo> findUsersByage(int age) {
        System.out.println("执行人员信息列表查找，age：" + age);
        return null;
    }

    @Override
    public UserInfo getUserInfoByNo(String userNo) {
        System.out.println("执行人员信息查找，userNo：" + userNo);
        return null;
    }

    @Override
    public void updateUserInfo(UserInfo user) {
        System.out.println("执行人员信息更新");
    }
}
