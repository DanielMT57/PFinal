/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;


import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author german
 */

@Entity
@Table(name = "Productos")
public class Productos implements Serializable {
    @Id
    @Column(name = "id")
    private int id;    
    @Column(name = "peso")
    private int peso;
    @Column(name = "precioCompra")
    private double precioCompra;
    @Column(name = "precioVenta")
    private double precioVenta;
    
      @Column(name = "descripcion")
    private String descripcion;
    @ManyToOne()
    @JoinColumn(name = "color_id")
    private Color colorId;
    @ManyToOne()
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
	private Categoria categoriaId;   
    @ManyToOne()
    @JoinColumn(name = "marca_id", referencedColumnName = "id")
	private Marca marcaId;
    @OneToMany(mappedBy = "productos")
    private List<Detallepedido> detallepedidoProductos;
    @OneToMany(mappedBy = "productos")
    private List<Productosbodega> productosbodegaProductos;
    @OneToMany(mappedBy = "productosId")
    private List<Promociones> promocionesProductos;
    @OneToMany(mappedBy = "productosId")
    private List<Areaspagina> areaspaginaProductos;

    public int getId() { 
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Color getColorId() {
        return colorId;
    }

    public void setColorId(Color colorId) {
        this.colorId = colorId;
    }

    public Categoria getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Categoria categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Marca getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(Marca marcaId) {
        this.marcaId = marcaId;
    }

    public List<Detallepedido> getDetallepedidoProductos() {
        return detallepedidoProductos;
    }

    public void setDetallepedidoProductos(List<Detallepedido> detallepedidoProductos) {
        this.detallepedidoProductos = detallepedidoProductos;
    }

    public List<Productosbodega> getProductosbodegaProductos() {
        return productosbodegaProductos;
    }

    public void setProductosbodegaProductos(List<Productosbodega> productosbodegaProductos) {
        this.productosbodegaProductos = productosbodegaProductos;
    }

    public List<Promociones> getPromocionesProductos() {
        return promocionesProductos;
    }

    public void setPromocionesProductos(List<Promociones> promocionesProductos) {
        this.promocionesProductos = promocionesProductos;
    }

    public List<Areaspagina> getAreaspaginaProductos() {
        return areaspaginaProductos;
    }

    public void setAreaspaginaProductos(List<Areaspagina> areaspaginaProductos) {
        this.areaspaginaProductos = areaspaginaProductos;
    }
    

}
