/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemacrediya.Vista;

import com.mycompany.sistemacrediya.Controlador.EmpleadosController;
import java.util.Scanner;
import com.mycompany.sistemacrediya.Modelo.Persistencia.EmpleadoRepository;
import com.mycompany.sistemacrediya.Modelo.Dao.EmpleadosDao;
import com.mycompany.sistemacrediya.Service.EmpleadosService;
/**
 *
 * @author USUARIO
 */
public class Menu {
    
    private final Scanner scanner;
    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenuPrincipal() {

        int opcion = 0;

        do {
            System.out.println("\n================================");
            System.out.println("       SISTEMA CREDIYA");
            System.out.println("================================");
            System.out.println("1. Gestión de empleados");
            System.out.println("2. Gestión de clientes");
            System.out.println("3. Gestión de préstamos");
            System.out.println("4. Gestión de pagos");
            System.out.println("5. Salir del sistema");
            System.out.println("================================");
            System.out.print("Seleccione una opción: ");

            String entrada = scanner.nextLine().trim();

            try {
                opcion = Integer.parseInt(entrada);

                switch (opcion) {

                    case 1:
                        abrirMenuEmpleados();
                        break;

                    case 2:
                        abrirMenuClientes();
                        break;

                    case 3:
                        abrirMenuPrestamos();
                        break;

                    case 4:
                        abrirMenuPagos();
                        break;

                    case 0:
                        System.out.println(
                                "Gracias por utilizar CrediYa."
                        );
                        break;

                    default:
                        System.out.println(
                                "Opción inválida. Seleccione del 0 al 4."
                        );
                }

            } catch (NumberFormatException e) {
                System.out.println(
                        "Entrada inválida. Debe ingresar un número."
                );
                opcion = -1;

            } catch (RuntimeException e) {
                System.out.println(
                        "Ocurrió un error al abrir el módulo: "
                                + e.getMessage()
                );
                opcion = -1;
            }

        } while (opcion != 5);
    }

    // Abrir módulo de empleados
    private void abrirMenuEmpleados() {

        EmpleadoRepository repository = new EmpleadosDao();

        EmpleadosService service =
                new EmpleadosService(repository);

        EmpleadosController controller =
                new EmpleadosController(service);

        MenuParaEmpleados menu =
                new MenuParaEmpleados(controller);

        menu.iniciar();
    }

    // Abrir módulo de clientes
    private void abrirMenuClientes() {

        MenuParaClientes menu = new MenuParaClientes();
        menu.mostrarMenu();
    }

    // Abrir módulo de préstamos
    private void abrirMenuPrestamos() {

        MenuPrestamo menu = new MenuPrestamo();
        menu.mostrarMenu();
    }

    // Abrir módulo de pagos
    private void abrirMenuPagos() {

        MenuPago menu = new MenuPago();
        menu.mostrarMenu();
    }

}
