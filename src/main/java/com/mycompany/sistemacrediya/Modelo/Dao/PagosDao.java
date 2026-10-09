/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemacrediya.Modelo.Dao;

import com.mycompany.sistemacrediya.Conexion.ConexionDB;
import static com.mycompany.sistemacrediya.Conexion.ConexionDB.con;
import com.mycompany.sistemacrediya.Conexion.Operaciones;
import com.mycompany.sistemacrediya.Modelo.Clases.Pago;
import com.mycompany.sistemacrediya.Modelo.Clases.Prestamos;
import com.mycompany.sistemacrediya.Modelo.Persistencia.PagoRepository;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class PagosDao implements PagoRepository {
    //para registrar el pago
    @Override
    public boolean registrarPago(Pago pago) throws SQLException {
        String sentencia = """
            INSERT INTO pagos (prestamo_id, fecha_pago, monto)
            VALUES (?, ?, ?)
            """;
        Operaciones.setConnection(ConexionDB.MysConnection());
        PreparedStatement ps = null;
        try {
            ps = Operaciones.getConnection()
                    .prepareStatement(sentencia);
            ps.setInt(1, pago.getPrestamo().getIdprestamo());
            ps.setDate(2, java.sql.Date.valueOf(pago.getFechapago()));
            ps.setDouble(3, pago.getMonto());
            int filas = ps.executeUpdate();
            return filas > 0;
        } finally {
            if (ps != null) {
                ps.close();
            }
            Operaciones.cerrarConexion();
        }
    }
    //historico de pagos
    @Override
    public List<Pago> listarPorPrestamo(int idPrestamo) throws SQLException {
        List<Pago> listaPagos = new ArrayList<>();
        Operaciones.setConnection(ConexionDB.MysConnection());
        String sentencia = "SELECT * FROM pagos WHERE prestamo_id = ?;";
        
        PreparedStatement ps = Operaciones.getConnection().prepareStatement(sentencia);
        ps.setInt(1, idPrestamo);
        ResultSet rs = Operaciones.consultar_BD(ps);
        
        while (rs != null && rs.next()) {
            int idPago = rs.getInt("id");
            double monto = rs.getDouble("monto");
            LocalDate fechaPago = rs.getDate("fecha_pago").toLocalDate();
            
            // Creamos un objeto préstamo referencial con el ID
            Prestamos pRef = new Prestamos(idPrestamo, 0, 0, null, null, 0, 0, 0, null, null, null);
            
            Pago pago = new Pago(idPago, monto, fechaPago,0.0, null, pRef);
            listaPagos.add(pago);
        }
        Operaciones.cerrarConexion();
        return listaPagos;
    }
    //Actualizar saldo
    @Override
    public boolean actualizarSaldo(int idPrestamo, double nuevoSaldo) throws SQLException {
        Operaciones.setConnection(ConexionDB.MysConnection());
        // Nota: Si manejas el saldo en la tabla préstamos, actualizamos directamente allá
        String sentencia = "UPDATE prestamos SET saldo_pendiente = ? WHERE id = ?;";
        
        PreparedStatement ps = Operaciones.getConnection().prepareStatement(sentencia);
        ps.setDouble(1, nuevoSaldo);
        ps.setInt(2, idPrestamo);
        
        int filas = ps.executeUpdate();
        Operaciones.cerrarConexion();
        return filas > 0;
    }
    //Persistencia en MySQL (JDBC)
    private static Connection getConnection(
        String url, String user, String password)
        throws SQLException {

    con = DriverManager.getConnection(url, user, password);

    if (con != null) {
        DatabaseMetaData meta = con.getMetaData();

        System.out.println(
                "Base de datos conectada: "
                + meta.getDriverName()
        );
    }

    return con;
    }
    
}
