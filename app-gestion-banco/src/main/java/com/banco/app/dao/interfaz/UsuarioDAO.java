package com.banco.app.dao.interfaz;

import java.util.List;

import com.banco.app.entidad.Usuario;

public interface UsuarioDAO {
    boolean agregarUsuario(Usuario usuario);
    boolean modificarPassword(int idUsuario, String nuevaPassword);
    Usuario obtenerPorNombreUsuario(String nombreUsuario);
    Usuario obtenerPorID(int idUsuario);
    List<Usuario> obtenerUsuarios();
}
