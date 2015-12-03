package facades;

import deploy.DeploymentConfiguration;
import entity.Url;
import entity.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author CosticaTeodor
 */
public class UrlFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);

    public UrlFacade() {

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
