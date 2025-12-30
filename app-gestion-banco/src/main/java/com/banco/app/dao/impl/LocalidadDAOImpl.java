package com.banco.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.banco.app.dao.interfaz.LocalidadDAO;
import com.banco.app.entidad.Localidad;
import com.banco.app.entidad.Provincia;

public class LocalidadDAOImpl implements LocalidadDAO {

    private Connection conexion;

    public LocalidadDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<Localidad> obtenerTodasLocalidades() {
        List<Localidad> localidades = new ArrayList<>();
        String sql = "SELECT l.ID_LOCALIDAD, l.DESCRIPCION AS DESC_LOCALIDAD, " +
                "p.ID_PROVINCIA, p.DESCRIPCION AS DESC_PROVINCIA " +
                "FROM LOCALIDADES l " +
                "JOIN PROVINCIAS p ON l.ID_PROVINCIA = p.ID_PROVINCIA";

        try (PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Provincia provincia = new Provincia(
                        rs.getString("ID_PROVINCIA"),
                        rs.getString("DESC_PROVINCIA"));
                Localidad localidad = new Localidad(
                        rs.getString("ID_LOCALIDAD"),
                        rs.getString("DESC_LOCALIDAD"),
                        provincia);
                localidades.add(localidad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return localidades;
    }

    @Override
    public Localidad obtenerLocalidadPorID(String id) {
        Localidad localidad = null;
        String sql = "SELECT l.ID_LOCALIDAD, l.DESCRIPCION AS DESC_LOCALIDAD, " +
                "p.ID_PROVINCIA, p.DESCRIPCION AS DESC_PROVINCIA " +
                "FROM LOCALIDADES l " +
                "JOIN PROVINCIAS p ON l.ID_PROVINCIA = p.ID_PROVINCIA " +
                "WHERE l.ID_LOCALIDAD = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Provincia provincia = new Provincia(
                            rs.getString("ID_PROVINCIA"),
                            rs.getString("DESC_PROVINCIA"));
                    localidad = new Localidad(
                            rs.getString("ID_LOCALIDAD"),
                            rs.getString("DESC_LOCALIDAD"),
                            provincia);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return localidad;
    }

    @Override
    public List<Localidad> obtenerLocalidadPorProvincia(String idProvincia) {
        List<Localidad> localidades = new ArrayList<>();
        String sql = "SELECT l.ID_LOCALIDAD, l.DESCRIPCION AS DESC_LOCALIDAD, " +
                "p.ID_PROVINCIA, p.DESCRIPCION AS DESC_PROVINCIA " +
                "FROM LOCALIDADES l " +
                "JOIN PROVINCIAS p ON l.ID_PROVINCIA = p.ID_PROVINCIA " +
                "WHERE p.ID_PROVINCIA = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, idProvincia);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Provincia provincia = new Provincia(
                            rs.getString("ID_PROVINCIA"),
                            rs.getString("DESC_PROVINCIA"));
                    Localidad localidad = new Localidad(
                            rs.getString("ID_LOCALIDAD"),
                            rs.getString("DESC_LOCALIDAD"),
                            provincia);
                    localidades.add(localidad);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return localidades;
    }

}
