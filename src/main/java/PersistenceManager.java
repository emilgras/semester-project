
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author abj
 * 
 * http://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
 */
public enum PersistenceManager {
    INSTANCE;
    /*
    Creating an EntityManagerFactory is a pretty expensive operation 
    and should be done once for the lifetime of the application
    
    */
    private EntityManagerFactory emFactory;
    
    //Change db here
    private PersistenceManager(){
        emFactory = Persistence.createEntityManagerFactory("pu_localDB");
    }
    
    public EntityManager getEntityManager(){
        return emFactory.createEntityManager();
    }
    /*
     Closing the EntityManager will actually release the JDBC connection 
    no, you should not close it for each persist/update/delete operation.
    */
    public void close(){
        emFactory.close();
    }
}
