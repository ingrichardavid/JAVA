/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consignacion_de_documentos.controlador;

import com.consignacion_de_documentos.configuracion.EstructuraDeMensajes;
import com.consignacion_de_documentos.configuracion.Mensajes;
import com.consignacion_de_documentos.global.Metodos;
import com.consignacion_de_documentos.modelo.entidad.Persona;
import com.consignacion_de_documentos.modelo.entidad.Usuario;
import com.consignacion_de_documentos.modelo.servicio.ServicioUsuario;
import com.consignacion_de_documentos.vista.VistaUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Ing Richard David
 */
public class ControladorUsuario implements ActionListener, KeyListener {

    private final VistaUsuario vistaUsuario;
    private Usuario usuario;
    private char caracter;
    private static final Mensajes messages = Mensajes.getInstance();
    
    public ControladorUsuario(VistaUsuario vistaUsuario) {
        this.vistaUsuario = vistaUsuario;
    }
    
    private boolean validar_campos() {    
        if (this.vistaUsuario.getTxtCedula().getText().trim().equalsIgnoreCase("")) {            
            EstructuraDeMensajes.mensajeDeAdvertencia(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.CEDULA_VACIO), EstructuraDeMensajes.justify));
            this.vistaUsuario.getTxtCedula().requestFocus();
            return false;
        } else if (this.vistaUsuario.getTxtNombres().getText().trim().equalsIgnoreCase("")) {            
            EstructuraDeMensajes.mensajeDeAdvertencia(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.NOMBRE_VACIO), EstructuraDeMensajes.justify));
            this.vistaUsuario.getTxtNombres().requestFocus();
            return false;
        } else if (this.vistaUsuario.getTxtApellidos().getText().trim().equalsIgnoreCase("")) {            
            EstructuraDeMensajes.mensajeDeAdvertencia(EstructuraDeMensajes.formato(200,messages.getProperty(Mensajes.APELLIDO_VACIO), EstructuraDeMensajes.justify));
            this.vistaUsuario.getTxtApellidos().requestFocus();
            return false;
        } else if (this.vistaUsuario.getTxtNombreUsuario().getText().trim().equalsIgnoreCase("")) {            
            EstructuraDeMensajes.mensajeDeAdvertencia(EstructuraDeMensajes.formato(200,messages.getProperty(Mensajes.NOMBRE_USUARIO_VACIO), EstructuraDeMensajes.justify));
            this.vistaUsuario.getTxtNombreUsuario().requestFocus();
            return false;        
        } else if (String.valueOf(this.vistaUsuario.getTxtClave().getPassword()).equalsIgnoreCase("")) {            
            EstructuraDeMensajes.mensajeDeAdvertencia(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.CLAVE_USUARIO_VACIA), EstructuraDeMensajes.justify));
            this.vistaUsuario.getTxtClave().requestFocus();
            return false;
        }   
        return true;    
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource().equals(this.vistaUsuario.getBtnBuscar())) {
            if (this.vistaUsuario.getTxtCedula().getText().equalsIgnoreCase("")) {            
                EstructuraDeMensajes.mensajeDeAdvertencia(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.CEDULA_VACIO), EstructuraDeMensajes.justify));
                this.vistaUsuario.getTxtCedula().requestFocus();
            } else {
                this.usuario = ServicioUsuario.encontrar_usuario(new Usuario(null, null, new Persona(Integer.valueOf(this.vistaUsuario.getTxtCedula().getText()), null, null, null), null));
                if (this.usuario != null) {
                    if (this.usuario.getNombre_de_usuario() == null) {
                        this.vistaUsuario.getBtnBuscar().setEnabled(false);
                        this.vistaUsuario.getTxtCedula().setEditable(false);
                        this.vistaUsuario.getTxtNombres().setEditable(false);
                        this.vistaUsuario.getTxtApellidos().setEditable(false);
                        this.vistaUsuario.getTxtNombreUsuario().setEditable(true);
                        this.vistaUsuario.getTxtClave().setEditable(true);
                        this.vistaUsuario.getBtnRegistrar().setEnabled(true);
                        this.vistaUsuario.getTxtNombres().setText(this.usuario.getPersona().getNombres());
                        this.vistaUsuario.getTxtApellidos().setText(this.usuario.getPersona().getApellidos());
                        this.vistaUsuario.getTxtNombreUsuario().requestFocus();
                    } else {
                       EstructuraDeMensajes.mensajeDeAdvertencia(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.USUARIO_EXISTE), EstructuraDeMensajes.justify));
                    }
                } else {
                    this.vistaUsuario.getBtnBuscar().setEnabled(false);
                    this.vistaUsuario.getTxtCedula().setEditable(false);
                    this.vistaUsuario.getTxtNombres().setEditable(true);
                    this.vistaUsuario.getTxtApellidos().setEditable(true);
                    this.vistaUsuario.getTxtNombreUsuario().setEditable(true);
                    this.vistaUsuario.getTxtClave().setEditable(true);
                    this.vistaUsuario.getBtnRegistrar().setEnabled(true);
                    this.vistaUsuario.getTxtNombres().requestFocus();
                }
            }        
        } else if (e.getSource().equals(this.vistaUsuario.getBtnRegistrar())) {
            if (this.validar_campos()){
                this.usuario = new Usuario(
                        this.vistaUsuario.getTxtNombreUsuario().getText(), 
                        String.valueOf(this.vistaUsuario.getTxtClave().getPassword()), 
                        new Persona(
                                Integer.valueOf(this.vistaUsuario.getTxtCedula().getText()), 
                                this.vistaUsuario.getTxtNombres().getText(), 
                                this.vistaUsuario.getTxtApellidos().getText(), 
                                null), 
                        null);
                if (ServicioUsuario.crear_usuario(usuario)) {
                    this.vistaUsuario.dispose();
                }
            }
        } else if (e.getSource().equals(this.vistaUsuario.getBtnModificar())) {
            if (this.validar_campos()){
                this.usuario = new Usuario(
                        this.vistaUsuario.getTxtNombreUsuario().getText(), 
                        String.valueOf(this.vistaUsuario.getTxtClave().getPassword()), 
                        new Persona(
                                Integer.valueOf(this.vistaUsuario.getTxtCedula().getText()), 
                                this.vistaUsuario.getTxtNombres().getText(), 
                                this.vistaUsuario.getTxtApellidos().getText(), 
                                null), 
                        null);
                if (ServicioUsuario.modificar_usuario(this.usuario)) {                
                    this.vistaUsuario.dispose();
                }
            }        
        } else if (e.getSource().equals(this.vistaUsuario.getBtnEliminar())) {
            if (this.validar_campos()){
                this.usuario = new Usuario(
                        this.vistaUsuario.getTxtNombreUsuario().getText(), 
                        String.valueOf(this.vistaUsuario.getTxtClave().getPassword()), 
                        new Persona(
                                Integer.valueOf(this.vistaUsuario.getTxtCedula().getText()), 
                                this.vistaUsuario.getTxtNombres().getText(), 
                                this.vistaUsuario.getTxtApellidos().getText(), 
                                null), 
                        null);
                if (ServicioUsuario.eliminar_usuario(this.usuario)) {                
                    this.vistaUsuario.dispose();
                }
            }        
        } else if (e.getSource().equals(this.vistaUsuario.getBtnCerrar())) {
            this.vistaUsuario.dispose();
        }
        
    }

    @Override
    public void keyTyped(KeyEvent e) { 
        this.caracter = e.getKeyChar();
        if (e.getSource().equals(this.vistaUsuario.getTxtCedula())) {
            if (Metodos.validar_solo_numeros(this.caracter)){
                e.consume();
            } else if (this.vistaUsuario.getTxtCedula().getText().length() == 9) {
                this.vistaUsuario.getToolkit().beep();
                e.consume();                
            }  
        } else if (e.getSource().equals(this.vistaUsuario.getTxtNombres())) {
            if (this.vistaUsuario.getTxtNombres().getText().length() == 50) {
                this.vistaUsuario.getToolkit().beep();
                e.consume();                
            } 
        } else if (e.getSource().equals(this.vistaUsuario.getTxtApellidos())) {
            if (this.vistaUsuario.getTxtApellidos().getText().length() == 50) {
                this.vistaUsuario.getToolkit().beep();
                e.consume();                
            } 
        } else if (e.getSource().equals(this.vistaUsuario.getTxtNombreUsuario())) {
            if (this.vistaUsuario.getTxtNombreUsuario().getText().length() == 20) {
                this.vistaUsuario.getToolkit().beep();
                e.consume();                
            } 
        } else if (e.getSource().equals(this.vistaUsuario.getTxtClave())) {
            if (String.valueOf(this.vistaUsuario.getTxtClave().getPassword()).length() == 20) {
                this.vistaUsuario.getToolkit().beep();
                e.consume();                
            } 
        }
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
