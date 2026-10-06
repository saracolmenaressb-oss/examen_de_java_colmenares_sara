/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemacrediya.Archivos;

import com.mycompany.sistemacrediya.Modelo.Clases.Prestamos;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class ArchivoPrestamos {
     private final PersistenciaArchivos<Prestamos> persistencia;

    public ArchivoPrestamos() {
        persistencia =
                new PersistenciaArchivos<>("archivos/prestamos.dat");
    }

    public void guardar(List<Prestamos> prestamos)
            throws IOException {

        persistencia.guardar(prestamos);
    }

    public List<Prestamos> cargar()
            throws IOException, ClassNotFoundException {

        return persistencia.cargar();
    }
}
