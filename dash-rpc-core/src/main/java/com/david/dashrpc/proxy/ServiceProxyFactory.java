package com.david.dashrpc.proxy;


import java.lang.reflect.Proxy;

/**
 * 服務代理工廠(用於創建代理對象)
 */
public class ServiceProxyFactory {
    /**
     * 根據服務類獲取代理對象
     *
     * @param serviceClass 服務類
     * @param <T>
     * @return
     */
    public static <T> T getProxy(Class<T> serviceClass){
        return (T) Proxy.newProxyInstance(serviceClass.getClassLoader(), new Class[]{serviceClass}, new ServiceProxy());
    }
    /**
     * 根據服務類獲取MOCK模擬代理對象
     *
     * @param serviceClass 服務類
     * @param <T>
     * @return
     */
    public static <T> T getMockProxy(Class<T> serviceClass){
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new MockServiceProxy());
    }
}
