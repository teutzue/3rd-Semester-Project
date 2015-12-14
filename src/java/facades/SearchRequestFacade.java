package facades;

import deploy.DeploymentConfiguration;
import entity.SearchRequest;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author CosticaTeodor
 */
public class SearchRequestFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);

    public SearchRequest addSearchRequest(entity.SearchRequest searchReq) {

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(searchReq);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return searchReq;
    }

    public List<String> getAllSearchRequests() {
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("Select c FROM SearchRequest c");
        
        List<String> results = null;
        try {
            results = query.getResultList();
        } catch (Exception e) {
            System.out.println("Fail in getAllSearchRequest: " + e.getMessage());
        }
        return results;

    }// End of method

}
