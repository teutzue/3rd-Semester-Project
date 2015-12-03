package rest;

import ApiReader.DisplayData;
import com.google.gson.Gson;
import java.util.List;
import java.util.concurrent.ExecutionException;
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

        
        System.out.println(date);
        Gson gson = new Gson();
        data.addUrls(from, to, date, passengernumber);
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

//        JsonArray jsonArray = new JsonArray();
//        for (Person p : people) 
//        {
//            JsonObject json = new JsonObject();
//            json.addProperty("id", p.getId());
//            json.addProperty("firstName", p.getFirstName());
//            json.addProperty("lastName", p.getLastName());
//            json.addProperty("email", p.getEmail());
//            
//            List<Phone> phones = p.getPhones();
//            if (!phones.isEmpty()) 
//            {
//            JsonArray phoneArray = new JsonArray();
//            for (Phone phone : phones) {
//                JsonObject phoneJson = new JsonObject();
//                phoneJson.addProperty("number", phone.getNumber());
//                phoneJson.addProperty("description", phone.getDescription());
//                phoneArray.add(phoneJson);
//            }
//            json.add("phones", phoneArray); 
//            }
//
//            if(p.getAddress() != null)
//            {
//                 json.addProperty("street", p.getAddress().getStreet());
//                 json.addProperty("additionalInfo", p.getAddress().getAdditionalInfo());
//                 
//            
//            if(p.getAddress().getCityInfo() != null)
//                    {
//            json.addProperty("zipcode", p.getAddress().getCityInfo().getZipCode());
//            json.addProperty("city", p.getAddress().getCityInfo().getCity());
//                        
//                    }
//            }
//           
//            List<Hobby> hobbies = p.getHobbys();
//            JsonArray hobArray = new JsonArray();
//            if(!hobbies.isEmpty())
//            {
//            for (Hobby hob : hobbies) 
//            {
//                JsonObject hobJson = new JsonObject();
//                hobJson.addProperty("name", hob.getName());
//                hobJson.addProperty("description", hob.getDescription());
//                hobArray.add(hobJson);
//            }
//            json.add("hobbies", hobArray);
//            }
//            jsonArray.add(json);
//        }
//        return gson.toJson(jsonArray);
//         return stringinfo;
//        return gson.toJson(stringinfo);
    }
//

} // End of Class
