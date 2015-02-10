/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "sala")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sala.findAll", query = "SELECT s FROM Sala s"),
    @NamedQuery(name = "Sala.findByIdSala", query = "SELECT s FROM Sala s WHERE s.salaPK.idSala = :idSala"),
    @NamedQuery(name = "Sala.findByIdSede", query = "SELECT s FROM Sala s WHERE s.salaPK.idSede = :idSede"),
    @NamedQuery(name = "Sala.findByCapacidad", query = "SELECT s FROM Sala s WHERE s.capacidad = :capacidad")})
public class Sala implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SalaPK salaPK;
    @Column(name = "capacidad")
    private Integer capacidad;
    @JoinColumn(name = "idSede", referencedColumnName = "idSede", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Sede sede;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sala", fetch = FetchType.EAGER)
    private List<Funcion> funcionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sala", fetch = FetchType.EAGER)
    private List<Silla> sillaList;

    public Sala() {
    }

    public Sala(SalaPK salaPK) {
        this.salaPK = salaPK;
    }

    public Sala(int idSala, int idSede) {
        this.salaPK = new SalaPK(idSala, idSede);
    }

    public SalaPK getSalaPK() {
        return salaPK;
    }

    public void setSalaPK(SalaPK salaPK) {
        this.salaPK = salaPK;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    @XmlTransient
    public List<Funcion> getFuncionList() {
        return funcionList;
    }

    public void setFuncionList(List<Funcion> funcionList) {
        this.funcionList = funcionList;
    }

    @XmlTransient
    public List<Silla> getSillaList() {
        return sillaList;
    }

    public void setSillaList(List<Silla> sillaList) {
        this.sillaList = sillaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (salaPK != null ? salaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sala)) {
            return false;
        }
        Sala other = (Sala) object;
        if ((this.salaPK == null && other.salaPK != null) || (this.salaPK != null && !this.salaPK.equals(other.salaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.modelo.Sala[ salaPK=" + salaPK + " ]";
    }
    
}
