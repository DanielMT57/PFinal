/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author german
 */
@Embeddable
public class SincronizadaPK implements Serializable {
    // @Column(name = "afiliados_cedula")
    @Column(name = "personas_cedula")
    private int personasCedula;
    
    
   // @Column(name = "nombretabla")
    
    @Column(name = "TABLASSINCRONIZADA_ID")
    private String tablassincronizadaId;

    public SincronizadaPK() {
    }

    public SincronizadaPK(int personasCedula, String tablassincronizadaId) {
        this.personasCedula = personasCedula;
        this.tablassincronizadaId = tablassincronizadaId;
    }

    public int getPersonasCedula() {
        return personasCedula;
    }

    public void setPersonasCedula(int personasCedula) {
        this.personasCedula = personasCedula;
    }

    public String getTablassincronizadaId() {
        return tablassincronizadaId;
    }

    public void setTablassincronizadaId(String tablassincronizadaId) {
        this.tablassincronizadaId = tablassincronizadaId;
    }
}
