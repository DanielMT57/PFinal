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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author german
 */
@Entity
@Table(name = "Pedidos")
@Inheritance(strategy=InheritanceType.JOINED)
public class Pedidos implements Serializable {
    @Id
    @Column(name = "Id")
    private int id;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "sincronizado")
    private Character sincronizado;
    private String descripcion;
    @JoinColumn(name = "estado_id", referencedColumnName = "Id")
    @ManyToOne
    private Estado estadoId;
    @JoinColumn(name = "afiliados_cedula", referencedColumnName = "cedula")
    @ManyToOne
    private Afiliados afiliadosCedula;
    @OneToMany(mappedBy = "pedidos")
    private List<Detallepedido> detallepedidoCollection;

    public Pedidos() {
    }

    public Pedidos(int id, Date fecha, Character sincronizado,String descripcion, Estado estadoId, Afiliados afiliadosCedula, List<Detallepedido> detallepedidoCollection) {
        this.id = id;
        this.fecha = fecha;
        this.sincronizado = sincronizado;
        this.descripcion=descripcion;
        this.estadoId = estadoId;
        this.afiliadosCedula = afiliadosCedula;
        this.detallepedidoCollection = detallepedidoCollection;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Character getSincronizado() {
        return sincronizado;
    }

    public void setSincronizado(Character sincronizado) {
        this.sincronizado = sincronizado;
    }

    public Estado getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Estado estadoId) {
        this.estadoId = estadoId;
    }

    public Afiliados getAfiliadosCedula() {
        return afiliadosCedula;
    }

    public void setAfiliadosCedula(Afiliados afiliadosCedula) {
        this.afiliadosCedula = afiliadosCedula;
    }

    public List<Detallepedido> getDetallepedidoCollection() {
        return detallepedidoCollection;
    }

    public void setDetallepedidoCollection(List<Detallepedido> detallepedidoCollection) {
        this.detallepedidoCollection = detallepedidoCollection;
    }

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
    
    
   
}
