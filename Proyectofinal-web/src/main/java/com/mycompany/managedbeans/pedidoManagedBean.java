/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.managedbeans;

import com.mycompany.Afiliados;

import com.mycompany.Estado;
import com.mycompany.Pedidos;
import com.mycompany.Detallepedido;
import com.mycompany.Productos;
import com.mycompany.sessionbeans.AfiliadoEJB;
import com.mycompany.sessionbeans.estadoEJB;
import com.mycompany.sessionbeans.ProductoEJB;
import com.mycompany.sessionbeans.PedidoEJB;
import com.mycompany.sessionbeans.detallePedidoEJB;

import java.io.Serializable;
import java.util.Date;
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
 * @author MAO
 */
@ManagedBean
@Named(value = "pedidoManagedBean")
@ViewScoped
public class pedidoManagedBean implements Serializable {

    /**
     * Atributos de la vista de pedidos
     */
    private int idPedido;
    private int idPedidoList;
    private int idAfiliado;
    private int idestado;
    private List<Afiliados> afiliados; // declaro una nueva de afiliados
    private List<Estado> estados; // declaro una nueva de estados
    private Date fecha;
    private String descripcion;
    private List<Detallepedido> detalles; // declaro una nueva de detalles
    private List<Pedidos> pedidos; // declaro una nueva de afiliados de pedidos

    private int ide;
    ////
    private int idProducto;
    private double precioUnitario;
    private int cantidad;
    private List<Productos> productos;

    @EJB
    private ProductoEJB productoEJB; //Instancio un objeto de productoEJB

    @EJB
    private AfiliadoEJB afiliadosEJB; //Instancio un objeto de afiliadosEJB

    @EJB
    private estadoEJB estadoEJB; //Instancio un objeto de estadoEJB

    @EJB
    private PedidoEJB pedidoEJB; //Instancio un objeto de pedidoEJB

    @EJB
    private detallePedidoEJB detallepedidoEJB; //Instancio un objeto de detallepedidoEJB

    //Getters y setters de los atributos
    public int getIde() {
        return ide;
    }

    public void setIde(int ide) {
        this.ide = ide;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdAfiliado() {
        return idAfiliado;
    }

    public void setIdAfiliado(int idAfiliado) {
        this.idAfiliado = idAfiliado;
    }

    public List<Afiliados> getAfiliados() {
        return afiliados;
    }

    public void setAfiliados(List<Afiliados> afiliados) {
        this.afiliados = afiliados;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdestado() {
        return idestado;
    }

    public void setIdestado(int idestado) {
        this.idestado = idestado;
    }

    @PostConstruct
    public void postConstruct() {
        afiliados = afiliadosEJB.listarTodos();   // carga el combo de afiliados
        estados = estadoEJB.listarTodos(); // carga el combo de estados
        productos = productoEJB.listarTodos(); // carga el combo de productos
        pedidos = pedidoEJB.listarPedidos(); // carga el combo de pedidos
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {

        this.precioUnitario = precioUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<Productos> getProductos() {
        return productos;
    }

    public void setProductos(List<Productos> productos) {
        this.productos = productos;
    }

    public List<Pedidos> getPedidos() {
        return pedidos;
    }

    /**
     * Metodo que carga los detalles pedidos a la vista, persistidos en la base
     * de datos
     *
     * @return detalles
     */
    public List<Detallepedido> getDetalles() {
        detalles = detallepedidoEJB.listarTodos();
        return detalles;
    }

    /**
     * Metodo que carga el precioventa
     */
    public void cargar() {
        Productos p = productoEJB.buscar(idProducto);
        double precio = p.getPrecioVenta();
        this.setPrecioUnitario(precio);
    }

    /**
     * Metodo que carga el precio unitario de un producto
     */
    public void actualizar() {
        Productos p = productoEJB.buscar(idProducto);
        precioUnitario = p.getPrecioVenta();
    }

    /**
     * Metodo que se encarga de crear los pedidos seteando los valores con
     * sincronizado y en inicio cuando ya haya sincronizado cambia el estado
     */
    public void crearPedidos() {

        Pedidos pe = new Pedidos();
        pe.setId(idPedido);
        pe.setAfiliadosCedula(afiliadosEJB.buscar(idAfiliado));
        pe.setEstadoId(estadoEJB.buscar(idestado));
        pe.setFecha(fecha);
        pe.setSincronizado('0');
        pe.setDescripcion("Inicio");
        pedidoEJB.crear(pe);
        pedidos = pedidoEJB.listarPedidos();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Ha insertado correctamente  "));
        System.out.println("ha insertado correctamente");
        limpiar();

    }

    /**
     * Metodo que se encarga de crear los detalles de los pedidos Teniendo en
     * cuenta que un pedido puede tener muchos detalles
     */
    public void crearDetallePedido() {
        try {
            Detallepedido de = new Detallepedido();
            //Buscar el id de los pedidos y los persiste
            de.setPedidos(pedidoEJB.buscar(idPedidoList));
            de.setProductos(productoEJB.buscar(idProducto));
            de.setCantidad(cantidad);
            de.setPreciounitario(precioUnitario);
            de.setSincronizado('0');
            detallepedidoEJB.crear(de);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Ha insertado correctamente  "));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "No se pudo insertar "));
            // en caso de ocurrir un error envia un mensaje
            e.getMessage();
        }

    }

    public void buscarDetallePedido() {
//        Detallepedido de = detallepedidoEJB.buscar(ide);
//            if (de != null) {
//                idProducto = de.getProductos().getId();
//                cantidad = de.getCantidad();
//                precioUnitario = de.getPreciounitario();
//            }
    }

    public void buscarPedido() {
        Pedidos pe = pedidoEJB.buscar(idPedido);
        if (pe != null) {
            descripcion = pe.getDescripcion();
            idAfiliado = pe.getAfiliadosCedula().getCedula();
            idestado = pe.getEstadoId().getId();
            fecha = pe.getFecha();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Ha encontrado correctamente "));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informacion", "No se encontro nada "));
        }
        //   limpiar ();

    }

    public void actualizarDetallePedido() {
//        Detallepedido de = pedidoEJB.
//        // de.setDetallepedidoPK(idPedido, idProducto);
//        de.setPedidos(pedidoEJB.buscar(idPedido));
//        de.setProductos(productoEJB.buscar(idProducto));
//        de.setCantidad(cantidad);
//        de.setPreciounitario(precioUnitario);
//        de.setSincronizado('0');
//        detallepedidoEJB.editar(de);
    }

    /**
     * Metodo que actualiza un pedido con sus nuevos valores envia un mensaje
     * cuando la transaccion sea exitosa
     */
    public void actualizarPedido() {
        Pedidos pe = pedidoEJB.buscar(idPedido);
        pe.setAfiliadosCedula(afiliadosEJB.buscar(idAfiliado));
        pe.setEstadoId(estadoEJB.buscar(idestado));
        pe.setFecha(fecha);
        pe.setSincronizado('0');
        pe.setDescripcion(descripcion);
        pedidoEJB.editar(pe);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Ha actualizado correctamente  "));
        limpiar();
    }

    /**
     * Metodo que limpia los campos cuando se hatan realizado transacciones
     * exitosas
     */
    public void limpiar() {
        this.setIdPedido(0);
        this.setCantidad(0);
        this.setDescripcion(null);
        this.setFecha(null);
        this.setPrecioUnitario(0);
    }

    public int getIdPedidoList() {
        return idPedidoList;
    }

    public void setIdPedidoList(int idPedidoList) {
        this.idPedidoList = idPedidoList;
    }

}
