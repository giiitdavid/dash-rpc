package com.david.example.consumer;

import com.david.dashrpc.config.RpcConfig;
import com.david.dashrpc.proxy.ServiceProxyFactory;
import com.david.dashrpc.utils.ConfigUtils;
import com.david.example.common.model.User;
import com.david.example.common.service.UserService;

/**
 * 實用RPC消費者調用示例
 * 1.  消費端程式碼拿到介面類型的代理物件（ServiceProxyFactory.getProxy(UserService.class)）。
 * 2.  調用 userService.someMethod(arg1, arg2)。
 * 3.  JVM 把調用路由到代理物件，代理物件內部把調用交給 ServiceProxy.invoke(...)。
 * 4.  invoke 根據 method + args 構造 RpcRequest。
 * 5.  invoke 序列化並發送請求到服務端（HTTP/Netty/...）。
 * 6.  服務端反序列化請求，找到對應實現並執行實際方法，封裝 RpcResponse 返回。
 * 7.  consume 端 invoke 反序列化回應，返回 rpcResponse.getData() 給原調用點，調用點得到值（或拋出異常）。
 *
 */

public class ConsumerExample {
    public static void main(String[] args){
        // 讀取打印全局配置
        RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class, "rpc");
        System.out.println(rpc);

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
        // 獲取數字測試(MOCK)
        long number = userService.getNumber();
        System.out.println(number);
    }
}
