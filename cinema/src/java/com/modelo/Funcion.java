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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "funcion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Funcion.findAll", query = "SELECT f FROM Funcion f"),
    @NamedQuery(name = "Funcion.findByIdSede", query = "SELECT f FROM Funcion f WHERE f.funcionPK.idSede = :idSede"),
    @NamedQuery(name = "Funcion.findByIdPelicula", query = "SELECT f FROM Funcion f WHERE f.funcionPK.idPelicula = :idPelicula"),
    @NamedQuery(name = "Funcion.findByIdSala", query = "SELECT f FROM Funcion f WHERE f.funcionPK.idSala = :idSala"),
    @NamedQuery(name = "Funcion.findByFecha", query = "SELECT f FROM Funcion f WHERE f.funcionPK.fecha = :fecha"),
    @NamedQuery(name = "Funcion.findByDuracion", query = "SELECT f FROM Funcion f WHERE f.duracion = :duracion"),
    @NamedQuery(name = "Funcion.findByCalidad", query = "SELECT f FROM Funcion f WHERE f.calidad = :calidad"),
    @NamedQuery(name = "Funcion.findByTipo", query = "SELECT f FROM Funcion f WHERE f.tipo = :tipo")})
public class Funcion implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FuncionPK funcionPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "duracion")
    private String duracion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "calidad")
    private String calidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "tipo")
    private String tipo;
    @JoinColumn(name = "idSede", referencedColumnName = "idSede", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Sede sede;
    @JoinColumn(name = "idPelicula", referencedColumnName = "idPelicula", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Pelicula pelicula;
    @JoinColumn(name = "idSala", referencedColumnName = "idSala", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Sala sala;

    public Funcion() {
    }

    public Funcion(FuncionPK funcionPK) {
        this.funcionPK = funcionPK;
    }

    public Funcion(FuncionPK funcionPK, String duracion, String calidad, String tipo) {
        this.funcionPK = funcionPK;
        this.duracion = duracion;
        this.calidad = calidad;
        this.tipo = tipo;
    }

    public Funcion(int idSede, int idPelicula, int idSala, Date fecha) {
        this.funcionPK = new FuncionPK(idSede, idPelicula, idSala, fecha);
    }

    public FuncionPK getFuncionPK() {
        return funcionPK;
    }

    public void setFuncionPK(FuncionPK funcionPK) {
        this.funcionPK = funcionPK;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getCalidad() {
        return calidad;
    }

    public void setCalidad(String calidad) {
        this.calidad = calidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (funcionPK != null ? funcionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcion)) {
            return false;
        }
        Funcion other = (Funcion) object;
        if ((this.funcionPK == null && other.funcionPK != null) || (this.funcionPK != null && !this.funcionPK.equals(other.funcionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.modelo.Funcion[ funcionPK=" + funcionPK + " ]";
    }
    
}
