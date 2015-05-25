/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author german
 */
@Entity
@Table(name = "Promociones")
public class Promociones implements Serializable {
    @Id
    @Column(name = "Id")
    private int id;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "fechafin")
    @Temporal(TemporalType.DATE)
    private Date fechafin;
    @Column(name = "descuento")
    private Short descuento;
    @Column(name = "preciofinal")
    private int preciofinal;
    @ManyToOne
    @JoinColumn(name = "productos_id", referencedColumnName = "id")
	private Productos productosId;
    @OneToMany(mappedBy = "promocionesId")
    private List<Areaspagina> areaspaginaCollection;

    public Promociones() {
    }

    public Promociones(int id, String descripcion, Date fechafin, Short descuento, int preciofinal, Productos productosId, List<Areaspagina> areaspaginaCollection) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechafin = fechafin;
        this.descuento = descuento;
        this.preciofinal = preciofinal;
        this.productosId = productosId;
        this.areaspaginaCollection = areaspaginaCollection;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public Short getDescuento() {
        return descuento;
    }

    public void setDescuento(Short descuento) {
        this.descuento = descuento;
    }

    public int getPreciofinal() {
        return preciofinal;
    }

    public void setPreciofinal(int preciofinal) {
        this.preciofinal = preciofinal;
    }

    public Productos getProductosId() {
        return productosId;
    }

    public void setProductosId(Productos productosId) {
        this.productosId = productosId;
    }

    public List<Areaspagina> getAreaspaginaCollection() {
        return areaspaginaCollection;
    }

    public void setAreaspaginaCollection(List<Areaspagina> areaspaginaCollection) {
        this.areaspaginaCollection = areaspaginaCollection;
    }   
}
