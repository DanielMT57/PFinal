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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author german
 */
@Entity
@Table(name="Marca")
public class Marca implements Serializable{   
     @Id
     @Column(name="id")
     private int id;    
     @Column(name="descripcion")
     private String descripcion;     
     @OneToMany(mappedBy = "marcaId")
     private List<Productos> productoMarca;

    public Marca(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Marca() {
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
        
}
