/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import logica.entidades.CategoriaMovimiento;
import logica.entidades.Cuenta;
import logica.entidades.IngresosVsEgresos;
import logica.entidades.Movimiento;
import logica.entidades.TipoCategoria;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Henry
 */
public interface LogicaPrincipalI {

    /**
     * Permite conectar a la base de datos
     */
    public void conectarABaseDeDatos();

    /**
     * Crea una nueva CUENTA
     *
     * @param cuenta
     */
    public void crearCuenta(Cuenta cuenta);

    /**
     * Editar una CUENTA
     *
     * @param cuenta
     */
    public void editarCuenta(Cuenta cuenta);

    /**
     * Obtiene la lista de cuentas
     *
     * @return Todas las cuentas
     */
    public List<Cuenta> obtenerListaCuenta();

    /**
     * Obtiene una cuenta en particular
     *
     * @param id El id de la cuenta
     * @return La cuenta encontrada
     */
    public Cuenta obtenerCuentaPorId(int id);

    /**
     * Obtiene todos los tipos de categoria
     *
     * @return Los tipos de categoria que cumplen con el criterio de busqueda
     */
    public List<TipoCategoria> obtenerTipoCategoriaList();

    /**
     * Obtiene las categorias de los movimientos por tipo de categoria
     *
     * @param tipoCategoria El tipo de categoria
     * @return Las categorias de movimiento que cumplen con el criterio de
     * busqueda
     */
    public List<CategoriaMovimiento> obtenerCategoriaMovimientoList(TipoCategoria tipoCategoria);

    /**
     * Crea una nueva categoria de movimiento
     *
     * @param categoria La categoria de movimiento a crear
     */
    public void crearCategoria(CategoriaMovimiento categoria);

    /**
     * Edita una categoria de movimiento
     *
     * @param categoria La categoria de movimiento a editar
     */
    public void editarCategoria(CategoriaMovimiento categoria);

    /**
     * Obtiene las categorias de movimiento segun el nombre
     *
     * @param nombreCategoria el nombre por cual se realiza la busqueda
     * @return Las categorias de movimiento que cumplen con el criterio de
     * busqueda
     */
    public CategoriaMovimiento obtenerCategoriaPorNombre(String nombreCategoria);

    /**
     * Crea un nuevo movimiento
     *
     * @param movimiento El movimiento a crear
     */
    public void crearMovimiento(Movimiento movimiento);

    /**
     * Obtiene los movimientos de una cuenta determinada
     *
     * @param idCuenta El id de la cuenta
     * @return Los movimientos que cumplen con el criterio de busqueda
     */
    public List<Movimiento> obtenerMovimientosPorCuenta(int idCuenta);

    /**
     * Obtiene los movimientos en un periodo determinado
     *
     * @param fechaInicial La fecha inicial del periodo
     * @param fechaFinal La fecha final del periodo
     * @return Los movimientos que cumplen con el criterio de busqueda
     */
    public List<Movimiento> obtenerMovimientoPorPeriodo(Date fechaInicial, Date fechaFinal);

    /**
     * Obtiene los movimientos de una categoria determinada
     *
     * @param idCategoria El id de la categoria
     * @return Los movimientos que cumplen con el criterio de busqueda
     */
    public List<Movimiento> obtenerMovimientoPorCategoria(Integer idCategoria);

    /**
     * Obtiene los movimientos de un tipo determinado
     *
     * @param idTipo El id del tipo
     * @return Los movimientos que cumplen con el criterio de busqueda
     */
    public List<Movimiento> obtenerMovimientoPorTipo(Integer idTipo);

    /**
     * Obtiene la relacion de ingresos vs egresos
     *
     * @return el resultado de ejecutar la vista IngresosVsEgresos
     */
    public List<IngresosVsEgresos> obtenerIngresosEgresos();

}
