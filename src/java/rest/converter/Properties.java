
package rest.converter;

/**
 *
 * @author bo
 */
public class Properties {

    private Properties() {}

    // User properties
    public static final String PASSWORD = "password";
    public static final String USER_NAME = "userName";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String EMAIL = "email";
    public static final String ADDRESS = "address";
    public static final String CITY = "city";
    public static final String COUNTRY = "country";
    public static final String ZIPCODE = "zipCode";
    public static final String PHONE = "phone";
    
    public static final String BOOKINGS = "listbook";
    

    // Booking properties
    public static final String FLIGHTID = "flightID";
    public static final String ORIGIN = "Origin";
    public static final String DESTINATION = "Destination";
    public static final String DATE = "Date";
    public static final String FLIGHT_TIME = "FlightTime"; // int
    public static final String NUMBER_OF_SEATS = "numberOfSeats"; //int
    public static final String RESERVEE_NAME = "ReserveeName";
    
    public static final String PASSENGERS = "passengers";
    
    // Passenger properties
    public static final String PASSENGER_FIRST_NAME = "firstName";
    public static final String PASSENGER_LAST_NAME = "lastName";
    
    //SearchRequest properties
    public static final String SEARCH_REQUEST_FROM = "from";
    public static final String SEARCH_REQUEST_TO = "to";
    public static final String SEARCH_REQUEST_Date = "Date_SR";
    public static final String SEARCH_REQUEST_NO_Passengers = "noPassengers";
    
} // End of Class
