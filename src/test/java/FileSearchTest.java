import Utilities.FileSearch;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class FileSearchTest {

    static FileSearch fileSearch;

    @BeforeClass
    public static void beforeClass () {
        System.out.println("BeforeClass - initializing Utilities.FileSearch \n\t\t-------------------- \n");
        fileSearch = new FileSearch();
    }

    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(Description description) {
            System.out.println("Starting test... " + description.getMethodName() + "\n");
        }
    };

    @Test
    @DisplayName("should return true for a city mentioned in file")
    public void testContainsString () {
        boolean expectedResult = true;

        String cityName = "copenhagen";
        File file = new File(System.getProperty("user.dir") + "/src/main/java/book1.txt");

        boolean actualResult = fileSearch.containsString(file, cityName);

        assertThat(actualResult, equalTo(expectedResult));
    }

    @Test
    @DisplayName("should return false for a city that is no mentioned in file")
    public void testContainsString2 () {
        boolean expectedResult = false;

        String cityName = "vulapyk";

       File file = new File(System.getProperty("user.dir") + "/src/main/java/book1.txt");

        boolean actualResult = fileSearch.containsString(file, cityName);
        assertThat(actualResult, equalTo(expectedResult));
    }
}
