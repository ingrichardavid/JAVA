/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consignacion_de_documentos.modelo.implementacion;
  
import com.consignacion_de_documentos.configuracion.Mensajes;
import com.consignacion_de_documentos.modelo.conexion.BaseDeDatosConexion;   
import com.consignacion_de_documentos.modelo.consulta.PersonaConsulta;
import com.consignacion_de_documentos.modelo.consulta.UsuarioConsulta;
import com.consignacion_de_documentos.modelo.dao.UsuarioDAO;
import com.consignacion_de_documentos.modelo.entidad.Persona;
import com.consignacion_de_documentos.modelo.entidad.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
public class UsuarioImplDAO implements UsuarioDAO {

    //Objetos, variables y constantes de la clase.
    private static final BaseDeDatosConexion conexion = BaseDeDatosConexion.getInstance();
    private static final Mensajes messages = Mensajes.getInstance();
    private static UsuarioImplDAO instance;
    private PreparedStatement preparedStatement;
    private ResultSet result; 
    private Usuario usuarioEncontrado;
    private List<Usuario> usuariosEncontrados;
 
    private UsuarioImplDAO() {
    } 
 
    public static synchronized UsuarioImplDAO getInstance() {
        if (instance == null) {
            instance = new UsuarioImplDAO();
        }
        return instance;
    } 
 
    public void closeConnection() {
        instance = null;
    } 

