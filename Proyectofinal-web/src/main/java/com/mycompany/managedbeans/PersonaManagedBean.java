/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.managedbeans;

import com.mycompany.Ciudades;
import com.mycompany.Nivel;
import com.mycompany.Personas;
import com.mycompany.Afiliados;

import com.mycompany.sessionbeans.CiudadesEJB;
import com.mycompany.sessionbeans.NivelesEJB;
import com.mycompany.sessionbeans.PersonasEJB;
import com.mycompany.sessionbeans.AfiliadoEJB;

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
 * @author german
 */
@ManagedBean
@Named(value = "personaBean")
@ViewScoped

public class PersonaManagedBean implements Serializable {

    /**
     * Atributos a los que hara referencia la vista de pedidos
     *
     */
    private int cedula;
    private int idCiudades;
    private long telefono;
    private String nombre;
    private String apellido;
    private String direccion;
    private String email;
    private List<Ciudades> ciudades;

    @EJB
    private PersonasEJB personasEJB; // instancio un objeto de personaejb
    @EJB
    private CiudadesEJB ciudadesEJB;  // instancio un objeto de ciudadesejb

    @EJB
    private NivelesEJB nivelesEJB; // instancio un objeto dniveles

    @EJB
    private AfiliadoEJB afiliadosEJB; // instancio un objeto de afiliados

    private List<Nivel> niveles; // declaro una lista de niveles
    private Date fechaAfiliacion;

    private int idNiveles;

    /**
     * Creates a new instance of PersonaManagedBean
     */
    @PostConstruct
    public void postConstruct() {
        ciudades = ciudadesEJB.listarTodos();

        niveles = nivelesEJB.listarTodos();

    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public int getIdCiudades() {
        return idCiudades;
    }

    public void setIdCiudades(int idCiudades) {
        this.idCiudades = idCiudades;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Ciudades> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<Ciudades> ciudades) {
        this.ciudades = ciudades;
    }

    public PersonasEJB getPersonasEJB() {
        return personasEJB;
    }

    public void setPersonasEJB(PersonasEJB personasEJB) {
        this.personasEJB = personasEJB;
    }

    public CiudadesEJB getCiudadesEJB() {
        return ciudadesEJB;
    }

    public void setCiudadesEJB(CiudadesEJB ciudadesEJB) {
        this.ciudadesEJB = ciudadesEJB;
    }

    public List<Nivel> getNiveles() {
        return niveles;
    }

    public void setNiveles(List<Nivel> niveles) {
        this.niveles = niveles;
    }

    public Date getFechaAfiliacion() {
        return fechaAfiliacion;
    }

    public void setFechaAfiliacion(Date fechaAfiliacion) {
        this.fechaAfiliacion = fechaAfiliacion;
    }

    public int getIdNiveles() {
        return idNiveles;
    }

    public void setIdNiveles(int idNiveles) {
        this.idNiveles = idNiveles;
    }

    /**
     * Metodo que se encarga de crear personas encierro en en bloque try catch
     * en caso de no poder insertar lanzar el mensaje que no s epudo insertar
     *
     */
    public void crearPersonas() {
        try {
            Personas pe = new Personas(); // creo una persona
            pe.setCedula(cedula);
            pe.setTelefono(telefono);
            pe.setNombre(nombre);
            pe.setApellidos(apellido);
            pe.setDireccion(direccion);
            pe.setEmail(email);
            pe.setCiudadesId(ciudadesEJB.buscar(idCiudades));
            // creo un afiliado con sus contrcutor y le envio los valores
            Afiliados af = new Afiliados(fechaAfiliacion, '0', nivelesEJB.buscar(idNiveles), cedula, telefono, nombre, apellido, direccion, ciudadesEJB.buscar(idCiudades), email);

            afiliadosEJB.crear(af);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Ha insertado correctamente "));
            System.out.println("ha insertado correctamente");
            limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Informacion", " ERROR---- "));

            e.getMessage();
        }

    }

    /**
     * Metodo que se encarga de buscar una persona, en caso de que la busqueda
     * sea null envia un mensaje indicando de que nose encontro nada
     */
    public void buscarPersona() {
        Personas p = personasEJB.buscar(cedula);
        Afiliados af = afiliadosEJB.buscar(cedula);
        if (p != null) {

            telefono = p.getTelefono();
            direccion = p.getDireccion();
            email = p.getEmail();
            nombre = p.getNombre();
            apellido = p.getApellidos();

            idCiudades = p.getCiudadesId().getId();
            idNiveles = af.getNivel().getId();
            fechaAfiliacion = af.getFechaafiliacion();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Ha encontrado correctamente "));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informacion", "No se encontro nada "));
        }

        System.out.println("ha encontrado  correctamente");
        //   limpiar ();

    }

    /**
     * Metodo que se encarga de actualizar una persona con sus nuevos valores
     */
    public void actualizarPersona() {

        try {
            Afiliados a = afiliadosEJB.buscar(cedula);
            a.setCedula(cedula);
            a.setCiudadesId(ciudadesEJB.buscar(idCiudades));
            a.setNombre(nombre);
            a.setApellidos(apellido);
            a.setDireccion(direccion);
            a.setEmail(email);
            a.setTelefono(telefono);
            idNiveles = a.getNivel().getId();
            fechaAfiliacion = a.getFechaafiliacion();
            a.setNivel(nivelesEJB.buscar(idNiveles));
            a.setFechaafiliacion(fechaAfiliacion);
            afiliadosEJB.editar(a);
            // envio un mensaje si la actualizacion fue exitosa
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Ha actualizado correctamente  "));
            System.out.println("ha actualizado correctamente");
            limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "error al editar  "));
            // en caso de fallar enviar mensaje de error
            e.getMessage();
        }

    }

    private List<Personas> personas; // nueva lista de personas

    /**
     * Metodo que se encarga de listar las personas persistidas en la base de
     * datos
     *
     * @return personas lista
     */
    public List<Personas> getPersonas() {
        personas = personasEJB.listarTodos();
        return personas;
    }

    /**
     * Metodo que limpia los campos
     */
    private void limpiar() {

        this.setCedula(0);
        this.setApellido(null);
        this.setEmail(null);
        this.setNombre(null);
        this.setTelefono(0);
        this.setDireccion(null);

        this.setFechaAfiliacion(null);
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

}
