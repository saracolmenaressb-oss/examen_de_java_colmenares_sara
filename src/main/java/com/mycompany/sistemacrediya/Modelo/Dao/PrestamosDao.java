/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemacrediya.Modelo.Dao;
import com.mycompany.sistemacrediya.Conexion.ConexionDB;
import com.mycompany.sistemacrediya.Conexion.Operaciones;
import com.mycompany.sistemacrediya.Modelo.Clases.Clientes;
import com.mycompany.sistemacrediya.Modelo.Clases.Empleado;
import com.mycompany.sistemacrediya.Modelo.Clases.EstadoPrestamo;
import com.mycompany.sistemacrediya.Modelo.Clases.Prestamos;
import com.mycompany.sistemacrediya.Modelo.Persistencia.PrestamoRepository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class PrestamosDao implements PrestamoRepository {
    //para registrar el prestamo
    @Override
    public boolean registrar(Prestamos prestamo) throws SQLException {
        Operaciones.setConnection(ConexionDB.MysConnection());
        String sql = "INSERT INTO prestamos (cliente_id, empleado_id, monto, interes, cuotas, fecha_inicio, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement ps = Operaciones.getConnection().prepareStatement(sql);
        ps.setInt(1, prestamo.getClientes().getIdpersona());
        ps.setInt(2, prestamo.getEmpleados().getIdpersona());
        ps.setDouble(3, prestamo.getMonto());
        ps.setDouble(4, prestamo.getTasaInteres());
        ps.setInt(5, prestamo.getNumeroCuotas());
        ps.setDate(6, java.sql.Date.valueOf(prestamo.getFechaIinicio()));
        ps.setString(7, prestamo.getEstado().name()); 
        
        int filas = ps.executeUpdate();
        Operaciones.cerrarConexion();
        return filas > 0;
    }
    //para onsultar el prestamo a traves del id
    @Override
    public Prestamos consultarPorId(int id) throws SQLException {
        Prestamos prestamo = null;
    Operaciones.setConnection(ConexionDB.MysConnection());
    String sentencia = "SELECT * FROM prestamos WHERE id = ?;";
    
    try (PreparedStatement ps = Operaciones.getConnection().prepareStatement(sentencia)){
        ps.setInt(1, id);
        try (ResultSet rs = Operaciones.consultar_BD(ps)){
            if (rs != null && rs.next()){
                int idp = rs.getInt("id");
                double monto = rs.getDouble("monto");
                double interes = rs.getDouble("interes");
                int numeroCuotas = rs.getInt("cuotas");
                LocalDate fechaInicio = rs.getDate("fecha_inicio").toLocalDate();
                String estadoStr = rs.getString("estado");
                EstadoPrestamo estado = EstadoPrestamo.valueOf(estadoStr);
                
                // 1. Instanciamos los objetos temporales para las relaciones
                Clientes clienteTemp = new Clientes(rs.getInt("cliente_id"), "", "", "", ""); 
                Empleado empleadoTemp = new Empleado(rs.getInt("empleado_id"), "", "", "", "", 0.0);
                
                // 2. Usamos el constructor vacío y asignamos con Setters (Súper limpio y seguro)
                prestamo = new Prestamos();
                prestamo.setIdprestamo(idp);
                prestamo.setMonto(monto);
                prestamo.setTasaInteres(interes);
                prestamo.setNumeroCuotas(numeroCuotas);
                prestamo.setFechaIinicio(fechaInicio);
                prestamo.setEstado(estado);
                prestamo.setClientes(clienteTemp);
                prestamo.setEmpleados(empleadoTemp);
            }
        }
        } finally {
            Operaciones.cerrarConexion();
        }
        return prestamo;
    }
    //para listar los prestamos
    @Override
    public List<Prestamos> listarTodos() throws SQLException {
       List<Prestamos> listaPrestamos = new ArrayList<>();
        Operaciones.setConnection(ConexionDB.MysConnection());
        String sentencia = "SELECT * FROM prestamos;";
        
        try (PreparedStatement ps = Operaciones.getConnection().prepareStatement(sentencia);
             ResultSet rs = Operaciones.consultar_BD(ps)) {
            while (rs != null && rs.next()) {
                int idprestamo = rs.getInt("id"); 
                double monto = rs.getDouble("monto");
                double tasaInteres = rs.getDouble("interes");
                int cuotas = rs.getInt("cuotas");
                LocalDate fechaInicio = rs.getDate("fecha_inicio").toLocalDate();
                String estadoStr = rs.getString("estado");
                EstadoPrestamo estado = EstadoPrestamo.valueOf(estadoStr);
                
                Clientes clienteTemp = new Clientes(rs.getInt("cliente_id"), "", "", "", "");
                Empleado empleadoTemp = new Empleado(rs.getInt("empleado_id"), "", "", "", "", 0.0);
                
                Prestamos p = new Prestamos(idprestamo, monto, tasaInteres, fechaInicio, null, 0.0, 0.0, 0.0, estado, clienteTemp, empleadoTemp);
                listaPrestamos.add(p);
            }
        } finally {
            Operaciones.cerrarConexion();
        }
        return listaPrestamos;
    }    
}
