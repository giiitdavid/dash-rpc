package com.david.example.provider;


import com.david.dashrpc.registry.LocalRegistry;
import com.david.example.common.service.UserService;
// import com.david.dashrpc.registry.LocalRegistry;
import com.david.dashrpc.server.HttpServer;
import com.david.dashrpc.server.VertxHttpServer;

/**
 * 簡單服務提供者示例, 非實用RPC示例
 */
public class EasyProviderExample {
    public static void main(String[] args) {
        // 提供服務的代碼
        // 注冊服務到本地注冊服務器
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 啟動Web服務
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(8080);
    }
}