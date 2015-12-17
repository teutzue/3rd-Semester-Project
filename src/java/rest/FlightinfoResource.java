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
import entity.SearchRequest;
import facades.SearchRequestFacade;
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
import static org.json.XMLTokener.entity;

/**
 * REST Web Service
 *
 * @author bo
 */
@Path("flightinfo")
public class FlightinfoResource {


    DisplayData data = new DisplayData();
   String url="";
    @Context
    private UriInfo context;

    public FlightinfoResource() {
    }

    @GET
    @Path("/{from}/{to}/{date}/{numTickets}")
    @Produces("application/json")
    public String getJson(
            @PathParam("from") String from,
            @PathParam("to") String to,
            @PathParam("date") String date,
            @PathParam("numTickets") int passengernumber
    ) throws InterruptedException, ExecutionException, JSONException {
        SearchRequestFacade src = new SearchRequestFacade();
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
        
        SearchRequest sr = new SearchRequest();
        
        sr.setTo(to);
        src.addSearchRequest(sr);
        
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
    @Path("/{name}/{username}")
    @Consumes("application/json")
    @Produces("application/json")
    public String postPerson(@PathParam("name") String name,@PathParam("username") String username, String jsonAsString) throws MalformedURLException, IOException 
    {
        
        System.out.println("the username is "+username);
        
         EntityManagerFactory factory;
         factory = Persistence.createEntityManagerFactory("PU-Local");  
         
         Gson gson = new Gson();  
         JsonObject json = new JsonObject();
         
        JsonObject person = new JsonParser().parse(jsonAsString).getAsJsonObject();
        System.out.println("the json returned "+ person.toString());
      //   return person.toString();
         
       UrlFacade urlr= new UrlFacade(Persistence.createEntityManagerFactory("PU-Local"));
        System.out.println("name "+name);
        String result = urlr.findUrl(name);
        System.out.println("the url is "+result);
        url = result;
       
        
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
        System.out.println("The book constructed is "+book.toString());  
        
          EntityManager em = factory.createEntityManager();
          entity.User user = em.find( entity.User.class ,username);
          book.setUser(user);
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

} // End of Class
