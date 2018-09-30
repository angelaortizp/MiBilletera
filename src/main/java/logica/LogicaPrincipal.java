package logica;

import logica.entidades.CategoriaMovimiento;
import logica.entidades.Cuenta;
import logica.entidades.IngresosVsEgresos;
import logica.entidades.Movimiento;
import logica.entidades.TipoCategoria;
import java.util.Date;
import java.util.List;
import persistencia.dao.implement.CategoriaMovimientoDao;
import persistencia.dao.CategoriaMovimientoDaoI;
import persistencia.dao.implement.CuentaDao;
import persistencia.implement.Persistencia;
import persistencia.dao.CuentaDaoI;
import persistencia.dao.implement.IngresosVsEgresosDao;
import persistencia.dao.IngresosVsEgresosDaoI;
import persistencia.dao.implement.MovimientoDao;
import persistencia.dao.MovimientoDaoI;
import persistencia.PersistenciaI;
import persistencia.dao.implement.TipoCategoriaDao;
import persistencia.dao.TipoCategoriaDaoI;

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

    private PersistenciaI persistencia;
    private CuentaDaoI cuentaDAO;
    private TipoCategoriaDaoI tipoCategoriaDao;
    private CategoriaMovimientoDaoI categoriaMovimientoDao;
    private MovimientoDaoI movimientoDao;
    private IngresosVsEgresosDaoI ingresosVsEgresosDao;

    @Override
    public void conectarABaseDeDatos() {
        //Inicializar la base de datos
        getPersistencia().connect();
    }

    public PersistenciaI getPersistencia() {
        if (persistencia == null) {
            persistencia = new Persistencia();
        }
        return persistencia;
    }

    public CuentaDaoI getCuentaDAO() {
        if (cuentaDAO == null) {
            cuentaDAO = new CuentaDao();
        }
        return cuentaDAO;
    }

    public TipoCategoriaDaoI getTipoCategoriaDAO() {
        if (tipoCategoriaDao == null) {
            tipoCategoriaDao = new TipoCategoriaDao();
        }
        return tipoCategoriaDao;
    }

    public CategoriaMovimientoDaoI getCategoriaMovimientoDAO() {
        if (categoriaMovimientoDao == null) {
            categoriaMovimientoDao = new CategoriaMovimientoDao();
        }
        return categoriaMovimientoDao;
    }

    public MovimientoDaoI getMovimientoDAO() {
        if (movimientoDao == null) {
            movimientoDao = new MovimientoDao();
        }
        return movimientoDao;
    }

    public IngresosVsEgresosDaoI getIngresosVsEgresosDAO() {
        if (ingresosVsEgresosDao == null) {
            ingresosVsEgresosDao = new IngresosVsEgresosDao();
        }
        return ingresosVsEgresosDao;
    }

    @Override
    public void crearCuenta(Cuenta cuenta) {
        getCuentaDAO().crearCuenta(cuenta);
    }

    @Override
    public void editarCuenta(Cuenta cuenta) {
        getCuentaDAO().editarCuenta(cuenta);
    }

    @Override
    public List<Cuenta> obtenerListaCuenta() {
        return getCuentaDAO().obtenerListaCuenta();
    }

    @Override
    public Cuenta obtenerCuentaPorId(int id) {
        return getCuentaDAO().obtenerCuentaPorId(id);
    }

    @Override
    public List<TipoCategoria> obtenerTipoCategoriaList() {
        return getTipoCategoriaDAO().obtenerTipoCategoriaList();
    }

    @Override
    public List<CategoriaMovimiento> obtenerCategoriaMovimientoList(TipoCategoria tipoCategoria) {
        return getCategoriaMovimientoDAO().obtenerCategoriaMovimientoList(tipoCategoria);
    }

    @Override
    public void crearCategoria(CategoriaMovimiento categoria) {
        getCategoriaMovimientoDAO().crearCategoria(categoria);
    }

    @Override
    public void editarCategoria(CategoriaMovimiento categoria) {
        getCategoriaMovimientoDAO().editarCategoria(categoria);

    }

    @Override
    public CategoriaMovimiento obtenerCategoriaPorNombre(String nombreCategoria) {
        return getCategoriaMovimientoDAO().obtenerCategoriaPorNombre(nombreCategoria);
    }

    @Override
    public void crearMovimiento(Movimiento movimiento) {
        getMovimientoDAO().crearMovimiento(movimiento);
    }

    @Override
    public List<Movimiento> obtenerMovimientosPorCuenta(int idCuenta) {
        return getMovimientoDAO().obtenerMovimientosPorCuenta(idCuenta);
    }

    @Override
    public List<Movimiento> obtenerMovimientoPorPeriodo(Date fechaInicial, Date fechaFinal) {
        return getMovimientoDAO().obtenerMovimientoPorPeriodo(fechaInicial, fechaFinal);
    }

    @Override
    public List<Movimiento> obtenerMovimientoPorCategoria(Integer idCategoria) {
        return getMovimientoDAO().obtenerMovimientoPorCategoria(idCategoria);
    }

    @Override
    public List<Movimiento> obtenerMovimientoPorTipo(Integer idTipo) {
        return getMovimientoDAO().obtenerMovimientoPorTipo(idTipo);
    }

    @Override
    public List<IngresosVsEgresos> obtenerIngresosEgresos() {
        return getIngresosVsEgresosDAO().obtenerIngresosEgresos();
    }

}
