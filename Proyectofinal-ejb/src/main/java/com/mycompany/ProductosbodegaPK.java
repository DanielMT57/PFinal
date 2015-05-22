/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author german
 */
@Embeddable
public class ProductosbodegaPK implements Serializable {
    
    
    private int productosId;
    private int bodegasId;

    public ProductosbodegaPK() {
    }

    public ProductosbodegaPK(int productosId, int bodegasId) {
        this.productosId = productosId;
        this.bodegasId = bodegasId;
    }

    public int getProductosId() {
        return productosId;
    }

    public void setProductosId(int productosId) {
        this.productosId = productosId;
    }

    public int getBodegasId() {
        return bodegasId;
    }

    public void setBodegasId(int bodegasId) {
        this.bodegasId = bodegasId;
    }
  
}
