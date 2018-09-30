/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.implement;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.eclipse.persistence.config.CacheUsage;
import org.eclipse.persistence.config.QueryHints;
import persistencia.SistemaDaoI;

/**
 *
 * @author usuario
 */
public class SistemaDAO implements SistemaDaoI {

    private Persistencia persistencia;

    public Persistencia getPersistencia() {
        if (persistencia == null) {
            persistencia = new Persistencia();
        }
        return persistencia;
    }

    @Override
    public <T> List<T> buscarPorConsultaNativa(String consultaNativa, Class<T> tipoEntidad,
            Object... parametros) {
        try {
            Query consulta;
            if (tipoEntidad != null) {
                consulta = getPersistencia().getEntityManager().createNativeQuery(consultaNativa, tipoEntidad);
            } else {
                consulta = getPersistencia().getEntityManager().createNativeQuery(consultaNativa);
            }
            if (parametros != null) {
                int i = 1;
                for (Object parametro : parametros) {
                    if (parametro != null) {
                        consulta.setParameter(i, parametro);
                    }
                    i++;
                }
            }

            List<T> resultado = consulta.getResultList();

            return resultado;
        } catch (Exception e) {
            System.out.println("ERROR AL EJECUTAR CONSULTA: " + consultaNativa + " --- " + e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public <T> T editar(T entidad) {
        EntityTransaction tx = getPersistencia().getEntityManager().getTransaction();
        try {

            tx.begin();
            getPersistencia().getEntityManager().merge(entidad);
            getPersistencia().getEntityManager().flush();
            tx.commit();
            return entidad;
        } catch (Exception e) {
            System.out.println("ERROR AL EDITAR : " + e.getMessage());
        }
        return null;
    }

    @Override
    public <T> T crear(T entidad) {
        try {
            EntityTransaction tx = getPersistencia().getEntityManager().getTransaction();
            tx.begin();
            getPersistencia().getEntityManager().persist(entidad);
            getPersistencia().getEntityManager().flush();
            tx.commit();
            return entidad;
        } catch (Exception e) {
            System.out.println("ERROR AL CREAR : " + e.getMessage());
        }
        return null;
    }

    @Override
    public <T> void borrar(T entidad) {
        try {
            EntityTransaction tx = getPersistencia().getEntityManager().getTransaction();
            tx.begin();
            getPersistencia().getEntityManager().remove(getPersistencia().getEntityManager().merge(entidad));
            tx.commit();
        } catch (Exception e) {
            System.out.println("ERROR AL BORRAR : " + e.getMessage());
        }
    }

    @Override
    public <T> List<T> findByNamedQuery(String namedQuery, Class<T> tipoEntidad, String[] parameterNames, Object[] parameterValues) {
        try {
            System.out.println("---------findByNamedQuery");
            if (parameterNames.length != parameterValues.length) {
                throw new Exception("Intento de ejecución de query " + namedQuery + " debe tener igual cantidad de nombres y valores de parámetros");
            }

            Query q = getPersistencia().getEntityManager().createNamedQuery(namedQuery, tipoEntidad).setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache);
            for (int i = 0; i < parameterNames.length; i++) {
                q.setParameter(parameterNames[i], parameterValues[i]);
            }
            List<T> lista = q.getResultList();
            System.out.println("---------" + lista.get(0));
            return lista;
        } catch (Exception ex) {
            System.out.println("ERROR AL EJECUTAR NAMEDQUERY : " + ex.getMessage());
            return null;
        }
    }

}
