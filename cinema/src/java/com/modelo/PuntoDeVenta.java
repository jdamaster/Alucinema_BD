/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "punto_de_venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PuntoDeVenta.findAll", query = "SELECT p FROM PuntoDeVenta p"),
    @NamedQuery(name = "PuntoDeVenta.findByIdSede", query = "SELECT p FROM PuntoDeVenta p WHERE p.puntoDeVentaPK.idSede = :idSede"),
    @NamedQuery(name = "PuntoDeVenta.findByIdPunto", query = "SELECT p FROM PuntoDeVenta p WHERE p.puntoDeVentaPK.idPunto = :idPunto"),
    @NamedQuery(name = "PuntoDeVenta.findByAdministrador", query = "SELECT p FROM PuntoDeVenta p WHERE p.administrador = :administrador")})
public class PuntoDeVenta implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PuntoDeVentaPK puntoDeVentaPK;
    @Size(max = 30)
    @Column(name = "administrador")
    private String administrador;
    @JoinColumn(name = "idSede", referencedColumnName = "idSede", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Sede sede;

    public PuntoDeVenta() {
    }

    public PuntoDeVenta(PuntoDeVentaPK puntoDeVentaPK) {
        this.puntoDeVentaPK = puntoDeVentaPK;
    }

    public PuntoDeVenta(int idSede, int idPunto) {
        this.puntoDeVentaPK = new PuntoDeVentaPK(idSede, idPunto);
    }

    public PuntoDeVentaPK getPuntoDeVentaPK() {
        return puntoDeVentaPK;
    }

    public void setPuntoDeVentaPK(PuntoDeVentaPK puntoDeVentaPK) {
        this.puntoDeVentaPK = puntoDeVentaPK;
    }

    public String getAdministrador() {
        return administrador;
    }

    public void setAdministrador(String administrador) {
        this.administrador = administrador;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (puntoDeVentaPK != null ? puntoDeVentaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PuntoDeVenta)) {
            return false;
        }
        PuntoDeVenta other = (PuntoDeVenta) object;
        if ((this.puntoDeVentaPK == null && other.puntoDeVentaPK != null) || (this.puntoDeVentaPK != null && !this.puntoDeVentaPK.equals(other.puntoDeVentaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.modelo.PuntoDeVenta[ puntoDeVentaPK=" + puntoDeVentaPK + " ]";
    }
    
}
