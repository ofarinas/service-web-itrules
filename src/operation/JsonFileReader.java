package operation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by osvaldo on 12/6/14.
 */
public class JsonFileReader {
    public  String fromStream(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder out = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
            out.append(newLine);
        }
        return out.toString();
    }

    public String readResource(String resource) {
        try {
            return fromStream(getClass().getClassLoader().getResourceAsStream(resource));
        } catch (IOException e) {
            return "";
        }
    }

}
