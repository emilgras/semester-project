/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBTest;

import Entities.sql.Author;
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
    @Ignore
    public void canPersistAndLoadAuthor(){
    
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    entityManager.getTransaction().begin();
   
    // create an Author
    Author bob = new Author( "Bob");
    entityManager.persist( bob );

    entityManager.getTransaction().commit();

    // get a new EM to make sure data is actually retrieved from the store and not Hibernate's internal cache
    entityManager.close();
    entityManager = entityManagerFactory.createEntityManager();
    
     // load it back
    entityManager.getTransaction().begin();

    Author loadedAuthor = entityManager.find( Author.class, bob.getId() );
    assertNotNull(loadedAuthor);
    assertThat(loadedAuthor.getAuthorName(), equalTo("Bob"));
    entityManager.getTransaction().commit();

    entityManager.close();
    
    }
    
}
