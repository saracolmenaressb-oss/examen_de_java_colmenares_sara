/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemacrediya.Vista;

/**
 *
 * @author USUARIO
 */
public class MenuReportes {
//     private final Scanner scanner;
//    private final ReporteController controller;
//
//    public MenuReportes() {
//        this.scanner = new Scanner(System.in);
//        this.controller = new ReporteController();
//    }
//
//    public void mostrarMenu() {
//
//        int opcion = -1;
//
//        do {
//            System.out.println("\n===== REPORTES =====");
//            System.out.println("1. Préstamos pendientes");
//            System.out.println("2. Préstamos pagados");
//            System.out.println("0. Volver");
//            System.out.print("Seleccione una opción: ");
//
//            try {
//                opcion = Integer.parseInt(scanner.nextLine());
//
//                switch (opcion) {
//                    case 1:
//                        mostrarReporte(EstadoPrestamo.PENDIENTE);
//                        break;
//
//                    case 2:
//                        mostrarReporte(EstadoPrestamo.PAGADO);
//                        break;
//
//                    case 0:
//                        System.out.println("Volviendo al menú...");
//                        break;
//
//                    default:
//                        System.out.println("Opción inválida.");
//                }
//
//            } catch (NumberFormatException e) {
//                System.out.println("Debes ingresar un número.");
//                opcion = -1;
//
//            } catch (SQLException e) {
//                System.out.println(
//                        "Error al generar el reporte: "
//                        + e.getMessage()
//                );
//                opcion = -1;
//
//            } catch (IllegalArgumentException e) {
//                System.out.println(e.getMessage());
//                opcion = -1;
//            }
//
//        } while (opcion != 0);
//    }
//
//    private void mostrarReporte(EstadoPrestamo estado)
//            throws SQLException {
//
//        ResumenPrestamos resumen =
//                controller.generarReporte(estado);
//
//        System.out.println("\n===== RESULTADO DEL REPORTE =====");
//        System.out.println("Estado: " + resumen.getEstado());
//        System.out.println(
//                "Cantidad de préstamos: " + resumen.getCantidad()
//        );
//
//        System.out.printf(
//                "Monto total: $%,.2f%n",
//                resumen.getMontoTotal()
//        );
//
//        if (resumen.getCantidad() == 0) {
//            System.out.println(
//                    "No hay préstamos registrados con ese estado."
//            );
//        }
//    }
//    /* MenuReportes menu = new MenuReportes();
//    menu.mostrarMenu();
///*/
}
