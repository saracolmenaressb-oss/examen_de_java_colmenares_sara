/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.sistemacrediya.Modelo.Persistencia;

import com.mycompany.sistemacrediya.Modelo.Clases.Clientes;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public interface ClienteRepository {
    //registrar
    boolean registrarCliente(Clientes cliente)throws SQLException;
    //consultar empleado por el id
    Clientes consultarPorId(int id)throws SQLException;
    //listarlos
    List<Clientes> listarClientes()throws SQLException;
    //actualizar datos
    boolean actualizar(Clientes cliente)throws SQLException;
    //eliminar
    boolean eliminar(int id)throws SQLException;
   
}
