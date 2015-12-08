package deploy;

import entity.Role;
import entity.Url;
import entity.User;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import security.PasswordHash;

@WebListener
public class DeploymentConfiguration implements ServletContextListener {

  public static String PU_NAME = "PU-Local";

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    Map<String, String> env = System.getenv();
    //If we are running in the OPENSHIFT environment change the pu-name 
    if (env.keySet().contains("OPENSHIFT_MYSQL_DB_HOST")) {
      PU_NAME = "PU_OPENSHIFT";
    }
    try {
      ServletContext context = sce.getServletContext();
      EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);
      Persistence.generateSchema(DeploymentConfiguration.PU_NAME, null);
      EntityManager em = emf.createEntityManager();
      
      //This flag is set in Web.xml -- Make sure to disable for a REAL system
      boolean makeTestUsers = context.getInitParameter("makeTestUsers").toLowerCase().equals("true");
      if (!makeTestUsers
              || (em.find(User.class, "user") != null && em.find(User.class, "admin") != null && em.find(User.class, "user_admin") != null)) {
        return;
      }
      Role userRole = new Role("User");
      Role adminRole = new Role("Admin");
      Url url1 = new Url();
      url1.setUrl("http://angularairline-plaul.rhcloud.com/api/flightinfo/");
      Url url2 = new Url();
      url2.setUrl("http://sargardon-001-site1.atempurl.com/api/flightinfo/");
      //  String email, String address, String city, String country, String zipCode, int phone)
      User user = new User(PasswordHash.createHash("test"),"user","Bo","Vilstrup","bo@yahoo.com","adress 200","Kongens Lyngby","Danmark",2800,72952797);
      User admin = new User(PasswordHash.createHash("test"),"admin","Bo","Vilstrup","bo@yahoo.com","adress 200","Kongens Lyngby","Danmark",2800,72952797);
      User both = new User(PasswordHash.createHash("test"),"user_admin","Bo","Vilstrup","bo@yahoo.com","adress 200","Kongens Lyngby","Danmark",2800,72952797);
      user.AddRole(userRole);
      admin.AddRole(adminRole);
      both.AddRole(userRole);
      both.AddRole(adminRole);

      try {
        em.getTransaction().begin();
        em.persist(userRole);
        em.persist(adminRole);
        em.persist(url1);
        em.persist(url2);
        em.persist(user);
        em.persist(admin);
        em.persist(both);
        em.getTransaction().commit();
      } finally {
        em.close();
      }
    } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
      Logger.getLogger(DeploymentConfiguration.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
  }
}
