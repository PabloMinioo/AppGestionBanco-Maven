public class Usuario {

    private int idUsuario;
    private Cliente cliente;
    private String nombreUsuario;
    private String contrasenia;
    private char rol;

    public Usuario() {
    }

    public Usuario(int idUsuario, Cliente cliente, String nombreUsuario, String contrasenia, char rol) {
        this.idUsuario = idUsuario;
        this.cliente = cliente;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public char getRol() {
        return rol;
    }

    public void setRol(char rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario [idUsuario=" + idUsuario + ", cliente=" + cliente + ", nombreUsuario=" + nombreUsuario
                + ", contrasenia=" + contrasenia + ", rol=" + rol + "]";
    }

}
