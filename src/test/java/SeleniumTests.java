
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

// 2. Given a book title, your application plots all cities mentioned in this book onto a map.
// 3. Given an author name your application lists all books written by that author and plots all cities mentioned in any of the books onto a map.

@RunWith(Parameterized.class)
public class SeleniumTests {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                // Example: { 0, 0 }, { 1, 1 }, { 2, 1 }, { 3, 2 }, { 4, 3 }, { 5, 5 }, { 6, 8 }
                {"city1", "selectedCityPlot"}, {"city2", "selectedCityPlot"}, {"city3", "selectedCityPlot"}
        });
    }

    private String actualResult;
    private String expectedResult;


    public SeleniumTests(String cityInput, String cityExpected) {
        actualResult = cityInput;
        expectedResult = cityExpected;
    }

    @Ignore
    @Test
    @DisplayName("should 2 .....")
    public void testAllCityPlots () {
        assertEquals(expectedResult, actualResult);
    }
}
