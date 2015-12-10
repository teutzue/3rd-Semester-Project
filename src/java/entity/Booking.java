/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 *
 * @author Yoana
 */
@Entity
public class Booking implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id

    private String Username;

    public String getusername() {
        return Username;
    }

    public void setgetusername(String username) {
        this.Username = username;
    }

    public Booking() {
    }

    
    String flightID;
    String Origin;
    String Destination;
    String Date;
    int FlightTime;
    int numberOfSeats;
    String ReserveeName;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public String getFlightID() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public String getOrigin() {
        return Origin;
    }

    public void setOrigin(String Origin) {
        this.Origin = Origin;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String Destination) {
        this.Destination = Destination;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public int getFlightTime() {
        return FlightTime;
    }

    public void setFlightTime(int FlightTime) {
        this.FlightTime = FlightTime;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getReserveeName() {
        return ReserveeName;
    }

    public void setReserveeName(String ReserveeName) {
        this.ReserveeName = ReserveeName;
    }

    @OneToMany(cascade=CascadeType.PERSIST)
    @JoinColumn      
    List<Passenger> list;
  
    public void addPassengers(Passenger cc) 
    {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(cc);
        
    }

    public List<Passenger> getList() {
        return list;
    }

    public void setList(List<Passenger> list) {
        this.list = list;
    }
    
    
    
    
}