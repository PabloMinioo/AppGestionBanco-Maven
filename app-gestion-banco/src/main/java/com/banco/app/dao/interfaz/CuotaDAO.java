package com.banco.app.dao.interfaz;

import java.util.List;
import com.banco.app.entidad.Cuota;

public interface CuotaDAO {
    boolean agregarCuota(Cuota cuota);
    boolean modificarEstado(int idCuota, char nuevoEstado);
    Cuota obtenerPorID(int idCuota);
    List<Cuota> obtenerPorPrestamo(int idPrestamo);
}
