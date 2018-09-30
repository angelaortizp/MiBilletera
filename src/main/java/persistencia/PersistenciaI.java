/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import javax.persistence.EntityManager;

/**
 *
 * @author usuario
 */
public interface PersistenciaI {

    public void connect();

    public void close();

    public EntityManager getEntityManager();

}
