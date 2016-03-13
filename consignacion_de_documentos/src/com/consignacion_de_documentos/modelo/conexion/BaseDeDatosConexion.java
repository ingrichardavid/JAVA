/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consignacion_de_documentos.modelo.conexion;

import com.consignacion_de_documentos.configuracion.Mensajes;
import com.consignacion_de_documentos.configuracion.EstructuraDeMensajes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author RichardJosé
 */
public class BaseDeDatosConexion {
    
    public static BaseDeDatosConexion instancia;
    private static final Mensajes messages = Mensajes.getInstance();
    private Connection conexion;
    
    private BaseDeDatosConexion() {
        try {
            Class.forName("org.postgresql.Driver");
            try {
                conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/consignacion_de_documentos","postgres","");
            } catch (SQLException ex) {    
                String message = "<html>" + "                                 <body>"
                    + "                                     <div width='290px' align='justify'>"
                    + "                                         <h3 align='center'>"
                    + "                                             <strong><u>ERROR DE CONEXIÓN</u></strong>"
                    + "                                         </h3><br>"
                    + "                                         <p>No se puede iniciar el Sistema debido alguna de las siguientes causas:</p>"
                    + "                                          <ul>"
                    + "                                             <li>El Sistema Manejador de Base de Datos PostgreSQL(9.4) no se encuentra instalado o en ejecución.</li>"
                    + "                                             <li>El usuario postgres ha sido eliminado.</li>"
                    + "                                             <li>La clave del usuario postgres ha sido modificada.</li>"
                    + "                                             <li>La Base de Datos ha sido eliminada.</li>"
                    + "                                         </ul>"
                    + "                                         " + "                                     </div>"
                    + "                                 </body>" + "                             </html>";
                if (ex.getSQLState().equalsIgnoreCase("3D000")) {
                    
                } else {
                    EstructuraDeMensajes.mensajeDeError(EstructuraDeMensajes.formato(290, message, EstructuraDeMensajes.justify));
                }
                System.exit(0);
            }
        } catch (ClassNotFoundException ex) {
            String message = "<html>" + "                                 <body>"
                + "                                     <div width='290px' align='justify'>"
                + "                                         <h3 align='center'>"
                + "                                             <strong><u>ERROR DE CONEXIÓN</u></strong>"
                + "                                         </h3><br>"
                + "                                         <p>No se puede iniciar el Sistema debido alguna de las siguientes causas:</p>"
                + "                                          <ul>"
                + "                                             <li>La versión del Manejador de Base de datos PostgreSQL no es la 9.4.</li>"
                + "                                             <li>El conector JDBC ha sido eliminado.</li>"
                + "                                         </ul>"
                + "                                         " + "                                     </div>"
                + "                                 </body>" + "                             </html>";
            EstructuraDeMensajes.mensajeDeError(EstructuraDeMensajes.formato(290, message, EstructuraDeMensajes.justify));
            System.exit(0);
        }
    } 
     
    public synchronized static BaseDeDatosConexion getInstance() {        
        if (instancia == null) {
            instancia = new BaseDeDatosConexion();
        }
        return instancia;        
    } 
 
    public Connection getConexion() {
        return conexion;
    } 
 
    public void cerrarConexion() {
        instancia = null;
    }
    
}
