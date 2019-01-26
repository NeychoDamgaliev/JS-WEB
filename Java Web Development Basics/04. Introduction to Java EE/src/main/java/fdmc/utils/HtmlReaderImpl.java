package fdmc.utils;

import java.io.*;

/**
 * Created by Neycho Damgaliev on 1/25/2019.
 */

public class HtmlReaderImpl implements HtmlReader {
    @Override
    public String readHtmlFile(String filePath) throws IOException {
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(
                                        new File(filePath)
                                )
                        )
                );
        StringBuilder htmlContent = new StringBuilder();
        String line;

        while((line = reader.readLine()) != null) {
            htmlContent.append(line).append(System.lineSeparator());
        }

        return htmlContent.toString().trim();
    }
}
