package com.bigone.aop;

import org.springframework.stereotype.Service;

@Service
public class UserTransferImpl implements UserTransfer{

    @Override
    public void transfer() {
        System.out.println("执行service方法体");
    }
}
