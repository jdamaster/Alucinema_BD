/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name = "silla")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Silla.findAll", query = "SELECT s FROM Silla s"),
    @NamedQuery(name = "Silla.findByIdSilla", query = "SELECT s FROM Silla s WHERE s.sillaPK.idSilla = :idSilla"),
    @NamedQuery(name = "Silla.findByIdSala", query = "SELECT s FROM Silla s WHERE s.sillaPK.idSala = :idSala"),
    @NamedQuery(name = "Silla.findByIdSede", query = "SELECT s FROM Silla s WHERE s.sillaPK.idSede = :idSede"),
    @NamedQuery(name = "Silla.findByNumero", query = "SELECT s FROM Silla s WHERE s.numero = :numero"),
    @NamedQuery(name = "Silla.findByTipo", query = "SELECT s FROM Silla s WHERE s.tipo = :tipo")})
public class Silla implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SillaPK sillaPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "numero")
    private String numero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "tipo")
    private String tipo;
    @JoinColumns({
            @JoinColumn(name = "idSala", referencedColumnName = "idSala", insertable = false, updatable = false),
            @JoinColumn(name = "idSede", referencedColumnName = "idSede", insertable = false, updatable = false)
     })
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Sala sala;
    @JoinColumn(name = "idSede", referencedColumnName = "idSede", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Sede sede;

    public Silla() {
    }

    public Silla(SillaPK sillaPK) {
        this.sillaPK = sillaPK;
    }

    public Silla(SillaPK sillaPK, String numero, String tipo) {
        this.sillaPK = sillaPK;
        this.numero = numero;
        this.tipo = tipo;
    }

    public Silla(int idSilla, int idSala, int idSede) {
        this.sillaPK = new SillaPK(idSilla, idSala, idSede);
    }

    public SillaPK getSillaPK() {
        return sillaPK;
    }

    public void setSillaPK(SillaPK sillaPK) {
        this.sillaPK = sillaPK;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
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
        hash += (sillaPK != null ? sillaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Silla)) {
            return false;
        }
        Silla other = (Silla) object;
        if ((this.sillaPK == null && other.sillaPK != null) || (this.sillaPK != null && !this.sillaPK.equals(other.sillaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.modelo.Silla[ sillaPK=" + sillaPK + " ]";
    }
    
}
