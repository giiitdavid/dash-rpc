package com.david.dashrpc.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
 * 本地方法注冊器 增查刪
 */
public class LocalRegistry {
    /*
     * 注冊信息服務使用線程安全ConcurrentHashMap
     * key: 服務接口名稱
     * value: 服務實現類
     */
    private static final Map<String, Class<?>> map = new ConcurrentHashMap<>();
    /*
     * 注冊服務
     * @param serviceName: 服務接口名稱
     * @param implClass: 服務實現類
     */
    public static void register(String serviceName, Class<?> implClass){
        map.put(serviceName,implClass);
    }
    /*
     * 獲取現有服務
     * @param serviceName: 服務接口名稱
     * @return
     */
    public static Class<?> get(String serviceName){
        return map.get(serviceName);
    }
    /*
     * 刪除服務
     *
     * @param serviceName 服務接口名稱
     */
    public static void remove(String serviceName){
        map.remove(serviceName);
    }
}
