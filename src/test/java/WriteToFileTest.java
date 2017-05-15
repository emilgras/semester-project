import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class WriteToFileTest {

    private static WriteToFile writeFile;

    @BeforeClass
    public static void beforeClass () {
        writeFile = new WriteToFile();
    }

    @Ignore
    @Test
    public void testCreateAndWriteFile () {

        String path = System.getProperty("user.dir") + "/files/city_nodes.csv";

        String header = "test1,test2,test3\n";

        boolean expectedResult = true;

        boolean actualResult = writeFile.writeFile(path, header);

        assertThat(actualResult, equalTo(expectedResult));
    }
}
