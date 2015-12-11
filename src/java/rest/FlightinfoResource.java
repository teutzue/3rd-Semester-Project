package rest;

import util.JSONConverter;
import ApiReader.DisplayData;
//import JSONConverter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Booking;
import entity.Passenger;
import facades.UrlFacade;
import facades.UserFacade;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
   String url="";
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
      @GET
    @Path("allidiots")
    @Produces("application/json")
    public String getUserInfo( )  {

     
        
        UserFacade cus = new UserFacade();
        List<String> usernames = cus.getUserNames();
//          for (int i = 0; i <usernames.size() ; i++) 
//          {
//              List<Booking> bookinfoforuser = 
//          }
        
        
//      List<String> newturl = new  ArrayList<>();
//      List<String> listurl = cus.getAllUrl();
//        for (int i = 0; i < listurl.size(); i++)
//        {
//            String url = listurl.get(i) + from + "/" + to + "/" + date + "/" + passengernumber;
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
        return null;

    }
     
    @GET
    @Produces("application/json")
    @Path("/{from}/{date}/{numTickets}")
    public String getFlight(@PathParam("from") String from, @PathParam("date") String date, @PathParam("numTickets") int passengernumber) throws InterruptedException, ExecutionException, JSONException
    {
        System.out.println("the from "+from+  " the date : "+date + "  numtickets "+passengernumber);
        UrlFacade cus = new UrlFacade(Persistence.createEntityManagerFactory("PU-Local"));
      List<String> newturl = new  ArrayList<>();
      List<String> listurl = cus.getAllUrl();
        for (int i = 0; i < listurl.size(); i++)
        {
            String url = listurl.get(i) + from + "/" + date + "/" + passengernumber;
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
    
     @POST
    @Consumes("application/json")
    @Produces("application/json")
    public String postPerson(String jsonAsString) throws MalformedURLException, IOException 
    {
         Gson gson = new Gson();  
         JsonObject json = new JsonObject();
        
       
    // String url = "http://angularairline-plaul.rhcloud.com/api/flightreservation/";
     
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestProperty("Content-Type", "application/json;");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Method", "POST");
        con.setDoOutput(true);
        PrintWriter pw = new PrintWriter(con.getOutputStream());
        try (OutputStream os = con.getOutputStream()) {
            os.write(jsonAsString.getBytes("UTF-8"));
        }
        int HttpResult = con.getResponseCode();
        InputStreamReader is = HttpResult < 400 ? new InputStreamReader(con.getInputStream(), "utf-8")
                : new InputStreamReader(con.getErrorStream(), "utf-8");
        Scanner responseReader = new Scanner(is);
        String response = "";
        while (responseReader.hasNext()) {
            response += responseReader.nextLine() + System.getProperty("line.separator");
        }
        System.out.println(response);
        System.out.println(con.getResponseCode());
        System.out.println(con.getResponseMessage());
     
        
        Booking book = JSONConverter.getBookingFromJSON(response);
        // Booking book = JSONConverter.getBookingFromJSON(response);
        System.out.println("The book constructed is "+book.toString());
        
         EntityManagerFactory factory;
          factory = Persistence.createEntityManagerFactory("PU-Local");
          
          EntityManager em = factory.createEntityManager();
       try{
          em.getTransaction().begin();
       
          em.persist(book);
          em.getTransaction().commit();
          
          
         json.addProperty("info", "Book Saved");
        return gson.toJson(json);
          
       }catch(Exception e)
        {
            json.addProperty("info", "Not Booked");
            return gson.toJson(json);
        }   
       finally{

          em.close();
     }
        
        
        
    }
    
    @POST
    @Path("name")
    @Consumes("application/json")
    @Produces("application/json")
    public String postAirline(String jsonAsString) throws MalformedURLException, IOException {
        Gson gson = new Gson();
        JsonObject json = new JsonObject();

        JsonObject nameAirline = new JsonParser().parse(jsonAsString).getAsJsonObject();
        System.out.println("the nameAirline is " + nameAirline.toString());

        String name = nameAirline.get("name").getAsString();
        UrlFacade cus = new UrlFacade(Persistence.createEntityManagerFactory("PU-Local"));
        url = cus.findUrl(name);
        System.out.println("the url is " + url);
        
       // return "bla".toJson();
          json.addProperty("info", url);
          return gson.toJson(json);
    }
    
//

} // End of Class
