package facades;

import deploy.DeploymentConfiguration;
import entity.Url;
import entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author CosticaTeodor
 */
public class UrlFacade {

//    EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);

    public UrlFacade() {

    }
    
    
     EntityManagerFactory emf;
    private EntityManager emp;

    public UrlFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }


    protected EntityManager getEntityManager() {
        return emp;
    }

  
    
    

    public List<String> getAllUrl() {
        EntityManager em = emf.createEntityManager();
//        TypedQuery<String> query = em.createQuery("SELECT c FROM Url c", (Class<T>) Url.class);
        TypedQuery<String> query = em.createQuery("Select c.url FROM Url c",String.class);
        // query.setParameter("phonenr", phone);
        List<String> results = null;
        try {
            results = query.getResultList();
        } catch (Exception e) {
            System.out.println("Fejl i getAllPersons: " + e.getMessage());
        }
        return results;

    }
    
    

    public Url getUrlByUserId(String id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Url.class, id);
        } finally {
            em.close();
        }
    }
}
