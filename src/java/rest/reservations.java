/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

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

/**
 * REST Web Service
 *
 * @author bo
 */
@Path("reservations")
@RolesAllowed("User")
public class reservations {

    @Context
    private UriInfo context;

    public reservations() {
    }

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@PathParam("username") String username) {
        System.out.println("username" + username);
        return "{\"message\" : \"This message was delivered via a REST call accesible by only authenticated USERS\"}";
    } // End of get

} // End of class
