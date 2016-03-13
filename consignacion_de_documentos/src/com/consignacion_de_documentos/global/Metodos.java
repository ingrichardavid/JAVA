/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consignacion_de_documentos.global;

import java.awt.event.KeyEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ing Richard David
 */
public class Metodos {
    
    public static final void remover_filas(JTable table) {
           while (((DefaultTableModel) table.getModel()).getRowCount() > 0) {
               ((DefaultTableModel) table.getModel()).removeRow(0);
           }      
    }
    
    public static boolean validar_solo_numeros(char character){        
        return (character < '0' || character > '9') && (character != (char) KeyEvent.VK_BACK_SPACE) && (character != (char) KeyEvent.VK_ENTER);                
    }
    
}
