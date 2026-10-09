/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemacrediya.Controlador;

/**
 *
 * @author USUARIO
 */
public class ReporteController {
    private final ReporteService reporteService;

    public ReporteController() {
        this.reporteService = new ReporteService();
    }

    public ResumenPrestamos generarReporte(
            EstadoPrestamo estado) throws SQLException {

        return reporteService.generarReporte(estado);
    }
}
