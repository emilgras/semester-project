import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MapperTest {

    static Mapper mapper;

    @BeforeClass
    public static void beforeClass () {
        System.out.println("BeforeClass - initializing mapper \n\t\t-------------------- \n");
        mapper = new Mapper();
    }

    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(Description description) {
            System.out.println("Starting test... " + description.getMethodName() + "\n");
        }
    };

    @Test
    @DisplayName("should return an empty array if city is not found in any books")
    public void testGetAuthorsByCityNameIfDontExist () {
        List<String> expectedResult = new ArrayList<String>();

        List<String> actualResult = mapper.getAuthorsByCityName("");

        assertThat(actualResult, equalTo(expectedResult));
    }


    @Test
    @DisplayName("should return all book titles with corresponding authors where the city is mentioned in the book")
    public void testGetAuthorsByCityNameListSize () {

        int expectedResult = 1;

        //List<String> actualResult = mapper.getAuthorsByCityName("copenhagen");

        List<String> actualResult = mapper.getBooksMentioningCity("copenhagen");

        assertThat(actualResult.size(), is(expectedResult));
    }

    @Test
    @DisplayName("should return an empty array if book title don't exist in entity")
    public void testGetAllCitiesByBookTitleIfDontExist () {
        List<String> expectedResult = new ArrayList<String>();

        List<String> actualResult = mapper.getAllCitiesByBookTitle("");

        assertThat(actualResult, equalTo(expectedResult));
    }

    @Test
    @DisplayName("should return cities corresponding to the the book title")
    public void testGetAllCitiesByBookTitleListSize () {

        List<String> actualResult = mapper.getAllCitiesByBookTitle("hp");

        assertThat(actualResult.size(), is(5));
    }

    @Test
    @DisplayName("should return all books written by a given author")
    public void testGetAllBooksWrittenByAuthor () {
        int expectedResult = 3;

        List<String> actualResult = mapper.getAllBooksWrittenByAuthor("Frank Hansen");

        assertThat(actualResult.size(), equalTo(expectedResult));
    }
}
