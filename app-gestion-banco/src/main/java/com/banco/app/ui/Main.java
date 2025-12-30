package com.banco.app.ui;

import com.banco.app.dao.Conexion;
import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
        try (Connection con = Conexion.getConnection()) {
            System.out.println("¡CONEXION A SQL EXITOSA!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
