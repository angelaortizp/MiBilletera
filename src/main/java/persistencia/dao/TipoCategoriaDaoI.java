/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.dao;

import java.util.List;
import logica.entidades.TipoCategoria;

/**
 *
 * @author usuario
 */
public interface TipoCategoriaDaoI {

    public List<TipoCategoria> obtenerTipoCategoriaList();

}
