package logica;

import logica.entidades.CategoriaMovimiento;
import logica.entidades.Cuenta;
import logica.entidades.IngresosVsEgresos;
import logica.entidades.Movimiento;
import logica.entidades.TipoCategoria;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import persistencia.Persistencia;
import utilidades.UtilidadesFecha;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Estudiantes
 */
public class LogicaPrincipal implements LogicaPrincipalI {

    private Persistencia persistencia;

    @Override
    public void conectarABaseDeDatos() {
        //Inicializar la base de datos
        getPersistencia().connect();
    }

    public Persistencia getPersistencia() {
        if (persistencia == null) {
            persistencia = new Persistencia();
        }
        return persistencia;
    }

    @Override
    public void crearCuenta(Cuenta cuenta) {
        getPersistencia().crear(cuenta);
    }

    @Override
    public void editarCuenta(Cuenta cuenta) {
        getPersistencia().editar(cuenta);
    }

    @Override
    public List<Cuenta> obtenerListaCuenta() {
        return getPersistencia().buscarPorConsultaNativa(
                "SELECT * FROM CUENTA", Cuenta.class);
    }

    @Override
    public Cuenta obtenerCuentaPorId(int id) {
        List<Cuenta> cuentaList = getPersistencia().buscarPorConsultaNativa(
                "SELECT * FROM CUENTA WHERE ID = ?", Cuenta.class, id);
        if (cuentaList != null && !cuentaList.isEmpty()) {
            return cuentaList.get(0);
        }
        return null;
    }

    @Override
    public List<TipoCategoria> obtenerTipoCategoriaList() {
        return getPersistencia().buscarPorConsultaNativa(
                "SELECT * FROM tipoCategoria", TipoCategoria.class);
    }

    @Override
    public void crearCategoria(CategoriaMovimiento categoria) {
        getPersistencia().crear(categoria);
    }

    @Override
    public void editarCategoria(CategoriaMovimiento categoria) {
        getPersistencia().editar(categoria);

    }

    @Override
    public CategoriaMovimiento obtenerCategoriaPorNombre(String nombreCategoria) {
        List<CategoriaMovimiento> categoriaMovimientoList = getPersistencia().buscarPorConsultaNativa(
                "SELECT * FROM categoriaMovimiento WHERE nombre = ? ",
                CategoriaMovimiento.class, nombreCategoria);
        if (categoriaMovimientoList != null && !categoriaMovimientoList.isEmpty()) {
            return categoriaMovimientoList.get(0);
        }
        return null;
    }

    @Override
    public void crearMovimiento(Movimiento movimiento) {
        getPersistencia().crear(movimiento);
    }

    @Override
    public List<Movimiento> obtenerMovimientosPorCuenta(int idCuenta) {
        /**
         * Modifique el query nativo, ya que este metodo debe traer los
         * movimientos asociados a una cuenta, no necesita la información de la
         * cuenta pues ya la tiene.. porque quueries nativos?
         */
        return getPersistencia().buscarPorConsultaNativa(
                "SELECT * FROM MOVIMIENTO M WHERE M.idCuenta = ? ",
                Movimiento.class, idCuenta);
    }

    @Override
    public List<Movimiento> obtenerMovimientoPorPeriodo(Date fechaInicial, Date fechaFinal) {

        String DateInicial = UtilidadesFecha.obtenerStringPorDate(fechaInicial);
        String DateFinal = UtilidadesFecha.obtenerStringPorDate(fechaFinal);

        List<Movimiento> movimientosPeriodo = getPersistencia().buscarPorConsultaNativa(
                "SELECT * FROM MOVIMIENTO WHERE FECHA between ? AND ? ",
                Movimiento.class, DateInicial, DateFinal);

        return movimientosPeriodo;

    }

    @Override
    public List<Movimiento> obtenerMovimientoPorCategoria(Integer idCategoria) {
        return getPersistencia().buscarPorConsultaNativa(
                "SELECT * FROM movimiento WHERE idCategoriaMovimiento = ?",
                Movimiento.class, idCategoria);
    }

    @Override
    public List<Movimiento> obtenerMovimientoPorTipo(Integer idTipo) {
        return getPersistencia().buscarPorConsultaNativa(
                "SELECT m.* FROM movimiento m \n"
                + "INNER JOIN categoriaMovimiento cm \n"
                + "on (m.idCategoriaMovimiento = cm.id)\n"
                + "INNER JOIN tipoCategoria tc \n"
                + "on (cm.idTipoCategoria = tc.id)\n"
                + "WHERE tc.id = ?",
                Movimiento.class, idTipo);
    }

    @Override
    public List<IngresosVsEgresos> obtenerIngresosEgresos() {
        List<Object[]> objectList = getPersistencia().buscarPorConsultaNativa(
                "select * from IngresosVsEgresos", null);
        List<IngresosVsEgresos> ingresosVsEgresosList = new ArrayList<>();
        for (Object[] o : objectList) {
            if (o.length > 2) {
                IngresosVsEgresos ive = new IngresosVsEgresos();
                ive.setId((Integer) o[0]);
                ive.setNombre((String) o[1]);
                ive.setSumatoria(((Integer) o[2]).toString());
                ingresosVsEgresosList.add(ive);
            }

        }
        return ingresosVsEgresosList;
    }

    @Override
    public List<CategoriaMovimiento> obtenerCategoriaMovimientoList(TipoCategoria tipoCategoria) {
        return getPersistencia().buscarPorConsultaNativa("SELECT * FROM CATEGORIAMOVIMIENTO CM WHERE CM.idTipoCategoria = ? ",
                CategoriaMovimiento.class, tipoCategoria.getId());
    }

}
