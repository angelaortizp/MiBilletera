/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.dao.implement;

import persistencia.implement.SistemaDAO;
import java.util.ArrayList;
import java.util.List;
import logica.entidades.IngresosVsEgresos;
import persistencia.dao.IngresosVsEgresosDaoI;
import persistencia.SistemaDaoI;

/**
 *
 * @author usuario
 */
public class IngresosVsEgresosDao implements IngresosVsEgresosDaoI {

    private SistemaDaoI iSistemaDAO;

    public SistemaDaoI getSistemaDAO() {
        if (iSistemaDAO == null) {
            iSistemaDAO = new SistemaDAO();
        }
        return iSistemaDAO;
    }

    @Override
    public List<IngresosVsEgresos> obtenerIngresosEgresos() {
        List<Object[]> objectList = getSistemaDAO().buscarPorConsultaNativa(
                "select * from IngresosVsEgresos", null);
        List<IngresosVsEgresos> ingresosVsEgresosList = new ArrayList<>();
        for (Object[] o : objectList) {
            if (o.length > 2) {
                IngresosVsEgresos ive = new IngresosVsEgresos();
                ive.setId((Integer) o[0]);
                ive.setNombre((String) o[1]);
                ive.setSumatoria(((Integer) o[2]).toString());
                ingresosVsEgresosList.add(ive);
            }

        }
        return ingresosVsEgresosList;
    }

}
