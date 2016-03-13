/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consignacion_de_documentos.modelo.dao;
  
import com.consignacion_de_documentos.modelo.entidad.Persona; 

/**
 * 
 * @author RichardJosé
 */
 
public interface PersonaDAO {
 
    public boolean insertar(Persona persona);    
    public boolean modificar(Persona persona);    
    public boolean eliminar(Persona persona);    
    public int validar_existencia(Persona persona);    
    public int validar_existencia_dos(Persona persona);

}
