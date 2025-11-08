package com.david.dashrpc.utils;


import cn.hutool.setting.dialect.Props;

/**
 * Config的工具類
 */
public class ConfigUtils {
    /**
     * 加載配置對象
     *
     * @param tClass
     * @param prefix
     * @param <T>
     * @return
     */

    public static <T> T loadConfig(Class<T> tClass, String prefix){
        return loadConfig(tClass,prefix, "");
    }

    /**
     * 加載配置對象, 區分環境版
     *
     * @param tClass
     * @param prefix
     * @param environment
     * @param <T>
     * @return
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix, String environment){
        StringBuilder configFileBuilder = new StringBuilder("application");
        if(environment != null && !environment.trim().isEmpty()){ //避免Null, 去除前後空白判斷空否
            configFileBuilder.append("").append(environment);
        }
        configFileBuilder.append(".properties");
        Props props = new Props(configFileBuilder.toString());
        return props.toBean(tClass,prefix);
    }
}
