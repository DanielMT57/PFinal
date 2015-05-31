/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.managedbeans;

import com.mycompany.Afiliados;
import com.mycompany.Estado;
import com.mycompany.Productos;
import com.mycompany.sessionbeans.AfiliadoEJB;
import com.mycompany.sessionbeans.estadoEJB;
import com.mycompany.sessionbeans.ProductoEJB;
import java.util.Date;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import javax.faces.view.ViewScoped;

/**
 *
 * @author MAO
 */
@ManagedBean
@Named(value = "pedidoManagedBean")
@ViewScoped

public class pedidoManagedBean {

    private int idPedido;
    private int idAfiliado;
    private int idestado;
    private List<Afiliados> afiliados;
    private List<Estado> estados;
    private Date fecha;
    private String descripcion;
    
    private int ide;

    ////
    private int idProducto;
    private double precioUnitario;
    private int cantidad;
    private List<Productos> productos;

    @EJB
    private ProductoEJB productoEJB;

    @EJB
    private AfiliadoEJB afiliadosEJB;

    @EJB
    private estadoEJB estadoEJB;

    public int getIde() {
        return ide;
    }

    public void setIde(int ide) {
        this.ide = ide;
    }

    
    
        
    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdAfiliado() {
        return idAfiliado;
    }

    public void setIdAfiliado(int idAfiliado) {
        this.idAfiliado = idAfiliado;
    }

    public List<Afiliados> getAfiliados() {
        return afiliados;
    }

    public void setAfiliados(List<Afiliados> afiliados) {
        this.afiliados = afiliados;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdestado() {
        return idestado;
    }

    public void setIdestado(int idestado) {
        this.idestado = idestado;
    }

    @PostConstruct
    public void postConstruct() {

        afiliados = afiliadosEJB.listarTodos();
        estados = estadoEJB.listarTodos();
        productos = productoEJB.listarTodos();
         
      

    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public double getPrecioUnitario() {                
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {        
 
        this.precioUnitario = precioUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<Productos> getProductos() {
        return productos;
    }

    public void setProductos(List<Productos> productos) {
        this.productos = productos;
    }
    
    public void cargar (){
    Productos p = productoEJB.buscar(idProducto);
    double precio = p.getPrecioVenta();
   this.setPrecioUnitario(precio);
    }

    
    
}
