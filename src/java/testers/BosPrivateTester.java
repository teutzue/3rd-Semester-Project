/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testers;

import entity.Booking;
import static entity.Booking_.user;
import entity.Passenger;
import entity.User;
import facades.ReservationsFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author bo
 */
public class BosPrivateTester {
    
    private static ReservationsFacade rf = new ReservationsFacade();

    public static void main(String[] args) {

//        EntityManagerFactory factory;
//        factory = Persistence.createEntityManagerFactory("PU-Local");
//        
//        EntityManager em = factory.createEntityManager();
//        
//       
//        
//
//        User user = em.find(User.class, "user");
        
//        User user = rf.getReservation("user");
//        
//        System.out.println(
//        
//        
//        
//                user.getCity() + " " +
//                user.getEmail() + " " +
//                
//                user.getListbook().get(0).getFlightID() + " " +
//                user.getListbook().get(0).getList().get(1).getFirstName()
//        
//        
//        );
        
        rest.Reservations r = new rest.Reservations();
        
//        r.getReservee("user");
        r.getReservees();
        
        
    } // End of main

} // End of Class
