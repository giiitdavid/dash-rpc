package com.david.dashrpc.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Mock 服務代理服務(JDK動態代理 )
 * 做一個空對象並根據返回值返回默認值來MOCK
 *
 */

@Slf4j
public class MockServiceProxy implements InvocationHandler{
    /**
     * 調用代理
     *
     * @return
     * @throws Throwable
     */

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        //根據方法返回值類型，生成特定默認值對象
        Class<?> methodReturnType = method.getReturnType();
        log.info("mock invoke {}", method.getName());
        return getDefaultObject(methodReturnType);
    }

    /**
     * 生成指定類型的默認值對象
     *
     * @param methodReturnType
     * @return
     * 使用 == 比较 Class 对象是不安全的，应该使用 equals() 方法进行比较。
     */
    private Object getDefaultObject(Class<?> methodReturnType) {
        //基本類型
        if(methodReturnType.isPrimitive()){
            if(methodReturnType==boolean.class){
                return false;
            } else if (methodReturnType == short.class){
                return (short) 0;
            } else if (methodReturnType == int.class){
                return 0;
            } else if (methodReturnType == long.class){
                return 0L;
            } else if (methodReturnType == float.class){
                return 0.0f;
            } else if (methodReturnType == double.class){
                return 0.0;
            } else if (methodReturnType == char.class){
                return '\u0000';
            }
        }
        // 對象類型
        return null;
    }
}
