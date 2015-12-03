package rest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import facades.UserFacade;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("demouser")
@RolesAllowed("User")
public class User {
    private final UserFacade facade = new UserFacade();
    private static Gson gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
    
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String getSomething(){
    return "{\"message\" : \"This message was delivered via a REST call accesible by only authenticated USERS\"}"; 
  }
  
  @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String putJson(String jUser) {

        entity.User user = new entity.User();

        JsonObject joUser = new JsonParser().parse(jUser).getAsJsonObject();

        user.setUserName(joUser.get("username").getAsString());
        user.setPassword(joUser.get("password").getAsString());
        
        user = facade.addUser(user, "User");

        JsonObject feetback = new JsonObject();
        if (user != null) {
            feetback.addProperty("message", "New user is saved");
            feetback.addProperty("isSaved", "yes");
        } else {
            feetback.addProperty("message", "username is already in the database");
            feetback.addProperty("isSaved", "no");

        }
        return feetback.toString();
    } // End of post()
 
}