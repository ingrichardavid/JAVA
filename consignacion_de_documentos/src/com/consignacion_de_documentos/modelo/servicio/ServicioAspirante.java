/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consignacion_de_documentos.modelo.servicio;
  
import com.consignacion_de_documentos.configuracion.Mensajes;
import com.consignacion_de_documentos.configuracion.EstructuraDeMensajes;
import com.consignacion_de_documentos.modelo.entidad.Aspirante; 
import com.consignacion_de_documentos.modelo.implementacion.AspiranteImplDAO;
import com.consignacion_de_documentos.modelo.implementacion.PersonaImplDAO; 
import java.util.List;
 
public class ServicioAspirante {

    private static final AspiranteImplDAO aspiranteImplDAO = AspiranteImplDAO.getInstance();
    private static final PersonaImplDAO personaImplDAO = PersonaImplDAO.getInstance();
    private static final Mensajes messages = Mensajes.getInstance();
 
    public ServicioAspirante() {
    }
 
    public static Aspirante encontrar_aspirante(Aspirante aspirante) {        
        if (personaImplDAO.validar_existencia(aspirante.getPersona()) > 0){
            if(aspiranteImplDAO.validar_existencia(aspirante) > 0){
                return buscar_aspirante_persona(aspirante);
            } else{
                EstructuraDeMensajes.mensajeDeInformacion(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.PERSONA_REGISTRADA_ASPIRANTE_NO), EstructuraDeMensajes.justify));
                return buscar_persona(aspirante);
            } 
        }         
        return null;    
    }
      
    public static boolean crear_aspirante(Aspirante aspirante) {
        if (EstructuraDeMensajes.mensajeDeConfirmacion(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.CONFIRMACION), EstructuraDeMensajes.justify)) == 0) {
            if (personaImplDAO.validar_existencia(aspirante.getPersona()) > 0){
                if(aspiranteImplDAO.validar_existencia(aspirante) > 0){
                    EstructuraDeMensajes.mensajeDeAdvertencia(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.ASPIRANTE_EXISTE), EstructuraDeMensajes.justify));
                } else{
                    return aspiranteImplDAO.insertar_aspirante(aspirante);
                } 
            } else {
                return aspiranteImplDAO.insertar_persona_aspirante(aspirante);
            }
        }
        return false;
    } 

    public static boolean modificar_aspirante(Aspirante aspirante){
        if (EstructuraDeMensajes.mensajeDeConfirmacion(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.CONFIRMACION), EstructuraDeMensajes.justify)) == 0) {
            if(aspiranteImplDAO.validar_existencia_dos(aspirante) > 0){
                 EstructuraDeMensajes.mensajeDeError(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.ASPIRANTE_EXISTE), EstructuraDeMensajes.justify));
                return false;
            } else {
                return personaImplDAO.modificar(aspirante.getPersona());
            }
        }
        return false;
    }
       public static boolean eliminar_aspirante(Aspirante aspirante){
           if (EstructuraDeMensajes.mensajeDeConfirmacion(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.COMFIRMACION_ASPIRANTE_ELIMINAR), EstructuraDeMensajes.justify)) == 0) {
                if(aspiranteImplDAO.eliminar(aspirante)){
                     EstructuraDeMensajes.mensajeDeInformacion(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.ASPIRANTE_ELIMINADO), EstructuraDeMensajes.justify));
                    return true;
                } else {
                     EstructuraDeMensajes.mensajeDeError(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.ASPIRANTE_ELIMINADO_ERROR), EstructuraDeMensajes.justify));
                    return false;
                }
           }
        return false;
    }
       
    public static List<Aspirante> llenar_todo(){
        return aspiranteImplDAO.llenar_todo();
    } 
       
    public static List<Aspirante> filtrar(Aspirante aspirante){ 
        return aspiranteImplDAO.filtrar(aspirante);
    } 
    
    public static Aspirante buscar_aspirante_persona(Aspirante aspirante){ 
        return aspiranteImplDAO.buscar_aspirante_persona(aspirante);
    } 
    
    public static Aspirante buscar_persona(Aspirante aspirante){ 
        return aspiranteImplDAO.buscar_persona(aspirante);
    } 
      
}
