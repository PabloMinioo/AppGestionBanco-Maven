package com.banco.app.dao.interfaz;

import java.util.List;

import com.banco.app.entidad.Prestamo;

public interface PrestamoDAO {
    boolean agregarPrestamo(Prestamo prestamo);
    boolean modificarPrestamo(int idPrestamo, char nuevoEstado);
    Prestamo obtenerPorID(int idPrestamo);
    List<Prestamo> obtenerPorCliente(String dniCliente);
    List<Prestamo> obtenerTodos();
}
