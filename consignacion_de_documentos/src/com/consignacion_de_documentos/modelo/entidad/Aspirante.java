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
public class Aspirante {
    
    private int codigo;
    private Date fecha_registro;
    private Persona persona;    

    public Aspirante() {
    }
    
    public Aspirante(int codigo, Persona persona, Date fecha_registro) {
        this.codigo = codigo;
        this.fecha_registro = fecha_registro;
        this.persona = persona;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
