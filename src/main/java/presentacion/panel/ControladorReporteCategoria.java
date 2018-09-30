/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.panel;

import presentacion.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Estudiantes
 */
public class ControladorReporteCategoria implements ActionListener {

    private final PanelReporteCategoria vista;

    public ControladorReporteCategoria(PanelReporteCategoria aThis) {
        vista = aThis;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Eventos evento = Eventos.valueOf(actionEvent.getActionCommand());
        if (evento != null) {
            switch (evento) {
                case CALCULAR_REPORTE_CATEGORIA: {
                    this.vista.getModelo().calcularReporteCategoria();
                }
                break;
            }
        }
    }

}
