/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;

/**
 *
 * @author usuario
 */
public interface SistemaDaoI {

    public <T> List<T> buscarPorConsultaNativa(String consultaNativa, Class<T> tipoEntidad, Object... parametros);

    public <T> T editar(T entidad);

    public <T> T crear(T entidad);

    public <T> void borrar(T entidad);

    public <T> List<T> findByNamedQuery(String namedQuery, Class<T> tipoEntidad, String[] parameterNames, Object[] parameterValues);

}
