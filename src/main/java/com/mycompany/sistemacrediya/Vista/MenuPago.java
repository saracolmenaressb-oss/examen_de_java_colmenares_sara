/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemacrediya.Vista;

import com.mycompany.sistemacrediya.Controlador.PagoController;
import java.util.Scanner;
import com.mycompany.sistemacrediya.Modelo.Clases.MetodoPago;
import com.mycompany.sistemacrediya.Modelo.Clases.Prestamos;
import com.mycompany.sistemacrediya.Modelo.Clases.Pago;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
/**
 *
 * @author USUARIO
 */
public class MenuPago {
     private final Scanner scanner;
    private final PagoController controller;

    public MenuPago() {
        this.scanner = new Scanner(System.in);
        this.controller = new PagoController();
    }

    public void mostrarMenu() {

        int opcion = -1;

        do {
            System.out.println("\n===== GESTIÓN DE PAGOS =====");
            System.out.println("1. Registrar pago");
            System.out.println("2. Consultar historial de pagos");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        registrarPago();
                        break;

                    case 2:
                        consultarHistorial();
                        break;

                    case 0:
                        System.out.println(
                                "Volviendo al menú principal..."
                        );
                        break;

                    default:
                        System.out.println(
                                "Opción inválida."
                        );
                }

            } catch (NumberFormatException e) {
                System.out.println(
                        "Ingrese un número válido."
                );
                opcion = -1;

            } catch (IllegalArgumentException e) {
                System.out.println(
                        "No se pudo realizar la operación: "
                                + e.getMessage()
                );
                opcion = -1;

            } catch (SQLException e) {
                System.out.println(
                        "Error en la base de datos: "
                                + e.getMessage()
                );
                opcion = -1;
            }

        } while (opcion != 0);
    }

    // Registrar un pago
    private void registrarPago() throws SQLException {

        System.out.println("\n--- REGISTRAR PAGO ---");

        System.out.print("ID del préstamo: ");
        int idPrestamo = Integer.parseInt(scanner.nextLine());

        Prestamos prestamo =
                controller.consultarPrestamo(idPrestamo);

        if (prestamo == null) {
            System.out.println(
                    "No existe un préstamo con ese ID."
            );
            return;
        }

        System.out.printf(
                "Monto original del préstamo: %.2f%n",
                prestamo.getMonto()
        );

        System.out.printf(
                "Saldo pendiente: %.2f%n",
                prestamo.getSaldoPendiente()
        );

        System.out.println(
                "Estado: " + prestamo.getEstado()
        );

        System.out.print("Monto que va a pagar: ");
        double monto = Double.parseDouble(scanner.nextLine());

        System.out.print("Fecha del pago (AAAA-MM-DD): ");
        LocalDate fechaPago = LocalDate.parse(scanner.nextLine());

        System.out.println("\nMétodos de pago:");
        System.out.println("1. Efectivo");
        System.out.println("2. Transferencia");
        System.out.println("3. Tarjeta");
        System.out.print("Seleccione el método: ");

        int opcionMetodo =
                Integer.parseInt(scanner.nextLine());

        MetodoPago metodoPago;

        switch (opcionMetodo) {
            case 1:
                metodoPago = MetodoPago.EFECTIVO;
                break;

            case 2:
                metodoPago = MetodoPago.TRANSFERENCIA;
                break;

            case 3:
                metodoPago = MetodoPago.TARJETA;
                break;

            default:
                throw new IllegalArgumentException(
                        "El método de pago seleccionado no existe."
                );
        }

        // El ID del pago es 0 porque MySQL lo genera automáticamente.
        Pago pago = new Pago(
                0,
                monto,
                fechaPago,
                0.0,
                metodoPago,
                prestamo
        );

        boolean registrado = controller.registrarPago(pago);

        if (registrado) {
            System.out.println(
                    "\nPago registrado correctamente."
            );

            System.out.printf(
                    "Monto pagado: %.2f%n",
                    pago.getMonto()
            );

            System.out.println(
                    "Fecha: " + pago.getFechapago()
            );

            System.out.println(
                    "Método de pago: " + pago.getMetodoPago()
            );

            System.out.printf(
                    "Saldo restante: %.2f%n",
                    pago.getSaldoRestante()
            );
        } else {
            System.out.println(
                    "No fue posible registrar el pago."
            );
        }
    }

    // Consultar historial de pagos
    private void consultarHistorial() throws SQLException {

        System.out.println("\n--- HISTORIAL DE PAGOS ---");

        System.out.print("ID del préstamo: ");
        int idPrestamo = Integer.parseInt(scanner.nextLine());

        List<Pago> pagos =
                controller.listarPagosPorPrestamo(idPrestamo);

        if (pagos == null || pagos.isEmpty()) {
            System.out.println(
                    "Este préstamo no tiene pagos registrados."
            );
            return;
        }

        System.out.println(
                "\nPagos asociados al préstamo " + idPrestamo
        );

        for (Pago pago : pagos) {
            System.out.println("----------------------------");
            System.out.println("ID del pago: " + pago.getIdpago());
            System.out.println("Fecha: " + pago.getFechapago());

            System.out.printf(
                    "Monto pagado: %.2f%n",
                    pago.getMonto()
            );
        }

        System.out.println("----------------------------");
    }
}
