/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemacrediya.Service;

import com.mycompany.sistemacrediya.Modelo.Dao.PagosDao;
import com.mycompany.sistemacrediya.Modelo.Dao.PrestamosDao;
import com.mycompany.sistemacrediya.Modelo.Clases.Prestamos;
import com.mycompany.sistemacrediya.Modelo.Clases.Pago;
import com.mycompany.sistemacrediya.Modelo.Clases.EstadoPrestamo;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class PagoService {
     private final PagosDao pagosDao;
    private final PrestamosDao prestamosDao;

    public PagoService() {
        this.pagosDao = new PagosDao();
        this.prestamosDao = new PrestamosDao();
    }

    public PagoService(PagosDao pagosDao, PrestamosDao prestamosDao) {
        this.pagosDao = pagosDao;
        this.prestamosDao = prestamosDao;
    }

    // Registrar un pago
    public boolean registrarPago(Pago pago) throws SQLException {

        validarPago(pago);

        int idPrestamo = pago.getPrestamo().getIdprestamo();

        // Consultar el préstamo real en la base de datos
        Prestamos prestamo = prestamosDao.consultarPorId(idPrestamo);

        if (prestamo == null) {
            throw new IllegalArgumentException(
                    "El préstamo indicado no existe."
            );
        }

        if (prestamo.getEstado() == EstadoPrestamo.PAGADO) {
            throw new IllegalArgumentException(
                    "Este préstamo ya está pagado."
            );
        }

        double saldoActual = prestamo.getSaldoPendiente();

        if (saldoActual <= 0) {
            throw new IllegalArgumentException(
                    "El préstamo no tiene saldo pendiente."
            );
        }

        if (pago.getMonto() > saldoActual) {
            throw new IllegalArgumentException(
                    "El pago supera el saldo pendiente del préstamo."
            );
        }

        // Calcular el saldo que quedará después del pago
        double nuevoSaldo = Math.round(
                (saldoActual - pago.getMonto()) * 100.0
        ) / 100.0;

        pago.setPrestamo(prestamo);
        pago.setSaldoRestante(nuevoSaldo);

        // Registrar primero el movimiento de pago
        boolean registrado = pagosDao.registrarPago(pago);

        if (!registrado) {
            return false;
        }

        // Actualizar el saldo pendiente del préstamo
        boolean saldoActualizado =
                pagosDao.actualizarSaldo(idPrestamo, nuevoSaldo);

        if (!saldoActualizado) {
            throw new SQLException(
                    "El pago se registró, pero no se pudo actualizar "
                    + "el saldo del préstamo. Se requiere revisar "
                    + "la operación en la base de datos."
            );
        }

        return true;
    }

    // Consultar el historial de pagos de un préstamo
    public List<Pago> listarPagosPorPrestamo(int idPrestamo)
            throws SQLException {

        if (idPrestamo <= 0) {
            throw new IllegalArgumentException(
                    "El ID del préstamo debe ser mayor que cero."
            );
        }

        Prestamos prestamo = prestamosDao.consultarPorId(idPrestamo);

        if (prestamo == null) {
            throw new IllegalArgumentException(
                    "No existe un préstamo con ese ID."
            );
        }

        return pagosDao.listarPorPrestamo(idPrestamo);
    }

    // Consultar un préstamo para mostrar sus datos antes del pago
    public Prestamos consultarPrestamo(int idPrestamo)
            throws SQLException {

        if (idPrestamo <= 0) {
            throw new IllegalArgumentException(
                    "El ID debe ser mayor que cero."
            );
        }

        return prestamosDao.consultarPorId(idPrestamo);
    }

    // Validaciones básicas del pago
    private void validarPago(Pago pago) {

        if (pago == null) {
            throw new IllegalArgumentException(
                    "El pago no puede ser nulo."
            );
        }

        if (pago.getMonto() <= 0
                || !Double.isFinite(pago.getMonto())) {
            throw new IllegalArgumentException(
                    "El monto debe ser mayor que cero."
            );
        }

        if (pago.getFechapago() == null) {
            throw new IllegalArgumentException(
                    "La fecha del pago es obligatoria."
            );
        }

        if (pago.getFechapago().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "La fecha del pago no puede ser futura."
            );
        }

        if (pago.getMetodoPago() == null) {
            throw new IllegalArgumentException(
                    "Debes seleccionar un método de pago."
            );
        }

        if (pago.getPrestamo() == null
                || pago.getPrestamo().getIdprestamo() <= 0) {
            throw new IllegalArgumentException(
                    "Debes indicar un préstamo válido."
            );
        }
    }
}
