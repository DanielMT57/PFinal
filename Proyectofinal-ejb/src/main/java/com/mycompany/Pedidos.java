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
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.annotations.ClassExtractor;

/**
 *
 * @author german
 */
@XmlRootElement(name = "pedidos")
@Entity
@Table(name = "Pedidos")
@NamedQuery(name = Pedidos.CONSULTA_LISTARTODOS, query = "SELECT  p From Pedidos p where p.estadoId.id=1")
public class Pedidos implements Serializable {
    public static final String CONSULTA_LISTARTODOS = "Pedidos.listartodos";
    @Id
    @Column(name = "Id")
    private int id;
    @JoinColumn(name = "afiliados_cedula",referencedColumnName = "cedula")
    @ManyToOne
    private Afiliados afiliadosCedula;
    @JoinColumn(name = "estado_id", referencedColumnName = "Id")
    @ManyToOne
    private Estado estadoId;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)   
    private Date fecha;
    @Column(name = "sincronizado")
    private Character sincronizado;
    
    private String descripcion;

    public Pedidos(int id) {
        this.id = id;
    }

    public Pedidos() {
    }

    
    
    @OneToMany(mappedBy = "pedidos")
    private List<Detallepedido> detallepedidoCollection;

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Afiliados getAfiliadosCedula() {
        return afiliadosCedula;
    }

    public void setAfiliadosCedula(Afiliados afiliadosCedula) {
        this.afiliadosCedula = afiliadosCedula;
    }

    public Estado getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Estado estadoId) {
        this.estadoId = estadoId;
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
