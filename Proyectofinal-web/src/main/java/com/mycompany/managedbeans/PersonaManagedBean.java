/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.managedbeans;

import com.mycompany.Ciudades;
import com.mycompany.sessionbeans.CiudadesEJB;
import com.mycompany.sessionbeans.PersonasEJB;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author german
 */
@ManagedBean
@Named(value = "personaBean")
@ViewScoped

public class PersonaManagedBean implements Serializable {

    private int cedula;
    private int idCiudades;
    private int telefono;
    private String nombre;
    private String apellido;
    private String direccion;
    private String email;
    private List<Ciudades> ciudades;

    @EJB
    private PersonasEJB personasEJB;
    @EJB
    private CiudadesEJB ciudadesEJB;
    

    /**
     * Creates a new instance of PersonaManagedBean
     */
    
    @PostConstruct
    public void postConstruct() {
        ciudades = ciudadesEJB.listarTodos();
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public int getIdCiudades() {
        return idCiudades;
    }

    public void setIdCiudades(int idCiudades) {
        this.idCiudades = idCiudades;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Ciudades> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<Ciudades> ciudades) {
        this.ciudades = ciudades;
    }

    public PersonasEJB getPersonasEJB() {
        return personasEJB;
    }

    public void setPersonasEJB(PersonasEJB personasEJB) {
        this.personasEJB = personasEJB;
    }

    public CiudadesEJB getCiudadesEJB() {
        return ciudadesEJB;
    }

    public void setCiudadesEJB(CiudadesEJB ciudadesEJB) {
        this.ciudadesEJB = ciudadesEJB;
    }
}
