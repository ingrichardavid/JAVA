/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consignacion_de_documentos.modelo.consulta;

 
public class AspiranteConsulta {

    public static final String INSERTAR = "INSERT INTO negocio.\"ASPIRANTE\"(cedula_persona) VALUES (?);"; 
    public static final String MODIFICAR = "UPDATE negocio.\"ASPIRANTE\" SET codigo=?, cedula_persona=?, fecha_registro=? WHERE <condition>;";
    public static final String ELIMINAR = "DELETE FROM negocio.\"ASPIRANTE\"  WHERE cedula_persona = ?;";
    public static final String BUSCAR_ASPIRANTE_PERSONA = "SELECT \n" +
                                            "	p.cedula,p.nombres,p.apellidos,a.codigo,a.fecha_registro \n" +
                                            "FROM \n" +
                                            "	negocio.\"ASPIRANTE\" AS a\n" +
                                            "JOIN \n" +
                                            "	negocio.\"PERSONA\" AS p\n" +
                                            "ON \n" +
                                            "	(a.cedula_persona = p.cedula)  \n" +
                                            "WHERE \n" +
                                            "p.cedula = ?;";   
    public static final String BUSCAR_PERSONA= "SELECT \n" +
                                            "	cedula,nombres,apellidos \n" +
                                            "FROM \n" +
                                            "	negocio.\"PERSONA\" \n" +
                                            "WHERE \n" +
                                            "cedula = ?;"; 
     
    public static final String LLENAR_TODO =    "SELECT\n" +
                                                "	a.codigo, a.cedula_persona,CONCAT(p.nombres,' ',p.apellidos), a.fecha_registro\n" +
                                                "FROM \n" +
                                                "	negocio.\"PERSONA\" AS p\n" +
                                                "JOIN \n" +
                                                "	negocio.\"ASPIRANTE\" AS a\n" +
                                                "ON \n" +
                                                "	(p.cedula = a.cedula_persona)  \n" +
                                                "ORDER BY\n" +
                                                "	a.codigo\n" +
                                                "ASC;";
    public static final String FILTRAR = "SELECT\n" +
                                                "	a.codigo, a.cedula_persona,CONCAT(p.nombres,' ',p.apellidos), a.fecha_registro\n" +
                                                "FROM \n" +
                                                "	negocio.\"ASPIRANTE\" AS a\n" +
                                                "JOIN \n" +
                                                "	negocio.\"PERSONA\" AS p\n" +
                                                "ON \n" +
                                                "	(a.cedula_persona = p.cedula)  \n" +
                                                "WHERE\n" +
                                                "	TRIM(LOWER(CONCAT(a.cedula_persona,' ',p.nombres,' ',p.apellidos))) LIKE TRIM(LOWER(?))\n" +
                                                "ORDER BY\n" +
                                                "	a.codigo\n" +
                                                "ASC;";
    public static final String VALIDAR_EXISTENCIA = "SELECT COUNT(cedula_persona) FROM negocio.\"ASPIRANTE\" WHERE cedula_persona = ?;";
    public static final String VALIDAR_EXISTENCIA_DOS = "SELECT COUNT(cedula_persona) FROM negocio.\"ASPIRANTE\" WHERE cedula_persona = ? AND cedula_persona != ?;";
  
}
