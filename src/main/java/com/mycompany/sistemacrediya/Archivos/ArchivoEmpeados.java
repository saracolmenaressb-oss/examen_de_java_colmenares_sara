/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemacrediya.Archivos;

import com.mycompany.sistemacrediya.Modelo.Clases.Empleado;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class ArchivoEmpeados {
    private final PersistenciaArchivos<Empleado> persistencia;

    public ArchivoEmpeados() {
        persistencia =
                new PersistenciaArchivos<>("archivos/empleados.dat");
    }

    public void guardar(List<Empleado> empleados)
            throws IOException {

        persistencia.guardar(empleados);
    }

    public List<Empleado> cargar()
            throws IOException, ClassNotFoundException {

        return persistencia.cargar();
    }
}
