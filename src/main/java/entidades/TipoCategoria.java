/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "tipoCategoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCategoria.findAll", query = "SELECT t FROM TipoCategoria t")})
public class TipoCategoria implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
    public static final int INGRESO       = 1;
    public static final int EGRESO        = 2;
    public static final int TRANSFERENCIA = 3;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000000000)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoCategoria")
    private List<CategoriaMovimiento> categoriaMovimientoList;

    public TipoCategoria() {
    }

    public TipoCategoria(Integer id) {
        this.id = id;
    }

    public TipoCategoria(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    @XmlTransient
    public List<CategoriaMovimiento> getCategoriaMovimientoList() {
        return categoriaMovimientoList;
    }

    public void setCategoriaMovimientoList(List<CategoriaMovimiento> categoriaMovimientoList) {
        this.categoriaMovimientoList = categoriaMovimientoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCategoria)) {
            return false;
        }
        TipoCategoria other = (TipoCategoria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getNombre();
    }
    
}
