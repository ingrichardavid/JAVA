/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consignacion_de_documentos.modelo.entidad;

import java.util.Date;

/**
 *
 * @author RichardJosé
 */
public class Usuario {
    
    private String nombre_de_usuario;
    private String contrasena;
    private Date fecha_registro;
    private Persona persona;
    
    public Usuario() {
    }
     
    public Usuario(String nombre_de_usuario, String contrasena,Persona persona,Date fecha_registro) {
        this.nombre_de_usuario = nombre_de_usuario;
        this.contrasena = contrasena;
        this.fecha_registro = fecha_registro;
        this.persona = persona;
    }    

    public String getNombre_de_usuario() {
        return nombre_de_usuario;
    }

    public void setNombre_de_usuario(String nombre_de_usuario) {
        this.nombre_de_usuario = nombre_de_usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    
}
