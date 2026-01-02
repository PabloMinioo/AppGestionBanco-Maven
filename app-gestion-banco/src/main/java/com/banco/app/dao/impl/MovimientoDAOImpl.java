package com.banco.app.dao.impl;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.banco.app.dao.interfaz.MovimientoDAO;
import com.banco.app.entidad.Movimiento;
import com.banco.app.entidad.Cuenta;
import com.banco.app.entidad.TipoMovimiento;
import com.banco.app.entidad.TipoCuenta;

public class MovimientoDAOImpl implements MovimientoDAO {

    private Connection conexion;

    public MovimientoDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public boolean agregarMovimiento(Movimiento movimiento) {
        String sql = "INSERT INTO MOVIMIENTOS (FECHA_MOVIMIENTO, DETALLE, IMPORTE, NUMERO_CUENTA, ID_TIPO_MOVIMIENTO) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setDate(1, java.sql.Date.valueOf(movimiento.getFechaMovimiento()));
            ps.setString(2, movimiento.getDetalle());
            ps.setBigDecimal(3, movimiento.getImporte());
            ps.setInt(4, movimiento.getCuenta().getNumeroCuenta());
            ps.setInt(5, movimiento.getTipoMovimiento().getIdTipoMovimiento());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Movimiento obtenerPorID(int idMovimiento) {
        Movimiento movimiento = null;
        String sql = "SELECT m.*, tm.DESCRIPCION AS DESC_TIPO, c.*, tc.DESCRIPCION AS DESC_TIPO_CUENTA " +
                     "FROM MOVIMIENTOS m " +
                     "JOIN TIPOS_MOVIMIENTOS tm ON m.ID_TIPO_MOVIMIENTO = tm.ID_TIPO_MOVIMIENTO " +
                     "JOIN CUENTAS c ON m.NUMERO_CUENTA = c.NUMERO_CUENTA " +
                     "JOIN TIPOS_CUENTAS tc ON c.ID_TIPO_CUENTA = tc.ID_TIPO_CUENTA " +
                     "WHERE m.ID_MOVIMIENTO = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idMovimiento);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    TipoMovimiento tipoMovimiento = new TipoMovimiento(rs.getInt("ID_TIPO_MOVIMIENTO"), rs.getString("DESC_TIPO"));
                    TipoCuenta tipoCuenta = new TipoCuenta(rs.getInt("ID_TIPO_CUENTA"), rs.getString("DESC_TIPO_CUENTA"));

                    Cuenta cuenta = new Cuenta(
                        rs.getInt("NUMERO_CUENTA"),
                        null,
                        rs.getDate("FECHA_CREACION").toLocalDate(),
                        tipoCuenta,
                        rs.getString("CBU"),
                        rs.getBigDecimal("SALDO"),
                        rs.getBoolean("ESTADO")
                    );

                    movimiento = new Movimiento(
                        rs.getInt("ID_MOVIMIENTO"),
                        rs.getDate("FECHA_MOVIMIENTO").toLocalDate(),
                        rs.getString("DETALLE"),
                        rs.getBigDecimal("IMPORTE"),
                        cuenta,
                        tipoMovimiento
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movimiento;
    }

    @Override
    public List<Movimiento> obtenerPorCuenta(int numeroCuenta) {
        List<Movimiento> movimientos = new ArrayList<>();
        String sql = "SELECT m.*, tm.DESCRIPCION AS DESC_TIPO " +
                     "FROM MOVIMIENTOS m " +
                     "JOIN TIPOS_MOVIMIENTOS tm ON m.ID_TIPO_MOVIMIENTO = tm.ID_TIPO_MOVIMIENTO " +
                     "WHERE m.NUMERO_CUENTA = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, numeroCuenta);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    TipoMovimiento tipoMovimiento = new TipoMovimiento(rs.getInt("ID_TIPO_MOVIMIENTO"), rs.getString("DESC_TIPO"));
                    Movimiento movimiento = new Movimiento(
                        rs.getInt("ID_MOVIMIENTO"),
                        rs.getDate("FECHA_MOVIMIENTO").toLocalDate(),
                        rs.getString("DETALLE"),
                        rs.getBigDecimal("IMPORTE"),
                        null,
                        tipoMovimiento
                    );
                    movimientos.add(movimiento);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movimientos;
    }

    @Override
    public List<Movimiento> obtenerPorFecha(LocalDate fecha) {
        List<Movimiento> movimientos = new ArrayList<>();
        String sql = "SELECT m.*, tm.DESCRIPCION AS DESC_TIPO " +
                     "FROM MOVIMIENTOS m " +
                     "JOIN TIPOS_MOVIMIENTOS tm ON m.ID_TIPO_MOVIMIENTO = tm.ID_TIPO_MOVIMIENTO " +
                     "WHERE m.FECHA_MOVIMIENTO = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setDate(1, java.sql.Date.valueOf(fecha));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    TipoMovimiento tipoMovimiento = new TipoMovimiento(rs.getInt("ID_TIPO_MOVIMIENTO"), rs.getString("DESC_TIPO"));
                    Movimiento movimiento = new Movimiento(
                        rs.getInt("ID_MOVIMIENTO"),
                        rs.getDate("FECHA_MOVIMIENTO").toLocalDate(),
                        rs.getString("DETALLE"),
                        rs.getBigDecimal("IMPORTE"),
                        null,
                        tipoMovimiento
                    );
                    movimientos.add(movimiento);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movimientos;
    }

    @Override
    public List<Movimiento> obtenerTodos() {
        List<Movimiento> movimientos = new ArrayList<>();
        String sql = "SELECT m.*, tm.DESCRIPCION AS DESC_TIPO " +
                     "FROM MOVIMIENTOS m " +
                     "JOIN TIPOS_MOVIMIENTOS tm ON m.ID_TIPO_MOVIMIENTO = tm.ID_TIPO_MOVIMIENTO";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                TipoMovimiento tipoMovimiento = new TipoMovimiento(rs.getInt("ID_TIPO_MOVIMIENTO"), rs.getString("DESC_TIPO"));
                Movimiento movimiento = new Movimiento(
                    rs.getInt("ID_MOVIMIENTO"),
                    rs.getDate("FECHA_MOVIMIENTO").toLocalDate(),
                    rs.getString("DETALLE"),
                    rs.getBigDecimal("IMPORTE"),
                    null,
                    tipoMovimiento
                );
                movimientos.add(movimiento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movimientos;
    }
}
