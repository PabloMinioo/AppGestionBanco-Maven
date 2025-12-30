package com.banco.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.banco.app.dao.interfaz.TipoMovimientoDAO;
import com.banco.app.entidad.TipoMovimiento;

public class TipoMovimientoDAOImpl implements TipoMovimientoDAO {

    private Connection conexion;

    public TipoMovimientoDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<TipoMovimiento> obtenerTodosTiposMovimientos() {
        List<TipoMovimiento> movimientos = new ArrayList<>();
        String sql = "SELECT * FROM TIPOS_MOVIMIENTOS";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                movimientos.add(new TipoMovimiento(
                        rs.getInt("ID_TIPO_MOVIMIENTO"),
                        rs.getString("DESCRIPCION")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movimientos;
    }

    @Override
    public TipoMovimiento obtenerTipoMovimientoPorID(int id) {
        TipoMovimiento movimiento = null;
        String sql = "SELECT * FROM TIPOS_MOVIMIENTOS WHERE ID_TIPO_MOVIMIENTO = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    movimiento = new TipoMovimiento(
                            rs.getInt("ID_TIPO_MOVIMIENTO"),
                            rs.getString("DESCRIPCION"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movimiento;
    }
}