    @Override
    public boolean insertar_usuario(Usuario usuario) {
        try {
            preparedStatement = conexion.getConexion().prepareStatement(UsuarioConsulta.INSERTAR);
            preparedStatement.setInt(1, usuario.getPersona().getCedula());
            preparedStatement.setString(2, usuario.getNombre_de_usuario());
            preparedStatement.setString(3, usuario.getContrasena()); 
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
    public boolean insertar_persona_usuario(Usuario usuario) {
        try {
            preparedStatement = conexion.getConexion().prepareStatement(PersonaConsulta.INSERTAR);
            preparedStatement.setInt(1, usuario.getPersona().getCedula());
            preparedStatement.setString(2, usuario.getPersona().getNombres());
            preparedStatement.setString(3, usuario.getPersona().getApellidos()); 
            if (preparedStatement.executeUpdate() > 0) {
                preparedStatement.close();
                preparedStatement = conexion.getConexion().prepareStatement(UsuarioConsulta.INSERTAR);
                preparedStatement.setInt(1, usuario.getPersona().getCedula());
                preparedStatement.setString(2, usuario.getNombre_de_usuario());
                preparedStatement.setString(3, usuario.getContrasena()); 
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
    public boolean modificar(Usuario usuario) {
        try {
            preparedStatement = conexion.getConexion().prepareStatement(PersonaConsulta.MODIFICAR);  
            preparedStatement.setString(1, usuario.getPersona().getNombres());
            preparedStatement.setString(2, usuario.getPersona().getApellidos()); 
            preparedStatement.setInt(3, usuario.getPersona().getCedula());
            if (preparedStatement.executeUpdate() > 0) {
                preparedStatement.close();
                preparedStatement = conexion.getConexion().prepareStatement(UsuarioConsulta.MODIFICAR); 
                System.err.println(usuario.getNombre_de_usuario()); 
                preparedStatement.setString(1, usuario.getNombre_de_usuario());
                preparedStatement.setString(2, usuario.getContrasena()); 
                preparedStatement.setInt(3, usuario.getPersona().getCedula());
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
    public boolean eliminar(Usuario usuario) {
        try {
            preparedStatement = conexion.getConexion().prepareStatement(UsuarioConsulta.ELIMINAR);  
            preparedStatement.setInt(1, usuario.getPersona().getCedula());
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
    public Usuario buscar_persona(Usuario usuario) {
        this.usuarioEncontrado = new Usuario();
        try {
            preparedStatement = conexion.getConexion().prepareStatement(UsuarioConsulta.BUSCAR_PERSONA);
            preparedStatement.setInt(1, usuario.getPersona().getCedula());
            result = preparedStatement.executeQuery(); 
            while (result.next()) {
                this.usuarioEncontrado  = new Usuario(null, null, new Persona(result.getInt(1), result.getString(2), result.getString(3), null), null); 
            }            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return this.usuarioEncontrado;
    }

    @Override
    public Usuario buscar_usuario_persona(Usuario usuario) {
        this.usuarioEncontrado = new Usuario();
        try {
            preparedStatement = conexion.getConexion().prepareStatement(UsuarioConsulta.BUSCAR_USUARIO_PERSONA);
            preparedStatement.setInt(1, usuario.getPersona().getCedula());
            result = preparedStatement.executeQuery(); 
            while (result.next()) {
                this.usuarioEncontrado  = new Usuario(result.getString(4), result.getString(5), new Persona(result.getInt(1), result.getString(2), result.getString(3), null), null); 
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return this.usuarioEncontrado;
    }

    @Override
    public List<Usuario> filtrar(Usuario usuario) {
        this.usuariosEncontrados = new ArrayList<>();
        try {
            preparedStatement = conexion.getConexion().prepareStatement(UsuarioConsulta.FILTRAR);
            preparedStatement.setString(1, "%"+usuario.getNombre_de_usuario()+"%");
            result = preparedStatement.executeQuery();
            while (result.next()) {
                usuariosEncontrados.add(new Usuario(
                        result.getString(3), 
                        null,
                        new Persona(result.getInt(1), result.getString(2), null, null),
                        null)); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return usuariosEncontrados;
    }
    
    @Override
    public List<Usuario> llenar_todo() {
        this.usuariosEncontrados = new ArrayList<>();
        try {
            preparedStatement = conexion.getConexion().prepareStatement(UsuarioConsulta.LLENAR_TODO);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                usuariosEncontrados.add(new Usuario(
                        result.getString(3), 
                        null,
                        new Persona(result.getInt(1), result.getString(2), null, null),
                        null)); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return usuariosEncontrados;
    }

    @Override
    public int validar_existencia(Usuario usuario) {
        try {
            preparedStatement = conexion.getConexion().prepareStatement(UsuarioConsulta.VALIDAR_EXISTENCIA); 
            preparedStatement.setInt(1, usuario.getPersona().getCedula());
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
    public int validar_existencia_dos(Usuario usuario) {
        try {
            preparedStatement = conexion.getConexion().prepareStatement(UsuarioConsulta.VALIDAR_EXISTENCIA_DOS); 
            preparedStatement.setInt(1, usuario.getPersona().getCedula());
            preparedStatement.setInt(2, usuario.getPersona().getCedula());
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
    public int validar_nombre_de_usuario(Usuario usuario) {
        try {
            preparedStatement = conexion.getConexion().prepareStatement(UsuarioConsulta.VALIDAR_NOMBRE_USUARIO); 
            preparedStatement.setString(1, usuario.getNombre_de_usuario()); 
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
    public int validar_nombre_de_usuario_dos(Usuario usuario) {
        try {
            preparedStatement = conexion.getConexion().prepareStatement(UsuarioConsulta.VALIDAR_NOMBRE_USUARIO_DOS); 
            preparedStatement.setString(1, usuario.getNombre_de_usuario()); 
            preparedStatement.setInt(2, usuario.getPersona().getCedula());
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
    public Usuario validar_usuario(Usuario usuario) {
        this.usuarioEncontrado = null;
        try {
            preparedStatement = conexion.getConexion().prepareStatement(UsuarioConsulta.VALIDAR_USUARIO);
            preparedStatement.setString(1, usuario.getNombre_de_usuario()); 
            preparedStatement.setString(2, usuario.getContrasena());
            result = preparedStatement.executeQuery();
            while (result.next()) {;
                return this.usuarioEncontrado = new Usuario(
                        result.getString(1),
                        null,
                        new Persona(result.getInt(2), null, null, null),
                        null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return this.usuarioEncontrado; 
    }
    
}
