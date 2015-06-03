/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.managedbeans;

import com.mycompany.Catalogo;

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

    /**
     * atributos de la vista de catalogos
     */
    private int id;
    private Date fechaInicio;
    private Date fechaFin;
    private int cantidadPaginas;
    private int cantidadProductos;

    @EJB
    private CatalogoEJB catalogoejb; //instancio un objeto del ejb de catalogos

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

    /**
     * Metodo que crea el catalogo
     */
    public void crearCatalogo() {
        Catalogo co = new Catalogo();
        co.setId(id);
        co.setFechainicio(fechaInicio);
        co.setFechafin(fechaFin);
        co.setCantidadpaginas(cantidadPaginas);
        co.setCantidadproductos(cantidadProductos);
        // llamo el ejb de catalogo y lo persisto
        catalogoejb.crear(co);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Ha insertado correctamente el catalogo "));
        System.out.println("ha insertado correctamente");
        limpiar();

    }

    /**
     * Metodo que busca el catalogo por su id
     */
    public void buscarCatalogo() {
        Catalogo co = catalogoejb.buscar(id);
        if (co != null) {
            // en caso no se ser null, seteo los nuevos valores
            fechaInicio = co.getFechainicio();
            fechaFin = co.getFechafin();
            cantidadPaginas = co.getCantidadpaginas();
            cantidadProductos = co.getCantidadproductos();
            System.out.println("encontrado");

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Ha encontrado correctamente "));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informacion", "No se encontro nada "));
        }

    }

    /**
     * Metodo que actualiza el catalogo
     */
    public void actualizarCatalogo() {
        // mando los nuevos valores y edito el objeto
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

    /**
     * Metodo que elimina el catalogo
     */
    public void eliminarCatalogo() {

        Catalogo co = catalogoejb.buscar(id);

        catalogoejb.eliminar(co);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Ha eliminado correctamente el Producto "));
        System.out.println("ha eliminado correctamente");
        limpiar();
    }

    // declaro una nueva lista de catalogos
    private List<Catalogo> catalogos;

    /**
     * Metodo que me lista los catalogos que existen en la base de datos
     *
     * @return catalogos
     */
    public List<Catalogo> getCatalogos() {
        catalogos = catalogoejb.listarTodos();
        return catalogos;
    }

    /**
     * Metodo que limpia los campos luego de haber realizado una transaccion
     */
    private void limpiar() {
        setId(0);
        setFechaInicio(null);
        setFechaFin(null);
        setCantidadPaginas(0);
        setCantidadProductos(0);

    }

}
