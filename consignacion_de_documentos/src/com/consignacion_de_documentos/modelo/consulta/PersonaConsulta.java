/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consignacion_de_documentos.modelo.consulta;

 
public class PersonaConsulta {

    public static final String INSERTAR = "INSERT INTO negocio.\"PERSONA\"(cedula, nombres, apellidos) VALUES (?, ?, ?);"; 
    public static final String MODIFICAR = "UPDATE negocio.\"PERSONA\" SET nombres=?, apellidos=? WHERE cedula = ?;"; 
    public static final String ELIMINAR = "DELETE FROM negocio.\"PERSONA\" WHERE cedula = ?;";   
    public static final String VALIDAR_EXISTENCIA = "SELECT COUNT(cedula) FROM negocio.\"PERSONA\" WHERE cedula = ?;";  
    public static final String VALIDAR_EXISTENCIA_DOS = "SELECT COUNT(cedula) FROM negocio.\"PERSONA\" WHERE cedula = ? AND cedula != ?;";
 
}
