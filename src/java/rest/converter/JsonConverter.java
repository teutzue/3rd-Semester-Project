/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.converter;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Booking;
import entity.Passenger;
import entity.SearchRequest;
import entity.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bo
 */
public class JsonConverter {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();

    private static JsonObject searches2JsonObj(entity.SearchRequest sr) {
        JsonObject joSearch = new JsonObject();

        joSearch.addProperty(Properties.SEARCH_REQUEST_FROM, sr.getFrom());
        joSearch.addProperty(Properties.SEARCH_REQUEST_TO, sr.getTo());
        joSearch.addProperty(Properties.SEARCH_REQUEST_Date, sr.getDate());
        joSearch.addProperty(Properties.SEARCH_REQUEST_NO_Passengers, sr.getNoPassengers());

        return joSearch;
    }

    // person -> json ..........................................................
    private static JsonObject user2JsonObj(entity.User user) {

        JsonObject joUser = new JsonObject();

        joUser.addProperty(Properties.USER_NAME, user.getUserName());
        joUser.addProperty(Properties.FIRST_NAME, user.getFirstName());
        joUser.addProperty(Properties.LAST_NAME, user.getLastName());
        joUser.addProperty(Properties.EMAIL, user.getEmail());
        joUser.addProperty(Properties.ADDRESS, user.getAddress());
        joUser.addProperty(Properties.CITY, user.getCity());
        joUser.addProperty(Properties.COUNTRY, user.getCountry());
        joUser.addProperty(Properties.ZIPCODE, user.getZipCode());
        joUser.addProperty(Properties.PHONE, user.getPhone());

        List<Booking> bookings = user.getListbook();

        if (!bookings.isEmpty()) {
            JsonArray jaBooking = new JsonArray();
            for (Booking booking : bookings) {
                JsonObject joBooking = new JsonObject();

                joBooking.addProperty(Properties.FLIGHTID, booking.getFlightID());
                joBooking.addProperty(Properties.ORIGIN, booking.getOrigin());
                joBooking.addProperty(Properties.DESTINATION, booking.getDestination());
                joBooking.addProperty(Properties.DATE, booking.getDate());
                joBooking.addProperty(Properties.FLIGHT_TIME, booking.getFlightTime());
                joBooking.addProperty(Properties.NUMBER_OF_SEATS, booking.getNumberOfSeats());
                joBooking.addProperty(Properties.RESERVEE_NAME, booking.getReserveeName());

                List<Passenger> passengers = booking.getList();

                if (!passengers.isEmpty()) {
                    JsonArray jaPassengers = new JsonArray();
                    for (Passenger passenger : passengers) {
                        JsonObject joPassenger = new JsonObject();

                        joPassenger.addProperty(Properties.PASSENGER_FIRST_NAME, passenger.getFirstName());
                        joPassenger.addProperty(Properties.PASSENGER_LAST_NAME, passenger.getLastName());

                        jaPassengers.add(joPassenger);
                    } // End of for-each-loop
                    joBooking.add(Properties.PASSENGERS, jaPassengers);
                } // End of if(passenger)
                jaBooking.add(joBooking);
            } // End of for-each-loop 
            joUser.add(Properties.BOOKINGS, jaBooking);
        } // End of if(booking)

        return joUser;
    }

    public static String user2Json(entity.User user) {

        return user2JsonObj(user).toString(); // return gson.toJson() ! brug ikke jsonAsString

    }
    // End (Person -> json) ..................................

    public static String users2Json(List<entity.User> users) {

        JsonArray jaUsers = new JsonArray();
        for (User user : users) {

            jaUsers.add(user2JsonObj(user));
        }
        return jaUsers.toString();
    } // End of users2Json

    //Search
    public static String serches2Json(List<entity.SearchRequest> searches) {

        JsonArray jaUsers = new JsonArray();
        for (SearchRequest sr : searches) {
            jaUsers.add(searches2JsonObj(sr));
        }
        return jaUsers.toString();
    }

} // End of Class
