package com.banco.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.InputStream;

public class Conexion {

    private static Connection connection;

    private Conexion() {
    }

    public static Connection getConnection() {

        try {
            if (connection == null || connection.isClosed()) {

                Properties props = new Properties();
                InputStream input = Conexion.class
                        .getClassLoader()
                        .getResourceAsStream("db.properties");

                if (input == null) {
                    throw new RuntimeException("¡ERROR!. NO SE ENCONTRO EL ARCHIVO 'db.properties'");
                }

                props.load(input);

                String url = props.getProperty("db.url");
                String user = props.getProperty("db.user");
                String pass = props.getProperty("db.password");

                connection = DriverManager.getConnection(url, user, pass);
            }
        } catch (Exception e) {
            throw new RuntimeException("¡ERROR!. NO SE PUDO CONECTAR A LA BASE DE DATOS", e);
        }

        return connection;
    }
}
