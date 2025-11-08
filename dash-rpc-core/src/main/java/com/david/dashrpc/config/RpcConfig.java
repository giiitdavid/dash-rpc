package com.david.dashrpc.config;

import lombok.Data;

/**
 * RPC 框架參數配置
 */
@Data
public class RpcConfig {
    /**
     * 名稱
     */
    private String name = "dash-rpc";

    /**
     * 版本號
     */
    private String version = "1.0.0";

    /**
     * 服務器主機名
     */
    private String serverHost = "localhost";

    /**
     * 服務器端口
     */
    private Integer serverPort = 8080;

    /**
     * 模擬調用
     */
    private boolean mock = false;

}


