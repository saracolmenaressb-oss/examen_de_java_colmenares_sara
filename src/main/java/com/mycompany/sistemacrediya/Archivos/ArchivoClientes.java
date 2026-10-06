/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemacrediya.Archivos;

import com.mycompany.sistemacrediya.Modelo.Clases.Clientes;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class ArchivoClientes {
    private final String ruta = "clientes.txt";

    public void guardar(List<Clientes> clientes) throws IOException {

        try (BufferedWriter escritor = new BufferedWriter(
                new FileWriter(ruta))) {

            for (Clientes cliente : clientes) {

                escritor.write(
                    cliente.getIdpersona() + ";" +
                    cliente.getNombre() + ";" +
                    cliente.getDocumento() + ";" +
                    cliente.getCorreo() + ";" +
                    cliente.getTelefono()
                );

                escritor.newLine();
            }
        }
    }

    public List<Clientes> cargar() throws IOException {

        List<Clientes> clientes = new ArrayList<>();

        File archivo = new File(ruta);

        if (!archivo.exists()) {
            return clientes;
        }

        try (BufferedReader lector = new BufferedReader(
                new FileReader(archivo))) {

            String linea;

            while ((linea = lector.readLine()) != null) {

                String[] datos = linea.split(";");

                Clientes cliente = new Clientes(
                    Integer.parseInt(datos[0]),
                    datos[1],
                    datos[2],
                    datos[3],
                    datos[4]
                );

                clientes.add(cliente);
            }
        }

        return clientes;
    }
}
