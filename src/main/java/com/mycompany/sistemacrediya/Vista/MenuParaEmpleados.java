/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemacrediya.Vista;

import com.mycompany.sistemacrediya.Controlador.EmpleadosController;
import java.sql.SQLException;
import java.util.Scanner;
import com.mycompany.sistemacrediya.Modelo.Clases.Empleado;
import java.util.List;
/**
 *
 * @author USUARIO
 */
public class MenuParaEmpleados {
    private final EmpleadosController controller;
    private final Scanner scanner = new Scanner(System.in);

    public MenuParaEmpleados(EmpleadosController controller) {
        this.controller = controller;
    }

    public void iniciar() {
        int opcion;

        do {
            System.out.println("\n===== CREDIYA | EMPLEADOS =====");
            System.out.println("1. Registrar empleado");
            System.out.println("2. Consultar empleado por ID");
            System.out.println("3. Listar empleados");
            System.out.println("4. Actualizar empleado");
            System.out.println("5. Eliminar empleado");
            System.out.println("0. Volver / salir");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero();

            try {
                switch (opcion) {
                    case 1 -> registrar();
                    case 2 -> consultar();
                    case 3 -> listar();
                    case 4 -> actualizar();
                    case 5 -> eliminar();
                    case 0 -> System.out.println("Menú finalizado.");
                    default -> System.out.println("Opción no válida.");
                }
            } catch (SQLException e) {
                System.out.println(
                    "Error de base de datos: " + e.getMessage()
                );
            } catch (IllegalArgumentException e) {
                System.out.println(
                    "Error de validación: " + e.getMessage()
                );
            }

        } while (opcion != 0);
    }

    private void registrar() throws SQLException {
        System.out.println("\n--- REGISTRAR EMPLEADO ---");

        Empleado empleado = solicitarDatosEmpleado(true);

        boolean resultado = controller.registrarEmpleado(empleado);

        if (resultado) {
            System.out.println("Empleado registrado correctamente.");
        } else {
            System.out.println("No se pudo registrar el empleado.");
        }
    }

    private void consultar() throws SQLException {
        System.out.println("\n--- CONSULTAR EMPLEADO ---");

        System.out.print("ID del empleado: ");
        int id = leerEntero();

        Empleado empleado = controller.consultarPorId(id);

        if (empleado == null) {
            System.out.println("No existe un empleado con ese ID.");
        } else {
            mostrarEmpleado(empleado);
        }
    }

    private void listar() throws SQLException {
        System.out.println("\n--- LISTADO DE EMPLEADOS ---");

        List<Empleado> empleados = controller.listarEmpleados();

        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }
        

        for (Empleado empleado : empleados) {
            mostrarEmpleado(empleado);
            System.out.println("----------------------------");
        }
    }

    private void actualizar() throws SQLException {
        System.out.println("\n--- ACTUALIZAR EMPLEADO ---");

        System.out.print("ID del empleado que desea actualizar: ");
        int id = leerEntero();

        Empleado existente = controller.consultarPorId(id);

        if (existente == null) {
            System.out.println("No se encontró el empleado.");
            return;
        }

        System.out.println("Datos actuales:");
        mostrarEmpleado(existente);

        System.out.println("\nIngrese los nuevos datos:");
        Empleado actualizado = solicitarDatosEmpleado(false);
        actualizado.setIdpersona(id);

        boolean resultado = controller.actualizar(actualizado);

        if (resultado) {
            System.out.println("Empleado actualizado correctamente.");
        } else {
            System.out.println("No se pudo actualizar el empleado.");
        }
    }

    private void eliminar() throws SQLException {
        System.out.println("\n--- ELIMINAR EMPLEADO ---");

        System.out.print("ID del empleado: ");
        int id = leerEntero();

        Empleado empleado = controller.consultarPorId(id);

        if (empleado == null) {
            System.out.println("No se encontró el empleado.");
            return;
        }

        mostrarEmpleado(empleado);

        System.out.print("¿Confirma la eliminación? (S/N): ");
        String confirmacion = scanner.nextLine().trim();

        if (confirmacion.equalsIgnoreCase("S")) {
            boolean resultado = controller.eliminar(id);

            if (resultado) {
                System.out.println("Empleado eliminado correctamente.");
            } else {
                System.out.println("No se pudo eliminar el empleado.");
            }
        } else {
            System.out.println("Operación cancelada.");
        }
    }

    private Empleado solicitarDatosEmpleado(boolean solicitarId) {
        int id = 0;

        if (solicitarId) {
            System.out.print("ID: ");
            id = leerEntero();
        }

        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Documento: ");
        String documento = scanner.nextLine().trim();

        System.out.print("Correo: ");
        String correo = scanner.nextLine().trim();

        System.out.print("Rol: ");
        String rol = scanner.nextLine().trim();

        System.out.print("Salario: ");
        double salario = leerDouble();

        return new Empleado(
            id, nombre, documento, correo, rol, salario
        );
    }

    private void mostrarEmpleado(Empleado empleado) {
        System.out.println("ID: " + empleado.getIdpersona());
        System.out.println("Nombre: " + empleado.getNombre());
        System.out.println("Documento: " + empleado.getDocumento());
        System.out.println("Correo: " + empleado.getCorreo());
        System.out.println("Rol: " + empleado.getRol());
        System.out.println("Salario: " + empleado.getSalario());
    }

    private int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número entero válido: ");
            }
        }
    }

    private double leerDouble() {
        while (true) {
            try {
                return Double.parseDouble(
                    scanner.nextLine().trim().replace(',', '.')
                );
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un salario válido: ");
            }
        }
    }
}
