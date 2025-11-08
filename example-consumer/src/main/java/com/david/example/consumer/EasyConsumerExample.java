package com.david.example.consumer;

import com.david.dashrpc.proxy.ServiceProxy;
import com.david.dashrpc.proxy.ServiceProxyFactory;
import com.david.example.common.model.User;
import com.david.example.common.service.UserService;
//import com.david.dashrpc.proxy.ServiceProxyFactory;

/**
 * 簡單服務消費者調用示例, 非實用RPC示例
 */
public class EasyConsumerExample {

    public static void main(String[] args) {
        // 代理 (靜態代理極不靈活，基本不用，用動態代理更好)
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);

        // UserService 的實現類對象
//        UserService userService = null;
        User user = new User();
        user.setName("david");
        // 調用
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else { // 初步未能獲取到User所以預留null
            System.out.println("user == null");
        }
    }
}