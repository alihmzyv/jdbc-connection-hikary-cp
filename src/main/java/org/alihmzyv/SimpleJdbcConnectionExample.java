package org.alihmzyv;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import oracle.jdbc.datasource.impl.OracleDataSource;

public class SimpleJdbcConnectionExample {
    public static void main(String[] args) throws SQLException, InterruptedException {
        OracleDataSource dataSource = new OracleDataSource();
        dataSource.setURL("jdbc:oracle:thin:@//localhost:1521/FREEPDB1");
        dataSource.setUser("app_user");
        dataSource.setPassword("app_usr_psswd");
        System.out.println("Sleeping for 10 seconds...");
        Thread.sleep(10_000);
        System.out.println("Waking up...");
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            System.out.println("Opened connection");
            Thread.sleep(20_000);
            ResultSet resultSet = stmt.executeQuery("select * from dual");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
        }
        System.out.println("Closed connection");
        Thread.sleep(30_000);
    }
}