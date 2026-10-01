/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemacrediya.Modelo.Clases;

/**
 *
 * @author USUARIO
 */
public class Empleado extends Persona{
    
    private String rol;
    private double salario;

    public Empleado(int idpersona, String nombre, String documento, String correo) {
        super(idpersona, nombre, documento, correo);
        this.rol = rol;
        this.salario = salario;
    }
    
    
    //getters
    public String getRol() {
        return rol;
    }

    public double getSalario() {
        return salario;
    }
    //setters
    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    
    
}
