/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.beans;

import com.mycompany.Color;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Daniel 
 */
@Local
public interface ColorBeanLocal {
    public void crearColor(Color c);
    public List<Color> listarTodos();
    public Color buscar(int idColor);
}
