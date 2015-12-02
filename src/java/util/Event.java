package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author CosticaTeodor
 */

/*
imports for jpa
@Temporal(javax.persistence.TemporalType.TIMESTAMP) 
@Temporal(javax.persistence.TemporalType.DATE)
@Temporal(javax.persistence.TemporalType.TIME)
*/
public class Event {

    String name;
    Date date;

    public Event(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static void main(String[] args) throws ParseException {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").
                setPrettyPrinting().create(); // note the 'Z'
        
        Event ev = new 
        Event("Christmas", new SimpleDateFormat("dd-M-yyyy").parse("24-12-2016"));
        String jsonStr = gson.toJson(ev);

        Event ev2 = gson.fromJson(jsonStr, Event.class);

        System.out.println(ev2.getName() + ", " + ev2.getDate());
    }

}
