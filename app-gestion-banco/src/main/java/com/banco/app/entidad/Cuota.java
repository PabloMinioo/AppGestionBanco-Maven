import java.math.BigDecimal;
import java.time.LocalDate;

public class Cuota {
    private int idCuota;
    private Prestamo prestamo;
    private Cuenta cuenta;
    private int numeroCuota;
    private LocalDate fechaVencimiento;
    private BigDecimal montoCuota;
    private char estado;

    public Cuota() {
    }

    public Cuota(int idCuota, Prestamo prestamo, Cuenta cuenta, int numeroCuota, LocalDate fechaVencimiento,
            BigDecimal montoCuota, char estado) {
        this.idCuota = idCuota;
        this.prestamo = prestamo;
        this.cuenta = cuenta;
        this.numeroCuota = numeroCuota;
        this.fechaVencimiento = fechaVencimiento;
        this.montoCuota = montoCuota;
        this.estado = estado;
    }

    public int getIdCuota() {
        return idCuota;
    }

    public void setIdCuota(int idCuota) {
        this.idCuota = idCuota;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public int getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(int numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public BigDecimal getMontoCuota() {
        return montoCuota;
    }

    public void setMontoCuota(BigDecimal montoCuota) {
        this.montoCuota = montoCuota;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cuota [idCuota=" + idCuota + ", prestamo=" + prestamo + ", cuenta=" + cuenta + ", numeroCuota="
                + numeroCuota + ", fechaVencimiento=" + fechaVencimiento + ", montoCuota=" + montoCuota + ", estado="
                + estado + "]";
    }

}
