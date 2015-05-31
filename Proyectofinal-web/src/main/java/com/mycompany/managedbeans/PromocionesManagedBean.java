/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.managedbeans;

import java.util.Date;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import com.mycompany.sessionbeans.ProductoEJB;
import com.mycompany.sessionbeans.PromocionesEJB;
import com.mycompany.Productos;
import com.mycompany.Promociones;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author MAO
 */
@ManagedBean
@Named(value = "promocionesManagedBean")
@ViewScoped
public class PromocionesManagedBean {

    /**
     * Creates a new instance of PromocionesManagedBean
     */
    private int id;

    private int productosId;
    private String descripcion;
    private Date fechafin;
    private int descuento;
    private double preciofinal;

    @EJB
    private ProductoEJB productosEJB;

    @EJB
    private PromocionesEJB promocionesEJB;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    } 

    public int getProductosId() {
        return productosId;
    }

    public void setProductosId(int productosId) {
        this.productosId = productosId;
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

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

  

   

 

    public double getPreciofinal() {
        return preciofinal;
    }

    public void setPreciofinal(double preciofinal) {
        this.preciofinal = preciofinal;
    }
    private List<Productos> productos;

    @PostConstruct
    public void postConstruct() {
        productos = productosEJB.listarTodos();
    }

    public List<Productos> getProductos() {
        return productos;
    }

    public void setProductos(List<Productos> productos) {
        this.productos = productos;
    }
    
     public void actualizar() {
        Productos p = productosEJB.buscar(productosId);
         preciofinal = p.getPrecioVenta()*(descuento)/100;
         setPreciofinal(preciofinal);
        
         
    }

     
     public void crearPromociones(){
     Promociones pe = new Promociones();
     pe.setId(id);
     pe.setDescripcion(descripcion);
     pe.setFechafin(fechafin);
     pe.setPreciofinal(productosId);
     pe.setProductosId(productosEJB.buscar(productosId));
     pe.setDescuento(descuento);
     promocionesEJB.crear(pe);
     
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Ha insertado correctamente  "));
        System.out.println("ha insertado correctamente");
        limpiar();
     
     
     
     }

    private void limpiar() {
       
        
        
        
        setDescripcion(null);
        setDescuento(0);
        setFechafin(null);
        setPreciofinal(0);
        setId(0);
        
    }
}
