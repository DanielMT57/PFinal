/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Daniel Moncada Tabares
 */
@Entity
@Table(name="niveles")
public class Nivel implements Serializable{
    @Id
    private int id;
    private String descripcion;
    private int numVentas;
    private int numAfiliados;
    private int descuento;
    private int gananciaRed;
    @OneToMany(mappedBy = "nivel")
    private List<Afiliados> afiliados;

    

    public Nivel(int id, String descripcion, int numVentas, int numAfiliados, int descuento, int gananciaRed) {
        this.id = id;
        this.descripcion = descripcion;
        this.numVentas = numVentas;
        this.numAfiliados = numAfiliados;
        this.descuento = descuento;
        this.gananciaRed = gananciaRed;
    }

    public Nivel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNumVentas() {
        return numVentas;
    }

    public void setNumVentas(int numVentas) {
        this.numVentas = numVentas;
    }

    public int getNumAfiliados() {
        return numAfiliados;
    }

    public void setNumAfiliados(int numAfiliados) {
        this.numAfiliados = numAfiliados;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public int getGananciaRed() {
        return gananciaRed;
    }

    public void setGananciaRed(int gananciaRed) {
        this.gananciaRed = gananciaRed;
    }
    
    public List<Afiliados> getAfiliados() {
        return afiliados;
    }

    public void setAfiliados(List<Afiliados> afiliados) {
        this.afiliados = afiliados;
    }
    
    
    
}
