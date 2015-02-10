/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Usuario
 */
@Embeddable
public class FuncionPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idSede")
    private int idSede;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPelicula")
    private int idPelicula;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idSala")
    private int idSala;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    public FuncionPK() {
    }

    public FuncionPK(int idSede, int idPelicula, int idSala, Date fecha) {
        this.idSede = idSede;
        this.idPelicula = idPelicula;
        this.idSala = idSala;
        this.fecha = fecha;
    }

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idSede;
        hash += (int) idPelicula;
        hash += (int) idSala;
        hash += (fecha != null ? fecha.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FuncionPK)) {
            return false;
        }
        FuncionPK other = (FuncionPK) object;
        if (this.idSede != other.idSede) {
            return false;
        }
        if (this.idPelicula != other.idPelicula) {
            return false;
        }
        if (this.idSala != other.idSala) {
            return false;
        }
        if ((this.fecha == null && other.fecha != null) || (this.fecha != null && !this.fecha.equals(other.fecha))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.modelo.FuncionPK[ idSede=" + idSede + ", idPelicula=" + idPelicula + ", idSala=" + idSala + ", fecha=" + fecha + " ]";
    }
    
}
