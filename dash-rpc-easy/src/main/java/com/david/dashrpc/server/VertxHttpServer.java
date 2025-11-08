package com.david.dashrpc.server;

import io.vertx.core.Vertx;

/**
 * Vertx Based HTTP 服務器
 */
public class VertxHttpServer implements HttpServer {

    /**
     * 啟動 HTTP 服務器
     * @param port
     */
    @Override
    public void doStart(int port) {
        // 建立 Vert.x 實例
        Vertx vertx = Vertx.vertx();

        // 建立 HTTP 服務器
        io.vertx.core.http.HttpServer server = vertx.createHttpServer();

        //監聽埠口並處理請求 綁定請求處理程序
        server.requestHandler(new HttpServerHandler());


        // 啟動 HTTP 服務器並監聽指定端埠口
        server.listen(port, result -> {
            if (result.succeeded()) {
                System.out.println("Server is now listening on port " + port);
            } else {
                System.err.println("Failed to start server: " + result.cause());
            }
        });
    }
}

//        // 監聽埠口並處理請求 測試
//        server.requestHandler(request -> {
//            // 處理 HTTP 請求
//            System.out.println("Received request: " + request.method() + " " + request.uri());
//
//            // 傳送 HTTP 回應
//            request.response()
//                    .putHeader("content-type", "text/plain")
//                    .end("Hello from Vert.x HTTP server!");
//        });