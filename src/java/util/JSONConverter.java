package util;

//import com.google.gson.Gson;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Booking;
import entity.Passenger;

/**
 *
 * @author Yoana
 */
public class JSONConverter {
    
    
     public static Booking getBookingFromJSON(String jsonAsString)
    {
        
        System.out.println("the string i s "+jsonAsString);
        Gson gson = new Gson();
        
        Booking p = new Booking(); 
        
        JsonObject book = new JsonParser().parse(jsonAsString).getAsJsonObject();
        System.out.println("the book is "+book.toString());
        
     
        
        p.setFlightID(book.get("flightID").getAsString());  
        p.setOrigin(book.get("Origin").getAsString());
        p.setDestination(book.get("Destination").getAsString());
        p.setDate(book.get("Date").getAsString());
        p.setFlightTime(book.get("FlightTime").getAsInt());
        p.setNumberOfSeats(book.get("numberOfSeats").getAsInt());
        p.setDate(book.get("Date").getAsString());
        p.setReserveeName(book.get("ReserveeName").getAsString());
        
         if(book.has("Passengers"))
        {
            
        JsonArray phonesArr = book.getAsJsonArray("Passengers");
        for (JsonElement pjo : phonesArr) 
        {
            Passenger pas = new Passenger();
            String firstName  = pjo.getAsJsonObject().get("firstName").getAsString();
            String lastName = pjo.getAsJsonObject().get("lastName").getAsString();
          
            pas.setFirstName(firstName);
            pas.setLastName(lastName);
            p.addPassengers(pas);
            
        }
        }
        
        
        return p;
       }
     //   System.out.println(p.getId());

    }
    
    
    
    

