/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemacrediya.Modelo.Clases;

import java.io.Serializable;

/**
 *
 * @author USUARIO
 */
public abstract class Persona implements Serializable{
    private int idpersona;
    private String nombre;
    private String documento;
    private String correo;
    private static final long serialVersionUID = 1L;
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
    //Constructor vacio

    public Persona() {
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

    public void setIdpersona(int idpersona) {
        this.idpersona = idpersona;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
    
}
