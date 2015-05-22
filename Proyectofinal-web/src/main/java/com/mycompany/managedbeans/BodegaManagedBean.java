/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.managedbeans;

import com.mycompany.Ciudades;
import com.mycompany.sessionbeans.CiudadesEJB;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;

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
    
}
