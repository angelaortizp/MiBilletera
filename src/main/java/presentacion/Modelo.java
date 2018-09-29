/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import logica.entidades.CategoriaMovimiento;
import logica.entidades.Cuenta;
import logica.entidades.IngresosVsEgresos;
import logica.entidades.Movimiento;
import logica.entidades.TipoCategoria;
import logica.entidades.TipoCuenta;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import logica.LogicaPrincipal;
import logica.LogicaPrincipalI;
import presentacion.panel.PanelCuentas;
import presentacion.panel.PanelCrearCuenta;
import presentacion.panel.PanelCrearMovimiento;
import presentacion.panel.PanelGraficoLineas;
import presentacion.panel.PanelGraficoTorta;
import presentacion.panel.PanelMovimientos;
import presentacion.panel.PanelReporteCategoria;
import presentacion.panel.PanelReporteIngVsEgr;
import presentacion.panel.PanelReportePeriodo;

/**
 *
 * @author Estudiantes
 */
public class Modelo {

    private Vista vista;

    private LogicaPrincipalI logicaPrincipal;

    public Vista getVista() {
        if (vista == null) {
            vista = new Vista(this);
        }
        return vista;
    }

    public LogicaPrincipalI getLogicaPrincipal() {
        if (logicaPrincipal == null) {
            logicaPrincipal = new LogicaPrincipal();
        }
        return logicaPrincipal;
    }

    public void iniciar() {
        //Inicializar la base de datos
        getLogicaPrincipal().conectarABaseDeDatos();

        //Crear ventana y abrirla
        getVista().setSize(400, 400);
        getVista().setVisible(true);
    }

