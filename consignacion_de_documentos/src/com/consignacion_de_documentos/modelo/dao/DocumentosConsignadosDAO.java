/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consignacion_de_documentos.modelo.dao;
  
import com.consignacion_de_documentos.modelo.entidad.DocumentosConsignados;
import com.consignacion_de_documentos.modelo.entidad.Aspirante;
import java.util.List;

/**
 * 
 * @author RichardJosé
 */
 
public interface DocumentosConsignadosDAO {

    public List<DocumentosConsignados> encontrar_documentos_consignados(Aspirante aspirante);
    public boolean modificar_documentos_consignados(List<DocumentosConsignados> documentosConsignados);

}
