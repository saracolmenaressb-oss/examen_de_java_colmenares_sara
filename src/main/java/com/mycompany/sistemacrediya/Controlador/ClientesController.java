/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemacrediya.Controlador;

import com.mycompany.sistemacrediya.Service.ClientesService;
import com.mycompany.sistemacrediya.Modelo.Clases.Clientes;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class ClientesController {
    
    private final ClientesService service;

    public ClientesController() {
        this.service = new ClientesService();
    }

    public ClientesController(ClientesService service) {
        this.service = service;
    }

    public boolean registrarCliente(Clientes cliente)
            throws SQLException {

        return service.registrarCliente(cliente);
    }

    public Clientes consultarPorId(int id)
            throws SQLException {

        return service.consultarPorId(id);
    }

    public List<Clientes> listarClientes()
            throws SQLException {

        return service.listarClientes();
    }

    public List<Clientes> buscarClientesPorNombre(String nombre)
            throws SQLException {

        return service.buscarClientesPorNombre(nombre);
    }

    public boolean actualizarCliente(Clientes cliente)
            throws SQLException {

        return service.actualizarCliente(cliente);
    }

    public boolean eliminarCliente(int id)
            throws SQLException {

        return service.eliminarCliente(id);
    }
}
