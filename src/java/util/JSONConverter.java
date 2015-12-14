package util;

//import com.google.gson.Gson;
import ApiReader.DisplayData;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Booking;
import entity.Passenger;
import entity.User;
import facades.UrlFacade;
import javax.persistence.Persistence;

/**
 *
 * @author Yoana
 */
public class JSONConverter {
    
        DisplayData data = new DisplayData();
   String url="";
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
     
     
     public void postBookAndUser(String name,String jsonAsString)
     {
         
//         Gson gson = new Gson();  
//         JsonObject json = new JsonObject();
//         UrlFacade  urlr= new UrlFacade(Persistence.createEntityManagerFactory("PU-Local"));
//         // UrlFacade urlr= new UrlFacade(Persistence.createEntityManagerFactory("PU-Local"));
//        System.out.println("name "+name);
//        String result = urlr.findUrl(name);
//        System.out.println("the url is "+result);
//        url = result;
//        
//        User u = new User();
//          Booking p = new Booking(); 
//          
//        JsonObject user = new JsonParser().parse(jsonAsString).getAsJsonObject();
//       u.setUserName(user.get("username").getAsString());
//       //Bo password thingy
//       u.setPassword(user.get("password").getAsString());
//        u.setFirstName(user.get("firstName").getAsString());
//        u.setLastName(user.get("lastName").getAsString());
//        u.setEmail(user.get("email").getAsString());
//        
//        u.setFirstName(user.get("address").getAsString());
//        u.setLastName(user.get("city").getAsString());
//        u.setEmail(user.get("country").getAsString());
//        u.setEmail(user.get("zipCode").getAsString());
//        u.setEmail(user.get("phone").getAsString());
        
        
         
//        p.setFlightID(user.get("flightID").getAsString());  
//        p.setOrigin(user.get("Origin").getAsString());
//        p.setDestination(user.get("Destination").getAsString());
//        p.setDate(user.get("Date").getAsString());
//        p.setFlightTime(user.get("FlightTime").getAsInt());
//        p.setNumberOfSeats(user.get("numberOfSeats").getAsInt());
//        p.setDate(user.get("Date").getAsString());
//        p.setReserveeName(user.get("ReserveeName").getAsString());
//        
//         if(user.has("Passengers"))
//        {
//            
//        JsonArray phonesArr = user.getAsJsonArray("Passengers");
//        for (JsonElement pjo : phonesArr) 
//        {
//            Passenger pas = new Passenger();
//            String firstName  = pjo.getAsJsonObject().get("firstName").getAsString();
//            String lastName = pjo.getAsJsonObject().get("lastName").getAsString();
//          
//            pas.setFirstName(firstName);
//            pas.setLastName(lastName);
//            p.addPassengers(pas);
//            
//        }
        
        
        
        
//        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
//        con.setRequestProperty("Content-Type", "application/json;");
//        con.setRequestProperty("Accept", "application/json");
//        con.setRequestProperty("Method", "POST");
//        con.setDoOutput(true);
//        PrintWriter pw = new PrintWriter(con.getOutputStream());
//        try (OutputStream os = con.getOutputStream()) {
//            os.write(jsonAsString.getBytes("UTF-8"));
//        }
//        int HttpResult = con.getResponseCode();
//        InputStreamReader is = HttpResult < 400 ? new InputStreamReader(con.getInputStream(), "utf-8")
//                : new InputStreamReader(con.getErrorStream(), "utf-8");
//        Scanner responseReader = new Scanner(is);
//        String response = "";
//        while (responseReader.hasNext()) {
//            response += responseReader.nextLine() + System.getProperty("line.separator");
//        }
//        System.out.println(response);
//        System.out.println(con.getResponseCode());
//        System.out.println(con.getResponseMessage());
//        Booking book = JSONConverter.getBookingFromJSON(response);
//        System.out.println("The book constructed is "+book.toString());       
//         EntityManagerFactory factory;
//         factory = Persistence.createEntityManagerFactory("PU-Local");         
//          EntityManager em = factory.createEntityManager();
//       try{
//          em.getTransaction().begin();    
//          em.persist(book);
//          em.getTransaction().commit();        
//         json.addProperty("info", "Book Saved");
//        return gson.toJson(json);      
//       }catch(Exception e)
//        {
//            json.addProperty("info", "Not Booked");
//            return gson.toJson(json);
//        }   
//       finally{
//
//          em.close();
//     }
   //  }
     
     
     
     
     //   System.out.println(p.getId());
     }
    }
    
    
    
    

