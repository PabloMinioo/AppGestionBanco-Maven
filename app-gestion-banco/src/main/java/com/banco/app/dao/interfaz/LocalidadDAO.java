package com.banco.app.dao.interfaz;

import com.banco.app.entidad.Localidad;

import java.util.List;

public interface LocalidadDAO {
    List<Localidad> obtenerTodasLocalidades();
    Localidad obtenerLocalidadPorID(String id);
    List<Localidad> obtenerLocalidadPorProvincia(String idProvincia);
}
