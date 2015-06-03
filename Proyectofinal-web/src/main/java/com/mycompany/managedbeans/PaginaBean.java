/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.managedbeans;

import com.mycompany.Categoria;
import com.mycompany.Catalogo;
import javax.inject.Named;

import com.mycompany.Paginas;

import com.mycompany.sessionbeans.PaginaEJB;
import com.mycompany.sessionbeans.CategoriaEJB;
import com.mycompany.sessionbeans.CatalogoEJB;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 *
 * @author MAO
 */
@ManagedBean
@Named(value = "paginaBean")
@ViewScoped
public class PaginaBean implements Serializable {

    /**
     * Atributos a capturar en la vista de paginas
     */
    private int idpagina;
    private int categoriaID;
    private int catalogoID;
    private BigInteger numero;

    private List<Categoria> categorias; // listado de categorias
    private List<Catalogo> catalogos; //listado de catalogos

    private List<Paginas> paginas; // listado de paginas

    @EJB
    private CategoriaEJB categoriaEJB; // instancio un ejb de categoria

    @EJB
    private CatalogoEJB catalogoEJB; // instancio un ejb de catalogos

    @EJB
    private PaginaEJB paginaEJB; // instancio un ejb de paginas

    @PostConstruct
    public void postConstruct() {
        // listado de categorias y catalogos que  quiero que se cargen
        categorias = categoriaEJB.listarTodos();
        catalogos = catalogoEJB.listarTodos();
    }

    public int getIdpagina() {
        return idpagina;
    }

    public void setIdpagina(int idpagina) {
        this.idpagina = idpagina;
    }

    public int getCategoriaID() {
        return categoriaID;
    }

    public void setCategoriaID(int categoriaID) {
        this.categoriaID = categoriaID;
    }

    public int getCatalogoID() {
        return catalogoID;
    }

    public void setCatalogoID(int catalogoID) {
        this.catalogoID = catalogoID;
    }

    public BigInteger getNumero() {
        return numero;
    }

    public void setNumero(BigInteger numero) {
        this.numero = numero;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public List<Catalogo> getCatalogos() {
        return catalogos;
    }

    public void setCatalogos(List<Catalogo> catalogos) {
        this.catalogos = catalogos;
    }

    public List<Paginas> getPaginas() {
        paginas = paginaEJB.listarTodos();
        return paginas;
    }

    /**
     * Metodo qeu se encarga de crear las paginas entra al try en caso de no ser
     * exitosa la transaccion entra al catch enviando un mensaje que no se pudo
     * crear
     */
    public void crearPaginas() {

        try {
            Paginas p = new Paginas();

            p.setId(idpagina);
            p.setCategoriaId(categoriaEJB.buscar(categoriaID));
            p.setCatalogoId(catalogoEJB.buscar(catalogoID));
            p.setNumero(numero);

            paginaEJB.crear(p);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Ha insertado correctamente  "));
            System.out.println("ha insertado correctamente");
            limpiar();
        } catch (Exception e) {
            e.getMessage();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "no se ha podido insertar  "));

        }

    }

    /**
     * Metodo que se encarga de editar las paginas entra al try en caso de no
     * ser exitosa la transaccion entra al catch enviando un mensaje que no se
     * pudo editar
     */
    public void editarPaginas() {

        try {
            Paginas p = new Paginas();
            //p.setId(id);
            p.setId(idpagina);
            p.setCategoriaId(categoriaEJB.buscar(categoriaID));
            p.setCatalogoId(catalogoEJB.buscar(catalogoID));
            p.setNumero(numero);

            paginaEJB.editar(p);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Ha editado correctamente  "));
            System.out.println("ha editado correctamente");
            limpiar();
        } catch (Exception e) {
            e.getMessage();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "no se ha podido editar "));

        }

    }

    /**
     * metodo que se encarga de limpiar los campos cuando se haya realizado una
     * transaccion
     */
    private void limpiar() {
        setIdpagina(0);
        setNumero(BigInteger.ZERO);
    }

    /**
     * Metodo que busca la pagina por su id en caso de que no sea null la
     * busqueda, carge los valores encontrados
     */
    public void buscarPagina() {
        Paginas p = paginaEJB.buscar(idpagina);
        if (p != null) {

            categoriaID = p.getCategoriaId().getId();
            catalogoID = p.getCatalogoId().getId();
            numero = p.getNumero();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Ha encontrado correctamente "));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informacion", "No se encontro nada "));
        }

        System.out.println("ha encontrado  correctamente");
        //   limpiar ();

    }

    /**
     * Metodo que elimina la pagina en caso de que no se pueda eliminar envio
     * mensaje
     */
    public void eliminarPagina() {
        try {
            Paginas p = paginaEJB.buscar(idpagina);

            paginaEJB.eliminar(p);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Ha eliminado correctamente "));
            System.out.println("ha eliminado  correctamente");
            limpiar();
        } catch (Exception e) {
            e.getMessage();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "no se ha podido eliminar  "));

        }

    }

}
