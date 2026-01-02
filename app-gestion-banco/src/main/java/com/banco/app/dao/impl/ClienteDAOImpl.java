package com.banco.app.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.banco.app.dao.interfaz.ClienteDAO;
import com.banco.app.entidad.Cliente;
import com.banco.app.entidad.Localidad;
import com.banco.app.entidad.Provincia;

public class ClienteDAOImpl implements ClienteDAO {

    private Connection conexion;

    public ClienteDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        String sql = "INSERT INTO CLIENTES (DNI, CUIL, NOMBRE, APELLIDO, SEXO, NACIONALIDAD, FECHA_NACIMIENTO, DIRECCION, ID_PROVINCIA, ID_LOCALIDAD, CORREO, TELEFONO, ESTADO) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, cliente.getDni());
            ps.setString(2, cliente.getCuil());
            ps.setString(3, cliente.getNombre());
            ps.setString(4, cliente.getApellido());
            ps.setString(5, String.valueOf(cliente.getSexo()));
            ps.setString(6, cliente.getNacionalidad());
            ps.setDate(7, java.sql.Date.valueOf(cliente.getFechaNacimiento()));
            ps.setString(8, cliente.getDireccion());
            ps.setString(9, cliente.getProvincia().getIdProvincia());
            ps.setString(10, cliente.getLocalidad().getIdLocalidad());
            ps.setString(11, cliente.getCorreo());
            ps.setString(12, cliente.getTelefono());
            ps.setBoolean(13, cliente.getEstado());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificarCliente(Cliente cliente) {
        String sql = "UPDATE CLIENTES SET NOMBRE=?, APELLIDO=?, SEXO=?, NACIONALIDAD=?, FECHA_NACIMIENTO=?, DIRECCION=?, ID_PROVINCIA=?, ID_LOCALIDAD=?, CORREO=?, TELEFONO=?, ESTADO=? WHERE DNI=?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, String.valueOf(cliente.getSexo()));
            ps.setString(4, cliente.getNacionalidad());
            ps.setDate(5, java.sql.Date.valueOf(cliente.getFechaNacimiento()));
            ps.setString(6, cliente.getDireccion());
            ps.setString(7, cliente.getProvincia().getIdProvincia());
            ps.setString(8, cliente.getLocalidad().getIdLocalidad());
            ps.setString(9, cliente.getCorreo());
            ps.setString(10, cliente.getTelefono());
            ps.setBoolean(11, cliente.getEstado());
            ps.setString(12, cliente.getDni());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarCliente(String dni) {
        String sql = "UPDATE CLIENTES SET ESTADO = FALSE WHERE DNI = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, dni);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Cliente obtenerClientePorDNI(String dni) {
        Cliente cliente = null;
        String sql = "SELECT c.*, p.DESCRIPCION AS DESC_PROVINCIA, l.DESCRIPCION AS DESC_LOCALIDAD " +
                     "FROM CLIENTES c " +
                     "JOIN PROVINCIAS p ON c.ID_PROVINCIA = p.ID_PROVINCIA " +
                     "JOIN LOCALIDADES l ON c.ID_LOCALIDAD = l.ID_LOCALIDAD " +
                     "WHERE c.DNI = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Provincia provincia = new Provincia(rs.getString("ID_PROVINCIA"), rs.getString("DESC_PROVINCIA"));
                    Localidad localidad = new Localidad(rs.getString("ID_LOCALIDAD"), rs.getString("DESC_LOCALIDAD"), provincia);

                    cliente = new Cliente();
                    cliente.setDni(rs.getString("DNI"));
                    cliente.setCuil(rs.getString("CUIL"));
                    cliente.setNombre(rs.getString("NOMBRE"));
                    cliente.setApellido(rs.getString("APELLIDO"));
                    cliente.setSexo(rs.getString("SEXO").charAt(0));
                    cliente.setNacionalidad(rs.getString("NACIONALIDAD"));
                    cliente.setFechaNacimiento(rs.getDate("FECHA_NACIMIENTO").toLocalDate());
                    cliente.setDireccion(rs.getString("DIRECCION"));
                    cliente.setProvincia(provincia);
                    cliente.setLocalidad(localidad);
                    cliente.setCorreo(rs.getString("CORREO"));
                    cliente.setTelefono(rs.getString("TELEFONO"));
                    cliente.setEstado(rs.getBoolean("ESTADO"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT c.*, p.DESCRIPCION AS DESC_PROVINCIA, l.DESCRIPCION AS DESC_LOCALIDAD " +
                     "FROM CLIENTES c " +
                     "JOIN PROVINCIAS p ON c.ID_PROVINCIA = p.ID_PROVINCIA " +
                     "JOIN LOCALIDADES l ON c.ID_LOCALIDAD = l.ID_LOCALIDAD " +
                     "WHERE c.ESTADO = TRUE";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Provincia provincia = new Provincia(rs.getString("ID_PROVINCIA"), rs.getString("DESC_PROVINCIA"));
                Localidad localidad = new Localidad(rs.getString("ID_LOCALIDAD"), rs.getString("DESC_LOCALIDAD"), provincia);

                Cliente cliente = new Cliente();
                cliente.setDni(rs.getString("DNI"));
                cliente.setCuil(rs.getString("CUIL"));
                cliente.setNombre(rs.getString("NOMBRE"));
                cliente.setApellido(rs.getString("APELLIDO"));
                cliente.setSexo(rs.getString("SEXO").charAt(0));
                cliente.setNacionalidad(rs.getString("NACIONALIDAD"));
                cliente.setFechaNacimiento(rs.getDate("FECHA_NACIMIENTO").toLocalDate());
                cliente.setDireccion(rs.getString("DIRECCION"));
                cliente.setProvincia(provincia);
                cliente.setLocalidad(localidad);
                cliente.setCorreo(rs.getString("CORREO"));
                cliente.setTelefono(rs.getString("TELEFONO"));
                cliente.setEstado(rs.getBoolean("ESTADO"));

                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }
}
