package com.banco.app.entidad;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Movimiento {
    private int idMovimiento;
    private LocalDate fechaMovimiento;
    private String detalle;
    private BigDecimal importe;
    private Cuenta cuenta;
    private TipoMovimiento tipoMovimiento;

    public Movimiento() {
    }

    public Movimiento(int idMovimiento, LocalDate fechaMovimiento, String detalle, BigDecimal importe, Cuenta cuenta,
            TipoMovimiento tipoMovimiento) {
        this.idMovimiento = idMovimiento;
        this.fechaMovimiento = fechaMovimiento;
        this.detalle = detalle;
        this.importe = importe;
        this.cuenta = cuenta;
        this.tipoMovimiento = tipoMovimiento;
    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public LocalDate getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(LocalDate fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    @Override
    public String toString() {
        return "Movimiento [idMovimiento=" + idMovimiento + ", fechaMovimiento=" + fechaMovimiento + ", detalle="
                + detalle + ", importe=" + importe + ", cuenta=" + cuenta + ", tipoMovimiento=" + tipoMovimiento + "]";
    }

}
