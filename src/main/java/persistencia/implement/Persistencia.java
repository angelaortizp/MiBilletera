/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.implement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import persistencia.PersistenciaI;

/**
 *
 * @author Estudiantes
 */
public class Persistencia implements PersistenciaI {

    private EntityManagerFactory factory;
    private EntityManager entityManager;

    @Override
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

    @Override
    public void close() {
        try {
            entityManager.close();
            factory.close();
        } catch (Exception ex) {
            System.err.println("ERROR AL DESCONECTAR DE LA BASE DE DATOS: " + ex.getMessage());
        }
    }

    @Override
    public EntityManager getEntityManager() {
        if (entityManager == null) {
            connect();
        }
        return entityManager;
    }

}
