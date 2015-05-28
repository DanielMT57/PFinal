/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.sessionbeans;

import com.mycompany.Pedidos;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.persistence.Query;
import javax.ws.rs.Produces;

/**
 *
 * @author MAO
 */
@Stateless
@LocalBean
@WebService(portName="PedidoWSPort", serviceName="PedidoWSService", targetNamespace="http://localhost:8082/PedidoWSPort/PedidoWSService")
 @Produces("application/json")
public class PedidoEJB extends EJBGenerico<Pedidos>{

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method"

    @WebMethod(operationName = "listarPedidos")
    @WebResult(name="listaPedidos")
   
    public List<Pedidos> listarPedidos(){
        Query q=super.getEm().createNamedQuery(Pedidos.CONSULTA_LISTARTODOS);
        return q.getResultList();
    }
    
    
    
}
