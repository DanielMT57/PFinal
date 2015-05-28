/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.managedbeans;

import com.mycompany.Bodegas;
import com.mycompany.Catalogo;
import com.mycompany.Productos;
import com.mycompany.sessionbeans.CatalogoEJB;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author german
 */
@ManagedBean
@Named(value = "catalogoBean")
@ViewScoped

public class CatalogoManagedBean implements Serializable {

    private int id;
    private Date fechaInicio;
    private Date fechaFin;
    private int cantidadPaginas;
    private int cantidadProductos;

    @EJB
    private CatalogoEJB catalogoejb;

    /**
     * Creates a new instance of CatalogoManagedBean
     */
//    public CatalogoManagedBean() {
//    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getCantidadPaginas() {
        return cantidadPaginas;
    }

    public void setCantidadPaginas(int cantidadPaginas) {
        this.cantidadPaginas = cantidadPaginas;
    }

    public int getCantidadProductos() {
        return cantidadProductos;
    }

    public void setCantidadProductos(int cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }

    public void crearCatalogo() {
        Catalogo co = new Catalogo();
        co.setId(id);
        co.setFechainicio(fechaInicio);
        co.setFechafin(fechaFin);
        co.setCantidadpaginas(cantidadPaginas);
        co.setCantidadproductos(cantidadProductos);

        catalogoejb.crear(co);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Ha insertado correctamente el catalogo "));
        System.out.println("ha insertado correctamente");
        limpiar();

    }

    public void buscarCatalogo() {
        Catalogo co = catalogoejb.buscar(id);
        if (co != null) {

            fechaInicio = co.getFechainicio();
            fechaFin = co.getFechafin();
            cantidadPaginas = co.getCantidadpaginas();
            cantidadProductos = co.getCantidadproductos();
            System.out.println("encontrado");

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Ha encontrado correctamente "));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informacion", "No se encontro nada "));
        }

        //   System.out.println("ha encontrado  correctamente");
        //   limpiar ();
    }

    public void actualizarCatalogo() {

        Catalogo co = new Catalogo();
        co.setId(id);
        co.setFechainicio(fechaInicio);
        co.setFechafin(fechaFin);
        co.setCantidadpaginas(cantidadPaginas);
        co.setCantidadproductos(cantidadProductos);

        catalogoejb.editar(co);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Ha actualizado correctamente el catalogo "));
        System.out.println("ha actualizado correctamente");
        limpiar();
    }
    
    
      public void eliminarCatalogo() {

       Catalogo co = catalogoejb.buscar(id);

       catalogoejb.eliminar(co);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Ha eliminado correctamente el Producto "));
         System.out.println("ha eliminado correctamente");
        limpiar();
    }
      
      
       private List<Catalogo> catalogos;

    public List<Catalogo> getCatalogos() {
        catalogos =catalogoejb.listarTodos();
        return catalogos;
    }
       
       

    private void limpiar() {
        setId(0);
        setFechaInicio(null);
        setFechaFin(null);
        setCantidadPaginas(0);
        setCantidadProductos(0);

    }

}
