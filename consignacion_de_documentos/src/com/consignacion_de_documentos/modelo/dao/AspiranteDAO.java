/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consignacion_de_documentos.modelo.dao;
   
import com.consignacion_de_documentos.modelo.entidad.Aspirante;
import java.util.List;

/**
 * 
 * @author RichardJosé
 */ 
public interface AspiranteDAO {
 
    public boolean insertar_aspirante(Aspirante aspirante);  
    public boolean insertar_persona_aspirante(Aspirante aspirante); 
    public boolean eliminar(Aspirante aspirante);
    public Aspirante buscar_persona(Aspirante aspirante);
    public Aspirante buscar_aspirante_persona(Aspirante aspirante);
    public List<Aspirante> filtrar(Aspirante aspirante);  
    public List<Aspirante> llenar_todo();
    public int validar_existencia(Aspirante aspirante);  
    public int validar_existencia_dos(Aspirante aspirante);

}
