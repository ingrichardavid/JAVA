/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consignacion_de_documentos.modelo.implementacion;
  
import com.consignacion_de_documentos.configuracion.Mensajes;
import com.consignacion_de_documentos.modelo.conexion.BaseDeDatosConexion;
import com.consignacion_de_documentos.modelo.consulta.PersonaConsulta;
import com.consignacion_de_documentos.modelo.dao.PersonaDAO;
import com.consignacion_de_documentos.modelo.entidad.Persona;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
public class PersonaImplDAO implements PersonaDAO {

    //Objetos, variables y constantes de la clase.
    private static final BaseDeDatosConexion conexion = BaseDeDatosConexion.getInstance();
    private static final Mensajes messages = Mensajes.getInstance();
    private static PersonaImplDAO instance;
    private PreparedStatement preparedStatement;
    private ResultSet result; 
 
    private PersonaImplDAO() {
    } 
 
    public static synchronized PersonaImplDAO getInstance() {
        if (instance == null) {
            instance = new PersonaImplDAO();
        }
        return instance;
    } 
 
    public void closeConnection() {
        instance = null;
    } 

    @Override
    public boolean insertar(Persona persona) {
        try {
            preparedStatement = conexion.getConexion().prepareStatement(PersonaConsulta.INSERTAR);
            preparedStatement.setInt(1, persona.getCedula());
            preparedStatement.setString(2, persona.getNombres());
            preparedStatement.setString(3, persona.getApellidos()); 
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean modificar(Persona persona) {
        try {
            preparedStatement = conexion.getConexion().prepareStatement(PersonaConsulta.MODIFICAR); 
            preparedStatement.setString(1, persona.getNombres());
            preparedStatement.setString(2, persona.getApellidos());
            preparedStatement.setInt(3, persona.getCedula());
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean eliminar(Persona persona) {
        try {
            preparedStatement = conexion.getConexion().prepareStatement(PersonaConsulta.ELIMINAR);  
            preparedStatement.setInt(1, persona.getCedula());
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conexion.cerrarConexion();
        }
        return false;
    }

    @Override
    public int validar_existencia(Persona persona) {
        try {
            preparedStatement = conexion.getConexion().prepareStatement(PersonaConsulta.VALIDAR_EXISTENCIA); 
            preparedStatement.setInt(1, persona.getCedula());
            result = preparedStatement.executeQuery();
            while (result.next()) {
                return result.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return 0;
    }

    @Override
    public int validar_existencia_dos(Persona persona) {
    try {
            preparedStatement = conexion.getConexion().prepareStatement(PersonaConsulta.VALIDAR_EXISTENCIA_DOS); 
            preparedStatement.setInt(1, persona.getCedula());
            preparedStatement.setInt(2, persona.getCedula());
            result = preparedStatement.executeQuery();
            while (result.next()) {
                return result.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return 0;
    }
    
}
