/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.dao.implement;

import persistencia.implement.SistemaDAO;
import java.util.Date;
import java.util.List;
import logica.entidades.Movimiento;
import persistencia.dao.MovimientoDaoI;
import persistencia.SistemaDaoI;
import utilidades.UtilidadesFecha;

/**
 *
 * @author usuario
 */
public class MovimientoDao implements MovimientoDaoI {

    private SistemaDaoI iSistemaDAO;

    public SistemaDaoI getSistemaDAO() {
        if (iSistemaDAO == null) {
            iSistemaDAO = new SistemaDAO();
        }
        return iSistemaDAO;
    }

    @Override
    public void crearMovimiento(Movimiento movimiento) {
        getSistemaDAO().crear(movimiento);
    }

    @Override
    public List<Movimiento> obtenerMovimientosPorCuenta(int idCuenta) {
        /**
         * Modifique el query nativo, ya que este metodo debe traer los
         * movimientos asociados a una cuenta, no necesita la información de la
         * cuenta pues ya la tiene.. porque quueries nativos?
         */
        return getSistemaDAO().buscarPorConsultaNativa(
                "SELECT * FROM MOVIMIENTO M WHERE M.idCuenta = ? ",
                Movimiento.class, idCuenta);
    }

    @Override
    public List<Movimiento> obtenerMovimientoPorPeriodo(Date fechaInicial, Date fechaFinal) {

        String DateInicial = UtilidadesFecha.obtenerStringPorDate(fechaInicial);
        String DateFinal = UtilidadesFecha.obtenerStringPorDate(fechaFinal);

        List<Movimiento> movimientosPeriodo = getSistemaDAO().buscarPorConsultaNativa(
                "SELECT * FROM MOVIMIENTO WHERE FECHA between ? AND ? ",
                Movimiento.class, DateInicial, DateFinal);

        return movimientosPeriodo;

    }

    @Override
    public List<Movimiento> obtenerMovimientoPorCategoria(Integer idCategoria) {
        return getSistemaDAO().buscarPorConsultaNativa(
                "SELECT * FROM movimiento WHERE idCategoriaMovimiento = ?",
                Movimiento.class, idCategoria);
    }

    @Override
    public List<Movimiento> obtenerMovimientoPorTipo(Integer idTipo) {
        return getSistemaDAO().buscarPorConsultaNativa(
                "SELECT m.* FROM movimiento m \n"
                + "INNER JOIN categoriaMovimiento cm \n"
                + "on (m.idCategoriaMovimiento = cm.id)\n"
                + "INNER JOIN tipoCategoria tc \n"
                + "on (cm.idTipoCategoria = tc.id)\n"
                + "WHERE tc.id = ?",
                Movimiento.class, idTipo);
    }

}
