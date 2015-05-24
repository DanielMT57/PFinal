/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.managedbeans;

import com.mycompany.Bodegas;
import com.mycompany.Ciudades;
import com.mycompany.sessionbeans.BodegasEJB;
import com.mycompany.sessionbeans.CiudadesEJB;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author german
 */
@ManagedBean
@Named(value = "bodegaBean")
@ViewScoped
public class BodegaManagedBean implements Serializable{

    private int id;
    private int idCiudades;
    private String direccion;
    private List<Ciudades> ciudades;
    @EJB
    private CiudadesEJB ciudadesEJB;
    
      @EJB
    private BodegasEJB bodegaEJB;
    /**
     * Creates a new instance of BodegaManagedBean
     */
    
    @PostConstruct
    public void postConstruct() {
     ciudades = ciudadesEJB.listarTodos();
    }       

//    public BodegaManagedBean() {
//    ciudades = ciudadesEJB.listarTodos();
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCiudades() {
        return idCiudades;
    }

    public void setIdCiudades(int idCiudades) {
        this.idCiudades = idCiudades;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Ciudades> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<Ciudades> ciudades) {
        this.ciudades = ciudades;
    }

    public CiudadesEJB getCiudadesEJB() {
        return ciudadesEJB;
    }

    public void setCiudadesEJB(CiudadesEJB ciudadesEJB) {
        this.ciudadesEJB = ciudadesEJB;
    }  
    
     public void crearBodega() {
        Bodegas bo = new Bodegas();
        bo.setId(id);
        bo.setCiudadesId(ciudadesEJB.buscar(idCiudades));
        bo.setDireccion(direccion);

        bodegaEJB.crear(bo);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Ha insertado correctamente la bodega "));
        System.out.println("ha insertado correctamente");
        limpiar();

    }

    public void buscarBodega() {
        Bodegas bo = bodegaEJB.buscar(id);
        if (bo != null) {

            idCiudades = bo.getCiudadesId().getId();
            direccion = bo.getDireccion();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Ha encontrado correctamente "));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informacion", "No se encontro nada "));
        }

     //   System.out.println("ha encontrado  correctamente");
        //   limpiar ();

    }

    
    public void actualizarBodega(){
    
      Bodegas bo = new Bodegas();
        bo.setId(id);
        bo.setCiudadesId(ciudadesEJB.buscar(idCiudades));
        bo.setDireccion(direccion);

        bodegaEJB.editar(bo);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Ha actualizado correctamente la bodega "));
        System.out.println("ha actualizado correctamente");
        limpiar();
    }
    
     public void eliminarBodega(){
    
      Bodegas bo = bodegaEJB.buscar(id);
    

        bodegaEJB.eliminar(bo);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Ha eliminado correctamente la bodega "));
       // System.out.println("ha actualizado correctamente");
        limpiar();
    }
    private void limpiar() {

        this.setId(0);
        this.setIdCiudades(0);
        this.setDireccion(null);

    }

    
}
