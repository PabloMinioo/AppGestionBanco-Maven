import java.math.BigDecimal;
import java.time.LocalDate;

public class Cuenta {
    private int numeroCuenta;
    private Cliente cliente;
    private LocalDate fechaCreacion;
    private TipoCuenta tipoCuenta;
    private String cbu;
    private BigDecimal saldo;
    private Boolean estado;

    public Cuenta() {
    }

    public Cuenta(int numeroCuenta, Cliente cliente, LocalDate fechaCreacion, TipoCuenta tipoCuenta, String cbu,
            BigDecimal saldo, Boolean estado) {
        this.numeroCuenta = numeroCuenta;
        this.cliente = cliente;
        this.fechaCreacion = fechaCreacion;
        this.tipoCuenta = tipoCuenta;
        this.cbu = cbu;
        this.saldo = saldo;
        this.estado = estado;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cuenta [numeroCuenta=" + numeroCuenta + ", cliente=" + cliente + ", fechaCreacion=" + fechaCreacion
                + ", tipoCuenta=" + tipoCuenta + ", cbu=" + cbu + ", saldo=" + saldo + ", estado=" + estado + "]";
    }

}
