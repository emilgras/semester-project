import org.junit.*;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class MapperTest {

    @BeforeClass
    public static void beforeClass () {
        System.out.println("Before class");
    }

    @AfterClass
    public static void afterClass () {
        System.out.println("After class");
    }

    @Before
    public void before () {
        System.out.println("Tests are starting.....");
    }

    @After
    public void after () {
        System.out.println("After....");
    }

    @Test
    public void testTwoStrings () {

        String expectedString = "test string";
        
        String actualString = "test string";
        assertEquals(actualString, expectedString);
    }

    @Test
    public void testIfTestsRun () {
        System.out.println("Tests run!");
    }

    @Test
    @DisplayName("should return cities by book title")
    public void getAllCitiesByBookTitle () {
        Mapper mapper = new Mapper();

        List<String> actualResult = mapper.getAllCitiesByBookTitle("hp");
        System.out.println("from testIfgetAllCitiesByBookTitleIsEmpty: " + actualResult);

        assertThat(actualResult.size(), is(5));
    }
}
