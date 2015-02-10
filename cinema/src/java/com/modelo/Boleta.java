/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "boleta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Boleta.findAll", query = "SELECT b FROM Boleta b"),
    @NamedQuery(name = "Boleta.findByIdBoleta", query = "SELECT b FROM Boleta b WHERE b.idBoleta = :idBoleta"),
    @NamedQuery(name = "Boleta.findByIdSala", query = "SELECT b FROM Boleta b WHERE b.idSala = :idSala"),
    @NamedQuery(name = "Boleta.findByIdPelicula", query = "SELECT b FROM Boleta b WHERE b.idPelicula = :idPelicula"),
    @NamedQuery(name = "Boleta.findByHora", query = "SELECT b FROM Boleta b WHERE b.hora = :hora"),
    @NamedQuery(name = "Boleta.findByIdSede", query = "SELECT b FROM Boleta b WHERE b.idSede = :idSede"),
    @NamedQuery(name = "Boleta.findByIdSilla", query = "SELECT b FROM Boleta b WHERE b.idSilla = :idSilla"),
    @NamedQuery(name = "Boleta.findByIdPunto", query = "SELECT b FROM Boleta b WHERE b.idPunto = :idPunto"),
    @NamedQuery(name = "Boleta.findByTipoDocumento", query = "SELECT b FROM Boleta b WHERE b.tipoDocumento = :tipoDocumento"),
    @NamedQuery(name = "Boleta.findByDocumento", query = "SELECT b FROM Boleta b WHERE b.documento = :documento"),
    @NamedQuery(name = "Boleta.findByPagada", query = "SELECT b FROM Boleta b WHERE b.pagada = :pagada")})
public class Boleta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idBoleta")
    private Integer idBoleta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idSala")
    private int idSala;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPelicula")
    private int idPelicula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "hora")
    private String hora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idSede")
    private int idSede;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idSilla")
    private int idSilla;
    @Column(name = "idPunto")
    private Integer idPunto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "tipoDocumento")
    private String tipoDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "documento")
    private String documento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pagada")
    private boolean pagada;

    public Boleta() {
    }

    public Boleta(Integer idBoleta) {
        this.idBoleta = idBoleta;
    }

    public Boleta(Integer idBoleta, int idSala, int idPelicula, String hora, int idSede, int idSilla, String tipoDocumento, String documento, boolean pagada) {
        this.idBoleta = idBoleta;
        this.idSala = idSala;
        this.idPelicula = idPelicula;
        this.hora = hora;
        this.idSede = idSede;
        this.idSilla = idSilla;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.pagada = pagada;
    }

    public Integer getIdBoleta() {
        return idBoleta;
    }

    public void setIdBoleta(Integer idBoleta) {
        this.idBoleta = idBoleta;
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }

    public int getIdSilla() {
        return idSilla;
    }

    public void setIdSilla(int idSilla) {
        this.idSilla = idSilla;
    }

    public Integer getIdPunto() {
        return idPunto;
    }

    public void setIdPunto(Integer idPunto) {
        this.idPunto = idPunto;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public boolean getPagada() {
        return pagada;
    }

    public void setPagada(boolean pagada) {
        this.pagada = pagada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBoleta != null ? idBoleta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Boleta)) {
            return false;
        }
        Boleta other = (Boleta) object;
        if ((this.idBoleta == null && other.idBoleta != null) || (this.idBoleta != null && !this.idBoleta.equals(other.idBoleta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.modelo.Boleta[ idBoleta=" + idBoleta + " ]";
    }
    
}
