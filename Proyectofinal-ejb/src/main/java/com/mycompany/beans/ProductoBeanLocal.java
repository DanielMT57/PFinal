/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.beans;

import com.mycompany.Productos;
import javax.ejb.Local;

/**
 *
 * @author Daniel
 */
@Local
public interface ProductoBeanLocal {
    public void crearProducto(Productos p);
}
