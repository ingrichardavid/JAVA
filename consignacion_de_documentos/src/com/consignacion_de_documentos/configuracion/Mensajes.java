/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consignacion_de_documentos.configuracion;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que contiene objetos estáticos para la emisión de mensajes en la aplicación.
 * @author Ing Erwis Álvarez
 * @version 1
 * @since 11/12/2015
 */
public class Mensajes {
    
    //Objecto, variables y constantes.
    Properties properties = null;
    
    /**
     * Ruta hacia el archivo de propiedades que contiene los mensajes.
     */
    public final static String FILE_ROUTE = "com/consignacion_de_documentos/configuracion/mensajes.properties";
    
    //Mensajes Globales
    
    /**
     * ¿Los datos ingresados son correctos?.
     */
    public final static String CONFIRMACION = "CONFIRMACION";    
    /**
     * Error al ejecutar esta operación.
     */
    public final static String ERROR_EN_LA_OPERACION = "ERROR_EN_LA_OPERACION";    
    /**
     * ¿Desea salir?.
     */
    public final static String SALIR = "SALIR";    
    /**
     * Error de Conexión con la Base de Datos. 
     */
    public final static String ERROR_DE_CONEXION = "ERROR_DE_CONEXION";    
    /**
     * Error al solicitar conector JDBC.
     */
    public final static String ERROR_DE_CONNECTOR = "ERROR_DE_CONNECTOR";    
    /**
     * Por favor, seleccione un registro.
     */
    public final static String FILA_NO_SELECCIONADA = "FILA_NO_SELECCIONADA";
    
    /**
     * Por favor, introduza la cédula.
     */
    public final static String CEDULA_VACIO = "CEDULA_VACIO";  
    
     /**
     * Por favor, introduza los nombres.
     */
    public final static String NOMBRE_VACIO = "NOMBRE_VACIO";  
    
     /**
     * Por favor, introduza los apellidos.
     */
    public final static String APELLIDO_VACIO = "APELLIDO_VACIO";  
    
           
    /**
     * Por favor, introduzca el nombre de usuario.
     */
    public final static String NOMBRE_USUARIO_VACIO = "NOMBRE_USUARIO_VACIO";    
    /**
     * Por favor, introduzca la clave de usuario.
     */
    public final static String CLAVE_USUARIO_VACIA = "CLAVE_USUARIO_VACIA";    
    /**
     * Acceso denegado.
     */
    public final static String ACCESO_DENEGADO = "ACCESO_DENEGADO";       
 
    /**
     * El usuario ya se encuentra registrado.
     */
    public final static String USUARIO_EXISTE = "USUARIO_EXISTE";  
    /**
     * Usuario registrado.
     */
    public final static String USUARIO_REGISTRADO = "USUARIO_REGISTRADO";  
  
    /**
     * Usuario eliminado.
     */
    public final static String USUARIO_ELIMINADO = "USUARIO_ELIMINADO";
    /**
     * ¿Desea eliminar el usuario?
     */
    public final static String COMFIRMACION_USUARIO_ELIMINAR = "COMFIRMACION_USUARIO_ELIMINAR";  
    /**
     * Usuario modificado.
     */
    public final static String USUARIO_MODIFICADO = "USUARIO_MODIFICADO"; 
    
    /**
     * La persona se encuentra registrada pero no como usuario. Sus datos básicos serán cargados.
     */
    public final static String PERSONA_REGISTRADA_USUARIO_NO = "PERSONA_REGISTRADA_USUARIO_NO"; 
    
    /**
     * Error al registrar el usuario.
     */
    public final static String USUARIO_REGISTRADO_ERROR = "USUARIO_REGISTRADO_ERROR";  
    /**
     *Error al modificar el usuario.
     */
    public final static String USUARIO_MODIFICADO_ERROR = "USUARIO_MODIFICADO_ERROR"; 
    
    /**
     * Error al eliminar el usuario.
     */
    public final static String USUARIO_ELIMINADO_ERROR = "USUARIO_ELIMINADO_ERROR"; 
    
    /**
     * El nombre de usuario esta siendo utilizado.
     */
    public final static String NOMBRE_USUARIO_EXISTE = "NOMBRE_USUARIO_EXISTE";  
    
    
    //Mensajes de Usuario 
 
    
     
    /**
     * La persona se encuentra registrada pero no como aspirante. Sus datos básicos serán cargados.
     */
    public final static String PERSONA_REGISTRADA_ASPIRANTE_NO = "PERSONA_REGISTRADA_ASPIRANTE_NO"; 
    
    /**
     * El Aspirante ya se encuentra registrado.
     */
    public final static String ASPIRANTE_EXISTE = "ASPIRANTE_EXISTE";  
    /**
     * Aspirante registrado.
     */
    public final static String ASPIRANTE_REGISTRADO = "ASPIRANTE_REGISTRADO";  
  
    /**
     * Aspirante eliminado.
     */
    public final static String ASPIRANTE_ELIMINADO = "ASPIRANTE_ELIMINADO";
    /**
     * ¿Desea eliminar el aspirante?
     */
    public final static String COMFIRMACION_ASPIRANTE_ELIMINAR = "COMFIRMACION_ASPIRANTE_ELIMINAR";  
    /**
     * Aspirante modificado.
     */
    public final static String ASPIRANTE_MODIFICADO = "ASPIRANTE_MODIFICADO"; 
  
    /**
     * Error al registrar el aspirante.
     */
    public final static String ASPIRANTE_REGISTRADO_ERROR = "ASPIRANTE_REGISTRADO_ERROR";
    /**
     *  Error al modificar el aspirante.
     */
    public final static String ASPIRANTE_MODIFICADO_ERROR = "ASPIRANTE_MODIFICADO_ERROR";  
    /**
     * Error al eliminar el aspirante.
     */
    public final static String ASPIRANTE_ELIMINADO_ERROR = "ASPIRANTE_ELIMINADO_ERROR"; 
    
    
    /**
     * Documentos modificados.
     */
    public final static String DOCUMENTOS_MODIFICADOS = "DOCUMENTOS_MODIFICADOS";
    /**
     * ¿Esta seguro que desea guardar los cambios realizados al los documentos?
     */
    public final static String COMFIRMACION_DOCUMENTOS_MODIFICADOS = "COMFIRMACION_DOCUMENTOS_MODIFICADOS";  
    /**
     * Error, no se pudo guardar los cambios en los documentos.
     */
    public final static String DOCUMENTOS_ERROR = "DOCUMENTOS_ERROR"; 
     
 
    private Mensajes() {
        try {
            this.properties = new Properties();
            properties.load(Mensajes.class.getClassLoader().getResourceAsStream(FILE_ROUTE));
        } catch (IOException ex) {
            Logger.getLogger(Mensajes.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
     
    public static Mensajes getInstance() {
        return ConfigurationHolder.INSTANCE;
    }
  
    private static class ConfigurationHolder { 
        private static final Mensajes INSTANCE = new Mensajes();
    }
  
    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }    
    
}
