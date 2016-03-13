/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consignacion_de_documentos.global;

import com.consignacion_de_documentos.modelo.entidad.Usuario;

/**
 *
 * @author Ing Richard David
 */
public class Sesion {

    public static Sesion instance;
    private int cedula;
    private String nombre_de_usuario;
    
    private Sesion() {
    }
    
    public synchronized static Sesion obtener_instancia() {        
        if (instance == null) {
            instance = new Sesion();
        }
        return instance;        
    }

    public void datos_de_sesion(Usuario usuario){
        this.cedula = usuario.getPersona().getCedula();
        this.nombre_de_usuario = usuario.getNombre_de_usuario();
    }
    
    public void eliminar_instancia() {
        instance = null;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre_de_usuario() {
        return nombre_de_usuario;
    }

    public void setNombre_de_usuario(String nombre_de_usuario) {
        this.nombre_de_usuario = nombre_de_usuario;
    }
    
}
