package com.banco.app.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

import com.banco.app.dao.interfaz.CuentaDAO;
import com.banco.app.entidad.Cuenta;
import com.banco.app.entidad.TipoCuenta;

public class CuentaDAOImpl implements CuentaDAO {

    private Connection conexion;

    public CuentaDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public boolean agregarCuenta(Cuenta cuenta) {
        String sql = "INSERT INTO CUENTAS (DNI_CLIENTE, FECHA_CREACION, ID_TIPO_CUENTA, CBU, SALDO, ESTADO) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, cuenta.getCliente().getDni());
            ps.setDate(2, java.sql.Date.valueOf(cuenta.getFechaCreacion()));
            ps.setInt(3, cuenta.getTipoCuenta().getIdTipoCuenta());
            ps.setString(4, cuenta.getCbu());
            ps.setBigDecimal(5, cuenta.getSaldo());
            ps.setBoolean(6, cuenta.getEstado());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificarSaldo(int numeroCuenta, BigDecimal nuevoSaldo) {
        String sql = "UPDATE CUENTAS SET SALDO = ? WHERE NUMERO_CUENTA = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setBigDecimal(1, nuevoSaldo);
            ps.setInt(2, numeroCuenta);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean bajaLogica(int numeroCuenta) {
        String sql = "UPDATE CUENTAS SET ESTADO = FALSE WHERE NUMERO_CUENTA = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, numeroCuenta);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Cuenta obtenerPorNumeroCuenta(int numeroCuenta) {
        Cuenta cuenta = null;
        String sql = "SELECT c.*, tc.DESCRIPCION AS DESC_TIPO " +
                     "FROM CUENTAS c " +
                     "JOIN TIPOS_CUENTAS tc ON c.ID_TIPO_CUENTA = tc.ID_TIPO_CUENTA " +
                     "WHERE c.NUMERO_CUENTA = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, numeroCuenta);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    TipoCuenta tipoCuenta = new TipoCuenta(rs.getInt("ID_TIPO_CUENTA"), rs.getString("DESC_TIPO"));
                    cuenta = new Cuenta(
                        rs.getInt("NUMERO_CUENTA"),
                        null,
                        rs.getDate("FECHA_CREACION").toLocalDate(),
                        tipoCuenta,
                        rs.getString("CBU"),
                        rs.getBigDecimal("SALDO"),
                        rs.getBoolean("ESTADO")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cuenta;
    }

    @Override
    public List<Cuenta> obtenerPorCliente(String dniCliente) {
        List<Cuenta> cuentas = new ArrayList<>();
        String sql = "SELECT c.*, tc.DESCRIPCION AS DESC_TIPO " +
                     "FROM CUENTAS c " +
                     "JOIN TIPOS_CUENTAS tc ON c.ID_TIPO_CUENTA = tc.ID_TIPO_CUENTA " +
                     "WHERE c.DNI_CLIENTE = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, dniCliente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    TipoCuenta tipoCuenta = new TipoCuenta(rs.getInt("ID_TIPO_CUENTA"), rs.getString("DESC_TIPO"));
                    Cuenta cuenta = new Cuenta(
                        rs.getInt("NUMERO_CUENTA"),
                        null,
                        rs.getDate("FECHA_CREACION").toLocalDate(),
                        tipoCuenta,
                        rs.getString("CBU"),
                        rs.getBigDecimal("SALDO"),
                        rs.getBoolean("ESTADO")
                    );
                    cuentas.add(cuenta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cuentas;
    }

    @Override
    public List<Cuenta> obtenerCuentas() {
        List<Cuenta> cuentas = new ArrayList<>();
        String sql = "SELECT c.*, tc.DESCRIPCION AS DESC_TIPO " +
                     "FROM CUENTAS c " +
                     "JOIN TIPOS_CUENTAS tc ON c.ID_TIPO_CUENTA = tc.ID_TIPO_CUENTA " +
                     "WHERE c.ESTADO = TRUE";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                TipoCuenta tipoCuenta = new TipoCuenta(rs.getInt("ID_TIPO_CUENTA"), rs.getString("DESC_TIPO"));
                Cuenta cuenta = new Cuenta(
                    rs.getInt("NUMERO_CUENTA"),
                    null,
                    rs.getDate("FECHA_CREACION").toLocalDate(),
                    tipoCuenta,
                    rs.getString("CBU"),
                    rs.getBigDecimal("SALDO"),
                    rs.getBoolean("ESTADO")
                );
                cuentas.add(cuenta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cuentas;
    }
}
