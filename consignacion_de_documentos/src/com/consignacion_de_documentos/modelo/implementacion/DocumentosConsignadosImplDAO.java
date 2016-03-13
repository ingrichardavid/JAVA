/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consignacion_de_documentos.modelo.implementacion;
  
import com.consignacion_de_documentos.configuracion.Mensajes;
import com.consignacion_de_documentos.modelo.conexion.BaseDeDatosConexion;   
import com.consignacion_de_documentos.modelo.consulta.DocumentosConsignadosConsulta;
import com.consignacion_de_documentos.modelo.dao.DocumentosConsignadosDAO;
import com.consignacion_de_documentos.modelo.entidad.Aspirante;
import com.consignacion_de_documentos.modelo.entidad.Documento;
import com.consignacion_de_documentos.modelo.entidad.DocumentosConsignados;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class DocumentosConsignadosImplDAO implements DocumentosConsignadosDAO {

    //Objetos, variables y constantes de la clase.
    private static final BaseDeDatosConexion conexion = BaseDeDatosConexion.getInstance();
    private static final Mensajes messages = Mensajes.getInstance();
    private static DocumentosConsignadosImplDAO instance;
    private PreparedStatement preparedStatement;
    private ResultSet result; 
    private List<DocumentosConsignados> documentosConsignadosEncontrados;
 
    private DocumentosConsignadosImplDAO() {
    } 
 
    public static synchronized DocumentosConsignadosImplDAO getInstance() {
        if (instance == null) {
            instance = new DocumentosConsignadosImplDAO();
        }
        return instance;
    } 
 
    public void closeConnection() {
        instance = null;
    } 
 
    @Override
    public List<DocumentosConsignados> encontrar_documentos_consignados(Aspirante aspirante) {
        this.documentosConsignadosEncontrados = new ArrayList<>();
        try {
            preparedStatement = conexion.getConexion().prepareStatement(DocumentosConsignadosConsulta.ENCONTRAR_DOCUMENTOS_CONSIGNADOS);
            preparedStatement.setInt(1, aspirante.getPersona().getCedula());
            result = preparedStatement.executeQuery();
            while (result.next()) {
                this.documentosConsignadosEncontrados.add(new DocumentosConsignados(result.getInt(1), new Documento(result.getInt(2), result.getString(3)), result.getBoolean(4), result.getBoolean(5), result.getString(6))); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return this.documentosConsignadosEncontrados;    
    }

    @Override
    public boolean modificar_documentos_consignados(List<DocumentosConsignados> documentosConsignados) {
        boolean indicador = true;
        try {
            conexion.getConexion().setAutoCommit(false);
            int i = 0;
            while (i < documentosConsignados.size() && indicador == true) {  
                preparedStatement = conexion.getConexion().prepareStatement(DocumentosConsignadosConsulta.MODIFICAR_DOCUMENTO_CONSIGNADO); 
                preparedStatement.setBoolean(1, documentosConsignados.get(i).isEstatus());
                preparedStatement.setBoolean(2, documentosConsignados.get(i).isDigitalizado());
                preparedStatement.setString(3, documentosConsignados.get(i).getObservaciones());
                preparedStatement.setInt(4, documentosConsignados.get(i).getCodigo());
                indicador = preparedStatement.executeUpdate() > 0; 
                i++;
            }
                        
            if (indicador) {
                conexion.getConexion().commit();
            } else {
                conexion.getConexion().rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conexion.getConexion().setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(DocumentosConsignadosImplDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            conexion.cerrarConexion();
        }
        
        return indicador;
        
    }
    
}
