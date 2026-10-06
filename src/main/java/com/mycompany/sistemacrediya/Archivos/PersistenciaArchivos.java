/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemacrediya.Archivos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class PersistenciaArchivos<T extends Serializable> {
    private final String rutaArchivo;

    public PersistenciaArchivos(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public void guardar(List<T> lista) throws IOException {

        File archivo = new File(rutaArchivo);

        File carpeta = archivo.getParentFile();

        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }

        try (ObjectOutputStream salida =
                new ObjectOutputStream(
                        new FileOutputStream(archivo))) {

            salida.writeObject(lista);
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> cargar()
            throws IOException, ClassNotFoundException {

        File archivo = new File(rutaArchivo);

        if (!archivo.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream entrada =
                new ObjectInputStream(
                        new FileInputStream(archivo))) {

            return (List<T>) entrada.readObject();
        }
    }
}
