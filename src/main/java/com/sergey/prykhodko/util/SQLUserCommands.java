package com.sergey.prykhodko.util;

public interface SQLUserCommands {
    String INSERT = "INSERT INTO users(login, password, email, name, last_name) " +
            "VALUES(?, ?, ?, ?, ?)";
    String UPDATE = "UPDATE users SET password=?, email=?, name=?, last_name=? " +
            "WHERE id=?";
    String DELETE = "DELETE FROM users WHERE id=?";
    String GET_BY_LOGIN = "SELECT * FROM users WHERE login=?";
    String GET_ALL = "SELECT * FROM users";
    String GET_COUNT = "SELECT COUNT(*) FROM users";
    String GET_BY_CRITERIA = "SELECT * FROM users WHERE true";
    String GET_PORTION = "SELECT * FROM users LIMIT ?, ?";
    String GET_ALL_LOGIN = "SELECT login FROM users";
}
