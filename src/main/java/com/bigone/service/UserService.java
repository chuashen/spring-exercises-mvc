package com.bigone.service;

import com.bigone.entity.UserInfo;

import java.util.List;

public interface UserService {

    List<UserInfo> findUsersByage(int age);

    UserInfo getUserInfoByNo(String userNo);

    void updateUserInfo(UserInfo user);


}
