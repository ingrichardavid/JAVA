/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consignacion_de_documentos.modelo.implementacion;

import com.consignacion_de_documentos.modelo.conexion.BaseDeDatosConexion;
import com.consignacion_de_documentos.modelo.dao.ReporteDAO;
import com.consignacion_de_documentos.modelo.entidad.Aspirante;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author RichardJosé
 */
public class ReporteImplDAO implements ReporteDAO {

    private static final BaseDeDatosConexion conexion = BaseDeDatosConexion.getInstance();
    private static ReporteImplDAO instance;
    private PreparedStatement preparedStatement;
    private ResultSet result;
    private String ruta;
    private Map<String, Object> parametros;
    private JasperReport jasperReport;
    private JasperPrint jasperPrint;
    private JasperViewer jasperViewer;
    private File file;
    private Object ChartFactory;

    public ReporteImplDAO() {
    }

    public static synchronized ReporteImplDAO GetInstance() {
        if (instance == null) {
            instance = new ReporteImplDAO();
        }
        return instance;
    }

    public void cancelInstance() {
        instance = null;
    }

    @Override
    public void report(Aspirante aspirante) {
        try {
            System.err.println("aspirante.getPersona().getCedula()" +aspirante.getPersona().getCedula());
            file = new File("C:\\consignacion_de_documentos\\reportes\\reporteDocumentos.jasper");
            parametros = new HashMap();
            parametros.put("cedula", aspirante.getPersona().getCedula()); 
            jasperPrint = JasperFillManager.fillReport(file.getAbsolutePath(), parametros,
                    conexion.getConexion());
            jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setTitle("Hoja de Cálculo");
            jasperViewer.setZoomRatio((float) 0.75);
            jasperViewer.setLocationRelativeTo(null);
            jasperViewer.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
    }

}
