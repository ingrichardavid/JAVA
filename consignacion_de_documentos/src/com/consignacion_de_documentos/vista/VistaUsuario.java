/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consignacion_de_documentos.vista;

import com.consignacion_de_documentos.controlador.ControladorUsuario;
import com.consignacion_de_documentos.modelo.entidad.Usuario;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author RichardJosé
 */
public class VistaUsuario extends javax.swing.JDialog {

    private ControladorUsuario controladorUsuario;
    
    /**
     * Creates new form VistaUsuario
     */
    public VistaUsuario(java.awt.Frame parent, boolean modal, String accion, Usuario usuario) {
        super(parent, modal);
        initComponents();
        
        this.configuracion_inicial(accion, usuario);
        
    }
        
    private void configuracion_inicial(String accion, Usuario usuario) {
        
        switch (accion) {        
            case "REGISTRAR":
                this.setTitle("Registrar Usuario");
                this.configuracion_de_campos_y_botones(accion,usuario);
                break;
            case "MODIFICAR":
                this.setTitle("Modificar Usuario");
                this.configuracion_de_campos_y_botones(accion,usuario);
                break;
            case "ELIMINAR":
                this.setTitle("Eliminar Usuario");
                this.configuracion_de_campos_y_botones(accion,usuario);
                break;                    
            default:
                break;        
        }
        
        this.controladorUsuario = new ControladorUsuario(this);
        this.btnBuscar.addActionListener(controladorUsuario);
        this.btnRegistrar.addActionListener(controladorUsuario);
        this.btnModificar.addActionListener(controladorUsuario);
        this.btnEliminar.addActionListener(controladorUsuario);
        this.btnCerrar.addActionListener(controladorUsuario);
        this.txtCedula.addKeyListener(controladorUsuario);
        this.txtNombres.addKeyListener(controladorUsuario);
        this.txtApellidos.addKeyListener(controladorUsuario);
        this.txtNombreUsuario.addKeyListener(controladorUsuario);
        this.txtClave.addKeyListener(controladorUsuario);
        
        this.setLocationRelativeTo(null);
    }
    
