/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import facades.ReservationsFacade;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import rest.converter.JsonConverter;

/**
 * REST Web Service
 *
 * @author bo
 */
@Path("reservations")
//@RolesAllowed("User")
public class Reservations {
    
    private ReservationsFacade rf = new ReservationsFacade();

    @Context
    private UriInfo context;

    public Reservations() {
    }
    
    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("User")
    public String getReservee(@PathParam("username") String username) {
        
        entity.User user = rf.getReservation(username);
        
        return JsonConverter.user2Json(user);
    } // End of get

    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("Admin")
    public String getReservees() {
        
        List<entity.User> users = rf.getReservations();
        
        return JsonConverter.users2Json(users);
    } // End of get
    
} // End of class
