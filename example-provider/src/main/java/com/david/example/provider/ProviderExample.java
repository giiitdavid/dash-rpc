package com.david.example.provider;

import com.david.dashrpc.RpcApplication;
import com.david.dashrpc.registry.LocalRegistry;
import com.david.dashrpc.server.HttpServer;
import com.david.dashrpc.server.VertxHttpServer;
import com.david.example.common.service.UserService;

/**
 * 實用RPC示例服務提供者示例
 */

public class ProviderExample {
    public static void main(String[] args) {
        //RPC 框架初始化
        RpcApplication.init();

        // 注冊服務到本地注冊服務器
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 啟動Web服務
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
