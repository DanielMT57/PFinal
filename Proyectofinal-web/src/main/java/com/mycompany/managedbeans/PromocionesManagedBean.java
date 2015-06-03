/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.managedbeans;

import java.util.Date;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import com.mycompany.sessionbeans.ProductoEJB;
import com.mycompany.sessionbeans.PromocionesEJB;
import com.mycompany.Productos;
import com.mycompany.Promociones;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author MAO
 */
@ManagedBean
@Named(value = "promocionesManagedBean")
@ViewScoped
public class PromocionesManagedBean implements Serializable {

    /**
     * atributos de la vista de promociones
     */
    private int id;

    private int productosId;
    private String descripcion;
    private Date fechafin;
    private int descuento;
    private double preciofinal;
    private int cantidad;

    @EJB
    private ProductoEJB productosEJB; // instancio un nuevo objeto de productosEJB

    @EJB
    private PromocionesEJB promocionesEJB; // instancio un nuevo objeto de prmocionesEJB

    //Metodos getters y setters de los atributos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductosId() {
        return productosId;
    }

    public void setProductosId(int productosId) {
        this.productosId = productosId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    private List<Promociones> promociones; // declaro una nueva lista de las promociones

    /**
     * Metodo que lista las promociones persistidas
     *
     * @return promociones
     */
    public List<Promociones> getPromociones() {
        promociones = promocionesEJB.listarTodos();
        return promociones;
    }

    public double getPreciofinal() {
        return preciofinal;
    }

    public void setPreciofinal(double preciofinal) {
        this.preciofinal = preciofinal;
    }
    private List<Productos> productos;

    @PostConstruct
    public void postConstruct() {
        productos = productosEJB.listarTodos(); //carga el listado de productos en el combo
    }

    public List<Productos> getProductos() {
        return productos;
    }

    public void setProductos(List<Productos> productos) {
        this.productos = productos;
    }

    /**
     * Metodo que carga el precio del producto seleccionado
     */
    public void actualizar() {
        Productos p = productosEJB.buscar(productosId);
        preciofinal = p.getPrecioVenta();
    }

    /**
     * Metodo que se encarga de crear las promociones en caso de no poder
     * insertar envia un mensaje indicando que no se puedo realizar la
     * transaccion
     */
    public void crearPromociones() {

        try {
            Promociones pe = new Promociones();
            pe.setId(id);
            pe.setDescripcion(descripcion);
            pe.setFechafin(fechafin);
            pe.setCantidad(cantidad);
            int precioTotal = (int) (preciofinal * cantidad);
            precioTotal = precioTotal - (precioTotal * descuento / 100);
            System.out.println(descuento);
            System.out.println(precioTotal);
            pe.setPreciofinal(precioTotal);
            pe.setProductosId(productosEJB.buscar(productosId));
            pe.setDescuento(descuento);
            promocionesEJB.crear(pe);
            // Crea la promocion
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Ha insertado correctamente  "));
            System.out.println("ha insertado correctamente");
            limpiar();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Informacion", " error al insertar "));
            // envia mensaje de error
            e.getMessage();
        }

    }

    /**
     * Metodo que se encarga de eliminar la promocion finaliza la promocion de
     * un producto
     */
    public void eliminarPromo() {
        try {
            Promociones p = promocionesEJB.buscar(id);

            promocionesEJB.eliminar(p);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Ha eliminado correctamente  "));
            System.out.println("ha eliminado  correctamente");
            limpiar();
        } catch (Exception e) {
            e.getMessage();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "no se ha podido eliminar el producto por que esta asignado a un registro  "));

        }

     
     
    }

    /**
     * Metodo que se encarga de buscar una promocion por su id
     */
    public void buscarPromociones() {

        Promociones p = promocionesEJB.buscar(id);
        if (p != null) { // si es null envia mensaje, si no carga los datos necesarios
            preciofinal = p.getPreciofinal();
            descuento = p.getDescuento();
            fechafin = p.getFechafin();
            descripcion = p.getDescripcion();
            cantidad = p.getCantidad();
            productosId = p.getProductosId().getId();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Ha encontrado correctamente "));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informacion", "No se encontro nada "));
        }

        System.out.println("ha encontrado  correctamente");

    }
/**
 * Metodo que se encarga de setear los campos cuando se han realizado transacciones exitosas
 */
    private void limpiar() {

        setDescripcion(null);
        setDescuento(0);
        setFechafin(null);
        setPreciofinal(0);
        setId(0);

    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}
