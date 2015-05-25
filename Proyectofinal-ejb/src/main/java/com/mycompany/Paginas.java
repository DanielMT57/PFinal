/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinTable;

/**
 *
 * @author german
 */
@Entity
@Table(name = "Paginas")
public class Paginas implements Serializable {
    @Id
    @Column(name = "Id")
    private int id;
    @Column(name = "numero")
    private BigInteger numero;
    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
	private Categoria categoriaId;
    @ManyToOne
    @JoinColumn(name = "catalogo_Id", referencedColumnName = "Id")
	private Catalogo catalogoId;
    @OneToMany(mappedBy = "paginasId")
    private List<Areaspagina> areaspaginaCollection;

    public Paginas() {
    }

    public Paginas(int id, BigInteger numero, Categoria categoriaId, Catalogo catalogoId, List<Areaspagina> areaspaginaCollection) {
        this.id = id;
        this.numero = numero;
        this.categoriaId = categoriaId;
        this.catalogoId = catalogoId;
        this.areaspaginaCollection = areaspaginaCollection;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigInteger getNumero() {
        return numero;
    }

    public void setNumero(BigInteger numero) {
        this.numero = numero;
    }

    public Categoria getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Categoria categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Catalogo getCatalogoId() {
        return catalogoId;
    }

    public void setCatalogoId(Catalogo catalogoId) {
        this.catalogoId = catalogoId;
    }

    public List<Areaspagina> getAreaspaginaCollection() {
        return areaspaginaCollection;
    }

    public void setAreaspaginaCollection(List<Areaspagina> areaspaginaCollection) {
        this.areaspaginaCollection = areaspaginaCollection;
    }

    
}
