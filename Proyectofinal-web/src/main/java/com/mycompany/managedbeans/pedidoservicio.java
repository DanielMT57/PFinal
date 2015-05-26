/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.managedbeans;

import javax.ejb.Stateless;
import javax.ws.rs.Path;
import com.mycompany.Pedidos;
import com.mycompany.sessionbeans.PedidoEJB;
import java.util.List;
import javax.ws.rs.GET;

/**
 *
 * @author MAO
 */

   @Stateless
 @Path("/servicio")
public class pedidoservicio {
    
    PedidoEJB pe;
    
    
    @GET
   public List<Pedidos> listarPedidos(){
       
        return pe.listarTodos();
        
    }
}
