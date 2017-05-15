
import Entities.nosql.Author;
import Entities.nosql.BookGraphEntity;
import Entities.nosql.CityGraphEntity;
import Entities.nosql.GeoLocation;
import antlr.debug.Event;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.SessionFactory;

public class NoSqlMapper implements NoSqlMapperInterface {

    private EntityManagerFactory sessionFactory;
    private EntityManager entityManager;

    public NoSqlMapper() {
        sessionFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        entityManager = sessionFactory.createEntityManager();
    }

    @Override
    public List<BookGraphEntity> getBooksMentioningCity(String cityName) {
        String getBooksMentioningCityQuery = "MATCH (b:Book)-[:MENTIONS]->(c:City) WHERE b.cityName = '" + cityName + "' RETURN b";
        entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<BookGraphEntity> result = entityManager.createNativeQuery(getBooksMentioningCityQuery).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        
        for (BookGraphEntity city : result) {
            System.out.println("City: " + city);
        }
        
        return result;
    }

    @Override
    public List<CityGraphEntity> getAllCitiesByBookTitle(String bookTitle) {
        String getAllCitiesByBookTitleQuery = "MATCH (c:City)<-[:MENTIONS]-(b:book) WHERE b.title = '" + bookTitle + "' RETURN c";
        entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<CityGraphEntity> result = entityManager.createNativeQuery(getAllCitiesByBookTitleQuery).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }

    @Override
    public List<BookGraphEntity> getAllBooksWrittenByAuthor(String author) {
        String getAllBooksWrittenByAuthorQuery = "MATCH (b:Book)<-[:WROTE]-(a:Author) WHERE a.name = '" + author + "' RETURN b";
        entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<BookGraphEntity> result = entityManager.createNativeQuery(getAllBooksWrittenByAuthorQuery).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }

    @Override
    public List<BookGraphEntity> getAuthorsByCityName(GeoLocation geo) {
        String getAuthorsByCityNameQuery = "MATCH (b:Books)-[:MENTIONS]-(c:City)-[]";
        entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<BookGraphEntity> result = entityManager.createNativeQuery(getAuthorsByCityNameQuery).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }

}
