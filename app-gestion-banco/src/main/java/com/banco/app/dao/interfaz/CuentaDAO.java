package com.banco.app.dao.interfaz;

import java.util.List;

import com.banco.app.entidad.Cuenta;

public interface CuentaDAO {
    boolean agregarCuenta(Cuenta cuenta);
    boolean modificarSaldo(int numeroCuenta, java.math.BigDecimal nuevoSaldo);
    boolean bajaLogica(int numeroCuenta);
    Cuenta obtenerPorNumeroCuenta(int numeroCuenta);
    List<Cuenta> obtenerPorCliente(String dniCliente);
    List<Cuenta> obtenerCuentas();
}
