
package rest;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.SearchRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author CosticaTeodor
 */

@Path("searchRequest")
public class SearchReqResource {
    //addSearchReq @Param{ 2 tipuri }
    //getAll @Param {allSRequ}
    
    @GET
    @Path("/{from}/{to}/{date}/{numTickets}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void putSearchRequestComplete(@PathParam("from") String from,
            @PathParam("to") String to,
            @PathParam("date") String date,
            @PathParam("numTickets") int passengernumber){
        
        SearchRequest sr = new SearchRequest();
        
        sr.setTo(to);
        sr.setFrom(from);
        sr.setDate(date);
        sr.setNoPassengers(Integer.toString(passengernumber));
    }
}
