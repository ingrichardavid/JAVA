/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consignacion_de_documentos.controlador;

import com.consignacion_de_documentos.configuracion.EstructuraDeMensajes;
import com.consignacion_de_documentos.global.Metodos;
import com.consignacion_de_documentos.global.Sesion;
import com.consignacion_de_documentos.modelo.entidad.Persona;
import com.consignacion_de_documentos.modelo.entidad.Usuario;
import com.consignacion_de_documentos.modelo.servicio.ServicioUsuario;
import com.consignacion_de_documentos.vista.VistaUsuario;
import com.consignacion_de_documentos.vista.VistaUsuarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ing Richard David
 */
public class ControladorUsuarios implements ActionListener, KeyListener, WindowListener {

    private static final Sesion sesion = Sesion.obtener_instancia();
    private final VistaUsuarios vistaUsuarios;
    private VistaUsuario vistaUsuario;
    private char caracter;
    
    public ControladorUsuarios(VistaUsuarios vistaUsuarios) {
        this.vistaUsuarios = vistaUsuarios;
    }
    
    private void mostrar_usuarios() {        
        List<Usuario> usuarios = ServicioUsuario.llenar_todo();
        
        if (usuarios != null) {
            Metodos.remover_filas(this.vistaUsuarios.getTblUsuario());
            for (Usuario usuario : usuarios) {
                Object[] datos = {usuario.getPersona().getCedula(),usuario.getPersona().getNombres()};
                ((DefaultTableModel) this.vistaUsuarios.getTblUsuario().getModel()).addRow(datos);
            } 
            this.vistaUsuarios.getTxtBuscar().requestFocus();
        }
        
    }
    
    private void mostrar_usuarios_filtrados(String filtro) {        
        List<Usuario> usuarios = ServicioUsuario.filtrar(new Usuario(filtro, null, null, null));
        
        if (usuarios != null) {
            Metodos.remover_filas(this.vistaUsuarios.getTblUsuario());
            for (Usuario usuario : usuarios) {
                Object[] datos = {usuario.getPersona().getCedula(),usuario.getPersona().getNombres()};
                ((DefaultTableModel) this.vistaUsuarios.getTblUsuario().getModel()).addRow(datos);
            }
            this.vistaUsuarios.getTxtBuscar().requestFocus();            
        }    
        
    }
    
    private Usuario encontrar_datos_de_usuario() {    
        try {
            int fila = this.vistaUsuarios.getTblUsuario().getSelectedRow();
            return ServicioUsuario.buscar_usuario_persona(new Usuario(null, null, new Persona(Integer.valueOf(this.vistaUsuarios.getTblUsuario().getValueAt(fila, 0).toString()), null, null, null), null));
        } catch (ArrayIndexOutOfBoundsException e) {
            EstructuraDeMensajes.mensajeDeAdvertencia(EstructuraDeMensajes.formato(200, "Seleccione un registro.", EstructuraDeMensajes.justify));
            this.vistaUsuarios.getTblUsuario().requestFocus();
        }            
        return null;        
    }
         
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource().equals(this.vistaUsuarios.getBtnNuevo())) {
            this.vistaUsuario = new VistaUsuario(null, true, "REGISTRAR", null);
            this.vistaUsuario.setVisible(true);
            this.mostrar_usuarios();
        } else if (e.getSource().equals(this.vistaUsuarios.getBtnModificar())) {
            this.vistaUsuario = new VistaUsuario(null, true, "MODIFICAR", this.encontrar_datos_de_usuario());
            this.vistaUsuario.setVisible(true);
            this.mostrar_usuarios();
        } if (e.getSource().equals(this.vistaUsuarios.getBtnEliminar())) {
            this.vistaUsuario = new VistaUsuario(null, true, "ELIMINAR", this.encontrar_datos_de_usuario());
            this.vistaUsuario.setVisible(true);
            this.mostrar_usuarios();
        } if (e.getSource().equals(this.vistaUsuarios.getBtnCerrar())) {
            this.vistaUsuarios.dispose();
        }
        
    }

    @Override
    public void keyTyped(KeyEvent e) {        
        this.caracter = e.getKeyChar();   
        
        if (e.getSource().equals(this.vistaUsuarios.getTxtBuscar())) {            
            if (this.vistaUsuarios.getTxtBuscar().getText().length() == 100) {                
                this.vistaUsuarios.getToolkit().beep();
                e.consume();                
            }                        
        }
                
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource().equals(this.vistaUsuarios.getTxtBuscar())){
            this.mostrar_usuarios_filtrados(this.vistaUsuarios.getTxtBuscar().getText());
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        this.mostrar_usuarios();
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
    
}
