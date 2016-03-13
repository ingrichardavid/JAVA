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
import com.consignacion_de_documentos.modelo.entidad.Documento;
import com.consignacion_de_documentos.modelo.entidad.DocumentosConsignados;
import com.consignacion_de_documentos.modelo.entidad.Persona;
import com.consignacion_de_documentos.modelo.implementacion.AspiranteImplDAO;
import com.consignacion_de_documentos.modelo.implementacion.ReporteImplDAO;
import com.consignacion_de_documentos.modelo.servicio.ServicioAspirante;
import com.consignacion_de_documentos.modelo.servicio.ServicioDocumentosConsignados;
import com.consignacion_de_documentos.vista.VistaAspirantes;
import com.consignacion_de_documentos.vista.VistaMenu;
import com.consignacion_de_documentos.vista.VistaUsuarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ing Richard David
 */
public class ControladorMenu implements ActionListener , WindowListener{

    private final VistaMenu vistaMenu;
    private VistaUsuarios vistaUsuarios;
    private VistaAspirantes vistaAspirantes;
    private static final Mensajes messages = Mensajes.getInstance();
    private static final ReporteImplDAO reporteImplDAO = ReporteImplDAO.GetInstance();
    private static final AspiranteImplDAO aspiranteImplDAO = AspiranteImplDAO.getInstance();
    
    public ControladorMenu(VistaMenu vistaMenu) {
        this.vistaMenu = vistaMenu;
    }
    
    private void consultar_documentos_consignados(Aspirante aspirante) {
        
        Metodos.remover_filas(this.vistaMenu.getTblDocumentosConsignados());
        List<DocumentosConsignados> documentosConsignados = ServicioDocumentosConsignados.encontrar_documentos_consignados(aspirante);
        
        if (documentosConsignados != null) {
            for (DocumentosConsignados documentosConsignado : documentosConsignados) {                
                Object[] datos = {
                    documentosConsignado.getCodigo(),
                    documentosConsignado.getDocumento().getCodigo(),
                    documentosConsignado.getDocumento().getNombre(),
                    documentosConsignado.isEstatus(),
                    documentosConsignado.isDigitalizado(),
                    documentosConsignado.getObservaciones()
                };                
                ((DefaultTableModel) this.vistaMenu.getTblDocumentosConsignados().getModel()).addRow(datos);
            }
        }
        
    }
    
