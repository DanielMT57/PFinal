/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.MapsId;

/**
 *
 * @author german
 * @author Daniel Moncada Tabares
 */
@Entity
@Table(name = "Detallepedido")
public class Detallepedido implements Serializable {

    @EmbeddedId
    protected DetallepedidoPK detallepedidoPK;
    @Column(name = "cantidad")
    private int cantidad;
    @Column(name = "preciounitario")
    private double preciounitario;
    @Column(name = "sincronizado")
    private Character sincronizado;

    @MapsId("productosId")
    @ManyToOne
    @JoinColumn(name = "productos_id", referencedColumnName = "id")
    private Productos productos;

    @MapsId("pedidosId")
    @ManyToOne
    @JoinColumn(name = "Pedidos_id", referencedColumnName = "Id")
    private Pedidos pedidos;

    public Detallepedido() {
    }

    public Detallepedido(DetallepedidoPK detallepedidoPK, int cantidad, double preciounitario, Character sincronizado, Productos productos, Pedidos pedidos) {
        this.detallepedidoPK = detallepedidoPK;
        this.cantidad = cantidad;
        this.preciounitario = preciounitario;
        this.sincronizado = sincronizado;
        this.productos = productos;
        this.pedidos = pedidos;
    }

    public DetallepedidoPK getDetallepedidoPK() {
        return detallepedidoPK;
    }

    public void setDetallepedidoPK(DetallepedidoPK detallepedidoPK) {
        this.detallepedidoPK = detallepedidoPK;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPreciounitario() {
        return preciounitario;
    }

    public void setPreciounitario(double preciounitario) {
        this.preciounitario = preciounitario;
    }

    public Character getSincronizado() {
        return sincronizado;
    }

    public void setSincronizado(Character sincronizado) {
        this.sincronizado = sincronizado;
    }

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    public Pedidos getPedidos() {
        return pedidos;
    }

    public void setPedidos(Pedidos pedidos) {
        this.pedidos = pedidos;
    }

   
}
