package com.david.dashrpc.proxy;


import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.david.dashrpc.model.RpcRequest;
import com.david.dashrpc.model.RpcResponse;
import com.david.dashrpc.serializer.JDKSerializerImpl;
import com.david.dashrpc.serializer.Serializer;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 服務動態代理（簡易JDK動態代理）
 */

public class ServiceProxy implements InvocationHandler{
    /**
     * 調用動態代理
     *
     * @return
     * @throws Throwable
     */
   @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //指定序列化器
        Serializer serializer = new JDKSerializerImpl();
        //構出動態RPC請求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();
        try { //try-with-resources
            //序列化
            byte[] bodyBytes = serializer.serialize(rpcRequest);
            // 發送請求 但目前地址固定（後續用注冊中心和服務發現機制解決）
            try (HttpResponse httpResponse = HttpRequest.post("http://localhost:8080")
                    .body(bodyBytes)
                    .execute()) {
                byte[] result = httpResponse.bodyBytes();
                //反序列化
                RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
                return rpcResponse.getData();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

}
