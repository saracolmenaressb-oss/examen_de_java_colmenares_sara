/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemacrediya.Vista;


import com.mycompany.sistemacrediya.Controlador.PrestamoController;
import com.mycompany.sistemacrediya.Modelo.Clases.Prestamos;
import com.mycompany.sistemacrediya.Modelo.Clases.Clientes;
import com.mycompany.sistemacrediya.Modelo.Clases.Empleado;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author USUARIO
 */
public class MenuPrestamo {
    private final Scanner scanner;
    private final PrestamoController controller;

    public MenuPrestamo() {
        this.scanner = new Scanner(System.in);
        this.controller = new PrestamoController();
    }

    public void mostrarMenu() {
        int opcion;

        do {
            System.out.println("\n===== GESTIÓN DE PRÉSTAMOS =====");
            System.out.println("1. Registrar préstamo");
            System.out.println("2. Consultar préstamo por ID");
            System.out.println("3. Listar todos los préstamos");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        registrarPrestamo();
                        break;

                    case 2:
                        consultarPrestamo();
                        break;

                    case 3:
                        listarPrestamos();
                        break;

                    case 0:
                        System.out.println("Volviendo al menú principal...");
                        break;

                    default:
                        System.out.println("Opción inválida. Intente nuevamente.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
                opcion = -1;

            } catch (SQLException e) {
                System.out.println("Error al consultar la base de datos: " + e.getMessage());
                opcion = -1;

            } catch (IllegalArgumentException e) {
                System.out.println("No se pudo procesar la operación: " + e.getMessage());
                opcion = -1;
            }

        } while (opcion != 0);
    }

    // Registrar préstamo
    private void registrarPrestamo() throws SQLException {
        System.out.println("\n--- REGISTRAR PRÉSTAMO ---");

        Prestamos prestamo = new Prestamos();

        System.out.print("ID del cliente: ");
        int idCliente = Integer.parseInt(scanner.nextLine());

        System.out.print("ID del empleado: ");
        int idEmpleado = Integer.parseInt(scanner.nextLine()); // Ya se lee aquí correctamente

        System.out.print("Monto del préstamo: ");
        double monto = Double.parseDouble(scanner.nextLine());

        System.out.print("Tasa de interés (%): ");
        double tasaInteres = Double.parseDouble(scanner.nextLine());

        System.out.print("Número de cuotas: ");
        int numeroCuotas = Integer.parseInt(scanner.nextLine());

        System.out.print("Fecha de inicio (AAAA-MM-DD): ");
        LocalDate fechaInicio = LocalDate.parse(scanner.nextLine());

        // Crear referencias para relacionar el préstamo
        // con el cliente y el empleado existentes.
        Clientes cliente = new Clientes();
        cliente.setIdpersona(idCliente);

        // AQUÍ ESTABA EL ERROR: Se eliminó la línea duplicada 'int idEmpleado = ...'
        Empleado empleado = new Empleado();
        empleado.setIdpersona(idEmpleado);

        prestamo.setClientes(cliente);
        prestamo.setEmpleados(empleado);
        prestamo.setMonto(monto);
        prestamo.setTasaInteres(tasaInteres);
        prestamo.setNumeroCuotas(numeroCuotas);
        prestamo.setFechaIinicio(fechaInicio);

        boolean registrado = controller.registrarPrestamo(prestamo);

        if (registrado) {
            System.out.println("\nPréstamo registrado correctamente.");
            System.out.printf("Monto total: %.2f%n", prestamo.getMontoTotal());
            System.out.printf("Valor de la cuota: %.2f%n", prestamo.getCuotaMensual());
            System.out.printf("Saldo pendiente: %.2f%n", prestamo.getSaldoPendiente());
            System.out.println("Fecha de vencimiento: " + prestamo.getFechaVencimiento());
        } else {
            System.out.println("No fue posible registrar el préstamo.");
        }
    }

    // Consultar un préstamo
    private void consultarPrestamo() throws SQLException {
        System.out.println("\n--- CONSULTAR PRÉSTAMO ---");
        System.out.print("Ingrese el ID del préstamo: ");

        int id = Integer.parseInt(scanner.nextLine());
        Prestamos prestamo = controller.consultarPrestamo(id);

        if (prestamo == null) {
            System.out.println("No se encontró un préstamo con ese ID.");
            return;
        }

        mostrarDatosPrestamo(prestamo);
    }

    // Listar préstamos
    private void listarPrestamos() throws SQLException {
        System.out.println("\n--- LISTADO DE PRÉSTAMOS ---");
        List<Prestamos> prestamos = controller.listarPrestamos();

        if (prestamos == null || prestamos.isEmpty()) {
            System.out.println("No hay préstamos registrados.");
            return;
        }

        for (Prestamos prestamo : prestamos) {
            mostrarDatosPrestamo(prestamo);
            System.out.println("------------------------------");
        }
    }

    // Mostrar información de un préstamo
    private void mostrarDatosPrestamo(Prestamos prestamo) {
        System.out.println("\nID: " + prestamo.getIdprestamo());
        System.out.printf("Monto prestado: %.2f%n", prestamo.getMonto());
        System.out.printf("Tasa de interés: %.2f%%%n", prestamo.getTasaInteres());
        System.out.printf("Monto total: %.2f%n", prestamo.getMontoTotal());
        System.out.printf("Cuota mensual: %.2f%n", prestamo.getCuotaMensual());
        System.out.printf("Saldo pendiente: %.2f%n", prestamo.getSaldoPendiente());
        System.out.println("Número de cuotas: " + prestamo.getNumeroCuotas());
        System.out.println("Fecha de inicio: " + prestamo.getFechaIinicio());
        System.out.println("Fecha de vencimiento: " + prestamo.getFechaVencimiento());
        System.out.println("Estado: " + prestamo.getEstado());

        if (prestamo.getClientes() != null) {
            System.out.println("ID del cliente: " + prestamo.getClientes().getIdpersona());
        }

        if (prestamo.getEmpleados() != null) {
            System.out.println("ID del empleado: " + prestamo.getEmpleados().getIdpersona());
        }
    }
}
