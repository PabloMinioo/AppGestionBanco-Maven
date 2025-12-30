package com.banco.app.dao.interfaz;

import java.util.List;

import com.banco.app.entidad.Provincia;

public interface ProvinciaDAO {
    List<Provincia> obtenerTodasProvincias();
    Provincia obtenerProvinciaPorID(String id);
}
