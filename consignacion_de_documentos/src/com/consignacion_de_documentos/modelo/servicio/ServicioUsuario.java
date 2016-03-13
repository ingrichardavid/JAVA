/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consignacion_de_documentos.modelo.servicio;
  
import com.consignacion_de_documentos.configuracion.Mensajes;
import com.consignacion_de_documentos.configuracion.EstructuraDeMensajes;
import com.consignacion_de_documentos.modelo.entidad.Usuario;
import com.consignacion_de_documentos.modelo.implementacion.PersonaImplDAO;
import com.consignacion_de_documentos.modelo.implementacion.UsuarioImplDAO;
import java.util.List;

public class ServicioUsuario {

    private static final UsuarioImplDAO usuarioImplDAO = UsuarioImplDAO.getInstance();
    private static final PersonaImplDAO personaImplDAO = PersonaImplDAO.getInstance();
    private static final Mensajes messages = Mensajes.getInstance();
 
    public ServicioUsuario() {
    }
 
    public static Usuario encontrar_usuario(Usuario usuario) {        
        if (personaImplDAO.validar_existencia(usuario.getPersona()) > 0){
            if(usuarioImplDAO.validar_existencia(usuario) > 0){
                return buscar_usuario_persona(usuario);
            } else{
                 EstructuraDeMensajes.mensajeDeInformacion(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.PERSONA_REGISTRADA_USUARIO_NO), EstructuraDeMensajes.justify));
                return buscar_persona(usuario);
            } 
        }         
        return null;    
    }
    
    public static boolean crear_usuario(Usuario usuario) {
        if (EstructuraDeMensajes.mensajeDeConfirmacion(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.CONFIRMACION), EstructuraDeMensajes.justify)) == 0) {
            if (personaImplDAO.validar_existencia(usuario.getPersona()) > 0){
                if(usuarioImplDAO.validar_existencia(usuario) > 0){
                      EstructuraDeMensajes.mensajeDeAdvertencia(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.USUARIO_EXISTE), EstructuraDeMensajes.justify));
                } else{
                    return usuarioImplDAO.insertar_usuario(usuario);
                } 
            } else {
                EstructuraDeMensajes.mensajeDeInformacion(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.USUARIO_REGISTRADO), EstructuraDeMensajes.justify));
                return usuarioImplDAO.insertar_persona_usuario(usuario); 
                  
            }
        }
        return false;
    } 

    public static boolean modificar_usuario(Usuario usuario){
        if (EstructuraDeMensajes.mensajeDeConfirmacion(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.CONFIRMACION), EstructuraDeMensajes.justify)) == 0) {
            if (personaImplDAO.validar_existencia_dos(usuario.getPersona()) > 0){
                if(usuarioImplDAO.validar_existencia_dos(usuario) > 0){
                           EstructuraDeMensajes.mensajeDeAdvertencia(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.USUARIO_EXISTE), EstructuraDeMensajes.justify));
                        return false;
                } else{
                    if(usuarioImplDAO.validar_nombre_de_usuario_dos(usuario) > 0){
                          EstructuraDeMensajes.mensajeDeAdvertencia(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.NOMBRE_USUARIO_EXISTE), EstructuraDeMensajes.justify));
                        return false;
                    }else{
                        if(usuarioImplDAO.modificar(usuario)){
                             EstructuraDeMensajes.mensajeDeInformacion(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.USUARIO_MODIFICADO), EstructuraDeMensajes.justify));
                            return true;
                        }else{
                            EstructuraDeMensajes.mensajeDeInformacion(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.USUARIO_MODIFICADO_ERROR), EstructuraDeMensajes.justify));
                            return false;
                        }
                    }
                } 
            }else{
                if(usuarioImplDAO.validar_existencia_dos(usuario) > 0){
                     EstructuraDeMensajes.mensajeDeInformacion(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.USUARIO_EXISTE), EstructuraDeMensajes.justify));
                    return false;
                } else if(usuarioImplDAO.validar_nombre_de_usuario_dos(usuario) > 0){
                     EstructuraDeMensajes.mensajeDeAdvertencia(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.NOMBRE_USUARIO_EXISTE), EstructuraDeMensajes.justify));
                    return false;
                } else{
                    if(personaImplDAO.modificar(usuario.getPersona())){
                        if(usuarioImplDAO.modificar(usuario)){
                             EstructuraDeMensajes.mensajeDeInformacion(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.USUARIO_MODIFICADO), EstructuraDeMensajes.justify));
                            return true;
                        }else{
                            EstructuraDeMensajes.mensajeDeError(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.USUARIO_MODIFICADO_ERROR), EstructuraDeMensajes.justify));
                            return false;
                        }
                    }else{
                        EstructuraDeMensajes.mensajeDeInformacion(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.USUARIO_MODIFICADO_ERROR), EstructuraDeMensajes.justify));
                        return false;
                    }
                }
            } 
        } else {
            return false;          
        }
    }
     
    public static boolean eliminar_usuario(Usuario usuario){
        if (EstructuraDeMensajes.mensajeDeConfirmacion(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.COMFIRMACION_USUARIO_ELIMINAR), EstructuraDeMensajes.justify)) == 0) {
            if(usuarioImplDAO.eliminar(usuario)){
               EstructuraDeMensajes.mensajeDeInformacion(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.USUARIO_ELIMINADO), EstructuraDeMensajes.justify));
                return true;
            } else {
                EstructuraDeMensajes.mensajeDeError(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.USUARIO_ELIMINADO_ERROR), EstructuraDeMensajes.justify));
                return false;
            }
        } else {
            return false;        
        }
    }
       
    public static List<Usuario> llenar_todo(){
        return usuarioImplDAO.llenar_todo();
    } 
       
    public static List<Usuario> filtrar(Usuario usuario){
        return usuarioImplDAO.filtrar(usuario);
    } 
    
    public static Usuario buscar_usuario_persona(Usuario usuario){ 
        return usuarioImplDAO.buscar_usuario_persona(usuario);
    } 
    
    public static Usuario buscar_persona(Usuario usuario){ 
        return usuarioImplDAO.buscar_persona(usuario);
    } 
      
    public static Usuario validar_usuario(Usuario usuario) {
        Usuario usuarioEncontrado = usuarioImplDAO.validar_usuario(usuario);
        if (usuarioEncontrado != null) {
            return usuarioEncontrado;
        } else {
            EstructuraDeMensajes.mensajeDeError(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.ACCESO_DENEGADO), EstructuraDeMensajes.justify));
            return null;
        }
    }
      
}
