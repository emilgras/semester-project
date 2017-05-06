
import org.hamcrest.Matcher;
import org.junit.*;
import org.junit.jupiter.api.DisplayName;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import static org.hamcrest.MatcherAssert.*;


public class UnitTests {

    @Ignore("Not implemented yet")
    @Test
    @DisplayName("should return all books by a city name that corresponds to authors that mention in this city")
    public void testGetBooksByCityName () {

        String cityName = "";

        String[] expectedResult = {"book1", "book2", "book3"};

        String[] actualBooks;

    }

    @Test
    //@DisplayName("should return all cities mentioned by book title")
    public void testGetAllCitiesByBookTitle () {

        final Mapper mapper = new Mapper();

        final String bookTitle = "mybook";

        String expectedCityOne = "city one";
        String expectedCityTwo = "city two";
        String expectedCityThree = "city three";

        String[] expectedResult = {expectedCityOne, expectedCityTwo, expectedCityThree};

        String[] actualResult = mapper.getAllCitiesByBookTitle();


        assertThat(actualResult, arrayContaining(Arrays.asList(equalTo(expectedCityOne), expectedCityTwo)));

    }
}
