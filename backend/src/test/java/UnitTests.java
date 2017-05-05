
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.lang.reflect.Array;
import java.util.List;

import static org.junit.Assert.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class UnitTests {

    public UnitTests() {
    }


    @Test
    @DisplayName("should return all books by a city name that corresponds to authors that mention in this city")
    public void testGetBooksByCityName () {

        String cityName = "";

        String[] expectedResult = {"book1", "book2", "book3"};

        String[] actualBooks;

    }

    @Test
    @DisplayName("should return all cities mentioned by book title")
    public void getAllCitiesByBookTitle () {


    }
}
