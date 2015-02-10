/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "actores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actores.findAll", query = "SELECT a FROM Actores a"),
    @NamedQuery(name = "Actores.findById", query = "SELECT a FROM Actores a WHERE a.id = :id"),
    @NamedQuery(name = "Actores.findByNombre", query = "SELECT a FROM Actores a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Actores.findByCiudad", query = "SELECT a FROM Actores a WHERE a.ciudad = :ciudad"),
    @NamedQuery(name = "Actores.findByNacionalidad", query = "SELECT a FROM Actores a WHERE a.nacionalidad = :nacionalidad")})
public class Actores implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ciudad")
    private String ciudad;
    @Size(max = 20)
    @Column(name = "nacionalidad")
    private String nacionalidad;
    @JoinTable(name = "actores_por_pelicula", joinColumns = {
        @JoinColumn(name = "idActor", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "idPelicula", referencedColumnName = "idPelicula")})
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Pelicula> peliculaList;

    public Actores() {
    }

    public Actores(Integer id) {
        this.id = id;
    }

    public Actores(Integer id, String nombre, String ciudad) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    @XmlTransient
    public List<Pelicula> getPeliculaList() {
        return peliculaList;
    }

    public void setPeliculaList(List<Pelicula> peliculaList) {
        this.peliculaList = peliculaList;
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
        if (!(object instanceof Actores)) {
            return false;
        }
        Actores other = (Actores) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.modelo.Actores[ id=" + id + " ]";
    }
    
}
