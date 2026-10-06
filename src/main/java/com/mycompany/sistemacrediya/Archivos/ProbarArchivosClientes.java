/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemacrediya.Archivos;

import com.mycompany.sistemacrediya.Modelo.Clases.Clientes;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class ProbarArchivosClientes {
    public static void main(String[] args) {

        try {

            ArchivoClientes archivo = new ArchivoClientes();

            List<Clientes> clientes = new ArrayList<>();

            clientes.add(new Clientes(
                    1,
                    "Carlos Perez",
                    "123456",
                    "carlos@gmail.com",
                    "3001234567"
            ));

            clientes.add(new Clientes(
                    2,
                    "Laura Gomez",
                    "654321",
                    "laura@gmail.com",
                    "3109876543"
            ));

            archivo.guardar(clientes);

            System.out.println("Clientes guardados correctamente.");

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
}
