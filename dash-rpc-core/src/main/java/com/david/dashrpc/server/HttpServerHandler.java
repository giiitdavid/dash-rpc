package com.david.dashrpc.server;

import com.david.dashrpc.model.RpcRequest;
import com.david.dashrpc.model.RpcResponse;
import com.david.dashrpc.registry.LocalRegistry;
import com.david.dashrpc.serializer.JDKSerializerImpl;
import com.david.dashrpc.serializer.Serializer;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

import java.io.IOException;
import java.lang.reflect.Method;

public class HttpServerHandler implements Handler <HttpServerRequest>{
    @Override
    public void handle(HttpServerRequest request){
        // 指定一個序列化器
        final Serializer serializer = new JDKSerializerImpl();

        // 紀錄日志
        System.out.println("Received request:" + request.method() + " " + request.uri());

        // 異步處理HTTP請求
        // 將請求反序列化為object，並從請求object中取得參數
        request.bodyHandler(body -> {
            byte[] bytes = body.getBytes();
            RpcRequest rpcRequest = null;
            try{
                rpcRequest = serializer.deserialize(bytes, RpcRequest.class);
            }catch(Exception e){
                e.printStackTrace();
            }

            // 構造響應結對象
            RpcResponse rpcResponse = new RpcResponse();
            // 如果請求為null運吉時返回；
            if (rpcRequest == null){
                rpcResponse.setMessage("rpcRequest is null");
                doResponse(request, rpcResponse, serializer);
                return;
            }

            try{ // 很難搞
                // 獲取調用服務實現類，並通過反射調用
                Class<?> implClass = LocalRegistry.get(rpcRequest.getServiceName());
                Method method  = implClass.getMethod(rpcRequest.getMethodName(), rpcRequest.getParameterTypes());
                Object result = method.invoke(implClass.newInstance(), rpcRequest.getArgs());
                //封裝返回結果
                rpcResponse.setData(result);
                rpcResponse.setDataType(method.getReturnType());
                rpcResponse.setMessage("success");
            }catch (Exception e){
                e.printStackTrace();
                rpcResponse.setMessage(e.getMessage());
                rpcResponse.setException(e);
            }
            //響應
            doResponse(request, rpcResponse, serializer);
        });
    }

    /**
     * 響應
     *
     * @param request
     * @param rpcResponse
     * @param serializer
     */
    void doResponse(HttpServerRequest request, RpcResponse rpcResponse, Serializer serializer){
        HttpServerResponse httpServerResponse = request.response()
                .putHeader("content-type", "application/json");
        try{
            //序列化
            byte[] serialized = serializer.serialize(rpcResponse);
            httpServerResponse.end(Buffer.buffer(serialized));
        } catch(IOException e){
            e.printStackTrace();
            httpServerResponse.end(Buffer.buffer());
        }
    }
}
