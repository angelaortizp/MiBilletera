/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author usuario
 */
public class UtilidadesFecha {

    private final static String FORMATO_FECHA = "YYYY-MM-dd";

    public static String obtenerStringPorDate(Date fecha) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(FORMATO_FECHA);
            return dateFormat.format(fecha);
        } catch (Exception ex) {
            return null;
        }
    }

    public static Date obtenerDatePorString(String string) {
        DateFormat dateFormat = new SimpleDateFormat(FORMATO_FECHA);
        try {
            return dateFormat.parse(string);
        } catch (ParseException e) {
            return null;
        }
    }

}
