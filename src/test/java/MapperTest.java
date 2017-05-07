import org.junit.*;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MapperTest {

    Mapper mapper;

    @Before
    public void before () {
        System.out.println("Tests are starting.....");
        mapper = new Mapper();
    }



    @Test
    @DisplayName("should return an empty array if city with corresponding authors don't exist in entity")
    public void testGetAuthorsByCityNameIfDontExist () {
        List<String> expectedResult = new ArrayList<String>();

        List<String> actualResult = mapper.getAuthorsByCityName("");

        assertThat(actualResult, equalTo(expectedResult));
    }


    @Test
    @DisplayName("should return authors corresponding to the city name")
    public void testGetAuthorsByCityNameListSize () {

        int expectedResult = 5;

        List<String> actualResult = mapper.getAuthorsByCityName("copenhagen");

        System.out.println("Actual result: " + actualResult.size() + " expected : " + expectedResult);

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
        System.out.println("from testIfgetAllCitiesByBookTitleIsEmpty: " + actualResult);

        assertThat(actualResult.size(), is(5));
    }
}
