/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemacrediya.Vista;

import com.mycompany.sistemacrediya.Controlador.ClientesController;
import com.mycompany.sistemacrediya.Modelo.Clases.Clientes;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author USUARIO
 */
public class MenuParaClientes {
        private final ClientesController controller;
    private final Scanner scanner;

    
    public  MenuParaClientes(){
        this.controller = new ClientesController();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {

        int opcion;

        do {
            System.out.println("\n===== MENÚ DE CLIENTES =====");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Listar clientes");
            System.out.println("3. Consultar cliente por ID");
            System.out.println("4. Buscar cliente por nombre");
            System.out.println("5. Actualizar cliente");
            System.out.println("6. Eliminar cliente");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero();

            try {
                switch (opcion) {
                    case 1:
                        registrarCliente();
                        break;

                    case 2:
                        listarClientes();
                        break;

                    case 3:
                        consultarPorId();
                        break;

                    case 4:
                        buscarPorNombre();
                        break;

                    case 5:
                        actualizarCliente();
                        break;

                    case 6:
                        eliminarCliente();
                        break;

                    case 0:
                        System.out.println(
                                "Volviendo al menú principal..."
                        );
                        break;

                    default:
                        System.out.println(
                                "Opción inválida. Intente nuevamente."
                        );
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Dato inválido: " + e.getMessage());

            } catch (SQLException e) {
                System.err.println(
                        "Error de base de datos: " + e.getMessage()
                );
            }

        } while (opcion != 0);
    }

    private void registrarCliente() throws SQLException {

        System.out.println("\n--- REGISTRAR CLIENTE ---");

        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Documento: ");
        String documento = scanner.nextLine().trim();

        System.out.print("Correo electrónico: ");
        String correo = scanner.nextLine().trim();

        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine().trim();

        Clientes cliente = new Clientes(
                0,
                nombre,
                documento,
                correo,
                telefono
        );

        boolean registrado = controller.registrarCliente(cliente);

        if (registrado) {
            System.out.println("Cliente registrado correctamente.");
        } else {
            System.out.println(
                    "No se pudo registrar el cliente."
            );
        }
    }

    private void listarClientes() throws SQLException {

        System.out.println("\n--- LISTA DE CLIENTES ---");

        List<Clientes> clientes = controller.listarClientes();

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        for (Clientes cliente : clientes) {
            mostrarCliente(cliente);
        }
    }

    private void consultarPorId() throws SQLException {

        System.out.println("\n--- CONSULTAR CLIENTE ---");

        System.out.print("Ingrese el ID del cliente: ");
        int id = leerEntero();

        Clientes cliente = controller.consultarPorId(id);

        if (cliente == null) {
            System.out.println(
                    "No se encontró un cliente con ese ID."
            );
        } else {
            mostrarCliente(cliente);
        }
    }

    private void buscarPorNombre() throws SQLException {

        System.out.println("\n--- BUSCAR CLIENTE ---");

        System.out.print("Ingrese el nombre o una parte: ");
        String nombre = scanner.nextLine().trim();

        List<Clientes> clientes =
                controller.buscarClientesPorNombre(nombre);

        if (clientes.isEmpty()) {
            System.out.println(
                    "No se encontraron clientes coincidentes."
            );
            return;
        }

        for (Clientes cliente : clientes) {
            mostrarCliente(cliente);
        }
    }

    private void actualizarCliente() throws SQLException {

        System.out.println("\n--- ACTUALIZAR CLIENTE ---");

        System.out.print("ID del cliente que desea actualizar: ");
        int id = leerEntero();

        Clientes existente = controller.consultarPorId(id);

        if (existente == null) {
            System.out.println(
                    "No se encontró un cliente con ese ID."
            );
            return;
        }

        System.out.println(
                "Deje el campo vacío para conservar su valor actual."
        );

        System.out.print(
                "Nombre [" + existente.getNombre() + "]: "
        );
        String nombre = scanner.nextLine().trim();

        System.out.print(
                "Documento [" + existente.getDocumento() + "]: "
        );
        String documento = scanner.nextLine().trim();

        System.out.print(
                "Correo [" + existente.getCorreo() + "]: "
        );
        String correo = scanner.nextLine().trim();

        System.out.print(
                "Teléfono [" + existente.getTelefono() + "]: "
        );
        String telefono = scanner.nextLine().trim();

        if (!nombre.isEmpty()) {
            existente.setNombre(nombre);
        }

        if (!documento.isEmpty()) {
            existente.setDocumento(documento);
        }

        if (!correo.isEmpty()) {
            existente.setCorreo(correo);
        }

        if (!telefono.isEmpty()) {
            existente.setTelefono(telefono);
        }

        boolean actualizado =
                controller.actualizarCliente(existente);

        if (actualizado) {
            System.out.println(
                    "Cliente actualizado correctamente."
            );
        } else {
            System.out.println(
                    "No se pudo actualizar el cliente."
            );
        }
    }

    private void eliminarCliente() throws SQLException {

        System.out.println("\n--- ELIMINAR CLIENTE ---");

        System.out.print("ID del cliente que desea eliminar: ");
        int id = leerEntero();

        Clientes cliente = controller.consultarPorId(id);

        if (cliente == null) {
            System.out.println(
                    "No se encontró un cliente con ese ID."
            );
            return;
        }

        mostrarCliente(cliente);

        System.out.print(
                "¿Está seguro de eliminarlo? (S/N): "
        );
        String confirmacion = scanner.nextLine().trim();

        if (confirmacion.equalsIgnoreCase("S")) {

            boolean eliminado = controller.eliminarCliente(id);

            if (eliminado) {
                System.out.println(
                        "Cliente eliminado correctamente."
                );
            } else {
                System.out.println(
                        "No se pudo eliminar el cliente."
                );
            }

        } else {
            System.out.println("Operación cancelada.");
        }
    }

    private void mostrarCliente(Clientes cliente) {

        System.out.println("------------------------------");
        System.out.println("ID: " + cliente.getIdpersona());
        System.out.println("Nombre: " + cliente.getNombre());
        System.out.println("Documento: " + cliente.getDocumento());
        System.out.println("Correo: " + cliente.getCorreo());
        System.out.println("Teléfono: " + cliente.getTelefono());
    }

    private int leerEntero() {

        while (true) {
            String entrada = scanner.nextLine().trim();

            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.print(
                        "Ingrese un número entero válido: "
                );
            }
        }
    }
}
