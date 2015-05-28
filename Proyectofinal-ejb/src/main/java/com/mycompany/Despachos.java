/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author german
 */
@Entity
@Table(name = "Despachos")
@PrimaryKeyJoinColumn(name = "Id", referencedColumnName = "Id")

//@PrimaryKeyJoinColumn(name = "pedidos_id", referencedColumnName = "Id")
public class Despachos extends Pedidos implements Serializable {
    
    @Column(name = "feha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "valor")
    private double valor;
    @Column(name = "sincronizado")
    private Character sincronizado;

    public Despachos() {
    }

    public Despachos( Date fecha, double valor, Character sincronizado) {
        this.fecha = fecha;
        this.valor = valor;
        this.sincronizado = sincronizado;
    }


    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Character getSincronizado() {
        return sincronizado;
    }

    public void setSincronizado(Character sincronizado) {
        this.sincronizado = sincronizado;
    } 
}
