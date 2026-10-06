/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemacrediya.Service;
import com.mycompany.sistemacrediya.Modelo.Clases.Prestamos;
import com.mycompany.sistemacrediya.Modelo.Clases.EstadoPrestamo;
import com.mycompany.sistemacrediya.Modelo.Dao.PrestamosDao;
import java.util.List;
import java.sql.SQLException;
import java.time.LocalDate;
/**
 *
 * @author USUARIO
 */
public class PrestamoService {
    private final PrestamosDao prestamosDao;

    public PrestamoService() {
        this.prestamosDao = new PrestamosDao();
    }

    public PrestamoService(PrestamosDao prestamosDao) {
        this.prestamosDao = prestamosDao;
    }

    // Registrar un préstamo
    public boolean registrarPrestamo(Prestamos prestamo)
            throws SQLException {

        validarPrestamo(prestamo);
        calcularValoresPrestamo(prestamo);

        return prestamosDao.registrar(prestamo);
    }

    // Consultar un préstamo por su identificador
    public Prestamos consultarPrestamo(int id)
            throws SQLException {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "El ID del préstamo debe ser mayor que cero."
            );
        }

        return prestamosDao.consultarPorId(id);
    }

    // Listar todos los préstamos
    public List<Prestamos> listarPrestamos()
            throws SQLException {

        return prestamosDao.listarTodos();
    }

    // Validaciones del préstamo
    private void validarPrestamo(Prestamos prestamo) {

        if (prestamo == null) {
            throw new IllegalArgumentException(
                    "El préstamo no puede ser nulo."
            );
        }

        if (prestamo.getMonto() <= 0
                || !Double.isFinite(prestamo.getMonto())) {
            throw new IllegalArgumentException(
                    "El monto debe ser mayor que cero."
            );
        }

        if (prestamo.getTasaInteres() < 0
                || !Double.isFinite(prestamo.getTasaInteres())) {
            throw new IllegalArgumentException(
                    "La tasa de interés no puede ser negativa."
            );
        }

        if (prestamo.getNumeroCuotas() <= 0) {
            throw new IllegalArgumentException(
                    "El número de cuotas debe ser mayor que cero."
            );
        }

        if (prestamo.getFechaIinicio() == null) {
            throw new IllegalArgumentException(
                    "La fecha de inicio es obligatoria."
            );
        }

        if (prestamo.getFechaIinicio().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "La fecha de inicio no puede estar en el pasado."
            );
        }

        if (prestamo.getClientes() == null
                || prestamo.getClientes().getIdpersona()<= 0) {
            throw new IllegalArgumentException(
                    "Debes asociar un cliente válido."
            );
        }

        if (prestamo.getEmpleados() == null
                || prestamo.getEmpleados().getIdpersona() <= 0) {
            throw new IllegalArgumentException(
                    "Debes asociar un empleado válido."
            );
        }
    }

    // Cálculo inicial con interés simple
    private void calcularValoresPrestamo(Prestamos prestamo) {

        double monto = prestamo.getMonto();
        double tasa = prestamo.getTasaInteres();
        int cuotas = prestamo.getNumeroCuotas();

        double interes = monto * (tasa / 100.0);
        double montoTotal = monto + interes;
        double valorCuota = montoTotal / cuotas;

        prestamo.setMontoTotal(montoTotal);
        prestamo.setValorCuota(valorCuota);
        prestamo.setCuotaMensual(valorCuota);
        prestamo.setSaldoPendiente(montoTotal);

        prestamo.setFechaVencimiento(
                prestamo.getFechaIinicio().plusMonths(cuotas)
        );

        prestamo.setEstado(EstadoPrestamo.PENDIENTE);
    }
}
