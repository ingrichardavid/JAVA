/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consignacion_de_documentos.modelo.servicio;
  
import com.consignacion_de_documentos.configuracion.EstructuraDeMensajes;
import com.consignacion_de_documentos.configuracion.Mensajes;
import com.consignacion_de_documentos.modelo.dao.DocumentosConsignadosDAO;
import com.consignacion_de_documentos.modelo.entidad.Aspirante;
import com.consignacion_de_documentos.modelo.entidad.DocumentosConsignados;
import com.consignacion_de_documentos.modelo.implementacion.DocumentosConsignadosImplDAO;
import java.util.List;
 
public class ServicioDocumentosConsignados {

    private static final DocumentosConsignadosDAO documentosConsignadosImplDAO = DocumentosConsignadosImplDAO.getInstance();
    private static final Mensajes messages = Mensajes.getInstance(); 
    
    public ServicioDocumentosConsignados() {
    }
                
    public static boolean modificar_documentos_consignados(List<DocumentosConsignados> documentosConsignados){
        if (EstructuraDeMensajes.mensajeDeConfirmacion(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.CONFIRMACION), EstructuraDeMensajes.justify)) == 0) {
        return documentosConsignadosImplDAO.modificar_documentos_consignados(documentosConsignados);
        }
        return false;
    } 
         
    public static List<DocumentosConsignados> encontrar_documentos_consignados(Aspirante aspirante){
        return documentosConsignadosImplDAO.encontrar_documentos_consignados(aspirante);
    } 
       
}
