package com.david.dashrpc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * RPC 提供者受請求處理
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RpcRequest implements Serializable {
    /**
     * 服務名稱
     */
    private String serviceName;
    /**
     * 方法名稱
     */
    private String methodName;
    /**
     * 方法參數類型列表
     */
    private Class<?>[] parameterTypes;
    /**
     * 方法參數列表
     */
    private Object[] args;

}
