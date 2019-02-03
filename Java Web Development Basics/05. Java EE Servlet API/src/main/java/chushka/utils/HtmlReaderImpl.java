package chushka.utils;

import java.io.*;

/**
 * Created by Neycho Damgaliev on 2/2/2019.
 */
public class HtmlReaderImpl implements HtmlReader {
    public  String readHtmlFile(String htmlFilePath) {
        StringBuilder htmlFileContent = new StringBuilder();
        try {
            File file = new File(htmlFilePath.substring(1).replaceAll("%20"," "));
            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    new FileInputStream(file)
                            )
                    );

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
