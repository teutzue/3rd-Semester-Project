package rest;

import ApiReader.DisplayData;
import com.google.gson.Gson;
import facades.UrlFacade;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import org.json.JSONException;
import org.json.JSONObject;

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

    // End of Get
//    @GET
//    @Path("/{from}/{to}/{date}/{numTickets}")
//    @Produces(MediaType.APPLICATION_JSON){
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
    @Path("/{from}/{to}/{date}/{numTickets}")
    @Produces("application/json")
    public String getJson(
            @PathParam("from") String from,
            @PathParam("to") String to,
            @PathParam("date") String date,
            @PathParam("numTickets") int passengernumber
    ) throws InterruptedException, ExecutionException, JSONException {

     
        
        UrlFacade cus = new UrlFacade(Persistence.createEntityManagerFactory("PU-Local"));
      List<String> newturl = new  ArrayList<>();
      List<String> listurl = cus.getAllUrl();
        for (int i = 0; i < listurl.size(); i++)
        {
            String url = listurl.get(i) + from + "/" + to + "/" + date + "/" + passengernumber;
            System.out.println("the url is "+url);
            newturl.add(url);
        }
        data.addUrls(newturl);
        
        Gson gson = new Gson();
        List<JSONObject> list = data.returnJsonStringAirlineInfo(10);

        String output = "";
        if (list.size() > 1) {
            output += "[";
            output += list.get(0).toString() + ",";
            for (int i = 1; i < list.size(); i++) {
                output += list.get(i).toString();
            }
            output += "]";
        } else {
            output += "[";
            for (int i = 0; i < list.size(); i++) {

                output += list.get(i).toString();
            }
            output += "]";
        }

        System.out.println(output);
        return output;

    }
    
     
//    @GET
//    @Produces("application/json")
//    @Path("{from}/{date}/{numTickets}")
//    public String getFlight(@PathParam("from") String from, @PathParam("date") String date, @PathParam("numTickets") int passengernumber) throws InterruptedException, ExecutionException, JSONException
//    {
//        
//        UrlFacade cus = new UrlFacade(Persistence.createEntityManagerFactory("PU-Local"));
//      List<String> newturl = new  ArrayList<>();
//      List<String> listurl = cus.getAllUrl();
//        for (int i = 0; i < listurl.size(); i++)
//        {
//            String url = listurl.get(i) + from + "/" + date + "/" + passengernumber;
//            System.out.println("the url is "+url);
//            newturl.add(url);
//        }
//        data.addUrls(newturl);
//        
//        Gson gson = new Gson();
//        List<JSONObject> list = data.returnJsonStringAirlineInfo(10);
//
//        String output = "";
//        if (list.size() > 1) {
//            output += "[";
//            output += list.get(0).toString() + ",";
//            for (int i = 1; i < list.size(); i++) {
//                output += list.get(i).toString();
//            }
//            output += "]";
//        } else {
//            output += "[";
//            for (int i = 0; i < list.size(); i++) {
//
//                output += list.get(i).toString();
//            }
//            output += "]";
//        }
//
//        System.out.println(output);
//        return output;

  //  }
//

} // End of Class
