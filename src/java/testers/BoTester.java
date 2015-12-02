/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testers;

import deploy.DeploymentConfiguration;
import javax.persistence.Persistence;

/**
 *
 * @author bo
 */
public class BoTester {
    
    public static void main(String[] args) {
        
        Persistence.generateSchema(DeploymentConfiguration.PU_NAME, null);
    }
    
    
} // End of Class
