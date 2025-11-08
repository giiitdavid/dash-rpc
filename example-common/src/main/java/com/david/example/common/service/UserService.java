package com.david.example.common.service;

import com.david.example.common.model.User;

/**
 * 用户服務
 */

public interface UserService {

    /**
     * 獲取用户
     *
     * @param user
     * @return
     */
    User getUser(User user);

    /**
     * 獲取數字測試
     */
    default short getNumber(){
        return 1;
    }
}
