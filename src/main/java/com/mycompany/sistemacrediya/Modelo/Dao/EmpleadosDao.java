/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemacrediya.Modelo.Dao;

import com.mycompany.sistemacrediya.Conexion.ConexionDB;
import com.mycompany.sistemacrediya.Conexion.Operaciones;
import com.mycompany.sistemacrediya.Modelo.Clases.Empleado;
import com.mycompany.sistemacrediya.Modelo.Persistencia.EmpleadoRepository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class EmpleadosDao implements EmpleadoRepository {

    @Override
    public boolean registrarEmpleado(Empleado empleado) throws SQLException {
        Operaciones.setConnection(ConexionDB.MysConnection());
        String sentencia = "INSERT INTO empleados (id, nombre, documento, correo, rol, salario) VALUES (?, ?, ?, ?, ?, ?);";
        
        try (PreparedStatement ps = Operaciones.getConnection().prepareStatement(sentencia)) {
            ps.setInt(1, empleado.getIdpersona());
            ps.setString(2, empleado.getNombre());
            ps.setString(3, empleado.getDocumento());
            ps.setString(4, empleado.getCorreo());
            ps.setString(5, empleado.getRol());
            ps.setDouble(6, empleado.getSalario());
            
            int filas = Operaciones.insertar_actualizar_borrar_BD(ps);
            
            Operaciones.setAutoCommitBD(false);
            if (filas > 0) {
                Operaciones.commitBD();
                System.out.println("Se ha registrado correctamente");
                return true;
            } else {
                Operaciones.rollbackBD();
                System.err.println("⚠️ Ha ocurrido un error al registrar");
                return false;
            }
        } finally {
            Operaciones.cerrarConexion();
        }
    }

    @Override
    public Empleado consultarPorId(int id) throws SQLException {
        Operaciones.setConnection(ConexionDB.MysConnection());
        String sentencia = "SELECT * FROM empleados WHERE id = ?;";
        Empleado empleado = null;
        
        try (PreparedStatement ps = Operaciones.getConnection().prepareStatement(sentencia)) {
            ps.setInt(1, id);
            try (ResultSet rs = Operaciones.consultar_BD(ps)) {
                if (rs != null && rs.next()) {
                    int idemp = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    String documento = rs.getString("documento");
                    String correo = rs.getString("correo");
                    String rol = rs.getString("rol");
                    double salario = rs.getDouble("salario");
               
                    empleado = new Empleado(idemp, nombre, documento, correo, rol, salario);
                }
            }
        } finally {
            Operaciones.cerrarConexion();
        }
        return empleado;
    }

    @Override
    public List<Empleado> listarEmpleados() throws SQLException {
        List<Empleado> listaEmpleados = new ArrayList<>();
        Operaciones.setConnection(ConexionDB.MysConnection());
        String sentencia = "SELECT * FROM empleados;";
        
        try (PreparedStatement ps = Operaciones.getConnection().prepareStatement(sentencia);
             ResultSet rs = Operaciones.consultar_BD(ps)) {
            while (rs != null && rs.next()) {
                int idemp = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String documento = rs.getString("documento");
                String correo = rs.getString("correo");
                String rol = rs.getString("rol");
                double salario = rs.getDouble("salario");
                
                Empleado p = new Empleado(idemp, nombre, documento, correo, rol, salario);
                listaEmpleados.add(p);
            }
        } finally {
            Operaciones.cerrarConexion();
        }
        return listaEmpleados;
    }

    @Override
    public boolean actualizar(Empleado empleado) throws SQLException {
        Operaciones.setConnection(ConexionDB.MysConnection());
        String sentencia = "UPDATE empleados SET nombre = ?, documento = ?, correo = ?, rol = ?, salario = ? WHERE id = ?;";
        
        try (PreparedStatement ps = Operaciones.getConnection().prepareStatement(sentencia)) {
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getDocumento());
            ps.setString(3, empleado.getCorreo());
            ps.setString(4, empleado.getRol());
            ps.setDouble(5, empleado.getSalario());
            ps.setInt(6, empleado.getIdpersona());
            
            int filasAfectadas = ps.executeUpdate();
            
            if (filasAfectadas > 0) {
                Operaciones.commitBD();
                System.out.println("Se ha actualizado correctamente");
                return true;
            } else {
                Operaciones.rollbackBD();
                System.err.println("⚠️ Error: No se pudo actualizar, ID no encontrado");
                return false;
            }
        } finally {
            Operaciones.cerrarConexion();
        }
    }

    @Override
    public boolean eliminar(int id) throws SQLException {
        Operaciones.setConnection(ConexionDB.MysConnection());
        String sentencia = "DELETE FROM empleados WHERE id = ?;";
        
        try (PreparedStatement ps = Operaciones.getConnection().prepareStatement(sentencia)) {
            ps.setInt(1, id);
            Operaciones.setAutoCommitBD(false);
            
            int filasAfectadas = Operaciones.insertar_actualizar_borrar_BD(ps);
            if (filasAfectadas > 0) {
                Operaciones.commitBD();
                System.out.println("El empleado con el id " + id + " fue eliminado");
                return true;
            } else {
                Operaciones.rollbackBD();
                System.err.println("⚠️ Error: No se encontró ningún empleado con ese id");
                return false;
            }
        } finally {
            Operaciones.cerrarConexion();
        }
    }
    
}
