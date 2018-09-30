/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.dao;

import java.util.Date;
import java.util.List;
import logica.entidades.Movimiento;

/**
 *
 * @author usuario
 */
public interface MovimientoDaoI {

    public void crearMovimiento(Movimiento movimiento);

    public List<Movimiento> obtenerMovimientosPorCuenta(int idCuenta);
    
    public List<Movimiento> obtenerMovimientoPorPeriodo(Date fechaInicial, Date fechaFinal);
    
    public List<Movimiento> obtenerMovimientoPorCategoria(Integer idCategoria);
    
    public List<Movimiento> obtenerMovimientoPorTipo(Integer idTipo);

}
