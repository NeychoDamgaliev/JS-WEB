package chushka.utils;

import java.io.*;

/**
 * Created by Neycho Damgaliev on 2/2/2019.
 */
public class HtmlReaderImpl implements HtmlReader {
    public  String readHtmlFile(String htmlFilePath) {
        StringBuilder htmlFileContent = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(htmlFilePath))));

            String line;

            while ((line = reader.readLine()) != null) {
                htmlFileContent.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  htmlFileContent.toString().trim();
    }
}
