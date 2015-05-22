/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.managedbeans;

import com.mycompany.Categoria;
import com.mycompany.Color;
import com.mycompany.Marca;
import com.mycompany.Productos;
import com.mycompany.sessionbeans.CategoriaEJB;
import com.mycompany.sessionbeans.ColorEJB;
import com.mycompany.sessionbeans.MarcaEJB;
import com.mycompany.sessionbeans.ProductoEJB;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Daniel
 */
@ManagedBean
@Named(value = "productoBean")
@ViewScoped
public class ProductoManagedBean implements Serializable {

    private int id;
    private int peso;
    private double precioCompra;
    private double precioVenta;
    private int idColor;
    private int idCategoria;
    private int idMarca;
    private String descripcion;

    private List<Color> colores;
    private List<Marca> marcas;
    private List<Categoria> categorias;

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    @EJB
    private ColorEJB colorEJB;
    @EJB
    private ProductoEJB productoEJB;
    @EJB
    private MarcaEJB marcaEJB;
    @EJB
    private CategoriaEJB categoriaEJB;

    @PostConstruct
    public void postConstruct() {

        colores = colorEJB.listarTodos();
        marcas = marcaEJB.listarTodos();
        categorias = categoriaEJB.listarTodos();
    }

    public void crearProducto() {
        Productos p = new Productos();
        p.setId(id);
        p.setColorId(colorEJB.buscar(idColor));
        p.setCategoriaId(categoriaEJB.buscar(idCategoria));
        p.setMarcaId(marcaEJB.buscar(idMarca));
        p.setPeso(peso);
        p.setPrecioCompra(precioCompra);
        p.setPrecioVenta(precioVenta);
        p.setDescripcion(descripcion);

        productoEJB.crear(p);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Ha insertado correctamente el producto  "));
        System.out.println("ha insertado correctamente");
        limpiar();

    }

    public void buscarProducto() {
        Productos p=productoEJB.buscar(id);
        if(p!=null){
            
        descripcion=p.getDescripcion();
        precioCompra=p.getPrecioCompra();
        precioVenta=p.getPrecioVenta();
        peso=p.getPeso();
        idMarca=p.getMarcaId().getId();
        idCategoria=p.getCategoriaId().getId();
        idColor=p.getColorId().getId();
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Ha encontrado correctamente "));  
        }
        else 
        {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informacion", "No se encontro nada "));  
        }
       
        System.out.println("ha encontrado  correctamente");
         //   limpiar ();

    }

      public void actualizarProducto() {
        Productos p = new Productos();
        p.setId(id);
        p.setColorId(colorEJB.buscar(idColor));
        p.setCategoriaId(categoriaEJB.buscar(idCategoria));
        p.setMarcaId(marcaEJB.buscar(idMarca));
        p.setPeso(peso);
        p.setPrecioCompra(precioCompra);
        p.setPrecioVenta(precioVenta);
        p.setDescripcion(descripcion);

        productoEJB.editar(p);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Ha actualizado correctamente el producto  "));
       // System.out.println("ha actualizado correctamente");
        limpiar();

    }
      
      
      
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getIdColor() {
        return idColor;
    }

    public void setIdColor(int idColor) {
        this.idColor = idColor;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public List<Color> getColores() {
        return colores;
    }

    public void setColores(List<Color> colores) {
        this.colores = colores;
    }

    public List<Marca> getMarcas() {
        return marcas;
    }

    public void setMarcas(List<Marca> marcas) {
        this.marcas = marcas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    private void limpiar() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        this.setCategorias(null);
        this.setColores(null);
        this.setDescripcion(null);
        this.setId(0);
        this.setPrecioCompra(0);
        this.setPrecioVenta(0);
        this.setPeso(0);

    }

}
