/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.dao.implement;

import persistencia.implement.SistemaDAO;
import java.util.List;
import logica.entidades.CategoriaMovimiento;
import logica.entidades.TipoCategoria;
import persistencia.dao.CategoriaMovimientoDaoI;
import persistencia.SistemaDaoI;

/**
 *
 * @author usuario
 */
public class CategoriaMovimientoDao implements CategoriaMovimientoDaoI {

    private SistemaDaoI iSistemaDAO;

    public SistemaDaoI getSistemaDAO() {
        if (iSistemaDAO == null) {
            iSistemaDAO = new SistemaDAO();
        }
        return iSistemaDAO;
    }

    @Override
    public void crearCategoria(CategoriaMovimiento categoria) {
        getSistemaDAO().crear(categoria);
    }

    @Override
    public void editarCategoria(CategoriaMovimiento categoria) {
        getSistemaDAO().editar(categoria);

    }

    @Override
    public CategoriaMovimiento obtenerCategoriaPorNombre(String nombreCategoria) {
        List<CategoriaMovimiento> categoriaMovimientoList = getSistemaDAO().buscarPorConsultaNativa(
                "SELECT * FROM categoriaMovimiento WHERE nombre = ? ",
                CategoriaMovimiento.class, nombreCategoria);
        if (categoriaMovimientoList != null && !categoriaMovimientoList.isEmpty()) {
            return categoriaMovimientoList.get(0);
        }
        return null;
    }

    @Override
    public List<CategoriaMovimiento> obtenerCategoriaMovimientoList(TipoCategoria tipoCategoria) {
        return getSistemaDAO().buscarPorConsultaNativa("SELECT * FROM CATEGORIAMOVIMIENTO CM WHERE CM.idTipoCategoria = ? ",
                CategoriaMovimiento.class, tipoCategoria.getId());
    }

}
