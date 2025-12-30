package com.banco.app.ui;

import java.sql.Connection;

import com.banco.app.dao.impl.Conexion;

public class Main {

    public static void main(String[] args) {
        try (Connection con = Conexion.getConnection()) {
            System.out.println("¡CONEXION A SQL EXITOSA!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
