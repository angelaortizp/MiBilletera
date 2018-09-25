/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.eclipse.persistence.config.CacheUsage;
import org.eclipse.persistence.config.QueryHints;

/**
 *
 * @author Estudiantes
 */
public class Persistencia {

    private EntityManagerFactory factory;
    private EntityManager entityManager;

    public void connect() {
        try {
            //step 1
            factory = Persistence.createEntityManagerFactory("miBilleteraPU");
            //step 2
            entityManager = factory.createEntityManager();
        } catch (Exception ex) {
            System.err.println("ERROR AL CONECTAR A LA BASE DE DATOS: " + ex.getMessage());
        }
    }

    public void close() {
        try {
            entityManager.close();
            factory.close();
        } catch (Exception ex) {
            System.err.println("ERROR AL DESCONECTAR DE LA BASE DE DATOS: " + ex.getMessage());
        }
    }

    public <T> List<T> buscarPorConsultaNativa(String consultaNativa, Class<T> tipoEntidad,
            Object... parametros) {
        factory.getCache().evictAll();
        try {
            Query consulta;
            if (tipoEntidad != null) {
                consulta = entityManager.createNativeQuery(consultaNativa, tipoEntidad);
            } else {
                consulta = entityManager.createNativeQuery(consultaNativa);
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

    public <T> T editar(T entidad) {
        EntityTransaction tx = entityManager.getTransaction();
        try {

            tx.begin();
            entityManager.merge(entidad);
            entityManager.flush();
            tx.commit();            
            return entidad;
        } catch (Exception e) {
            System.out.println("ERROR AL EDITAR : " + e.getMessage());
        }
        return null;
    }

    public <T> T crear(T entidad) {
        try {
            EntityTransaction tx = entityManager.getTransaction();
            tx.begin();
            entityManager.persist(entidad);
            entityManager.flush();
            tx.commit();            
            return entidad;
        } catch (Exception e) {
            System.out.println("ERROR AL CREAR : " + e.getMessage());
        }
        return null;
    }

    public <T> void borrar(T entidad) {
        try {
            EntityTransaction tx = entityManager.getTransaction();
            tx.begin();
            entityManager.remove(entityManager.merge(entidad));
            tx.commit();
        } catch (Exception e) {
            System.out.println("ERROR AL BORRAR : " + e.getMessage());
        }
    }

    public <T> List<T> findByNamedQuery(String namedQuery, Class<T> tipoEntidad, String[] parameterNames, Object[] parameterValues) {
        try {
            System.out.println("---------findByNamedQuery");
            if (parameterNames.length != parameterValues.length) {
                throw new Exception("Intento de ejecución de query " + namedQuery + " debe tener igual cantidad de nombres y valores de parámetros");
            }
           factory.getCache().evictAll();

            Query q = entityManager.createNamedQuery(namedQuery, tipoEntidad).setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache);
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
