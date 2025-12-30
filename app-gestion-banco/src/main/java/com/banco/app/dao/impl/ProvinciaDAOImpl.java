package com.banco.app.dao.impl;

import com.banco.app.dao.interfaz.ProvinciaDAO;
import com.banco.app.entidad.Provincia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProvinciaDAOImpl implements ProvinciaDAO {
    private Connection conexion;

    public ProvinciaDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<Provincia> obtenerTodasProvincias() {
        List<Provincia> provincias = new ArrayList<>();
        String sql = "SELECT * FROM PROVINCIAS";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                provincias.add(new Provincia(
                    rs.getString("ID_PROVINCIA"),
                    rs.getString("DESCRIPCION")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return provincias;
    }

    @Override
    public Provincia obtenerProvinciaPorID(String id) {
        Provincia provincia = null;
        String sql = "SELECT * FROM PROVINCIAS WHERE ID_PROVINCIA = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    provincia = new Provincia(
                        rs.getString("ID_PROVINCIA"),
                        rs.getString("DESCRIPCION")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return provincia;
    }
}

