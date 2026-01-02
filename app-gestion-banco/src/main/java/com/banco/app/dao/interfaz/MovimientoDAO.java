package com.banco.app.dao.interfaz;

import java.time.LocalDate;
import java.util.List;

import com.banco.app.entidad.Movimiento;

public interface MovimientoDAO {
    boolean agregarMovimiento(Movimiento movimiento);
    Movimiento obtenerPorID(int idMovimiento);
    List<Movimiento> obtenerPorCuenta(int numeroCuenta);
    List<Movimiento> obtenerPorFecha(LocalDate fecha);
    List<Movimiento> obtenerTodos();
}