    private void modificar_documentos_consignados() { 
        List<DocumentosConsignados> documentosConsignados = new ArrayList<>();
        int numero_de_filas = this.vistaMenu.getTblDocumentosConsignados().getRowCount();
        boolean status = true; 
        int codigo = 0;
        for (int i = 0; i < numero_de_filas; i++) { 
            if (String.valueOf(this.vistaMenu.getTblDocumentosConsignados().getValueAt(i, 5)).length() > 100){
                codigo = i + 1; 
                EstructuraDeMensajes.mensajeDeAdvertencia(EstructuraDeMensajes.formato(200,"El campo observaciones en la fila "+codigo+" sobrepasa los 100 carácteres.", EstructuraDeMensajes.justify)); 
                status = false; 
            }else {
                String observaciones = "";
                try {
                    if(this.vistaMenu.getTblDocumentosConsignados().getValueAt(i, 5).toString().equals("")){
                        observaciones = "";
                    }else{
                        observaciones = this.vistaMenu.getTblDocumentosConsignados().getValueAt(i, 5).toString();
                    }
                } catch (NullPointerException e) {
                }
                documentosConsignados.add(new DocumentosConsignados(
                  Integer.valueOf(String.valueOf(this.vistaMenu.getTblDocumentosConsignados().getValueAt(i, 0))), 
                  new Documento(
                          Integer.valueOf(String.valueOf(this.vistaMenu.getTblDocumentosConsignados().getValueAt(i, 1))), 
                          String.valueOf(this.vistaMenu.getTblDocumentosConsignados().getValueAt(i, 2))), 
                  Boolean.valueOf(String.valueOf(this.vistaMenu.getTblDocumentosConsignados().getValueAt(i, 3))), 
                  Boolean.valueOf(String.valueOf(this.vistaMenu.getTblDocumentosConsignados().getValueAt(i, 4))), 
                  observaciones));
            }  
        }
        if (status == true) {
            if (ServicioDocumentosConsignados.modificar_documentos_consignados(documentosConsignados)) {
                EstructuraDeMensajes.mensajeDeInformacion(EstructuraDeMensajes.formato(200, messages.getProperty(Mensajes.DOCUMENTOS_MODIFICADOS), EstructuraDeMensajes.justify));
                this.consultar_documentos_consignados(this.vistaMenu.getAspirante());
            }
        }
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource().equals(this.vistaMenu.getItemArchivoUsuarios())) {
            this.vistaUsuarios = new VistaUsuarios(vistaMenu, true);
            this.vistaUsuarios.setVisible(true);
        } else if (e.getSource().equals(vistaMenu.getBtnBuscar())){
            this.vistaAspirantes = new VistaAspirantes(null, true); 
            this.vistaAspirantes.setVisible(true);
            if (this.vistaAspirantes.getAspirante() != null) {
                this.consultar_documentos_consignados(this.vistaAspirantes.getAspirante());
                this.vistaMenu.setAspirante(this.vistaAspirantes.getAspirante());
                this.vistaMenu.datos_aspirante(this.vistaAspirantes.getAspirante());
                this.vistaMenu.getBtnGuardarCambios().setEnabled(true);
            }else{  
                if(vistaMenu.getLblCedula().getText() != ""){
                Aspirante aspirante = ServicioAspirante.buscar_aspirante_persona(new Aspirante(0, new Persona(Integer.valueOf(this.vistaMenu.getLblCedula().getText()), null, null, null), null));
                if (aspirante != null) { 
                    if (aspirante.getFecha_registro() == null) {
                        vistaMenu.getLblCedula().setText("");
                        vistaMenu.getLblNombres().setText("");
                        vistaMenu.getLblApellidos().setText("");
                        vistaMenu.getLblCodigo().setText(""); 
                        vistaMenu.getBtnGuardarCambios().setEnabled(false);
                        Metodos.remover_filas(vistaMenu.getTblDocumentosConsignados());
                        vistaMenu.setAspirante(new Aspirante(0, new Persona(0, null, null, null), null));
                        
                    }else{
                        vistaMenu.getLblNombres().setText(aspirante.getPersona().getNombres());
                        vistaMenu.getLblApellidos().setText(aspirante.getPersona().getApellidos());
                    }                   
                }  
                }
            
            }
        } else if (e.getSource().equals(vistaMenu.getBtnGuardarCambios())){ 
             int cedula = aspiranteImplDAO.validar_existencia(new Aspirante(0, new Persona(Integer.valueOf(vistaMenu.getLblCedula().getText()), null, null, null), null));
             if(cedula > 0){ 
                  this.modificar_documentos_consignados();
             }
        } else if(e.getSource().equals(vistaMenu.getBtnReporte())){
             if(vistaMenu.getLblCedula().getText() != ""){
                reporteImplDAO.report(new Aspirante(0, new Persona(Integer.valueOf(vistaMenu.getLblCedula().getText()), null, null, null), null));
             }
             
        } else if(e.getSource().equals(vistaMenu.getItemSalir())){
             if(EstructuraDeMensajes.mensajeDeConfirmacion(EstructuraDeMensajes.formato(200, "¿Desea salir del sistema?", EstructuraDeMensajes.justify)) == 0){
                System.exit(0);
             }
             
        }
        
    }    

    @Override
    public void windowOpened(WindowEvent e) {
     }

    @Override
    public void windowClosing(WindowEvent e) {
       if (EstructuraDeMensajes.mensajeDeConfirmacion(EstructuraDeMensajes.formato(200, "¿Desea salir del sistema?", EstructuraDeMensajes.justify)) == 0){ 
            System.exit(0);
        } else {
            vistaMenu.setDefaultCloseOperation(0);
        }  
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