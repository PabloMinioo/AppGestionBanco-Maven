package com.banco.app.entidad;

public class Provincia {
    private String idProvincia;
    private String descripcion;

    public Provincia() {
    }

    public Provincia(String idProvincia, String descripcion) {
        this.idProvincia = idProvincia;
        this.descripcion = descripcion;
    }

    public String getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(String idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Provincia [idProvincia=" + idProvincia + ", descripcion=" + descripcion + "]";
    }
}
