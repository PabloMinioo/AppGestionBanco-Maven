package com.banco.app.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.banco.app.dao.interfaz.CuotaDAO;
import com.banco.app.entidad.Cuota;
import com.banco.app.entidad.Prestamo;
import com.banco.app.entidad.Cuenta;

public class CuotaDAOImpl implements CuotaDAO {

    private Connection conexion;

    public CuotaDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public boolean agregarCuota(Cuota cuota) {
        String sql = "INSERT INTO CUOTAS (ID_PRESTAMO, NUMERO_CUENTA, NUMERO_CUOTA, FECHA_VENCIMIENTO, MONTO_CUOTA, ESTADO) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, cuota.getPrestamo().getIdPrestamo());
            ps.setInt(2, cuota.getCuenta().getNumeroCuenta());
            ps.setInt(3, cuota.getNumeroCuota());
            ps.setDate(4, java.sql.Date.valueOf(cuota.getFechaVencimiento()));
            ps.setBigDecimal(5, cuota.getMontoCuota());
            ps.setString(6, String.valueOf(cuota.getEstado()));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificarEstado(int idCuota, char nuevoEstado) {
        String sql = "UPDATE CUOTAS SET ESTADO = ? WHERE ID_CUOTA = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, String.valueOf(nuevoEstado));
            ps.setInt(2, idCuota);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Cuota obtenerPorID(int idCuota) {
        Cuota cuota = null;
        String sql = "SELECT * FROM CUOTAS WHERE ID_CUOTA = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idCuota);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Prestamo prestamo = new Prestamo();
                    prestamo.setIdPrestamo(rs.getInt("ID_PRESTAMO"));

                    Cuenta cuenta = new Cuenta();
                    cuenta.setNumeroCuenta(rs.getInt("NUMERO_CUENTA"));

                    cuota = new Cuota(
                            rs.getInt("ID_CUOTA"),
                            prestamo,
                            cuenta,
                            rs.getInt("NUMERO_CUOTA"),
                            rs.getDate("FECHA_VENCIMIENTO").toLocalDate(),
                            rs.getBigDecimal("MONTO_CUOTA"),
                            rs.getString("ESTADO").charAt(0));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cuota;
    }

    @Override
    public List<Cuota> obtenerPorPrestamo(int idPrestamo) {
        List<Cuota> cuotas = new ArrayList<>();
        String sql = "SELECT * FROM CUOTAS WHERE ID_PRESTAMO = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idPrestamo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Prestamo prestamo = new Prestamo();
                    prestamo.setIdPrestamo(rs.getInt("ID_PRESTAMO"));

                    Cuenta cuenta = new Cuenta();
                    cuenta.setNumeroCuenta(rs.getInt("NUMERO_CUENTA"));

                    Cuota cuota = new Cuota(
                            rs.getInt("ID_CUOTA"),
                            prestamo,
                            cuenta,
                            rs.getInt("NUMERO_CUOTA"),
                            rs.getDate("FECHA_VENCIMIENTO").toLocalDate(),
                            rs.getBigDecimal("MONTO_CUOTA"),
                            rs.getString("ESTADO").charAt(0));
                    cuotas.add(cuota);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cuotas;
    }
}
