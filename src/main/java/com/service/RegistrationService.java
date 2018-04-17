package com.service;

import com.dao.UserDao;
import com.model.UserModel;

/**
 * Created by Ireneusz Zagan on 13.04.18, 21:39
 * Contact: sefrys@gmail.com
 */
public class RegistrationService {
    private UserDao dao;

    public RegistrationService() {
        this.dao = new UserDao();
    }

    public boolean registerNewUser(UserModel newUser) {

        if (checkIfUserExists(newUser.getLogin()) && checkIfEmailExists(newUser.getEmail())) {
            dao.addUser(newUser);
            return true;
        }
        return false;
    }

    private boolean checkIfUserExists(String userLogin) {
        return dao.findUserLogin(userLogin) == 0;
    }

    private boolean checkIfEmailExists(String userEmail) {
        return dao.findUserEmail(userEmail) == 0;
    }
}
