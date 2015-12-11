package facades;

import deploy.DeploymentConfiguration;
import entity.Role;
import entity.User;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import security.PasswordHash;

public class UserFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);

    public UserFacade() {

    }
    
     public List<String> getUserNames() 
     {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("select c.userName from User c");
        // query.setParameter("phonenr", phone);
        List<String> results = null;
        try {
            results = query.getResultList();
        } catch (Exception e) {
            System.out.println("Fejl i getAllUserNames: " + e.getMessage());
        }
        return results;

        } finally {
            em.close();
        }
    }

    public User getUserByUserId(String id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    public User addUser(entity.User user, String sRole) {

        try {
            user.setPassword(PasswordHash.createHash(user.getPassword()));

        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            System.out.println(ex);
        }

        EntityManager em = emf.createEntityManager();

        try {

            if (em.find(User.class, user.getUserName()) == null) {

                Role newRole = em.find(Role.class, sRole);
                user.AddRole(newRole);

                em.getTransaction().begin();
                em.persist(user);
                em.getTransaction().commit();

            } else {
                user = null;
            }

        } finally {
            em.close();
        }
        return user;
    } // End of method

    public List<String> authenticateUser(String userName, String password) {
        EntityManager em = emf.createEntityManager();
        try {
            User user = em.find(User.class, userName);
            if (user == null) {
                return null;
            }
            try {
                boolean authenticated = PasswordHash.validatePassword(password, user.getPassword());
                //System.out.println("authenticated: " + authenticated);
                return authenticated ? user.getRolesAsStrings() : null;
            } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }

        } finally {
            em.close();
        }

    }

}
