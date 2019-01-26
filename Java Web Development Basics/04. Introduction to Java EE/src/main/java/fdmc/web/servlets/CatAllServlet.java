package fdmc.web.servlets;

import fdmc.domain.entities.Cat;
import fdmc.utils.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Neycho Damgaliev on 1/25/2019.
 */
public class CatAllServlet extends HttpServlet {

    private final static String ALL_CATS_HTML_FILE_PATH = "D:\\SoftUni\\JAVA-WEB\\Java Web Development Basics\\04. Introduction to Java EE\\src\\main\\resources\\views\\cats_all.html";

    private final HtmlReader htmlReader;

    @Inject
    public CatAllServlet(HtmlReader htmlReader) {
        this.htmlReader = htmlReader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Cat> allCats = (Map<String, Cat>) req.getSession().getAttribute("Cats");
        List<Cat> cats = null;
        if(allCats != null) {
            cats = ((Map<String, Cat>) req.getSession().getAttribute("Cats"))
                    .values()
                    .stream()
                    .collect(Collectors.toList());
        }

        String htmlFileContent = this.htmlReader.readHtmlFile(ALL_CATS_HTML_FILE_PATH);

        if (cats == null || cats.size() == 0) {
            htmlFileContent = htmlFileContent.replace("{{allCats}}",
                    "There are no cats. <a href=\"/cats/create\">Create some.</a>");
        } else {
            StringBuilder aS = new StringBuilder();
            cats.forEach(cat->aS
                    .append(String.format("<a href=\"/cats/profile?catName=%s\">%s</a> <br/>",cat.getName(),cat.getName()))
                    .append(System.lineSeparator()));
            htmlFileContent = htmlFileContent.replace("{{allCats}}",aS.toString());
        }

        resp.getWriter().println(htmlFileContent);
    }
}
