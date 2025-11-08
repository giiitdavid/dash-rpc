package com.david.example.consumer;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.david.dashrpc.model.RpcRequest;
import com.david.dashrpc.model.RpcResponse;
import com.david.dashrpc.serializer.JDKSerializerImpl;
import com.david.dashrpc.serializer.Serializer;
import com.david.example.common.model.User;
import com.david.example.common.service.UserService;

import java.io.IOException;

/**
 * 靜態代理模式 放在這主要是為了方便，實際應用中應該使用動態代理，而且放在別的包中 todo 研究一下語法
 */
public class UserServiceProxy implements UserService {

    @Override
    public User getUser(User user) {
        //指定序列化器
        Serializer serializer = new JDKSerializerImpl();
        //發出RPC請求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(UserService.class.getName())
                .methodName("getUser")
                .parameterTypes(new Class[]{User.class})
                .args(new Object[] {user})
                .build();
        try { //try-with-resources
            byte[] bodyBytes = serializer.serialize(rpcRequest);
            byte[] result;
            try (HttpResponse httpResponse = HttpRequest.post("http://localhost:8080")
                    .body(bodyBytes)
                    .execute()) {
                result = httpResponse.bodyBytes();
            }
            RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
            return (User) rpcResponse.getData();
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
