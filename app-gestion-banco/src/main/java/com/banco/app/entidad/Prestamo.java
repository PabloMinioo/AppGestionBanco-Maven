import java.math.BigDecimal;
import java.time.LocalDate;

public class Prestamo {
    private int idPrestamo;
    private Cliente cliente;
    private Cuenta cuenta;
    private LocalDate fechaPrestamo;
    private BigDecimal importeTotal;
    private BigDecimal importeSolicitado;
    private int plazoMeses;
    private BigDecimal montoMensual;
    private char estado;

    public Prestamo() {
    }

    public Prestamo(int idPrestamo, Cliente cliente, Cuenta cuenta, LocalDate fechaPrestamo, BigDecimal importeTotal,
            BigDecimal importeSolicitado, int plazoMeses, BigDecimal montoMensual, char estado) {
        this.idPrestamo = idPrestamo;
        this.cliente = cliente;
        this.cuenta = cuenta;
        this.fechaPrestamo = fechaPrestamo;
        this.importeTotal = importeTotal;
        this.importeSolicitado = importeSolicitado;
        this.plazoMeses = plazoMeses;
        this.montoMensual = montoMensual;
        this.estado = estado;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public BigDecimal getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(BigDecimal importeTotal) {
        this.importeTotal = importeTotal;
    }

    public BigDecimal getImporteSolicitado() {
        return importeSolicitado;
    }

    public void setImporteSolicitado(BigDecimal importeSolicitado) {
        this.importeSolicitado = importeSolicitado;
    }

    public int getPlazo() {
        return plazoMeses;
    }

    public void setPlazo(int plazoMeses) {
        this.plazoMeses = plazoMeses;
    }

    public BigDecimal getMontoMensual() {
        return montoMensual;
    }

    public void setMontoMensual(BigDecimal montoMensual) {
        this.montoMensual = montoMensual;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Prestamo [idPrestamo=" + idPrestamo + ", cliente=" + cliente + ", cuenta=" + cuenta + ", fechaPrestamo="
                + fechaPrestamo + ", importeTotal=" + importeTotal + ", importeSolicitado=" + importeSolicitado
                + ", plazo=" + plazoMeses + ", montoMensual=" + montoMensual + ", estado=" + estado + "]";
    }

}
