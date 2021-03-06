/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.managedbeans;

import com.mycompany.Pedidos;
import com.mycompany.sessionbeans.PedidoEJB;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;

/**
 * REST Web Service
 * Servicio web que carga la lista de pedidos
 * @author MAO
 */
@Path("servicio")
@RequestScoped
public class ServicioPedidoResource {

    @EJB
    PedidoEJB pe;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServicioPedidoResource
     */
    public ServicioPedidoResource() {
    }

    /**
     * Retrieves representation of an instance of
     * com.mycompany.managedbeans.ServicioPedidoResource
     *   carga la lista de pedidos en format json
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public List<Pedidos> listarPedidos(){

       return pe.listarPedidos();

    }

    /**
     * PUT method for updating or creating an instance of ServicioPedidoResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
