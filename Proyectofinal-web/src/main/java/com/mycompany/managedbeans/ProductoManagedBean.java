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
import com.mycompany.Promociones;
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

    /**
     * Atributos que controlan la vista de productos
     */
    private int id;
    private int peso;
    private double precioCompra;
    private double precioVenta;
    private int idColor;
    private int idCategoria;
    private int idMarca;
    private String descripcion;

    private List<Color> colores; //Declaro una lista de colores
    private List<Marca> marcas; //Declaro una lista marcas
    private List<Categoria> categorias; //Declaro una lista categorias

    public List<Categoria> getCategorias() {
        return categorias; // retorna las categorias
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    @EJB
    private ColorEJB colorEJB; // instancio un ejb de color
    @EJB
    private ProductoEJB productoEJB; // instancio un ejb Productos
    @EJB
    private MarcaEJB marcaEJB; // instancio un ejb de marcas
    @EJB
    private CategoriaEJB categoriaEJB; // instancio un ejb categorias

    @PostConstruct
    public void postConstruct() {
        // Postcontruct que me carga el listado de los colores marcas y categorias el abrir la aplicacino
        colores = colorEJB.listarTodos();
        marcas = marcaEJB.listarTodos();
        categorias = categoriaEJB.listarTodos();
    }

    /**
     * Metodo que se encarga de crear un producto en casi de ya existir se
     * canecelala transaccion y manda el mensaje indicando que ocurre un error
     */
    public void crearProducto() {

        try {
            Productos p = new Productos();
            /**
             * Seteo los nuevos valores
             */
            p.setColorId(colorEJB.buscar(idColor));
            p.setCategoriaId(categoriaEJB.buscar(idCategoria));
            p.setMarcaId(marcaEJB.buscar(idMarca));
            p.setPeso(peso);
            p.setPrecioCompra(precioCompra);
            p.setPrecioVenta(precioVenta);
            p.setDescripcion(descripcion);
            // hago la persistencia
            productoEJB.crear(p);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Ha insertado correctamente el producto  "));
            System.out.println("ha insertado correctamente");
            limpiar();
        } catch (Exception e) {
            e.getMessage(); // si no inserta envia el error
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "no se ha podido insertar el producto  "));

        }

    }

    /**
     * Metodo que se encarga de buscar un producto y cargarlo a la vista si el
     * resultado no es null en cas de ser null es por que no encontro ningun
     * producto conn ese id
     */
    public void buscarProducto() {
        Productos p = productoEJB.buscar(id);
        if (p != null) {

            descripcion = p.getDescripcion();
            precioCompra = p.getPrecioCompra();
            precioVenta = p.getPrecioVenta();
            peso = p.getPeso();
            idMarca = p.getMarcaId().getId();
            idCategoria = p.getCategoriaId().getId();
            idColor = p.getColorId().getId();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Ha encontrado correctamente "));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informacion", "No se encontro nada "));
        }

        System.out.println("ha encontrado  correctamente");
        //   limpiar ();

    }

    /**
     * Metodo que se encarga de actualizar un prodcuto, mandandole los nuevos
     * valores y persisitiendolos en la base de datos
     */
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

    /**
     * Metodo que se encarga de eliminar un producto si se encuentra asignado a
     * un pedido o una venta, este producto no puede ser eliminado
     *
     */
    public void eliminarProducto() {
        try {
            Productos p = productoEJB.buscar(id);

            productoEJB.eliminar(p);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Ha eliminado correctamente el Producto "));
            System.out.println("ha eliminado  correctamente");
            limpiar();
        } catch (Exception e) {
            e.getMessage(); // envia un mensaje en caso de no poder ser elliminado este producto
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "no se ha podido eliminar el producto por que esta asignado a un registro  "));

        }

    }

    private List<Productos> productos; // Declaro una nueva lista de productos

    public List<Productos> getProductos() {
        productos = productoEJB.listarTodos(); // retorno la nueva lista de productos y los cargo en la vista
        return productos;
    }

    //Metodos getters y setters de los atributos  de la vista
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

    /**
     * Metodo que se encarga de limpiar los campos cuando ya se ha realizado una
     * transaccion exitosa
     */
    private void limpiar() {
        this.setId(0);
        this.setPrecioCompra(0);
        this.setPrecioVenta(0);
        this.setPeso(0);
        this.setDescripcion("");

    }

}
