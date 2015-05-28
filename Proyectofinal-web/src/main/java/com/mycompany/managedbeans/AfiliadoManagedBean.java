/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.managedbeans;

import com.mycompany.Afiliados;
import com.mycompany.Nivel;
import com.mycompany.sessionbeans.AfiliadoEJB;
import com.mycompany.sessionbeans.NivelesEJB;
import java.io.Serializable;
import java.util.Date;
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
@Named(value = "afiliadoBean")
@ViewScoped
public class AfiliadoManagedBean implements Serializable{
    
    private int cedula;
    private int idNiveles;
    private int idAfiliados;
    private Date fechaAfiliacion; 
    
    private List<Nivel> niveles;
    private List<Afiliados> afiliados;
    
    @EJB
    private AfiliadoEJB afiliadosEJB;
    @EJB
    private NivelesEJB nivelesEJB;

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public int getIdNiveles() {
        return idNiveles;
    }

    public void setIdNiveles(int idNiveles) {
        this.idNiveles = idNiveles;
    }

    public int getIdAfiliados() {
        return idAfiliados;
    }

    public void setIdAfiliados(int idAfiliados) {
        this.idAfiliados = idAfiliados;
    }

    public Date getFechaAfiliacion() {
        return fechaAfiliacion;
    }

    public void setFechaAfiliacion(Date fechaAfiliacion) {
        this.fechaAfiliacion = fechaAfiliacion;
    }

    public List<Nivel> getNiveles() {
        return niveles;
    }

    public void setNiveles(List<Nivel> niveles) {
        this.niveles = niveles;
    }

    public List<Afiliados> getAfiliados() {
        return afiliados;
    }

    public void setAfiliados(List<Afiliados> afiliados) {
        this.afiliados = afiliados;
    }

    public AfiliadoEJB getAfiliadosEJB() {
        return afiliadosEJB;
    }

    public void setAfiliadosEJB(AfiliadoEJB afiliadosEJB) {
        this.afiliadosEJB = afiliadosEJB;
    }

    public NivelesEJB getNivelesEJB() {
        return nivelesEJB;
    }

    public void setNivelesEJB(NivelesEJB nivelesEJB) {
        this.nivelesEJB = nivelesEJB;
    }

    /**
     * Creates a new instance of AfiliadoManagedBean
     */
//    public AfiliadoManagedBean() {        
//    }
    
      @PostConstruct
    public void postConstruct() {
        niveles = nivelesEJB.listarTodos();
     
    }
    
    
}
