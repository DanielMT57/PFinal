/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sessionbeans;

import com.mycompany.Productos;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Daniel
 */
@LocalBean
@Stateless
public class ProductoEJB extends EJBGenerico<Productos>{
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
     // estos ejbs Heredan de un ejb generico
}
