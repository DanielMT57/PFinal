/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author german
 */
@Entity
@Table(name = "Catalogo")
public class Catalogo implements Serializable {
    @Id
    @Column(name = "Id")
    private int id;
    @Column(name = "fechainicio")
    @Temporal(TemporalType.DATE)
    private Date fechainicio;
    @Column(name = "fechafin")
    @Temporal(TemporalType.DATE)
    private Date fechafin;
    @Column(name = "cantidadpaginas")
    private int cantidadpaginas;
    @Column(name = "cantidadproductos")
    private int cantidadproductos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "catalogoId")
    private Collection<Paginas> paginasCollection;

    public Catalogo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public int getCantidadpaginas() {
        return cantidadpaginas;
    }

    public void setCantidadpaginas(int cantidadpaginas) {
        this.cantidadpaginas = cantidadpaginas;
    }

    public int getCantidadproductos() {
        return cantidadproductos;
    }

    public void setCantidadproductos(int cantidadproductos) {
        this.cantidadproductos = cantidadproductos;
    }

    public Collection<Paginas> getPaginasCollection() {
        return paginasCollection;
    }

    public void setPaginasCollection(Collection<Paginas> paginasCollection) {
        this.paginasCollection = paginasCollection;
    }


  
}
