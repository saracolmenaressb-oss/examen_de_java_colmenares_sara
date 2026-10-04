/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.sistemacrediya.Modelo.Persistencia;

import com.mycompany.sistemacrediya.Modelo.Clases.Prestamos;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public interface PrestamoRepository {
    boolean registrar(Prestamos prestamo) throws SQLException;
    Prestamos consultarPorId(int id) throws SQLException;
    List<Prestamos> listarTodos() throws SQLException;
    
}
