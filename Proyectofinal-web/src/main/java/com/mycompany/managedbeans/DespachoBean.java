/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.managedbeans;

import com.mycompany.Despachos;
import com.mycompany.sessionbeans.DespachoEJB;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author MAO
 */
@ManagedBean
@Named(value = "DespachoBean")
@ViewScoped
public class DespachoBean implements Serializable {

    /**
     * Creates a new instance of AreasBean
     */
//    public AreasBean() {
//    }
    private List<Despachos> despachos;

    @EJB
    private DespachoEJB despachoEJB;

    @PostConstruct
    public void postConstruct() {
       
        despachos=despachoEJB.listarTodos();
    }
    public List<Despachos> getDespachos() {
        despachos = despachoEJB.listarTodos();
        return despachos;
    }

}
