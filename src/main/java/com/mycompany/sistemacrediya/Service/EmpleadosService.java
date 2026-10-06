/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemacrediya.Service;
import com.mycompany.sistemacrediya.Modelo.Clases.Empleado;
import com.mycompany.sistemacrediya.Modelo.Persistencia.EmpleadoRepository;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author USUARIO
 */
public class EmpleadosService {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private static final String SOLO_LETRAS_REGEX = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$";
    private final EmpleadoRepository repository;
    /*
    validarNombre(nombre);
        validarDocumento(documento);
        validarCorreo(correo);
        validarTelefono(telefono);
    */
    public EmpleadosService(EmpleadoRepository repository) {
        this.repository = repository;
    }

    public boolean registrarEmpleado(Empleado empleado)
            throws SQLException {

        validarEmpleado(empleado);

        return repository.registrarEmpleado(empleado);
    }

    public Empleado consultarPorId(int id) throws SQLException {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "El ID debe ser mayor que cero."
            );
        }

        return repository.consultarPorId(id);
    }

    public List<Empleado> listarEmpleados() throws SQLException {

        return repository.listarEmpleados();
    }

    public boolean actualizar(Empleado empleado)
            throws SQLException {

        validarEmpleado(empleado);

        if (empleado.getIdpersona() <= 0) {
            throw new IllegalArgumentException(
                "El ID del empleado no es válido."
            );
        }

        return repository.actualizar(empleado);
    }

    public boolean eliminar(int id) throws SQLException {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "El ID debe ser mayor que cero."
            );
        }

        return repository.eliminar(id);
    }

    private void validarEmpleado(Empleado empleado) {

        if (empleado == null) {
            throw new IllegalArgumentException(
                "El empleado no puede ser nulo."
            );
        }

        if (empleado.getNombre() == null
                || empleado.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException(
                "El nombre es obligatorio."
            );
        }
        if (!Pattern.matches(SOLO_LETRAS_REGEX, empleado.getNombre())) {
            throw new IllegalArgumentException("El nombre contiene números o caracteres inválidos (solo se permiten letras).");
        }
        
        if (empleado.getDocumento() == null
                || empleado.getDocumento().trim().isEmpty()) {
            throw new IllegalArgumentException(
                "El documento es obligatorio."
            );
        }

        if (empleado.getCorreo() == null
                || empleado.getCorreo().isBlank()
                || !empleado.getCorreo().matches(
                    "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException(
                "El correo no tiene un formato válido."
            );
        }
        if (!Pattern.matches(EMAIL_REGEX, empleado.getCorreo())) {
            throw new IllegalArgumentException("El formato del correo electrónico no es válido (ejemplo: usuario@dominio.com).");
        }
        if (empleado.getRol() == null
                || empleado.getRol().isBlank()) {
            throw new IllegalArgumentException(
                "El rol es obligatorio."
            );
        }

        if (empleado.getSalario() <= 0
                || !Double.isFinite(empleado.getSalario())) {
            throw new IllegalArgumentException(
                "El salario debe ser un número válido mayor que cero."
            );
        }
    }

}
