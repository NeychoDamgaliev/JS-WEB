package fdmc.utils;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Neycho Damgaliev on 1/25/2019.
 */
public interface HtmlReader {
    String readHtmlFile (String filePath) throws IOException;
}
