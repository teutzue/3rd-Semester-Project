/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import deploy.DeploymentConfiguration;
import entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * @author bo
 */
public class ReservationsFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);

    public ReservationsFacade() {
    }

    public List<User> getReservations() {
        
        EntityManager em = emf.createEntityManager();
        
        Query query = em.createQuery("SELECT u FROM User u");
        
        List<User> users = query.getResultList();
        
        return users;
    }
    
    public User getReservation(String username) {

        EntityManager em = emf.createEntityManager();

        return em.find(User.class, username);
    } // End of Method
} // End of class
