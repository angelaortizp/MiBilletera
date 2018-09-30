/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.dao;

import java.util.List;
import logica.entidades.Cuenta;

/**
 *
 * @author usuario
 */
public interface CuentaDaoI {

    public void crearCuenta(Cuenta cuenta);

    public void editarCuenta(Cuenta cuenta);

    public List<Cuenta> obtenerListaCuenta();

    public Cuenta obtenerCuentaPorId(int id);

}
