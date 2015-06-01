/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.managedbeans;

import com.mycompany.Categoria;
import com.mycompany.Catalogo;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import com.mycompany.Paginas;
import com.mycompany.Productos;
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

    private int idpagina;
    private int categoriaID;
    private int catalogoID;
    private BigInteger numero;

    private List<Categoria> categorias;
    private List<Catalogo> catalogos;

    private List<Paginas> paginas;

    @EJB
    private CategoriaEJB categoriaEJB;

    @EJB
    private CatalogoEJB catalogoEJB;

    @EJB
    private PaginaEJB paginaEJB;

    @PostConstruct
    public void postConstruct() {

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

    public void crearPaginas() {

        try {
            Paginas p = new Paginas();
            //p.setId(id);
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

    private void limpiar() {
        setIdpagina(0);
        setNumero(BigInteger.ZERO);
    }

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
