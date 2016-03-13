/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consignacion_de_documentos.controlador;

import com.consignacion_de_documentos.configuracion.EstructuraDeMensajes;
import com.consignacion_de_documentos.configuracion.Mensajes;
import com.consignacion_de_documentos.global.Metodos;
import com.consignacion_de_documentos.modelo.entidad.Aspirante;
import com.consignacion_de_documentos.modelo.entidad.Persona;
import com.consignacion_de_documentos.modelo.servicio.ServicioAspirante;
import com.consignacion_de_documentos.vista.VistaAspirante;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author RichardJosé
 */
public class ControladorAspirante implements ActionListener, KeyListener {
    
    private final VistaAspirante vistaAspirante;
    private Aspirante aspirante;
    private char caracter;
    private static final Mensajes messages = Mensajes.getInstance();

    public ControladorAspirante(VistaAspirante vistaAspirante) {
        this.vistaAspirante = vistaAspirante;
    }
    
    private boolean validar_campos(){
        if (this.vistaAspirante.getTxtCedula().getText().trim().equalsIgnoreCase("")) {            
            EstructuraDeMensajes.mensajeDeAdvertencia(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.CEDULA_VACIO), EstructuraDeMensajes.justify));
            this.vistaAspirante.getTxtCedula().requestFocus();
            return false;
        } else if (this.vistaAspirante.getTxtNombres().getText().trim().equalsIgnoreCase("")) {            
            EstructuraDeMensajes.mensajeDeAdvertencia(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.NOMBRE_VACIO), EstructuraDeMensajes.justify));
            this.vistaAspirante.getTxtNombres().requestFocus();
            return false;
        } else if (this.vistaAspirante.getTxtApellidos().getText().trim().equalsIgnoreCase("")) {   
             EstructuraDeMensajes.mensajeDeAdvertencia(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.APELLIDO_VACIO), EstructuraDeMensajes.justify));
             this.vistaAspirante.getTxtApellidos().requestFocus();
            return false;
        } 
        return true;    
    }
        
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource().equals(this.vistaAspirante.getBtnBuscar())) {
            if (this.vistaAspirante.getTxtCedula().getText().equalsIgnoreCase("")) {            
                EstructuraDeMensajes.mensajeDeInformacion(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.CEDULA_VACIO), EstructuraDeMensajes.justify));
                this.vistaAspirante.getTxtCedula().requestFocus();
            } else {
                this.aspirante = ServicioAspirante.encontrar_aspirante(new Aspirante(0, new Persona(Integer.valueOf(this.vistaAspirante.getTxtCedula().getText()), null, null, null), null));
                if (this.aspirante != null) {
                    if (this.aspirante.getFecha_registro() == null) {
                        this.vistaAspirante.getBtnBuscar().setEnabled(false);
                        this.vistaAspirante.getTxtCedula().setEditable(false);
                        this.vistaAspirante.getTxtNombres().setEditable(false);
                        this.vistaAspirante.getTxtApellidos().setEditable(false);
                        this.vistaAspirante.getBtnRegistrar().setEnabled(true);
                        this.vistaAspirante.getTxtNombres().setText(this.aspirante.getPersona().getNombres());
                        this.vistaAspirante.getTxtApellidos().setText(this.aspirante.getPersona().getApellidos());
                    } else {
                        EstructuraDeMensajes.mensajeDeAdvertencia(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.ASPIRANTE_EXISTE), EstructuraDeMensajes.justify));
                    }
                } else {
                    this.vistaAspirante.getBtnBuscar().setEnabled(false);
                    this.vistaAspirante.getTxtCedula().setEditable(false);
                    this.vistaAspirante.getTxtNombres().setEditable(true);
                    this.vistaAspirante.getTxtApellidos().setEditable(true);
                    this.vistaAspirante.getBtnRegistrar().setEnabled(true);
                }
            }        
        } else if (e.getSource().equals(this.vistaAspirante.getBtnRegistrar())) {
            if (this.validar_campos()){
                this.aspirante = new Aspirante(
                        0, 
                        new Persona(
                                Integer.valueOf(this.vistaAspirante.getTxtCedula().getText()), 
                                this.vistaAspirante.getTxtNombres().getText(), 
                                this.vistaAspirante.getTxtApellidos().getText(), 
                                null), 
                        null);
                if (ServicioAspirante.crear_aspirante(aspirante)) {
                   EstructuraDeMensajes.mensajeDeInformacion(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.ASPIRANTE_REGISTRADO), EstructuraDeMensajes.justify));
                    this.vistaAspirante.dispose();
                }
            }
        } else if (e.getSource().equals(this.vistaAspirante.getBtnModificar())) {
            if (this.validar_campos()){
                this.aspirante = new Aspirante(
                        0, 
                        new Persona(
                                Integer.valueOf(
                                        this.vistaAspirante.getTxtCedula().getText()), 
                                this.vistaAspirante.getTxtNombres().getText(), 
                                this.vistaAspirante.getTxtApellidos().getText(), 
                                null), 
                        null);
                if (ServicioAspirante.modificar_aspirante(this.aspirante)) {
                    EstructuraDeMensajes.mensajeDeInformacion(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.ASPIRANTE_MODIFICADO), EstructuraDeMensajes.justify));
                    this.vistaAspirante.dispose();
                }
            }        
        } else if (e.getSource().equals(this.vistaAspirante.getBtnEliminar())) {
            if (this.validar_campos()){
                this.aspirante = new Aspirante(
                        0, 
                        new Persona(
                                Integer.valueOf(
                                        this.vistaAspirante.getTxtCedula().getText()), 
                                null, 
                                null, 
                                null), 
                        null);
                if (ServicioAspirante.eliminar_aspirante(this.aspirante)) {                
                    this.vistaAspirante.dispose();
                }
            }        
        } else if (e.getSource().equals(this.vistaAspirante.getBtnCerrar())) {
            this.vistaAspirante.dispose();
        }
        
    }

    @Override
    public void keyTyped(KeyEvent e) { 
        this.caracter = e.getKeyChar();
        if (e.getSource().equals(this.vistaAspirante.getTxtCedula())) {
            if (Metodos.validar_solo_numeros(this.caracter)){
                e.consume();
            } else if (this.vistaAspirante.getTxtCedula().getText().length() == 9) {
                this.vistaAspirante.getToolkit().beep();
                e.consume();                
            }  
        } else if (e.getSource().equals(this.vistaAspirante.getTxtNombres())) {
            if (this.vistaAspirante.getTxtNombres().getText().length() == 50) {
                this.vistaAspirante.getToolkit().beep();
                e.consume();                
            } 
        } else if (e.getSource().equals(this.vistaAspirante.getTxtApellidos())) {
            if (this.vistaAspirante.getTxtApellidos().getText().length() == 50) {
                this.vistaAspirante.getToolkit().beep();
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
