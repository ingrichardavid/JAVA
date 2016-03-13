/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consignacion_de_documentos.modelo.implementacion;
  
import com.consignacion_de_documentos.configuracion.Mensajes;
import com.consignacion_de_documentos.modelo.conexion.BaseDeDatosConexion;   
import com.consignacion_de_documentos.modelo.consulta.AspiranteConsulta;
import com.consignacion_de_documentos.modelo.consulta.PersonaConsulta;
import com.consignacion_de_documentos.modelo.dao.AspiranteDAO; 
import com.consignacion_de_documentos.modelo.entidad.Aspirante;
import com.consignacion_de_documentos.modelo.entidad.Persona; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
public class AspiranteImplDAO implements AspiranteDAO {

    //Objetos, variables y constantes de la clase.
    private static final BaseDeDatosConexion conexion = BaseDeDatosConexion.getInstance();
    private static final Mensajes messages = Mensajes.getInstance();
    private static AspiranteImplDAO instance;
    private PreparedStatement preparedStatement;
    private ResultSet result; 
    private Aspirante aspiranteEncontrado;
    private List<Aspirante> aspirantesEncontrados;
 
    private AspiranteImplDAO() {
    } 
 
    public static synchronized AspiranteImplDAO getInstance() {
        if (instance == null) {
            instance = new AspiranteImplDAO();
        }
        return instance;
    } 
 
    public void closeConnection() {
        instance = null;
    } 

    @Override
    public boolean insertar_aspirante(Aspirante aspirante) {
        try {
            preparedStatement = conexion.getConexion().prepareStatement(AspiranteConsulta.INSERTAR);
            preparedStatement.setInt(1, aspirante.getPersona().getCedula());
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
    public boolean insertar_persona_aspirante(Aspirante aspirante) {
        try {
            preparedStatement = conexion.getConexion().prepareStatement(PersonaConsulta.INSERTAR);
            preparedStatement.setInt(1, aspirante.getPersona().getCedula());
            preparedStatement.setString(2, aspirante.getPersona().getNombres());
            preparedStatement.setString(3, aspirante.getPersona().getApellidos()); 
            if (preparedStatement.executeUpdate() > 0) {
                preparedStatement.close();
                preparedStatement = conexion.getConexion().prepareStatement(AspiranteConsulta.INSERTAR);
                preparedStatement.setInt(1, aspirante.getPersona().getCedula());
                if (preparedStatement.executeUpdate() > 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean eliminar(Aspirante aspirante) {
        try {
            preparedStatement = conexion.getConexion().prepareStatement(AspiranteConsulta.ELIMINAR);  
            preparedStatement.setInt(1, aspirante.getPersona().getCedula());
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
    public Aspirante buscar_persona(Aspirante aspirante) {
        aspiranteEncontrado = new Aspirante();
        try {
            preparedStatement = conexion.getConexion().prepareStatement(AspiranteConsulta.BUSCAR_PERSONA); 
            preparedStatement.setInt(1, aspirante.getPersona().getCedula());
            result = preparedStatement.executeQuery(); 
            while (result.next()) {
                aspiranteEncontrado  = new Aspirante(
                        0,
                        new Persona(result.getInt(1),
                                result.getString(2),
                                result.getString(3),
                                null),
                        null); 
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return aspiranteEncontrado;
    }
    
    @Override
    public Aspirante buscar_aspirante_persona(Aspirante aspirante) {
        aspiranteEncontrado = new Aspirante();
        try {
            preparedStatement = conexion.getConexion().prepareStatement(AspiranteConsulta.BUSCAR_ASPIRANTE_PERSONA); 
            preparedStatement.setInt(1, aspirante.getPersona().getCedula());
            result = preparedStatement.executeQuery(); 
            while (result.next()) {
                aspiranteEncontrado  = new Aspirante(
                        result.getInt(4),
                        new Persona(result.getInt(1),
                                result.getString(2),
                                result.getString(3),
                                null),
                        result.getDate(5)); 
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return aspiranteEncontrado;
    }

    @Override
    public List<Aspirante> filtrar(Aspirante aspirante) {
         aspirantesEncontrados = new ArrayList<>();
        try {
            preparedStatement = conexion.getConexion().prepareStatement(AspiranteConsulta.FILTRAR); 
            preparedStatement.setString(1, "%"+aspirante.getPersona().getNombres()+"%");
            result = preparedStatement.executeQuery();
            while (result.next()) {
                aspirantesEncontrados.add(new Aspirante(
                                                result.getInt(1),
                        new Persona(result.getInt(2),
                                result.getString(3),
                                null,
                                null),
                        result.getDate(4))); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return aspirantesEncontrados;
    }

    @Override
    public List<Aspirante> llenar_todo() {
        aspirantesEncontrados = new ArrayList<>();
        try {
            preparedStatement = conexion.getConexion().prepareStatement(AspiranteConsulta.LLENAR_TODO);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                aspirantesEncontrados.add(new Aspirante(
                                                result.getInt(1),
                        new Persona(result.getInt(2),
                                result.getString(3),
                                null,
                                null),
                        result.getDate(4))); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return aspirantesEncontrados;
    }

    @Override
    public int validar_existencia(Aspirante aspirante) {
        try {
            preparedStatement = conexion.getConexion().prepareStatement(AspiranteConsulta.VALIDAR_EXISTENCIA); 
            preparedStatement.setInt(1, aspirante.getPersona().getCedula());
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
    public int validar_existencia_dos(Aspirante aspirante) {
        try {
            preparedStatement = conexion.getConexion().prepareStatement(AspiranteConsulta.VALIDAR_EXISTENCIA_DOS); 
            preparedStatement.setInt(1, aspirante.getPersona().getCedula());
            preparedStatement.setInt(2, aspirante.getPersona().getCedula());
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
