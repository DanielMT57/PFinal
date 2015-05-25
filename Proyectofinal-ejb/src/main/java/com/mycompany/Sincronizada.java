/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author german
 */
@Entity
@Table(name = "Sincronizada")
public class Sincronizada implements Serializable {
    @EmbeddedId
    protected SincronizadaPK sincronizadaPK;
    @Column(name = "sincronizo")
    private Character sincronizo;
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @MapsId("personasCedula")
    @JoinColumn(name = "personas_cedula", referencedColumnName = "cedula")
    @ManyToOne
    private Personas personas;

    public Sincronizada() {
    }

    public Sincronizada(SincronizadaPK sincronizadaPK, Character sincronizo, Date fecha, Personas personas) {
        this.sincronizadaPK = sincronizadaPK;
        this.sincronizo = sincronizo;
        this.fecha = fecha;
        this.personas = personas;
    }

    public SincronizadaPK getSincronizadaPK() {
        return sincronizadaPK;
    }

    public void setSincronizadaPK(SincronizadaPK sincronizadaPK) {
        this.sincronizadaPK = sincronizadaPK;
    }

    public Character getSincronizo() {
        return sincronizo;
    }

    public void setSincronizo(Character sincronizo) {
        this.sincronizo = sincronizo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Personas getPersonas() {
        return personas;
    }

    public void setPersonas(Personas personas) {
        this.personas = personas;
    }

   
}
