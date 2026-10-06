/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemacrediya.Service;

import java.sql.SQLException;
import java.util.List;
import com.mycompany.sistemacrediya.Modelo.Clases.Clientes;
import com.mycompany.sistemacrediya.Modelo.Dao.ClientesDao;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
/**
 *
 * @author USUARIO
 */
public class ClientesService {
    private final ClientesDao clientesDao;

    private static final String EMAIL_REGEX =
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    private static final String SOLO_LETRAS_REGEX =
            "^[\\p{L}]+(?:[ '\\-][\\p{L}]+)*$";

    private static final String SOLO_NUMEROS_REGEX =
            "^[0-9]+$";

    public ClientesService() {
        this.clientesDao = new ClientesDao();
    }

    // Constructor para inyectar un DAO cuando sea necesario.
    public ClientesService(ClientesDao clientesDao) {
        this.clientesDao = clientesDao;
    }

    // REGISTRAR
    public boolean registrarCliente(Clientes cliente)
            throws SQLException {

        validarCliente(cliente);

        // El ID lo genera MySQL.
        return clientesDao.registrarCliente(cliente);
    }

    // CONSULTAR POR ID
    public Clientes consultarPorId(int id) throws SQLException {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "El ID debe ser un número positivo."
            );
        }

        return clientesDao.consultarPorId(id);
    }

    // LISTAR TODOS
    public List<Clientes> listarClientes() throws SQLException {

        List<Clientes> clientes = clientesDao.listarClientes();

        return clientes.stream()
                .sorted((c1, c2) ->
                        c1.getNombre().compareToIgnoreCase(c2.getNombre()))
                .collect(Collectors.toList());
    }

    // BUSCAR POR NOMBRE
    public List<Clientes> buscarClientesPorNombre(String filtro)
            throws SQLException {

        if (filtro == null || filtro.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El nombre de búsqueda no puede estar vacío."
            );
        }

        String nombreBuscado = filtro.trim().toLowerCase();

        return clientesDao.listarClientes().stream()
                .filter(c -> c.getNombre() != null
                        && c.getNombre().toLowerCase()
                                .contains(nombreBuscado))
                .collect(Collectors.toList());
    }

    // ACTUALIZAR
    public boolean actualizarCliente(Clientes cliente)
            throws SQLException {

        if (cliente == null) {
            throw new IllegalArgumentException(
                    "El cliente no puede ser nulo."
            );
        }

        if (cliente.getIdpersona() <= 0) {
            throw new IllegalArgumentException(
                    "El ID del cliente debe ser válido."
            );
        }

        validarCliente(cliente);

        return clientesDao.actualizar(cliente);
    }

    // ELIMINAR
    public boolean eliminarCliente(int id) throws SQLException {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "El ID debe ser un número positivo."
            );
        }

        return clientesDao.eliminar(id);
    }

    // VALIDACIÓN GENERAL
    private void validarCliente(Clientes cliente) {

        if (cliente == null) {
            throw new IllegalArgumentException(
                    "El cliente no puede ser nulo."
            );
        }

        validarNombre(cliente.getNombre());
        validarDocumento(cliente.getDocumento());
        validarCorreo(cliente.getCorreo());
        validarTelefono(cliente.getTelefono());
    }

    private void validarNombre(String nombre) {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El nombre no puede estar vacío."
            );
        }

        if (!Pattern.matches(SOLO_LETRAS_REGEX, nombre.trim())) {
            throw new IllegalArgumentException(
                    "El nombre contiene caracteres no permitidos."
            );
        }
    }

    private void validarDocumento(String documento) {

        if (documento == null || documento.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El documento no puede estar vacío."
            );
        }

        if (!Pattern.matches(SOLO_NUMEROS_REGEX, documento.trim())) {
            throw new IllegalArgumentException(
                    "El documento debe contener únicamente números."
            );
        }
    }

    private void validarCorreo(String correo) {

        if (correo == null || correo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El correo no puede estar vacío."
            );
        }

        if (!Pattern.matches(EMAIL_REGEX, correo.trim())) {
            throw new IllegalArgumentException(
                    "El formato del correo electrónico no es válido."
            );
        }
    }

    private void validarTelefono(String telefono) {

        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El teléfono no puede estar vacío."
            );
        }

        if (!Pattern.matches(SOLO_NUMEROS_REGEX, telefono.trim())) {
            throw new IllegalArgumentException(
                    "El teléfono debe contener únicamente números."
            );
        }
    }
}
