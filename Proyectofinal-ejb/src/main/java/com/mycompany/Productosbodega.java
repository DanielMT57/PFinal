/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 *
 * @author german
 */
@Entity
@Table(name = "PRODUCTOSBODEGA")
public class Productosbodega implements Serializable {
    @EmbeddedId
    protected ProductosbodegaPK productosbodegaPK;
    @Column(name = "cantidad")
    private BigInteger cantidad;
    
    @MapsId("productosId")
    @JoinColumn(name = "productos_id", referencedColumnName = "Id")
    @ManyToOne
    private Productos productos;
    @MapsId("bodegasId")
    @JoinColumn(name = "bodegas_id", referencedColumnName = "Id")
    @ManyToOne
    private Bodegas bodegas;

    public Productosbodega() {
    }

    public Productosbodega(ProductosbodegaPK productosbodegaPK, BigInteger cantidad, Productos productos, Bodegas bodegas) {
        this.productosbodegaPK = productosbodegaPK;
        this.cantidad = cantidad;
        this.productos = productos;
        this.bodegas = bodegas;
    }

    public ProductosbodegaPK getProductosbodegaPK() {
        return productosbodegaPK;
    }

    public void setProductosbodegaPK(ProductosbodegaPK productosbodegaPK) {
        this.productosbodegaPK = productosbodegaPK;
    }

    public BigInteger getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigInteger cantidad) {
        this.cantidad = cantidad;
    }

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    public Bodegas getBodegas() {
        return bodegas;
    }

    public void setBodegas(Bodegas bodegas) {
        this.bodegas = bodegas;
    }  
}
