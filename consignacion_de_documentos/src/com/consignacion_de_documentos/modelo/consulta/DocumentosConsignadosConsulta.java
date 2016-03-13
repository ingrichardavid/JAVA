/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consignacion_de_documentos.modelo.consulta;

/**
 *
 * @author Ing Richard David
 */
public class DocumentosConsignadosConsulta {
    
    public static final String MODIFICAR_DOCUMENTO_CONSIGNADO = "UPDATE \n" +
                                                                "	negocio.\"DOCUMENTOS_CONSIGNADOS\"\n" +
                                                                "SET \n" +
                                                                "	estatus=?, digitalizado=?, observaciones= ?\n" +
                                                                "WHERE\n" +
                                                                "	codigo = ?;";
    public static final String ENCONTRAR_DOCUMENTOS_CONSIGNADOS =   "SELECT \n" +
                                                                    "	DC.codigo, D.codigo AS codigo_documento, D.nombre, DC.estatus, DC.digitalizado, DC.observaciones\n" +
                                                                    "FROM\n" +
                                                                    "	negocio.\"DOCUMENTOS_CONSIGNADOS\" AS DC\n" +
                                                                    "JOIN\n" +
                                                                    "	maestros.\"DOCUMENTO\" AS D\n" +
                                                                    "ON\n" +
                                                                    "	(DC.codigo_documento = D.codigo)\n" +
                                                                    "WHERE\n" +
                                                                    "	DC.cedula_persona_aspirante = ?\n" +
                                                                    "ORDER BY D.codigo ASC;";
        
}
