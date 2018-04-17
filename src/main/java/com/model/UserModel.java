package com.model;

/**
 * Created by Ireneusz Zagan on 13.04.18, 22:19
 * Contact: sefrys@gmail.com
 */
public class UserModel {

    private String login;
    private String email;
    private String password;

    public UserModel(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
