/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemacrediya.Controlador;

import com.mycompany.sistemacrediya.Service.PrestamoService;
import com.mycompany.sistemacrediya.Modelo.Clases.Prestamos;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class PrestamoController {
    private final PrestamoService prestamosService;

    public PrestamoController() {
        this.prestamosService = new PrestamoService();
    }

    public PrestamoController(PrestamoService prestamosService) {
        this.prestamosService = prestamosService;
    }

    public boolean registrarPrestamo(Prestamos prestamo)
            throws SQLException {

        return prestamosService.registrarPrestamo(prestamo);
    }

    public Prestamos consultarPrestamo(int id)
            throws SQLException {

        return prestamosService.consultarPrestamo(id);
    }

    public List<Prestamos> listarPrestamos()
            throws SQLException {

        return prestamosService.listarPrestamos();
    }
}
