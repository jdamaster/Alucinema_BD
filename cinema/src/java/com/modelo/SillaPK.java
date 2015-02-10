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
public class SillaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idSilla")
    private int idSilla;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idSala")
    private int idSala;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idSede")
    private int idSede;

    public SillaPK() {
    }

    public SillaPK(int idSilla, int idSala, int idSede) {
        this.idSilla = idSilla;
        this.idSala = idSala;
        this.idSede = idSede;
    }

    public int getIdSilla() {
        return idSilla;
    }

    public void setIdSilla(int idSilla) {
        this.idSilla = idSilla;
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idSilla;
        hash += (int) idSala;
        hash += (int) idSede;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SillaPK)) {
            return false;
        }
        SillaPK other = (SillaPK) object;
        if (this.idSilla != other.idSilla) {
            return false;
        }
        if (this.idSala != other.idSala) {
            return false;
        }
        if (this.idSede != other.idSede) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.modelo.SillaPK[ idSilla=" + idSilla + ", idSala=" + idSala + ", idSede=" + idSede + " ]";
    }
    
}
