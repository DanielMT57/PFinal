/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author german
 * @author Daniel Moncada Tabares
 */

@Entity
@Table(name="Ciudades")
public class Ciudades implements Serializable{
    @Id
    @Column(name="id")
    private int id;
    @Column(name="descripcion")
    private String descripcion;   
    @ManyToOne
    @JoinColumn(name = "departamentos_id", referencedColumnName = "id")
	private Departamentos departamentosId;  
    @OneToMany(mappedBy = "ciudadesId")
    private List<Personas> personasCiudades;
    @OneToMany(mappedBy = "ciudadesId")
    private List<Bodegas> bodegasCiudades;

    public Ciudades(int id, Departamentos departamento, String descripcion) {
        this.id = id;
        this.departamentosId = departamento;
        this.descripcion = descripcion;
    }

    public Ciudades() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Departamentos getDepartamento() {
        return departamentosId;
    }

    public void setDepartamento(Departamentos departamento) {
        this.departamentosId = departamento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
      
}
