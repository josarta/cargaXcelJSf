/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.archivos.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JoseLuis
 */
@Entity
@Table(name = "rutas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rutas.findAll", query = "SELECT r FROM Rutas r"),
    @NamedQuery(name = "Rutas.findByRutaID", query = "SELECT r FROM Rutas r WHERE r.rutaID = :rutaID"),
    @NamedQuery(name = "Rutas.findByTiempo", query = "SELECT r FROM Rutas r WHERE r.tiempo = :tiempo")})
public class Rutas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "rutaID")
    private Integer rutaID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Tiempo")
    @Temporal(TemporalType.TIME)
    private Date tiempo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rutaID")
    private Collection<Tarifas> tarifasCollection;
    @JoinColumn(name = "ciudad_origen", referencedColumnName = "ciudadID")
    @ManyToOne(optional = false)
    private Ciudades ciudadOrigen;
    @JoinColumn(name = "ciudad_destino", referencedColumnName = "ciudadID")
    @ManyToOne(optional = false)
    private Ciudades ciudadDestino;

    public Rutas() {
    }

    public Rutas(Integer rutaID) {
        this.rutaID = rutaID;
    }

    public Rutas(Integer rutaID, Date tiempo) {
        this.rutaID = rutaID;
        this.tiempo = tiempo;
    }

    public Integer getRutaID() {
        return rutaID;
    }

    public void setRutaID(Integer rutaID) {
        this.rutaID = rutaID;
    }

    public Date getTiempo() {
        return tiempo;
    }

    public void setTiempo(Date tiempo) {
        this.tiempo = tiempo;
    }

    @XmlTransient
    public Collection<Tarifas> getTarifasCollection() {
        return tarifasCollection;
    }

    public void setTarifasCollection(Collection<Tarifas> tarifasCollection) {
        this.tarifasCollection = tarifasCollection;
    }

    public Ciudades getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(Ciudades ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public Ciudades getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(Ciudades ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rutaID != null ? rutaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rutas)) {
            return false;
        }
        Rutas other = (Rutas) object;
        if ((this.rutaID == null && other.rutaID != null) || (this.rutaID != null && !this.rutaID.equals(other.rutaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.archivos.entity.Rutas[ rutaID=" + rutaID + " ]";
    }
    
}
