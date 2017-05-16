import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteToFile {
    public boolean writeFile(String filePath, String header) {

        String body = "body1, body2, body3\n";

        File file = new File(filePath);
        try {

            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream writer = new FileOutputStream(filePath);
            writer.write(header.getBytes());
            int times = 20000;
            for (int i = 0; i < times; i++) {
                writer.write(body.getBytes());
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
