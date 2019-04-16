package com.bigone.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("application.xml");

        UserTransfer userTransfer = (UserTransfer) ac.getBean("userTransferImpl");
        userTransfer.transfer();

    }

}
