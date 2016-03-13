/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consignacion_de_documentos.configuracion;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class EstructuraDeMensajes {
    
    private static int response = 0;
    public static final String left = "left";    
    public static final String right = "right";
    public static final String center = "center";
    public static final String justify = "justify";
    
    public static String formato(int width, String message, String alignment) {
        return "<html><body><div width='" + width + "px' align='" + alignment + "'>" + message + "</div></body></html>";
    }

    public static void mensajeDeInformacion(String message) {
        JOptionPane.showMessageDialog(new JDialog(), message, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void mensajeDeAdvertencia(String message) {
        JOptionPane.showMessageDialog(new JDialog(), message, "Advertencia", JOptionPane.WARNING_MESSAGE);
    }

    public static int mensajeDeConfirmacion(String message) {
        return response = JOptionPane.showConfirmDialog(new JDialog(), message, "Confirmación", JOptionPane.YES_NO_OPTION);
    }

    public static void mensajeDeError(String message) {
        JOptionPane.showMessageDialog(new JDialog(), message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
}
