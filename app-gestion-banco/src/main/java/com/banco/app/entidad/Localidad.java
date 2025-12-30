package com.banco.app.entidad;

public class Localidad {

    private String idLocalidad;
    private String descripcion;
    private Provincia provincia;

    public Localidad() {
    }

    public Localidad(String idLocalidad, String descripcion, Provincia provincia) {
        this.idLocalidad = idLocalidad;
        this.descripcion = descripcion;
        this.provincia = provincia;
    }

    public String getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(String idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return "Localidad [idLocalidad=" + idLocalidad + ", descripcion=" + descripcion + ", provincia=" + provincia
                + "]";
    }

}