    public void crearCuenta() {
        try {
            PanelCrearCuenta panelNuevaCuenta = (PanelCrearCuenta) this.getVista().getjPanelNuevaCuenta();
            TipoCuenta tipoCuenta = (TipoCuenta) panelNuevaCuenta.getjComboBoxTipoCuenta().getSelectedItem();
            if (tipoCuenta == null) {
                this.vista.showDialog("Error de validación", "Seleccione un tipo de cuenta", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String nombreCuenta = panelNuevaCuenta.getjTextFieldNombre().getText();
            if (nombreCuenta == null || nombreCuenta.trim().isEmpty()) {
                this.vista.showDialog("Error de validación", "Ingrese un nombre para la cuenta", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Cuenta cuenta = new Cuenta();
            cuenta.setFechaAperturaDate(new Date());
            cuenta.setNombre(nombreCuenta);
            cuenta.setTipoCuenta(tipoCuenta);
            cuenta.setSaldo(0.0d);

            getLogicaPrincipal().crearCuenta(cuenta);
            panelNuevaCuenta.getjTextFieldNombre().setText("");
            PanelCuentas panelCuentas = (PanelCuentas) this.getVista().getjPanelCuentas();
            panelCuentas.refrescar();
        } catch (Throwable t) {
            this.vista.showDialog("Upps!!!", "Ocurrio un error inesperado de tipo: " + t.getLocalizedMessage(), JOptionPane.ERROR_MESSAGE);
        }

    }

    public void crearMovimiento() {
        try {
            PanelCrearMovimiento panelCrearMovimiento = (PanelCrearMovimiento) this.getVista().getjPanelNuevoMovimiento();

            Cuenta cuenta = panelCrearMovimiento.getCuenta();

            if (cuenta == null) {
                this.vista.showDialog("Error de validación", "Seleccione una cuenta", JOptionPane.WARNING_MESSAGE);
                return;
            }

            TipoCategoria tipoCategoria = (TipoCategoria) panelCrearMovimiento.getjComboBoxTipoCategoria().getSelectedItem();
            if (tipoCategoria == null) {
                this.vista.showDialog("Error de validación", "Seleccione un tipo de categoria", JOptionPane.WARNING_MESSAGE);
                return;
            }

            CategoriaMovimiento categoriaMovimiento = (CategoriaMovimiento) panelCrearMovimiento.getjComboBoxCategoria().getSelectedItem();
            if (categoriaMovimiento == null) {
                this.vista.showDialog("Error de validación", "Seleccione una categoria", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String descripcion = (String) panelCrearMovimiento.getjTextFieldDescripcion().getText();
            if (descripcion == null || descripcion.trim().isEmpty()) {
                this.vista.showDialog("Error de validación", "Ingrese una descripción para el movimiento", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Cuenta cuentaDestino = (Cuenta) panelCrearMovimiento.getjComboBoxCuenta().getSelectedItem();
            if (cuentaDestino == null && TipoCategoria.TRANSFERENCIA == tipoCategoria.getId()) {
                this.vista.showDialog("Error de validación", "Seleccione una cuenta destino para el movimiento", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Double valor = null;
            try {
                valor = Double.parseDouble((String) panelCrearMovimiento.getjTextFieldValor().getText());
                if (valor.compareTo(0d) <= 0) {
                    this.vista.showDialog("Error de validación", "El valor debe ser positivo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } catch (Exception e) {
                this.vista.showDialog("Error de validación", "Ingrese un valor númerico", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Movimiento movimiento = new Movimiento();

            movimiento.setCuenta(cuenta);
            movimiento.setIdCategoriaMovimiento(categoriaMovimiento);
            movimiento.setDescripcion(descripcion);
            movimiento.setValor(TipoCategoria.INGRESO == tipoCategoria.getId() ? valor : -1d * valor);
            movimiento.setFechaDate(new Date());

            getLogicaPrincipal().crearMovimiento(movimiento);

            //Actualizar saldo cuenta
            if (movimiento.getId() != null) {
                System.out.println("saldo actual: " + movimiento.getCuenta().getSaldo());
                movimiento.getCuenta().setSaldo(movimiento.getCuenta().getSaldo() + movimiento.getValor());
                System.out.println("saldo nuevo: " + movimiento.getCuenta().getSaldo());
                getLogicaPrincipal().editarCuenta(movimiento.getCuenta());
            }

            if (TipoCategoria.TRANSFERENCIA == tipoCategoria.getId()) {
                movimiento = new Movimiento();
                movimiento.setCuenta(cuentaDestino);
                movimiento.setIdCategoriaMovimiento(categoriaMovimiento);
                movimiento.setDescripcion(descripcion);
                movimiento.setValor(valor);
                movimiento.setFechaDate(new Date());

                getLogicaPrincipal().crearMovimiento(movimiento);

                //Actualizar saldo cuenta
                if (movimiento.getId() != null) {
                    System.out.println("saldo actual: " + movimiento.getCuenta().getSaldo());
                    movimiento.getCuenta().setSaldo(movimiento.getCuenta().getSaldo() + movimiento.getValor());
                    System.out.println("saldo nuevo: " + movimiento.getCuenta().getSaldo());
                    getLogicaPrincipal().editarCuenta(movimiento.getCuenta());
                }
            }

            panelCrearMovimiento.getjTextFieldDescripcion().setText("");
            panelCrearMovimiento.getjTextFieldValor().setText("");

            PanelMovimientos panelMovimientos = (PanelMovimientos) this.getVista().getjPanelMovimientos();
            panelMovimientos.refrescar(cuenta);

            PanelCuentas panelCuentas = (PanelCuentas) this.getVista().getjPanelCuentas();
            panelCuentas.refrescar();
        } catch (Throwable t) {
            this.vista.showDialog("Upps!!!", "Ocurrio un error inesperado de tipo: " + t.getLocalizedMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    public void crearCategoria() {
        try {
            PanelCrearMovimiento panelCrearMovimiento = (PanelCrearMovimiento) this.getVista().getjPanelNuevoMovimiento();

            TipoCategoria tipoCategoria = (TipoCategoria) panelCrearMovimiento.getjComboBoxTipoCategoria().getSelectedItem();
            if (tipoCategoria == null) {
                this.vista.showDialog("Error de validación", "Seleccione un tipo de categoria", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Object categoriaMovimiento = panelCrearMovimiento.getjComboBoxCategoria().getSelectedItem();
            if (categoriaMovimiento == null) {
                this.vista.showDialog("Error de validación", "Seleccione una categoria", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (categoriaMovimiento instanceof String) {
                CategoriaMovimiento newCategoriaMovimiento = new CategoriaMovimiento();
                newCategoriaMovimiento.setIdTipoCategoria(tipoCategoria);
                newCategoriaMovimiento.setNombre((String) categoriaMovimiento);
                getLogicaPrincipal().crearCategoria(newCategoriaMovimiento);

                //Agregar la categoria al comboBox
                panelCrearMovimiento.getjComboBoxCategoria().addItem(newCategoriaMovimiento);

                ActionEvent actionEvent = new ActionEvent(panelCrearMovimiento.getjComboBoxTipoCategoria(), 0, "test");
                panelCrearMovimiento.getjComboBoxCategoria().actionPerformed(actionEvent);

                //Seleccionar la categoria recien creada
                panelCrearMovimiento.getjComboBoxCategoria().setSelectedItem(newCategoriaMovimiento);

            }
        } catch (Throwable t) {
            this.vista.showDialog("Upps!!!", "Ocurrio un error inesperado de tipo: " + t.getLocalizedMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Cuenta> getListadoCuentas() {
        return this.logicaPrincipal.obtenerListaCuenta();
    }

    public List<Movimiento> getListadoMovimientos(Cuenta cuenta) {
        if (cuenta != null) {
            return this.logicaPrincipal.obtenerMovimientosPorCuenta(cuenta.getId());
        }
        return new ArrayList<>();
    }

    public List<Movimiento> obtenerMovimientoPorPeriodo(Date fechaInicial, Date fechaFinal) {
        return this.logicaPrincipal.obtenerMovimientoPorPeriodo(fechaInicial, fechaFinal);
    }

    public List<Movimiento> obtenerMovimientoPorCategoria(CategoriaMovimiento categoria) {
        if (categoria != null) {
            return this.logicaPrincipal.obtenerMovimientoPorCategoria(categoria.getId());
        }
        return new ArrayList<>();
    }

    public List<IngresosVsEgresos> obtenerIngresosEgresos() {
        return this.logicaPrincipal.obtenerIngresosEgresos();
    }

    public List<TipoCategoria> getListadoTipoCategoria() {
        return this.logicaPrincipal.obtenerTipoCategoriaList();
    }

    public List<CategoriaMovimiento> getListadoCategoriaMovimiento(TipoCategoria tipoCategoria) {
        if (tipoCategoria != null) {
            return this.logicaPrincipal.obtenerCategoriaMovimientoList(tipoCategoria);
        }
        return new ArrayList<>();
    }

    public void calcularReporteCategoria() {
        PanelReporteCategoria pRC = (PanelReporteCategoria) this.vista.getjPanelRepCategoria();
        pRC.refrescar((CategoriaMovimiento) pRC.getjComboBoxCategoria().getSelectedItem());
    }

    public void calcularReportePeriodo() {
        try {
            PanelReportePeriodo panelReportePeriodo = (PanelReportePeriodo) this.getVista().getjPanelRepPeriodo();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaInicial = null;
            try {
                fechaInicial = dateFormat.parse(panelReportePeriodo.getjTextFieldFechaInicial().getText());
            } catch (Exception e) {
                this.vista.showDialog("Error de validación", "Ingrese la fecha inicial en formato dd/MM/YYYY", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Date fechaFinal = null;
            try {
                fechaFinal = dateFormat.parse(panelReportePeriodo.getjTextFieldFechaFinal().getText());
            } catch (Exception e) {
                this.vista.showDialog("Error de validación", "Ingrese la fecha final en formato dd/MM/YYYY", JOptionPane.WARNING_MESSAGE);
                return;
            }
            panelReportePeriodo.refrescar(fechaInicial, fechaFinal);

            List<Date> valuesX = new ArrayList<Date>();
            List<Double> valuesY = new ArrayList<Double>();
            Calendar calendar = Calendar.getInstance();
            for (Movimiento movimiento : this.obtenerMovimientoPorPeriodo(fechaInicial, fechaFinal)) {
                /**
                 * BUGFIX: Como alguien decidio que las transacciones no llevan
                 * hora minuto segundo toca hacer una maña para garantizar que
                 * el grafico se vea bien
                 *
                 */
                calendar.setTime(movimiento.getFechaDate());
                calendar.add(Calendar.MILLISECOND, movimiento.getId());
                valuesX.add(calendar.getTime());
                valuesY.add(movimiento.getValor());
            }

            PanelGraficoLineas panelGraficoLineas = (PanelGraficoLineas) panelReportePeriodo.getjPanelGraficoLineas();
            panelGraficoLineas.setValues(valuesX, valuesY);
            panelGraficoLineas.updateUI();

        } catch (Throwable t) {
            this.vista.showDialog("Upps!!!", "Ocurrio un error inesperado de tipo: " + t.getLocalizedMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    public void calcularReporteIngVsEgr() {
        try {
            PanelReporteIngVsEgr panelReporteIngVsEgr = (PanelReporteIngVsEgr) this.getVista().getjPanelInvVsEgr();

            List<IngresosVsEgresos> ingEgr = obtenerIngresosEgresos();

            System.out.println("INGRESOS: " + ingEgr.get(0).getSumatoria());
            System.out.println("EGRESOS: " + ingEgr.get(1).getSumatoria());

            panelReporteIngVsEgr.getjTextFieldIngresos().setText(ingEgr.get(0).getSumatoria());

            panelReporteIngVsEgr.getjTextFieldEgresos().setText(ingEgr.get(1).getSumatoria());

            PanelGraficoTorta panelGraficoTorta = (PanelGraficoTorta) panelReporteIngVsEgr.getjPanelGraficoTorta();
            List<Double> values = new ArrayList<Double>();
            values.add(Double.valueOf(ingEgr.get(0).getSumatoria()));
            values.add(Double.valueOf(ingEgr.get(1).getSumatoria()));
            panelGraficoTorta.setValues(values);

        } catch (Throwable t) {
            this.vista.showDialog("Upps!!!", "Ocurrio un error inesperado de tipo: " + t.getLocalizedMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

}
