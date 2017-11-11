package com.sergey.prykhodko.util;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public enum DataSources {
    MY_SQL{
        private static final String DRIVER_URL = "com.mysql.jdbc.Driver";
        private static final String DB_URL = "jdbc:mysql://localhost:3306/group_shopping";
        private static final String USERNAME = "root";
        private static final String PASSWORD = "Vika_Ruban";


    public DataSource getDataSource() throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER_URL);
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }
};

public DataSource getDataSource() throws SQLException {
        return null;
        }
}
