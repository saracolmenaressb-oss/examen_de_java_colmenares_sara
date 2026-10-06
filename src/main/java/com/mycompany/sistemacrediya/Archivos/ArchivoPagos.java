/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemacrediya.Archivos;

import com.mycompany.sistemacrediya.Modelo.Clases.Pago;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class ArchivoPagos {
    private final PersistenciaArchivos<Pago> persistencia;

    public ArchivoPagos() {
        persistencia =
                new PersistenciaArchivos<>("archivos/pagos.dat");
    }

    public void guardar(List<Pago> pagos)
            throws IOException {

        persistencia.guardar(pagos);
    }

    public List<Pago> cargar()
            throws IOException, ClassNotFoundException {

        return persistencia.cargar();
    }
}
