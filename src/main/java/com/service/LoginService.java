package com.service;

import com.dao.UserDao;

/**
 * Created by Ireneusz Zagan on 14.04.18, 20:42
 * Contact: sefrys@gmail.com
 */
public class LoginService {
    private UserDao dao;

    public LoginService() {
        dao = new UserDao();
    }

    public boolean authenticateUser(String userLogin, String userPassword) {
        return dao.findUserLoginPasswordMatch(userLogin, userPassword) == 1;
    }

}

