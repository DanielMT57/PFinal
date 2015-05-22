/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author german
 */
@Entity
@Table(name = "Bodegas")
public class Bodegas implements Serializable {
    @Id
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "ciudadesId_id", referencedColumnName = "id")
	private Ciudades ciudadesId;
    @Column(name = "direccionId")
    private String direccion;

    public Bodegas() {
    }

    public Bodegas(int id, Ciudades ciudadesId, String direccion) {
        this.id = id;
        this.ciudadesId = ciudadesId;
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ciudades getCiudadesId() {
        return ciudadesId;
    }

    public void setCiudadesId(Ciudades ciudadesId) {
        this.ciudadesId = ciudadesId;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

   
}
