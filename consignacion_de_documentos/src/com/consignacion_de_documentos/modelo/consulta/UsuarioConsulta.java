/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consignacion_de_documentos.modelo.consulta;

 
public class UsuarioConsulta {
    
    public static final String INSERTAR = "INSERT INTO seguridad.\"USUARIO\"(cedula_persona, nombre_de_usuario, contrasena) VALUES (?, ?, ?);";
    public static final String MODIFICAR = "UPDATE seguridad.\"USUARIO\" SET  nombre_de_usuario=?, contrasena=? WHERE cedula_persona=?;"; 
    public static final String ELIMINAR = "DELETE FROM seguridad.\"USUARIO\"  WHERE cedula_persona=?;";   
    public static final String BUSCAR_USUARIO_PERSONA = "SELECT \n" +
                                            "	u.cedula_persona,p.nombres,p.apellidos,u.nombre_de_usuario,u.contrasena \n" +
                                            "FROM \n" +
                                            "	seguridad.\"USUARIO\" AS u\n" +
                                            "JOIN \n" +
                                            "	negocio.\"PERSONA\" AS p\n" +
                                            "ON \n" +
                                            "	(u.cedula_persona = p.cedula)  \n" +
                                            "WHERE \n" +
                                            "u.cedula_persona = ?;";   
    public static final String BUSCAR_PERSONA= "SELECT \n" +
                                            "	cedula,nombres,apellidos \n" +
                                            "FROM \n" +
                                            "	negocio.\"PERSONA\" \n" +
                                            "WHERE \n" +
                                            "cedula = ?;"; 
    public static final String LLENAR_TODO = "SELECT \n" +
                                                    "	u.cedula_persona,CONCAT(p.nombres,' ',p.apellidos) AS nombre, u.nombre_de_usuario\n" +
                                                    "FROM \n" +
                                                    "	seguridad.\"USUARIO\" AS u\n" +
                                                    "JOIN \n" +
                                                    "	negocio.\"PERSONA\" AS p\n" +
                                                    "ON \n" +
                                                    "	(u.cedula_persona = p.cedula)\n" +
                                                    " \n" +
                                                    "ORDER BY \n" +
                                                    "	u.cedula_persona \n" +
                                                    "ASC;";    
    public static final String FILTRAR = "SELECT \n" +
                                            "	u.cedula_persona,CONCAT(p.nombres,' ',p.apellidos) AS nombre, u.nombre_de_usuario\n" +
                                            "FROM \n" +
                                            "	seguridad.\"USUARIO\" AS u\n" +
                                            "JOIN \n" +
                                            "	negocio.\"PERSONA\" AS p\n" +
                                            "ON \n" +
                                            "	(u.cedula_persona = p.cedula)\n" +
                                            "WHERE \n" +
                                            "	TRIM(LOWER(CONCAT(u.cedula_persona,' ',p.nombres,' ',p.apellidos,' ',u.nombre_de_usuario))) LIKE TRIM(LOWER(?))\n" +
                                            "ORDER BY \n" +
                                            "	u.cedula_persona \n" +
                                            "ASC;";    
    public static final String VALIDAR_EXISTENCIA = "SELECT COUNT(cedula_persona) FROM seguridad.\"USUARIO\" WHERE cedula_persona = ?;";  
    public static final String VALIDAR_EXISTENCIA_DOS = "SELECT COUNT(cedula_persona) FROM seguridad.\"USUARIO\" WHERE cedula_persona = ? AND cedula_persona != ?;";
    public static final String VALIDAR_NOMBRE_USUARIO = "SELECT COUNT(cedula_persona) FROM seguridad.\"USUARIO\" WHERE TRIM(LOWER(nombre_de_usuario)) = LOWER(?);";
    public static final String VALIDAR_NOMBRE_USUARIO_DOS = "SELECT COUNT(cedula_persona) FROM seguridad.\"USUARIO\" WHERE TRIM(LOWER(nombre_de_usuario)) = TRIM(LOWER(?)) AND cedula_persona != ?;";
    public static final String VALIDAR_USUARIO = "SELECT nombre_de_usuario,cedula_persona\n" +
                                                    "FROM seguridad.\"USUARIO\" \n" +
                                                    "WHERE TRIM(LOWER(nombre_de_usuario)) = TRIM(LOWER(?)) AND contrasena = ?;";
      
}
