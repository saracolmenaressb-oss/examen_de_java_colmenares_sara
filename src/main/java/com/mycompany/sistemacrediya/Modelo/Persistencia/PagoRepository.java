/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.sistemacrediya.Modelo.Persistencia;

import com.mycompany.sistemacrediya.Modelo.Clases.Pago;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public interface PagoRepository {
    boolean registrarPago(Pago pago) throws SQLException;
    List<Pago> listarPorPrestamo(int idPrestamo) throws SQLException;
    boolean actualizarSaldo(int idPrestamo, double nuevoSaldo) throws SQLException;
    
}
