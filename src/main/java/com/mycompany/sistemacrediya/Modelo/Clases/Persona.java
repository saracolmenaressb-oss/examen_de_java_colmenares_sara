/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemacrediya.Modelo.Clases;

/**
 *
 * @author USUARIO
 */
public abstract class Persona {
    private int idpersona;
    private String nombre;
    private String documento;
    private String correo;
    
    //constructor con el id
    public Persona(int idpersona, String nombre, String documento, String correo) {
        this.idpersona = idpersona;
        this.nombre = nombre;
        this.documento = documento;
        this.correo = correo;
    }
    //Constructor sin el idpersona
    public Persona(String nombre, String documento, String correo) {
        this.nombre = nombre;
        this.documento = documento;
        this.correo = correo;
    }
    
    //getters
    public int getIdpersona() {
        return idpersona;
    }

    public String getCorreo() {
        return correo;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNombre() {
        return nombre;
    }
    //setters

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
}
