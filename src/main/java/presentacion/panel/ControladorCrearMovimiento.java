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
public class ControladorCrearMovimiento implements ActionListener {

    private final PanelCrearMovimiento vista;

    public ControladorCrearMovimiento(PanelCrearMovimiento aThis) {
        vista = aThis;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Eventos evento = Eventos.valueOf(actionEvent.getActionCommand());
        if (evento != null) {
            switch (evento) {
                case CREAR_CATEGORIA: {
                    this.vista.getModelo().crearCategoria();
                }
                break;
                case CREAR_MOVIMIENTO: {
                    this.vista.getModelo().crearMovimiento();
                }
                break;
                case comboBoxEdited: {
                    this.vista.getModelo().crearCategoria();
                }
                break;
            }
        }
    }

}
