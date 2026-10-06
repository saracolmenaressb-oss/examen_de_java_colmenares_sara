/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemacrediya.Controlador;
import com.mycompany.sistemacrediya.Modelo.Clases.Empleado;
import com.mycompany.sistemacrediya.Service.EmpleadosService;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;
/**
 *
 * @author USUARIO
 */
public class EmpleadosController {

    private final EmpleadosService service;

    public EmpleadosController(EmpleadosService service) {
        this.service = service;
    }

    public boolean registrarEmpleado(Empleado empleado)
            throws SQLException {
        
        return service.registrarEmpleado(empleado);
    }

    public Empleado consultarPorId(int id)
            throws SQLException {

        return service.consultarPorId(id);
    }

    public List<Empleado> listarEmpleados()
            throws SQLException {

        return service.listarEmpleados();
    }

    public boolean actualizar(Empleado empleado)
            throws SQLException {

        return service.actualizar(empleado);
    }

    public boolean eliminar(int id)
            throws SQLException {

        return service.eliminar(id);
    }

}
