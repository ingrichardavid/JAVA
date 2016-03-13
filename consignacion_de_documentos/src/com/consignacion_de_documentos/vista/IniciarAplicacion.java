/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consignacion_de_documentos.vista;

/**
 *
 * @author Ing Richard David
 */
public class IniciarAplicacion {

    private static VistaInicioDeSesion vistaInicioDeSesion;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here        
        vistaInicioDeSesion = new VistaInicioDeSesion();
        vistaInicioDeSesion.setVisible(true);
        
    }
    
}
