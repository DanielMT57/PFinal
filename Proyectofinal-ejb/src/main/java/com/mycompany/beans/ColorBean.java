/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.beans;

import com.mycompany.Color;
import com.mycompany.sessionbeans.EJBGenerico;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Daniel
 */
@Stateless
public class ColorBean extends EJBGenerico<Color> implements ColorBeanLocal {

    @Override
    public void crearColor(Color c) {
       super.crear(c);
    }

    @Override
    public List<Color> listarTodos() {
        return super.listarTodos();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public Color buscar(int idColor) {
        return super.buscar(idColor);    
    }
}
