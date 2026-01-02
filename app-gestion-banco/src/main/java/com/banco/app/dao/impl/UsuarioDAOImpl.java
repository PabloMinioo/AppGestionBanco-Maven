package com.banco.app.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.banco.app.dao.interfaz.UsuarioDAO;
import com.banco.app.entidad.Usuario;
import com.banco.app.entidad.Cliente;
import com.banco.app.entidad.Localidad;
import com.banco.app.entidad.Provincia;

public class UsuarioDAOImpl implements UsuarioDAO {

    private Connection conexion;

    public UsuarioDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public boolean agregarUsuario(Usuario usuario) {
        String sql = "INSERT INTO USUARIOS (DNI_CLIENTE, NOMBRE_USUARIO, CONTRASENIA, ROL) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, usuario.getCliente().getDni());
            ps.setString(2, usuario.getNombreUsuario());
            ps.setString(3, usuario.getContrasenia()); // sin hash
            ps.setString(4, String.valueOf(usuario.getRol()));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificarPassword(int idUsuario, String nuevaPassword) {
        String sql = "UPDATE USUARIOS SET CONTRASENIA = ? WHERE ID_USUARIO = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nuevaPassword); // sin hash
            ps.setInt(2, idUsuario);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Usuario obtenerPorNombreUsuario(String nombreUsuario) {
        Usuario usuario = null;
        String sql = "SELECT u.*, c.*, p.DESCRIPCION AS DESC_PROVINCIA, l.DESCRIPCION AS DESC_LOCALIDAD " +
                     "FROM USUARIOS u " +
                     "JOIN CLIENTES c ON u.DNI_CLIENTE = c.DNI " +
                     "JOIN PROVINCIAS p ON c.ID_PROVINCIA = p.ID_PROVINCIA " +
                     "JOIN LOCALIDADES l ON c.ID_LOCALIDAD = l.ID_LOCALIDAD " +
                     "WHERE u.NOMBRE_USUARIO = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombreUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
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

                    usuario = new Usuario(
                        rs.getInt("ID_USUARIO"),
                        cliente,
                        rs.getString("NOMBRE_USUARIO"),
                        rs.getString("CONTRASENIA"), // texto plano
                        rs.getString("ROL").charAt(0)
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    public Usuario obtenerPorID(int idUsuario) {
        Usuario usuario = null;
        String sql = "SELECT u.*, c.*, p.DESCRIPCION AS DESC_PROVINCIA, l.DESCRIPCION AS DESC_LOCALIDAD " +
                     "FROM USUARIOS u " +
                     "JOIN CLIENTES c ON u.DNI_CLIENTE = c.DNI " +
                     "JOIN PROVINCIAS p ON c.ID_PROVINCIA = p.ID_PROVINCIA " +
                     "JOIN LOCALIDADES l ON c.ID_LOCALIDAD = l.ID_LOCALIDAD " +
                     "WHERE u.ID_USUARIO = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
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

                    usuario = new Usuario(
                        rs.getInt("ID_USUARIO"),
                        cliente,
                        rs.getString("NOMBRE_USUARIO"),
                        rs.getString("CONTRASENIA"),
                        rs.getString("ROL").charAt(0)
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    public List<Usuario> obtenerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT u.*, c.*, p.DESCRIPCION AS DESC_PROVINCIA, l.DESCRIPCION AS DESC_LOCALIDAD " +
                     "FROM USUARIOS u " +
                     "JOIN CLIENTES c ON u.DNI_CLIENTE = c.DNI " +
                     "JOIN PROVINCIAS p ON c.ID_PROVINCIA = p.ID_PROVINCIA " +
                     "JOIN LOCALIDADES l ON c.ID_LOCALIDAD = l.ID_LOCALIDAD";
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

                Usuario usuario = new Usuario(
                    rs.getInt("ID_USUARIO"),
                    cliente,
                    rs.getString("NOMBRE_USUARIO"),
                    rs.getString("CONTRASENIA"),
                    rs.getString("ROL").charAt(0)
                );

                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }
}
