package com.mycompany.sistemacrediya.Controlador;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.mycompany.sistemacrediya.Modelo.Clases.Pago;
import com.mycompany.sistemacrediya.Modelo.Clases.Prestamos;
import com.mycompany.sistemacrediya.Service.PagoService;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author USUARIO
 */
public class PagoController {
    private final PagoService pagosService;

    public PagoController() {
        this.pagosService = new PagoService();
    }

    public PagoController(PagoService pagosService) {
        this.pagosService = pagosService;
    }

    public boolean registrarPago(Pago pago) throws SQLException {
        return pagosService.registrarPago(pago);
    }

    public List<Pago> listarPagosPorPrestamo(int idPrestamo)
            throws SQLException {
        return pagosService.listarPagosPorPrestamo(idPrestamo);
    }

    public Prestamos consultarPrestamo(int idPrestamo)
            throws SQLException {
        return pagosService.consultarPrestamo(idPrestamo);
    }
}
