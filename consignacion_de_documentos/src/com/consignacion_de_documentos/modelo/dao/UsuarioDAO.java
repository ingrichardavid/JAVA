/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consignacion_de_documentos.modelo.dao;
 
import com.consignacion_de_documentos.modelo.entidad.Usuario;
import java.util.List;

/**
 * 
 * @author RichardJosé
 */
 
public interface UsuarioDAO {
 
    public boolean insertar_persona_usuario(Usuario usuario); 
    public boolean insertar_usuario(Usuario usuario); 
    public boolean modificar(Usuario usuario);
    public boolean eliminar(Usuario usuario);
    public Usuario buscar_persona(Usuario usuario);   
    public Usuario buscar_usuario_persona(Usuario usuario);    
    public List<Usuario> llenar_todo();
    public List<Usuario> filtrar(Usuario usuario);  
    public int validar_existencia(Usuario usuario); 
    public int validar_nombre_de_usuario(Usuario usuario); 
    public int validar_existencia_dos(Usuario usuario);    
    public int validar_nombre_de_usuario_dos(Usuario usuario);     
    public Usuario validar_usuario(Usuario usuario);

}
