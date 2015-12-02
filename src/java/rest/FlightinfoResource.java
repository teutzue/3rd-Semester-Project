package rest;


import ApiReader.DisplayData;
import ApiReader.GetTheAirlineInfo;
import com.google.gson.Gson;
import java.util.concurrent.ExecutionException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author bo
 */
@Path("flightinfo")
public class FlightinfoResource {

//    private GetTheAirlineInfo get = new GetTheAirlineInfo();
    DisplayData data = new DisplayData();
    
    @Context
    private UriInfo context;

    public FlightinfoResource() {
    }

//    @GET
//    @Path("/{from}/{date}/{numTickets}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public String getFrom(
//            @PathParam("from") String dDate,
//            @PathParam("from") String fDate,
//    
//    ) {
//    
//        
//    } // End of Get
//    @GET
//    @Path("/{from}/{to}/{date}/{numTickets}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public String getFromTo(
//            @PathParam("from") String from,
//            @PathParam("to") String to,
//            @PathParam("date") String date,
//            @PathParam("numTickets") String tickets
//    ) {
//
//        System.out.println(
//        
//                from + "\n" +
//                to + "\n" +
//                date + "\n" +
//                tickets + "\n" 
//        
//        );
//        
//        return "";
//    } // End of Get
    
     @GET
    @Path("info")
    @Produces("application/json")
    public String getJson() throws InterruptedException, ExecutionException
    {
   
        
        
        Gson gson = new Gson();
        String stringinfo = data.returnJsonStringAirlineInfo(2);
        System.out.println(stringinfo);
         return stringinfo;
    }
//
    
    
} // End of Class