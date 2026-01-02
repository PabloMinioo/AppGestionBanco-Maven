package com.banco.app.dao.interfaz;

import java.util.List;
import com.banco.app.entidad.Cliente;

public interface ClienteDAO {
    boolean agregarCliente(Cliente cliente);
    boolean modificarCliente(Cliente cliente);
    boolean eliminarCliente(String dni);
    Cliente obtenerClientePorDNI(String dni);
    List<Cliente> listarClientes();
}
