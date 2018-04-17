package com.dao;

import com.model.UserModel;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Created by Ireneusz Zagan on 13.04.18, 21:42
 * Contact: sefrys@gmail.com
 */
public class UserDao {

    public void addUser(UserModel userModel) {
        DataSource ds = null;
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/database");
        } catch (NamingException e) {
            e.printStackTrace();
        }


        PreparedStatement stmt;

        try {
            Connection con = Objects.requireNonNull(ds).getConnection();
            stmt = con.prepareStatement("INSERT INTO users (login, email, password) VALUES (?, ?, ?)");

            stmt.setString(1, userModel.getLogin());
            stmt.setString(2, userModel.getEmail());
            stmt.setString(3, userModel.getPassword());

            stmt.executeUpdate();

            stmt.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer findUserLogin(String userLogin) {
        DataSource ds = null;
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/database");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        PreparedStatement stmt;
        Integer matchCount = null;

        try {
            Connection con = Objects.requireNonNull(ds).getConnection();
            stmt = con.prepareStatement("SELECT count(1) AS match FROM users WHERE login=?");
            stmt.setString(1, userLogin);

            ResultSet rs = stmt.executeQuery();
            matchCount =  rs.getInt("match");

            stmt.close();
            rs.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matchCount;
    }

    public Integer findUserEmail(String userEmail) {
        DataSource ds = null;
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/database");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        PreparedStatement stmt;
        Integer matchCount = null;

        try {
            Connection con = Objects.requireNonNull(ds).getConnection();
            stmt = con.prepareStatement("SELECT count(1) AS match FROM users WHERE email=?");
            stmt.setString(1, userEmail);

            ResultSet rs = stmt.executeQuery();
            matchCount =  rs.getInt("match");

            stmt.close();
            rs.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matchCount;
    }

    public Integer findUserLoginPasswordMatch(String userLogin, String userPassword) {
        DataSource ds = null;
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/database");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        PreparedStatement stmt;
        Integer matchCount = null;

        try {
            Connection con = Objects.requireNonNull(ds).getConnection();
            stmt = con.prepareStatement("SELECT count(1) AS match FROM users WHERE login=? AND password=?" );
            stmt.setString(1, userLogin);
            stmt.setString(2, userPassword);

            ResultSet rs = stmt.executeQuery();
            matchCount =  rs.getInt("match");

            stmt.close();
            rs.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matchCount;
    }
}
