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
public class DocumentosConsignados {
    
    private int codigo;
    private String observaciones;
    private Date fecha_registro;
    private Documento documento;
    private Aspirante aspirante;
    private boolean estatus;
    private boolean digitalizado;

    public DocumentosConsignados(int codigo, Documento documento, boolean estatus, boolean digitalizado, String observaciones){
        this.codigo = codigo;
        this.documento = documento;
        this.estatus = estatus;
        this.digitalizado = digitalizado;
        this.observaciones = observaciones;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }
    
    public Aspirante getAspirante() {
        return aspirante;
    }

    public void setAspirante(Aspirante aspirante) {
        this.aspirante = aspirante;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    public boolean isDigitalizado() {
        return digitalizado;
    }

    public void setDigitalizado(boolean digitalizado) {
        this.digitalizado = digitalizado;
    }
        
}
