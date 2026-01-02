package com.banco.app.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.banco.app.dao.interfaz.PrestamoDAO;
import com.banco.app.entidad.Prestamo;
import com.banco.app.entidad.Cuenta;
import com.banco.app.entidad.TipoCuenta;

public class PrestamoDAOImpl implements PrestamoDAO {

    private Connection conexion;

    public PrestamoDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public boolean agregarPrestamo(Prestamo prestamo) {
        String sql = "INSERT INTO PRESTAMOS (DNI_CLIENTE, NUMERO_CUENTA, FECHA_PRESTAMO, IMPORTE_TOTAL, IMPORTE_SOLICITADO, PLAZO_MESES, MONTO_MENSUAL, ESTADO) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, prestamo.getCliente().getDni());
            ps.setInt(2, prestamo.getCuenta().getNumeroCuenta());
            ps.setDate(3, java.sql.Date.valueOf(prestamo.getFechaPrestamo()));
            ps.setBigDecimal(4, prestamo.getImporteTotal());
            ps.setBigDecimal(5, prestamo.getImporteSolicitado());
            ps.setInt(6, prestamo.getPlazo());
            ps.setBigDecimal(7, prestamo.getMontoMensual());
            ps.setString(8, String.valueOf(prestamo.getEstado()));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificarPrestamo(int idPrestamo, char nuevoEstado) {
        String sql = "UPDATE PRESTAMOS SET ESTADO = ? WHERE ID_PRESTAMO = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, String.valueOf(nuevoEstado));
            ps.setInt(2, idPrestamo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Prestamo obtenerPorID(int idPrestamo) {
        Prestamo prestamo = null;
        String sql = "SELECT p.*, c.NUMERO_CUENTA, c.FECHA_CREACION, c.CBU, c.SALDO, c.ESTADO AS ESTADO_CUENTA, tc.ID_TIPO_CUENTA, tc.DESCRIPCION AS DESC_TIPO " +
                     "FROM PRESTAMOS p " +
                     "JOIN CUENTAS c ON p.NUMERO_CUENTA = c.NUMERO_CUENTA " +
                     "JOIN TIPOS_CUENTAS tc ON c.ID_TIPO_CUENTA = tc.ID_TIPO_CUENTA " +
                     "WHERE p.ID_PRESTAMO = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idPrestamo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    TipoCuenta tipoCuenta = new TipoCuenta(rs.getInt("ID_TIPO_CUENTA"), rs.getString("DESC_TIPO"));
                    Cuenta cuenta = new Cuenta(
                        rs.getInt("NUMERO_CUENTA"),
                        null,
                        rs.getDate("FECHA_CREACION").toLocalDate(),
                        tipoCuenta,
                        rs.getString("CBU"),
                        rs.getBigDecimal("SALDO"),
                        rs.getBoolean("ESTADO_CUENTA")
                    );

                    prestamo = new Prestamo(
                        rs.getInt("ID_PRESTAMO"),
                        null,
                        cuenta,
                        rs.getDate("FECHA_PRESTAMO").toLocalDate(),
                        rs.getBigDecimal("IMPORTE_TOTAL"),
                        rs.getBigDecimal("IMPORTE_SOLICITADO"),
                        rs.getInt("PLAZO_MESES"),
                        rs.getBigDecimal("MONTO_MENSUAL"),
                        rs.getString("ESTADO").charAt(0)
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prestamo;
    }

    @Override
    public List<Prestamo> obtenerPorCliente(String dniCliente) {
        List<Prestamo> prestamos = new ArrayList<>();
        String sql = "SELECT * FROM PRESTAMOS WHERE DNI_CLIENTE = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, dniCliente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Prestamo prestamo = new Prestamo(
                        rs.getInt("ID_PRESTAMO"),
                        null,
                        null,
                        rs.getDate("FECHA_PRESTAMO").toLocalDate(),
                        rs.getBigDecimal("IMPORTE_TOTAL"),
                        rs.getBigDecimal("IMPORTE_SOLICITADO"),
                        rs.getInt("PLAZO_MESES"),
                        rs.getBigDecimal("MONTO_MENSUAL"),
                        rs.getString("ESTADO").charAt(0)
                    );
                    prestamos.add(prestamo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prestamos;
    }

    @Override
    public List<Prestamo> obtenerTodos() {
        List<Prestamo> prestamos = new ArrayList<>();
        String sql = "SELECT * FROM PRESTAMOS";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Prestamo prestamo = new Prestamo(
                    rs.getInt("ID_PRESTAMO"),
                    null,
                    null,
                    rs.getDate("FECHA_PRESTAMO").toLocalDate(),
                    rs.getBigDecimal("IMPORTE_TOTAL"),
                    rs.getBigDecimal("IMPORTE_SOLICITADO"),
                    rs.getInt("PLAZO_MESES"),
                    rs.getBigDecimal("MONTO_MENSUAL"),
                    rs.getString("ESTADO").charAt(0)
                );
                prestamos.add(prestamo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prestamos;
    }
}
