
package testers;

import deploy.DeploymentConfiguration;
import entity.Booking;
import entity.Passenger;

import facades.UrlFacade;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author bo
 */
public class BoTester {
    
    public static void main(String[] args) {
        
        Persistence.generateSchema(DeploymentConfiguration.PU_NAME, null);
//         UrlFacade cus = new UrlFacade(Persistence.createEntityManagerFactory("PU-Local"));
//         List<String> string = cus.getAllUrl();
//         for (int i = 0; i < string.size(); i++)
//        {
//            System.out.println(string.get(i));    
//        }
        
        
//        Booking book = new Booking();
//        book.setDate("2016-01-10T19:00:00.000Z");
//        book.setFlightID("COL3256x100x2016-01-10T19:00:00.000Z");
//        book.setDestination("London Stansted(STN)");
//        book.setOrigin("Copenhagen Kastrup(CPH)");
//        book.setFlightTime(90);
//        book.setNumberOfSeats(2);
//        book.setReserveeName("Peter Hansen");
//        Passenger one = new Passenger("Peter","Peterson");
//        Passenger two=new Passenger("Jane","Peterson");
//       
//           
//        
//        book.addPassengers(one);
//        book.addPassengers(two);
//        book.setUsername("Pesho");
//    
//    
//          EntityManagerFactory factory;
//          factory = Persistence.createEntityManagerFactory("PU-Local");
//          
//          EntityManager em = factory.createEntityManager();
//       try{
//          em.getTransaction().begin();
//       
//          em.persist(book);
//          em.getTransaction().commit();
//       }finally{
//
//          em.close();
//     }
//        
        
    }
    
    
} // End of Class
