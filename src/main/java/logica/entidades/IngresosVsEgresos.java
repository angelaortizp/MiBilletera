/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "IngresosVsEgresos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IngresosVsEgresos.findAll", query = "SELECT i FROM IngresosVsEgresos i")})
public class IngresosVsEgresos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "id")
    @Id
    private Integer id;
    @Size(max = 2000000000)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 2000000000)
    @Column(name = "sumatoria")
    private String sumatoria;

    public IngresosVsEgresos() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSumatoria() {
        return sumatoria;
    }

    public void setSumatoria(String sumatoria) {
        this.sumatoria = sumatoria;
    }
    
}
