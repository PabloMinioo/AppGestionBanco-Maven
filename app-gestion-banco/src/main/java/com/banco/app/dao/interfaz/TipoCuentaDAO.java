package com.banco.app.dao.interfaz;

import com.banco.app.entidad.TipoCuenta;

import java.util.List;

public interface TipoCuentaDAO {
    List<TipoCuenta> obtenerTodosTiposCuentas();
    TipoCuenta obtenerTipoCuentaPorID(int id);
}
