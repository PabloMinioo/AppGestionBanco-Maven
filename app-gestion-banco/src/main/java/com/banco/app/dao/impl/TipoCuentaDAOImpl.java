package com.banco.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.banco.app.dao.interfaz.TipoCuentaDAO;
import com.banco.app.entidad.TipoCuenta;

public class TipoCuentaDAOImpl implements TipoCuentaDAO {
    private Connection conexion;

    public TipoCuentaDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<TipoCuenta> obtenerTodosTiposCuentas() {
        List<TipoCuenta> tipos = new ArrayList<>();
        String sql = "SELECT * FROM TIPOS_CUENTAS";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                tipos.add(new TipoCuenta(
                    rs.getInt("ID_TIPO_CUENTA"),
                    rs.getString("DESCRIPCION")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tipos;
    }

    @Override
    public TipoCuenta obtenerTipoCuentaPorID(int id) {
        TipoCuenta tipo = null;
        String sql = "SELECT * FROM TIPOS_CUENTAS WHERE ID_TIPO_CUENTA = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tipo = new TipoCuenta(
                        rs.getInt("ID_TIPO_CUENTA"),
                        rs.getString("DESCRIPCION")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tipo;
    }
}

