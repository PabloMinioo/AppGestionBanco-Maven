package com.banco.app.dao.interfaz;

import java.util.List;

import com.banco.app.entidad.TipoMovimiento;

public interface TipoMovimientoDAO {
    List<TipoMovimiento> obtenerTodosTiposMovimientos();
    TipoMovimiento obtenerTipoMovimientoPorID(int id);
}