    private void configuracion_de_campos_y_botones(String accion, Usuario usuario) {
    
        switch (accion) {        
            case "REGISTRAR":
                this.txtNombres.setEditable(false);
                this.txtApellidos.setEditable(false);
                this.txtNombreUsuario.setEditable(false);
                this.txtApellidos.setEditable(false);
                this.txtClave.setEditable(false);
                this.btnRegistrar.setEnabled(false);
                this.btnModificar.setVisible(false);
                this.btnEliminar.setVisible(false);
                break;
            case "MODIFICAR":
                this.txtCedula.setText(String.valueOf(usuario.getPersona().getCedula()));
                this.txtNombres.setText(String.valueOf(usuario.getPersona().getNombres()));
                this.txtApellidos.setText(String.valueOf(usuario.getPersona().getApellidos()));
                this.txtNombreUsuario.setText(String.valueOf(usuario.getNombre_de_usuario()));
                this.txtClave.setText(String.valueOf(usuario.getContrasena()));
                this.txtCedula.setEditable(false);
                this.btnBuscar.setVisible(false);
                this.btnRegistrar.setVisible(false);
                this.btnEliminar.setVisible(false);
                break;
            case "ELIMINAR":
                this.txtCedula.setText(String.valueOf(usuario.getPersona().getCedula()));
                this.txtNombres.setText(String.valueOf(usuario.getPersona().getNombres()));
                this.txtApellidos.setText(String.valueOf(usuario.getPersona().getApellidos()));
                this.txtNombreUsuario.setText(String.valueOf(usuario.getNombre_de_usuario()));
                this.txtClave.setText(String.valueOf(usuario.getContrasena()));
                this.txtCedula.setEditable(false);
                this.txtNombres.setEditable(false);
                this.txtApellidos.setEditable(false);
                this.txtNombreUsuario.setEditable(false);
                this.txtApellidos.setEditable(false);
                this.txtClave.setEditable(false);
                this.btnBuscar.setVisible(false);
                this.btnRegistrar.setVisible(false);
                this.btnModificar.setVisible(false);
                break;
            default:
                break;        
        }
            
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JButton getBtnCerrar() {
        return btnCerrar;
    }

    public void setBtnCerrar(JButton btnCerrar) {
        this.btnCerrar = btnCerrar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(JButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public void setBtnModificar(JButton btnModificar) {
        this.btnModificar = btnModificar;
    }

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public void setBtnRegistrar(JButton btnRegistrar) {
        this.btnRegistrar = btnRegistrar;
    }

    public JTextField getTxtApellidos() {
        return txtApellidos;
    }

    public void setTxtApellidos(JTextField txtApellidos) {
        this.txtApellidos = txtApellidos;
    }

    public JTextField getTxtCedula() {
        return txtCedula;
    }

    public void setTxtCedula(JTextField txtCedula) {
        this.txtCedula = txtCedula;
    }

    public JPasswordField getTxtClave() {
        return txtClave;
    }

    public void setTxtClave(JPasswordField txtClave) {
        this.txtClave = txtClave;
    }

    public JTextField getTxtNombreUsuario() {
        return txtNombreUsuario;
    }

    public void setTxtNombreUsuario(JTextField txtNombreUsuario) {
        this.txtNombreUsuario = txtNombreUsuario;
    }

    public JTextField getTxtNombres() {
        return txtNombres;
    }

    public void setTxtNombres(JTextField txtNombres) {
        this.txtNombres = txtNombres;
    }
       
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnBuscar = new javax.swing.JButton();
        txtCedula = new javax.swing.JTextField();
        txtNombres = new javax.swing.JTextField();
        txtNombreUsuario = new javax.swing.JTextField();
        txtClave = new javax.swing.JPasswordField();
        txtApellidos = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnCerrar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(493, 190));
        setMinimumSize(new java.awt.Dimension(493, 190));
        setPreferredSize(new java.awt.Dimension(493, 190));
        setResizable(false);
        getContentPane().setLayout(new javax.swing.OverlayLayout(getContentPane()));

        jPanel1.setMinimumSize(new java.awt.Dimension(350, 140));
        jPanel1.setPreferredSize(new java.awt.Dimension(350, 140));
        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0};
        jPanel1Layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0};
        jPanel1.setLayout(jPanel1Layout);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Cédula:");
        jLabel1.setMaximumSize(new java.awt.Dimension(45, 37));
        jLabel1.setMinimumSize(new java.awt.Dimension(45, 37));
        jLabel1.setPreferredSize(new java.awt.Dimension(45, 37));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Nombres:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Apellidos:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jLabel4, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Usuario:");
        jLabel5.setMinimumSize(new java.awt.Dimension(49, 37));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(7, 5, 5, 0);
        jPanel1.add(jLabel5, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Clave:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(7, 0, 5, 0);
        jPanel1.add(jLabel6, gridBagConstraints);

        java.awt.GridBagLayout jPanel2Layout = new java.awt.GridBagLayout();
        jPanel2Layout.columnWidths = new int[] {0, 5, 0};
        jPanel2Layout.rowHeights = new int[] {0};
        jPanel2.setLayout(jPanel2Layout);

        btnBuscar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/consignacion_de_documentos/imagenes/buscar.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel2.add(btnBuscar, gridBagConstraints);

        txtCedula.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(txtCedula, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel1.add(jPanel2, gridBagConstraints);

        txtNombres.setMaximumSize(new java.awt.Dimension(100, 20));
        txtNombres.setMinimumSize(new java.awt.Dimension(100, 20));
        txtNombres.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(txtNombres, gridBagConstraints);

        txtNombreUsuario.setMaximumSize(new java.awt.Dimension(100, 20));
        txtNombreUsuario.setMinimumSize(new java.awt.Dimension(100, 20));
        txtNombreUsuario.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(7, 0, 5, 0);
        jPanel1.add(txtNombreUsuario, gridBagConstraints);

        txtClave.setMaximumSize(new java.awt.Dimension(100, 20));
        txtClave.setMinimumSize(new java.awt.Dimension(100, 20));
        txtClave.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(7, 0, 5, 5);
        jPanel1.add(txtClave, gridBagConstraints);

        txtApellidos.setMaximumSize(new java.awt.Dimension(100, 20));
        txtApellidos.setMinimumSize(new java.awt.Dimension(100, 20));
        txtApellidos.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel1.add(txtApellidos, gridBagConstraints);

        java.awt.GridBagLayout jPanel3Layout = new java.awt.GridBagLayout();
        jPanel3Layout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0};
        jPanel3Layout.rowHeights = new int[] {0};
        jPanel3.setLayout(jPanel3Layout);

        btnCerrar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/consignacion_de_documentos/imagenes/cerrarRegistro.png"))); // NOI18N
        btnCerrar.setText("Cerrar");
        btnCerrar.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel3.add(btnCerrar, gridBagConstraints);

        btnEliminar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/consignacion_de_documentos/imagenes/eliminarRegistro.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel3.add(btnEliminar, gridBagConstraints);

        btnModificar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/consignacion_de_documentos/imagenes/modificarRegistro.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel3.add(btnModificar, gridBagConstraints);

        btnRegistrar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/consignacion_de_documentos/imagenes/registrarRegistro.png"))); // NOI18N
        btnRegistrar.setText("Registrar");
        btnRegistrar.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel3.add(btnRegistrar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel1.add(jPanel3, gridBagConstraints);

        getContentPane().add(jPanel1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JPasswordField txtClave;
    private javax.swing.JTextField txtNombreUsuario;
    private javax.swing.JTextField txtNombres;
    // End of variables declaration//GEN-END:variables
}
