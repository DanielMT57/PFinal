/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.managedbeans;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

import com.mycompany.Areaspagina;
import com.mycompany.Paginas;
import com.mycompany.Productos;
import com.mycompany.Promociones;
import com.mycompany.sessionbeans.AreasPaginaEJB;
import com.mycompany.sessionbeans.PaginaEJB;
import com.mycompany.sessionbeans.ProductoEJB;
import com.mycompany.sessionbeans.PromocionesEJB;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author MAO
 */
@ManagedBean
@Named(value = "areasBean")
@ViewScoped
public class AreasBean implements Serializable {

    /**
     * Creates a new instance of AreasBean
     */
//    public AreasBean() {
//    }
    private int id;
    private int paginasId;
    private int productosId;
    private int promocionesId;
    private List<Productos> productos;

    private List<Promociones> promociones;
    private List<Paginas> paginas;

    @EJB
    private AreasPaginaEJB areaspaginaEJB;
    @EJB
    private ProductoEJB productosEJB;
    @EJB
    private PromocionesEJB promocionesEJB;
    @EJB
    private PaginaEJB paginaEJB;

    @PostConstruct
    public void postConstruct() {
        productos = productosEJB.listarTodos();
        promociones = promocionesEJB.listarTodos();
        paginas = paginaEJB.listarTodos();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPaginasId() {
        return paginasId;
    }

    public void setPaginasId(int paginasId) {
        this.paginasId = paginasId;
    }

    public int getProductosId() {
        return productosId;
    }

    public void setProductosId(int productosId) {
        this.productosId = productosId;
    }

    public int getPromocionesId() {
        return promocionesId;
    }

    public void setPromocionesId(int promocionesId) {
        this.promocionesId = promocionesId;
    }

    public List<Productos> getProductos() {
        return productos;
    }

    public void setProductos(List<Productos> productos) {
        this.productos = productos;
    }

    public List<Promociones> getPromociones() {
        return promociones;
    }

    public void setPromociones(List<Promociones> promociones) {
        this.promociones = promociones;
    }

    public List<Paginas> getPaginas() {
        return paginas;
    }

    public void setPaginas(List<Paginas> paginas) {
        this.paginas = paginas;
    }

    private List<Areaspagina> areas;

    public List<Areaspagina> getAreas() {
        areas = areaspaginaEJB.listarTodos();
        return areas;
    }

    public void crearAreas() {

        try {
            Areaspagina a = new Areaspagina();
            //p.setId(id);
            a.setId(id);
            a.setPaginasId(paginaEJB.buscar(paginasId));
            a.setProductosId(productosEJB.buscar(productosId));
            a.setPromocionesId(promocionesEJB.buscar(promocionesId));

            areaspaginaEJB.crear(a);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Ha insertado correctamente  "));
            System.out.println("ha insertado correctamente");
            limpiar();
        } catch (Exception e) {
            e.getMessage();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "no se ha podido insertar  "));

        }

    }

    public void actualizarPromociones(){
        promociones=productosEJB.buscar(productosId).getPromocionesProductos();
    }
    public void buscarArea() {

        Areaspagina a = areaspaginaEJB.buscar(id);
        if (a != null) {
            paginasId = a.getPaginasId().getId();
            productosId = a.getProductosId().getId();
            promocionesId = a.getPromocionesId().getId();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Ha encontrado correctamente "));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informacion", "No se encontro nada "));
        }

        System.out.println("ha encontrado  correctamente");
        //   limpiar ();

    }

    public void editarAreas() {

        try {
            Areaspagina a = new Areaspagina();
            //p.setId(id);
            a.setId(id);
            a.setPaginasId(paginaEJB.buscar(paginasId));
            a.setProductosId(productosEJB.buscar(productosId));
            a.setPromocionesId(promocionesEJB.buscar(promocionesId));

            areaspaginaEJB.editar(a);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Ha  editado correctamente  "));
            System.out.println("ha editado correctamente");
            limpiar();
        } catch (Exception e) {
            e.getMessage();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Informacion", "no se ha podido editar  "));

        }

    }
    
    
    public void eliminarArea() {
        try {
            Areaspagina a = areaspaginaEJB.buscar(id);

            areaspaginaEJB.eliminar(a);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Ha eliminado correctamente "));
            System.out.println("ha eliminado  correctamente");
            limpiar();
        } catch (Exception e) {
            e.getMessage();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Informacion", "no se ha podido eliminar  "));

        }

    }

    private void limpiar() {
        setId(0);
    }

}
