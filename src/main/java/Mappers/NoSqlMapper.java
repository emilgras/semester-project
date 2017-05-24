package Mappers;

import Entities.nosql.AuthorGraphEntity;
import Entities.nosql.BookGraphEntity;
import Entities.nosql.CityGraphEntity;
import Entities.nosql.GeoLocation;
import Facades.PersistenceManager;
import Facades.PersistenceManager.Database;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class NoSqlMapper implements NoSqlMapperInterface {

    private EntityManagerFactory sessionFactory;
    private EntityManager entityManager;
    private PersistenceManager persistenceManager;

    public NoSqlMapper() {
        persistenceManager = new PersistenceManager(Database.GRAPH);
    }

    @Override
    public List<BookGraphEntity> getBooksMentioningCity(String cityName) {
        String getBooksMentioningCityQuery = "MATCH (b:Book)-[:MENTIONS]->(c:City) WHERE b.cityName = '" + cityName + "' RETURN b";
        persistenceManager.getEntityManager().getTransaction().begin();
        List<BookGraphEntity> result = entityManager.createNativeQuery(getBooksMentioningCityQuery).getResultList();
        persistenceManager.getEntityManager().getTransaction().commit();
        persistenceManager.close();
        
        for (BookGraphEntity city : result) {
            System.out.println("City: " + city);
        }
        
        return result;
    }

    @Override
    public List<CityGraphEntity> getAllCitiesByBookTitle(String bookTitle) {
        String getAllCitiesByBookTitleQuery = "MATCH (c:City)<-[:MENTIONS]-(b:book) WHERE b.title = '" + bookTitle + "' RETURN c";
        entityManager.getTransaction().begin();
        List<CityGraphEntity> result = entityManager.createNativeQuery(getAllCitiesByBookTitleQuery).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }

    @Override
    public List<BookGraphEntity> getAllBooksWrittenByAuthor(String author) {
        String getAllBooksWrittenByAuthorQuery = "MATCH (b:Book)<-[:WROTE]-(a:Author) WHERE a.name = '" + author + "' RETURN b";
        entityManager.getTransaction().begin();
        List<BookGraphEntity> result = entityManager.createNativeQuery(getAllBooksWrittenByAuthorQuery).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }

    @Override
    public List<BookGraphEntity> getAuthorsByCityName(GeoLocation geo) {
        String getAuthorsByCityNameQuery = "MATCH (b:Books)-[:MENTIONS]-(c:City)-[]";
        entityManager.getTransaction().begin();
        List<BookGraphEntity> result = entityManager.createNativeQuery(getAuthorsByCityNameQuery).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }
    
    public AuthorGraphEntity getAllAuthors(){
        AuthorGraphEntity bobTheHub = new AuthorGraphEntity();
        bobTheHub.setId(165744L);
        persistenceManager.getEntityManager().getTransaction().begin();
        AuthorGraphEntity authorGraph = persistenceManager.getEntityManager().find(AuthorGraphEntity.class, bobTheHub.getId());
        
        persistenceManager.close();
        return authorGraph;
    }

    public static void main(String[] args) {
        NoSqlMapper map = new NoSqlMapper();
//        for (AuthorGraphEntity allAuthor : map.getAllAuthors()) {
//            System.out.println(allAuthor.getId());
//        }
        System.out.println(map.getAllAuthors().getId());
    }
    
}
