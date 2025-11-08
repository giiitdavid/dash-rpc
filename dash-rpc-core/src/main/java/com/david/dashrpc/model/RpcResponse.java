package com.david.dashrpc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * RPC 提供者響應返回
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RpcResponse implements Serializable {
    /**
     * 響應數據
     */
    private Object data;

    /**
     * 響應數據類型(備留)
     */
    private Class<?> dataType;

    /**
     * 響應信息
     */
    private String message;

    /**
     * 異常信息
     */
    private Exception exception;

}
