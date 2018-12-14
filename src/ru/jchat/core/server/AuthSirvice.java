package ru.jchat.core.server;

import java.awt.font.MultipleMaster;
import java.sql.*;

public class AuthSirvice {
    private Connection connection;
    private Statement stmt;

    public void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:userb.db");
        stmt = connection.createStatement();
    }

    public String getNickByLoginAndPass(String login, String pass){
        try {
            stmt.execute("SELECT nick FROM users WHERE login = '" + login + "' AND password = '" + pass + "';");
            ResultSet rs = stmt.getResultSet();
            if(rs.next()){
                return rs.getString("nick");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void disconnect(){
        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
