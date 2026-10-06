/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemacrediya.Modelo.Clases;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author USUARIO
 */
public class Pago implements Serializable{
    private static final long serialVersionUID = 1L;
    private int idpago;
    private double monto;
    private LocalDate fechapago;
    private double saldoRestante;
    //Referencias
    private MetodoPago metodoPago;
    private Prestamos prestamo;

    public Pago(int idpago, double monto, LocalDate fechapago, double saldoRestante, MetodoPago metodoPago, Prestamos prestamo) {
        this.idpago = idpago;
        this.monto = monto;
        this.fechapago = fechapago;
        this.saldoRestante = saldoRestante;
        this.metodoPago = metodoPago;
        this.prestamo = prestamo;
    }
    //getters
    public int getIdpago() {
        return idpago;
    }

    public double getMonto() {
        return monto;
    }

    public LocalDate getFechapago() {
        return fechapago;
    }

    public double getSaldoRestante() {
        return saldoRestante;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public Prestamos getPrestamo() {
        return prestamo;
    }
    //setters
    public void setIdpago(int idpago) {
        this.idpago = idpago;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public void setFechapago(LocalDate fechapago) {
        this.fechapago = fechapago;
    }

    public void setSaldoRestante(double saldoRestante) {
        this.saldoRestante = saldoRestante;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public void setPrestamo(Prestamos prestamo) {
        this.prestamo = prestamo;
    }
    
}
