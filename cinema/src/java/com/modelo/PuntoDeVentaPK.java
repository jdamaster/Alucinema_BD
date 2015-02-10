/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Usuario
 */
@Embeddable
public class PuntoDeVentaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idSede")
    private int idSede;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPunto")
    private int idPunto;

    public PuntoDeVentaPK() {
    }

    public PuntoDeVentaPK(int idSede, int idPunto) {
        this.idSede = idSede;
        this.idPunto = idPunto;
    }

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }

    public int getIdPunto() {
        return idPunto;
    }

    public void setIdPunto(int idPunto) {
        this.idPunto = idPunto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idSede;
        hash += (int) idPunto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PuntoDeVentaPK)) {
            return false;
        }
        PuntoDeVentaPK other = (PuntoDeVentaPK) object;
        if (this.idSede != other.idSede) {
            return false;
        }
        if (this.idPunto != other.idPunto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.modelo.PuntoDeVentaPK[ idSede=" + idSede + ", idPunto=" + idPunto + " ]";
    }
    
}
