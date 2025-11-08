package com.david.example.common.model;

import java.io.Serializable;

/**
 * 用户Entity ，序列化支持要Serializable
 */
public class User implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
