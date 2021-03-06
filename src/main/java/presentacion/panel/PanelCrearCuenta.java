/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.panel;

import logica.entidades.TipoCuenta;
import javax.swing.DefaultComboBoxModel;
import presentacion.Eventos;
import presentacion.Modelo;

/**
 *
 * @author rfcas
 */
public class PanelCrearCuenta extends javax.swing.JPanel {

    private Modelo modelo;
    private ControladorCrearCuenta controlador;

    /**
     * Creates new form PanelNuevaCuenta
     */
    public PanelCrearCuenta(Modelo modelo) {
        super();
        this.controlador = new ControladorCrearCuenta(this);
        this.modelo = modelo;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jComboBoxTipoCuenta = new javax.swing.JComboBox<>();
        jTextFieldNombre = new javax.swing.JTextField();
        jButtonCrear = new javax.swing.JButton();

        setLayout(new javax.swing.OverlayLayout(this));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Nueva Cuenta"));
        jPanel1.setLayout(new java.awt.GridLayout(0, 1, 20, 20));

        jComboBoxTipoCuenta.setModel(new TipoCuentaModel());
        jComboBoxTipoCuenta.setToolTipText("Seleccione el tipo de cuenta a crear");
        jComboBoxTipoCuenta.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo de cuenta"));
        jPanel1.add(jComboBoxTipoCuenta);

        jTextFieldNombre.setBorder(javax.swing.BorderFactory.createTitledBorder("Nombre"));
        jPanel1.add(jTextFieldNombre);

        jButtonCrear.setText("Crear");
        jButtonCrear.setActionCommand(Eventos.CREAR_CUENTA.toString());
        jButtonCrear.addActionListener(controlador);
        jPanel1.add(jButtonCrear);

        add(jPanel1);
    }// </editor-fold>


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCrear;
    private javax.swing.JComboBox<TipoCuenta> jComboBoxTipoCuenta;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextFieldNombre;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the jComboBoxTipoCuenta
     */
    public javax.swing.JComboBox<TipoCuenta> getjComboBoxTipoCuenta() {
        return jComboBoxTipoCuenta;
    }

    /**
     * @return the jTextFieldNombre
     */
    public javax.swing.JTextField getjTextFieldNombre() {
        return jTextFieldNombre;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public ControladorCrearCuenta getControlador() {
        return controlador;
    }

}

class TipoCuentaModel extends DefaultComboBoxModel<TipoCuenta> {

    public TipoCuentaModel() {
        super(TipoCuenta.values());
    }
}
