/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.beans;

import com.mycompany.Productos;
import com.mycompany.sessionbeans.EJBGenerico;
import com.mycompany.sessionbeans.ProductoEJB;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Daniel
 */
@Stateless
public class ProductoBean extends EJBGenerico<Productos> implements ProductoBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    ProductoEJB productoEJB;
    
    
    @Override
   public void crearProducto(Productos p){
       productoEJB.crear(p);
   } 
}
