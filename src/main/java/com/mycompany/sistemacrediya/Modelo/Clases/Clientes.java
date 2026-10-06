/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemacrediya.Modelo.Clases;

/**
 *
 * @author USUARIO
 */
public class Clientes extends Persona {
    
    private String telefono;
    //constructor
    public Clientes(int idpersona, String nombre, String documento, String correo, String telefono) {
        super(idpersona, nombre, documento, correo);
        this.telefono = telefono;
    }

    public Clientes(String telefono, String nombre, String documento, String correo) {
        super(nombre, documento, correo);
        this.telefono = telefono;
    }

    public Clientes() {
    }
    
    
    //getters
    public String getTelefono() {
        return telefono;
    }
    //setters
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
}
