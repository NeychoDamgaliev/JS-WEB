package realestate.utils;

import java.io.*;

/**
 * Created by Neycho Damgaliev on 3/4/2019.
 */
public class HtmlReader {

    public String readHtmlFile(String htmlFilePath) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(htmlFilePath).getAbsolutePath())));

        StringBuilder stringBuilder = new StringBuilder();
        String line;

        while ((line=reader.readLine()) !=null ) {
            stringBuilder.append(line).append(System.lineSeparator());
        }
        return stringBuilder.toString().trim();
    }
}
