/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.panel;

import logica.entidades.Cuenta;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import presentacion.Modelo;
import presentacion.Vista;
import utilidades.UtilidadesFecha;

/**
 *
 * @author rfcas
 */
public class PanelCuentas extends javax.swing.JPanel implements ListSelectionListener {

    private Vista vista;

    private TableModel cuentasModel;

    /**
     * Creates new form PanelCuentas
     */
    public PanelCuentas(Vista vista) {
        super();
        this.vista = vista;
        initModel();
        initComponents();
    }

    private void initModel() {
        this.cuentasModel = new CuentaTableModel(this.vista.getModelo());
    }

    public void refrescar() {
        ((AbstractTableModel) this.cuentasModel).fireTableDataChanged();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCuentas = new javax.swing.JTable();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Mis Cuentas"));

        jTableCuentas.setAutoCreateRowSorter(true);
        jTableCuentas.setModel(this.cuentasModel);
        jTableCuentas.setRowSelectionAllowed(true);
        jTableCuentas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableCuentas.getSelectionModel().addListSelectionListener(this);
        jScrollPane1.setViewportView(jTableCuentas);

        add(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCuentas;
    // End of variables declaration//GEN-END:variables

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }
        int index = jTableCuentas.getSelectedRow();
        if (index >= 0) {
            CuentaTableModel cuentaTableModel = (CuentaTableModel) this.cuentasModel;
            Cuenta cuenta = cuentaTableModel.getCuentas().get(index);
            System.out.println("la cuenta es " + cuenta);
            PanelMovimientos panelMovimientos = (PanelMovimientos) this.vista.getjPanelMovimientos();
            panelMovimientos.refrescar(cuenta);
            PanelCrearMovimiento panelCrearMovimiento = (PanelCrearMovimiento) this.vista.getjPanelNuevoMovimiento();
            panelCrearMovimiento.setCuenta(cuenta);
        }
        //
    }
}

class CuentaTableModel extends AbstractTableModel {

    private static final String[] COLUMNAS = new String[]{"Tipo", "Nombre", "Fecha apertura", "Saldo COP"};

    private Modelo modelo;

    private List<Cuenta> cuentas;

    public CuentaTableModel(Modelo modelo) {
        super();
        this.modelo = modelo;
        this.cuentas = null;
    }

    public List<Cuenta> getCuentas() {
        if (this.cuentas == null) {
            this.cuentas = modelo.getListadoCuentas();
        }
        return this.cuentas;
    }

    @Override
    public int getRowCount() {
        return this.getCuentas().size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int col) {
        return CuentaTableModel.COLUMNAS[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cuenta cuenta = this.getCuentas().get(rowIndex);
        switch (columnIndex) {
            case 0:
                if (cuenta.getTipoCuenta() != null) {
                    return cuenta.getTipoCuenta().toString();
                } else {
                    return null;
                }
            case 1:
                return cuenta.getNombre();
            case 2: {
                return cuenta.getFechaApertura();
            }
            case 3: {
                if (cuenta.getSaldo() != null) {
                    NumberFormat numberFormat = new DecimalFormat("0.##");
                    return numberFormat.format(cuenta.getSaldo());
                } else {
                    return null;
                }

            }
            default:
                return null;
        }
    }

    @Override
    public void fireTableDataChanged() {
        this.cuentas = null;
        super.fireTableDataChanged(); //To change body of generated methods, choose Tools | Templates.
    }

}
