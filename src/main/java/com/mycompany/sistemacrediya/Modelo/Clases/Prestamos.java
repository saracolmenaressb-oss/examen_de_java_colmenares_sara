/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemacrediya.Modelo.Clases;

import java.time.LocalDate;

/**
 *
 * @author USUARIO
 */
public class Prestamos {
    private int idprestamo;
    private double monto;
    private double tasaInteres;
    private LocalDate fechaIinicio;
    private LocalDate fechaVencimiento;
    private double cuotaMensual;
    private double montoTotal;
    private double saldoPendiente;
    //Referencias
    private EstadoPrestamo estado;
    private Clientes clientes;
    private Empleado empledos;
    private int numeroCuotas;
    private double valorCuota;
    
    //Constructor
    public Prestamos(int idprestamo, double monto, double tasaInteres, LocalDate fechaIinicio, LocalDate fechaVencimiento, double cuotaMensual, double montoTotal, double saldoPendiente, EstadoPrestamo estado, Clientes clientes, Empleado empledos) {
        this.idprestamo = idprestamo;
        this.monto = monto;
        this.tasaInteres = tasaInteres;
        this.fechaIinicio = fechaIinicio;
        this.fechaVencimiento = fechaVencimiento;
        this.cuotaMensual = cuotaMensual;
        this.montoTotal = montoTotal;
        this.saldoPendiente = saldoPendiente;
        this.estado = estado;
        this.clientes = clientes;
        this.empledos = empledos;
    }
    // metodo de calcular monto total
    public double calcularMontoTotal(){
        double valorInteres = monto * (tasaInteres/100);
        montoTotal = (valorCuota*numeroCuotas)+ valorInteres;
        return montoTotal;
    }
    //calcular cuota mensual
    public double calcularCuotaMensual(int numCuotas){
        return montoTotal / numCuotas;
    }
    //cambiar estado
    public void cambiarEstado(EstadoPrestamo estado) {
        this.estado = estado;
    }
    //getters
    public int getIdprestamo() {
        return idprestamo;
    }

    public double getMonto() {
        return monto;
    }

    public double getTasaInteres() {
        return tasaInteres;
    }

    public LocalDate getFechaIinicio() {
        return fechaIinicio;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public double getCuotaMensual() {
        return cuotaMensual;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public double getSaldoPendiente() {
        return saldoPendiente;
    }

    public EstadoPrestamo getEstado() {
        return estado;
    }

    public Clientes getClientes() {
        return clientes;
    }

    public Empleado getEmpledos() {
        return empledos;
    }
    //setters
    public void setIdprestamo(int idprestamo) {
        this.idprestamo = idprestamo;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public void setTasaInteres(double tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public void setFechaIinicio(LocalDate fechaIinicio) {
        this.fechaIinicio = fechaIinicio;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public void setCuotaMensual(double cuotaMensual) {
        this.cuotaMensual = cuotaMensual;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public void setSaldoPendiente(double saldoPendiente) {
        this.saldoPendiente = saldoPendiente;
    }

    public void setEstado(EstadoPrestamo estado) {
        this.estado = estado;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

    public void setEmpledos(Empleado empledos) {
        this.empledos = empledos;
    }
    
    
}
