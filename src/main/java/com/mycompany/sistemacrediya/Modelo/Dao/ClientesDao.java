/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemacrediya.Modelo.Dao;

import com.mycompany.sistemacrediya.Conexion.ConexionDB;
import com.mycompany.sistemacrediya.Conexion.Operaciones;
import com.mycompany.sistemacrediya.Modelo.Clases.Clientes;
import com.mycompany.sistemacrediya.Modelo.Persistencia.ClienteRepository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class ClientesDao implements ClienteRepository{

    @Override
    public boolean registrarCliente(Clientes cliente) throws SQLException {
        Operaciones.setConnection(ConexionDB.MysConnection());
        String sentencia = "INSERT INTO clientes (id, nombre, documento, correo, telefono) VALUES(?, ?, ?, ?, ?);";
        try (PreparedStatement ps = Operaciones.getConnection().prepareStatement(sentencia)){
            ps.setInt(1, cliente.getIdpersona());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getDocumento());
            ps.setString(4, cliente.getCorreo());
            ps.setString(5, cliente.getTelefono());
            int filas = Operaciones.insertar_actualizar_borrar_BD(ps);
            Operaciones.setAutoCommitBD(false);
            if(filas > 0){
                Operaciones.commitBD();
                Operaciones.cerrarConexion();
                System.out.println("Cliente registrado correctamente");
                return true;
            }else{
                Operaciones.rollbackBD();
                System.err.println("⚠️ Ha ocurrido un error al registrar");
                return false;
            }
        }finally{
            Operaciones.cerrarConexion();
            return false;
        }
    }

    @Override
    public Clientes consultarPorId(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Clientes> listarClientes() throws SQLException {
        List<Clientes> listaClientes = new ArrayList<>();
        Operaciones.setConnection(ConexionDB.MysConnection());
        String sentencia = "SELECT * FROM clietes;";
        try(PreparedStatement ps = Operaciones.getConnection().prepareStatement(sentencia);
            ResultSet rs = Operaciones.consultar_BD(ps)){
            while(rs != null && rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String documento = rs.getString("documento");
                String correo = rs.getString("correo");
                String telefono = rs.getString("telefono");
                Clientes c = new Clientes(id, nombre, documento, correo, telefono);
                listaClientes.add(c);
            }
        }finally{
            Operaciones.cerrarConexion();
            return listaClientes;
        }
    }

    @Override
    public boolean actualizar(Clientes cliente) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(int id) throws SQLException {
        Operaciones.setConnection(ConexionDB.MysConnection());
        String sentencia = "DELATE FROM clientes WHERE id= ?;";
        try(PreparedStatement ps = Operaciones.getConnection().prepareStatement(sentencia)){
            ps.setInt(1, id);
            int filas = Operaciones.insertar_actualizar_borrar_BD(ps);
            if(Operaciones.setAutoCommitBD(false)){
                if(filas > 0){
                Operaciones.commitBD();
                Operaciones.cerrarConexion();
                System.out.println("El cliente "+id+" ha sido eliminado correctamente");
                return true;
                }else{
                    Operaciones.rollbackBD();
                    Operaciones.cerrarConexion();
                    System.err.println("⚠️ Error: No se encontró ningún empleado con ese id");
                    return false;
                }
            }        
        }finally{
            Operaciones.cerrarConexion();
            return false;
        }      
    }
    
}
