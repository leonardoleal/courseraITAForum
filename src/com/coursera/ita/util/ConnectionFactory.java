package com.coursera.ita.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection() throws ClassNotFoundException, SQLException
    {
        return(getConnectionPostgre());
    }

    public static Connection getConnectionPostgre() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/forum_ita";
        String username = "postgres";
        String password = "postgres";
        Connection conexao = DriverManager.getConnection(url, username, password);
        return conexao;
    }

}