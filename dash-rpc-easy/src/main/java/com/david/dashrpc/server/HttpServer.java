package com.david.dashrpc.server;

/**
 * HTTP 服務器介面(接口)
 */
public interface HttpServer {

    /**
     * 啟動服務器
     *
     * @param port
     */
    void doStart(int port);
}
