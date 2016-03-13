/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consignacion_de_documentos.controlador;

import com.consignacion_de_documentos.configuracion.EstructuraDeMensajes;
import com.consignacion_de_documentos.configuracion.Mensajes;
import com.consignacion_de_documentos.global.Metodos;
import com.consignacion_de_documentos.global.Sesion;
import com.consignacion_de_documentos.modelo.entidad.Aspirante;
import com.consignacion_de_documentos.modelo.entidad.Persona;
import com.consignacion_de_documentos.modelo.servicio.ServicioAspirante;
import com.consignacion_de_documentos.vista.VistaAspirante;
import com.consignacion_de_documentos.vista.VistaAspirantes; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ing Richard David
 */
public class ControladorAspirantes implements ActionListener, KeyListener, WindowListener, MouseListener {

    private static final Sesion sesion = Sesion.obtener_instancia();
    private final VistaAspirantes vistaAspirantes;
    private VistaAspirante vistaAspirante;
    private char caracter;
    private static final Mensajes messages = Mensajes.getInstance();
    
    public ControladorAspirantes(VistaAspirantes vistaAspirantes) {
        this.vistaAspirantes = vistaAspirantes;
    }
    
    private void mostrar_aspirantes() {        
        List<Aspirante> aspirantes = ServicioAspirante.llenar_todo();
        
        if (aspirantes != null) {
            Metodos.remover_filas(this.vistaAspirantes.getTblAspirante());
            for (Aspirante aspirante : aspirantes) {
                Object[] datos = {aspirante.getCodigo(), aspirante.getPersona().getCedula(),aspirante.getPersona().getNombres()};
                ((DefaultTableModel) this.vistaAspirantes.getTblAspirante().getModel()).addRow(datos);
            } 
            this.vistaAspirantes.getTxtBuscar().requestFocus();
        }
        
    }
    
    private void mostrar_aspirantes_filtrados(String filtro) {        
        List<Aspirante> aspirantes = ServicioAspirante.filtrar(new Aspirante(0, new Persona(0, filtro,null, null), null));
        
        if (aspirantes != null) {
            Metodos.remover_filas(this.vistaAspirantes.getTblAspirante());
            for (Aspirante aspirante : aspirantes) {
                Object[] datos = {aspirante.getCodigo(), aspirante.getPersona().getCedula(),aspirante.getPersona().getNombres()};
                ((DefaultTableModel) this.vistaAspirantes.getTblAspirante().getModel()).addRow(datos);
            }
            this.vistaAspirantes.getTxtBuscar().requestFocus();            
        }    
        
    }
     
    private Aspirante encontrar_datos_de_aspirante() {    
        try {
            int fila = this.vistaAspirantes.getTblAspirante().getSelectedRow();
            return ServicioAspirante.buscar_aspirante_persona(new Aspirante(
                    0, 
                    new Persona(
                            Integer.valueOf(this.vistaAspirantes.getTblAspirante().getValueAt(fila, 1).toString()), 
                            null, 
                            null, 
                            null), 
                    null)); 
        } catch (ArrayIndexOutOfBoundsException e) {
            EstructuraDeMensajes.mensajeDeAdvertencia(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.FILA_NO_SELECCIONADA), EstructuraDeMensajes.justify));
            this.vistaAspirantes.getTblAspirante().requestFocus();
        }            
        return null;        
    }
         
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource().equals(this.vistaAspirantes.getBtnNuevo())) {
            this.vistaAspirante = new  VistaAspirante(null, true,"REGISTRAR", null);
            this.vistaAspirante.setVisible(true);
            this.mostrar_aspirantes();
        } else if (e.getSource().equals(this.vistaAspirantes.getBtnModificar())) {
            this.vistaAspirante = new VistaAspirante(null, true, "MODIFICAR", this.encontrar_datos_de_aspirante()); 
            this.vistaAspirante.setVisible(true);
            this.mostrar_aspirantes();
        } if (e.getSource().equals(this.vistaAspirantes.getBtnEliminar())) {
            this.vistaAspirante = new VistaAspirante(null, true,"ELIMINAR", this.encontrar_datos_de_aspirante()); 
            this.vistaAspirante.setVisible(true);
            this.mostrar_aspirantes();
        } if (e.getSource().equals(this.vistaAspirantes.getBtnCerrar())) {
            this.vistaAspirantes.dispose();
        }
        
    }

    @Override
    public void keyTyped(KeyEvent e) {        
        this.caracter = e.getKeyChar();   
        
        if (e.getSource().equals(this.vistaAspirantes.getTxtBuscar())) {            
            if (this.vistaAspirantes.getTxtBuscar().getText().length() == 100) {                
                this.vistaAspirantes.getToolkit().beep();
                e.consume();                
            }                        
        }
                
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource().equals(this.vistaAspirantes.getTxtBuscar())){
            this.mostrar_aspirantes_filtrados(this.vistaAspirantes.getTxtBuscar().getText());
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        this.mostrar_aspirantes();
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(this.vistaAspirantes.getTblAspirante())) {
            if (e.getClickCount() == 2) {
                this.vistaAspirantes.setAspirante(this.encontrar_datos_de_aspirante());
                this.vistaAspirantes.dispose();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
