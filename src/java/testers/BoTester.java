/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testers;

import deploy.DeploymentConfiguration;
import facades.UrlFacade;
import java.util.List;
import javax.persistence.Persistence;

/**
 *
 * @author bo
 */
public class BoTester {
    
    public static void main(String[] args) {
        
        //Persistence.generateSchema(DeploymentConfiguration.PU_NAME, null);
         UrlFacade cus = new UrlFacade(Persistence.createEntityManagerFactory("PU-Local"));
         List<String> string = cus.getAllUrl();
         for (int i = 0; i < string.size(); i++)
        {
            System.out.println(string.get(i));    
        }
    }
    
    
} // End of Class
