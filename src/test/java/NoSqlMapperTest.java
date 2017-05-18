import Entities.nosql.BookGraphEntity;
import Entities.nosql.CityGraphEntity;
import Entities.nosql.GeoLocation;
import Mappers.NoSqlMapper;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@Ignore
public class NoSqlMapperTest {

    static NoSqlMapper mapper;

    @BeforeClass
    public static void beforeClass () {
        mapper = new NoSqlMapper();

    }

    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(Description description) {
            System.out.println("Starting test... " + description.getMethodName() + "\n");
        }
    };

    @Test
    @DisplayName("should return a list of all book titles and corresponding author, which content mention a given city name")
    public void testGetBooksMentioningCity () {

        String cityname = "";

        List<BookGraphEntity> expectedResult = new ArrayList();

        List<BookGraphEntity> actualResult = mapper.getBooksMentioningCity(cityname);

        assertThat(actualResult, equalTo(expectedResult));
    }

    @Test
    @DisplayName("should return all cities by book title")
    public void testGetAllCitiesByBookTitle () {
        String bookTitle = "";

        List<CityGraphEntity> expectedResult = new ArrayList();

        List<CityGraphEntity> actualResult = mapper.getAllCitiesByBookTitle(bookTitle);

        assertThat(actualResult, equalTo(expectedResult));
    }

    @Test
    @DisplayName("should return a list of books written by a given author")
    public void testGetAllBooksWrittenByAuthor () {
        String author = "";

        List<BookGraphEntity> expectedResult = new ArrayList();

        List<BookGraphEntity> actualResult = mapper.getAllBooksWrittenByAuthor(author);

        assertThat(actualResult, equalTo(expectedResult));
    }

    @Test
    @DisplayName("should return a list of books mentioning a city in a vicinity of a given geolocation")
    public void testGetAuthorsByCityName () {

        GeoLocation geoLocation = new GeoLocation();

        List<BookGraphEntity> expectedResult = new ArrayList();

        List<BookGraphEntity> actualResult = mapper.getAuthorsByCityName(geoLocation);

        assertThat(actualResult, equalTo(expectedResult));

    }
}
