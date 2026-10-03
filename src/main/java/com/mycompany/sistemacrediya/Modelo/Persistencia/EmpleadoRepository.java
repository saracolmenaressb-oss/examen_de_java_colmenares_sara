/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.sistemacrediya.Modelo.Persistencia;

import com.mycompany.sistemacrediya.Modelo.Clases.Empleado;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public interface EmpleadoRepository {
    //para reigstrar
    boolean registrarEmpleado(Empleado empleado) throws SQLException;
    //para consultar el id
    Empleado consultarPorId(int id) throws SQLException;
    //para listarlos
    List<Empleado> listarEmpleados() throws SQLException;
    //para actualizarlos
    boolean actualizar(Empleado empleado) throws SQLException;
    //para eliminarlos
    boolean eliminar(int id) throws SQLException;
}
