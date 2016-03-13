/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consignacion_de_documentos.controlador;

import com.consignacion_de_documentos.configuracion.EstructuraDeMensajes;
import com.consignacion_de_documentos.configuracion.Mensajes;
import com.consignacion_de_documentos.global.Sesion;
import com.consignacion_de_documentos.modelo.entidad.Usuario;
import com.consignacion_de_documentos.modelo.servicio.ServicioUsuario;
import com.consignacion_de_documentos.vista.VistaInicioDeSesion;
import com.consignacion_de_documentos.vista.VistaMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Ing Richard David
 */
public class ControladorInicioDeSesion implements ActionListener, KeyListener {
    
    private static final Sesion sesion = Sesion.obtener_instancia();
    private final VistaInicioDeSesion vistaInicioDeSesion;
    private VistaMenu vistaMenu;
    private int caracter;
    private static final Mensajes messages = Mensajes.getInstance();
    
    public ControladorInicioDeSesion(VistaInicioDeSesion vistaInicioDeSesion) {
        this.vistaInicioDeSesion = vistaInicioDeSesion;
    }

    private boolean validarCampos() {        
        if (this.vistaInicioDeSesion.getTxtUsuario().getText().trim().equalsIgnoreCase("")) {
            EstructuraDeMensajes.mensajeDeAdvertencia(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.NOMBRE_USUARIO_VACIO), EstructuraDeMensajes.justify));
            this.vistaInicioDeSesion.getTxtUsuario().requestFocus();
            return false;        
        } else if (String.valueOf(this.vistaInicioDeSesion.getTxtClave().getPassword()).equals("")) {
            EstructuraDeMensajes.mensajeDeAdvertencia(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.CLAVE_USUARIO_VACIA), EstructuraDeMensajes.justify));
            this.vistaInicioDeSesion.getTxtClave().requestFocus();
            return false;            
        }
        return true;        
    }
    
    private void desplegarMenu() {        
        this.vistaInicioDeSesion.dispose();
        this.vistaMenu = new VistaMenu();
        this.vistaMenu.setVisible(true);        
    }
    
    private void iniciarSesion() {    
        if (this.validarCampos()) {
            Usuario usuario = ServicioUsuario.validar_usuario(new Usuario(
                    this.vistaInicioDeSesion.getTxtUsuario().getText(),
                    String.valueOf(this.vistaInicioDeSesion.getTxtClave().getPassword()), 
                    null, 
                    null));
            if (usuario != null) {
                sesion.datos_de_sesion(usuario);
                this.desplegarMenu();
            }                
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {        
        if (e.getSource().equals(this.vistaInicioDeSesion.getBtnEntrar())) { 
            this.iniciarSesion();
        }        
    }

    @Override
    public void keyTyped(KeyEvent e) {        
        this.caracter = e.getKeyChar();   
        
        if (e.getSource().equals(this.vistaInicioDeSesion.getTxtUsuario())) {            
            if (this.vistaInicioDeSesion.getTxtUsuario().getText().length() == 20) {                
                this.vistaInicioDeSesion.getToolkit().beep();
                e.consume();                
            } else if (caracter == (char) KeyEvent.VK_ENTER) {
                this.iniciarSesion();
            }                        
        }
        
        if (e.getSource().equals(this.vistaInicioDeSesion.getTxtClave())) {            
            if (String.valueOf(this.vistaInicioDeSesion.getTxtClave().getPassword()).length() == 20) {                
                this.vistaInicioDeSesion.getToolkit().beep();
                e.consume();            
            } else if (caracter == (char) KeyEvent.VK_ENTER) {
                this.iniciarSesion();
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
