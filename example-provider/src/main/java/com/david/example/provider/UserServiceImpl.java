package com.david.example.provider;

import com.david.example.common.model.User;
import com.david.example.common.service.UserService;

/**
 * 用户服務實現類 用户服務接口
 */
// todo 沒能找到User
public class UserServiceImpl implements UserService {
 //功能是列印用戶的名稱，並且返回參數中的用戶對象
    @Override
    public User getUser(User user) {
        System.out.println("用户名：" + user.getName());
        return user;
    }
}
