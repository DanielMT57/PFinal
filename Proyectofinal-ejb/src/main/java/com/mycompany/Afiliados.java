/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
/**
 *
 * @author german
 */
@Entity
@Table(name = "Afiliados")
@PrimaryKeyJoinColumn(name = "cedula", referencedColumnName = "cedula")
public class Afiliados extends Personas {
    @Column(name = "fechaafiliacion")
    @Temporal(TemporalType.DATE)
    private Date fechaafiliacion;
    @Column(name = "SINCRONIZADO")
    private Character sincronizado;
    @OneToMany(mappedBy = "afiliadosCedula")
    private List<Pedidos> pedidosCollection;
//    @OneToOne
//    @JoinColumn(name = "personas_cedula", referencedColumnName = "cedula")
//	private Personas personas;
    @OneToOne
    @JoinColumn(name = "Afiliado", referencedColumnName = "cedula")
	private Afiliados afiliadosCollection;
    @ManyToOne
    @JoinColumn(name="niveles_id", referencedColumnName = "id")
    private Nivel nivel;

    public Afiliados() {
    }

    public Afiliados(Date fechaafiliacion, Character sincronizado, Nivel nivel, int cedula, long telefono, String nombre, String apellido, String direccion, Ciudades ciudad, String email) {
        super(cedula, telefono, nombre, apellido, direccion, email, ciudad, null);
        this.fechaafiliacion = fechaafiliacion;
        this.sincronizado = sincronizado;
        this.nivel = nivel;
    }

  

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public Afiliados getAfiliadosCollection() {
        return afiliadosCollection;
    }

    public void setAfiliadosCollection(Afiliados afiliadosCollection) {
        this.afiliadosCollection = afiliadosCollection;
    }
    

    public Date getFechaafiliacion() {
        return fechaafiliacion;
    }

    public void setFechaafiliacion(Date fechaafiliacion) {
        this.fechaafiliacion = fechaafiliacion;
    }

    public Character getSincronizado() {
        return sincronizado;
    }

    public void setSincronizado(Character sincronizado) {
        this.sincronizado = sincronizado;
    }

    public List<Pedidos> getPedidosCollection() {
        return pedidosCollection;
    }

    public void setPedidosCollection(List<Pedidos> pedidosCollection) {
        this.pedidosCollection = pedidosCollection;
    }

  

   
}
