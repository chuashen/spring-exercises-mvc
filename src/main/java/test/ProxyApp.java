package test;

import com.bigone.proxy.ProxyUtil;
import com.bigone.proxy.UserServiceImplProxyLogE;
import com.bigone.proxy.UserServiceImplProxyLogI;
import com.bigone.service.UserService;
import com.bigone.service.impl.UserServiceImpl;

public class ProxyApp {


    /**
     * 调用继承静态代理
     * */
    static void invokeLogE(){
        UserServiceImplProxyLogE le = new UserServiceImplProxyLogE();
        le.findUsersByage(18);
        le.getUserInfoByNo("hake");
    }

    /**
     * 调用继承静态代理
     * */
    static void invokeLogI(){
        UserService le = new UserServiceImpl();
        UserServiceImplProxyLogI li = new UserServiceImplProxyLogI(le);
        li.getUserInfoByNo("hake");
        li.updateUserInfo(null);
    }


    public static void main(String[] args) {

//        invokeLogE();
//        invokeLogI();

        UserService userService = (UserService) ProxyUtil.newInstance(new UserServiceImpl());
        userService.getUserInfoByNo("hake");

    }


}
