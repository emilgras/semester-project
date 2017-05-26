///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package DBTest;

import Entities.sql.Authors;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Ignore;

/**
 *
 * @author abj
 */
@Ignore
public class SQLDbConnectionTest {
    
    private static EntityManagerFactory entityManagerFactory;

    public SQLDbConnectionTest() {
    
    
    }

     @BeforeClass
    public static void setUpEntityManagerFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory("pu_testDB");
    }

    @AfterClass
    public static void closeEntityManagerFactory() {
        entityManagerFactory.close();
    }

    
    @Test
    public void canPersistAndLoadAuthor(){
    
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    entityManager.getTransaction().begin();
   
    List<Authors> loadedAuthor = entityManager.createNamedQuery("Authors.findAll", Authors.class).getResultList();
    assertNotNull(loadedAuthor);
    entityManager.close();
    
    }
    
}
