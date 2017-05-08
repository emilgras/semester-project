import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileSearch {

    public static void main(String[] args) {

        // Testing only
        File file = new File("C:\\Cphbusiness - PBA\\test-data-semester-project\\semester-project\\src\\main\\java\\book1.txt");
        String city = "søborg";
        System.out.printf("Result of searching for %s in %s was %b\n", city, file.getName(), FileSearch.containsString(file, city));
    }

    public static boolean containsString(File file, String searchString) {
        boolean result = false;
        Scanner in = null;
        try {
            in = new Scanner(new FileReader(file));
            while(in.hasNextLine() && !result) {
                result = in.nextLine().toLowerCase().indexOf(searchString.toLowerCase()) >= 0;
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        finally {
            try { in.close() ; } catch(Exception e) { /* ignore */ }
        }
        return result;
    }
}
