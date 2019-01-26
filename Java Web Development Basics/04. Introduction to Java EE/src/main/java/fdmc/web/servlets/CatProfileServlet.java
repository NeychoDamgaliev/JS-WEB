package fdmc.web.servlets;

import fdmc.domain.entities.Cat;
import fdmc.utils.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Neycho Damgaliev on 1/25/2019.
 */
@WebServlet("/cats/profile")
public class CatProfileServlet extends HttpServlet {

    private final static String CAT_PROFILE_HTML_FILE_PATH = "D:\\SoftUni\\JAVA-WEB\\Java Web Development Basics\\04. Introduction to Java EE\\src\\main\\resources\\views\\cat_profile.html";
    private final static String CAT_NOT_EXISTING_HTML_FILE_PATH = "D:\\SoftUni\\JAVA-WEB\\Java Web Development Basics\\04. Introduction to Java EE\\src\\main\\resources\\views\\non_existent_cat.html";
    private final HtmlReader htmlReader;

    @Inject
    public CatProfileServlet(HtmlReader htmlReader) {
        this.htmlReader = htmlReader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Map<String, Cat> cats = (Map<String, Cat>) req.getSession().getAttribute("Cats");
        String catName = req.getQueryString().split("=")[1];

        String htmlFileContent = null;
        if (cats == null || !cats.containsKey(catName)) {
            htmlFileContent = this.htmlReader.readHtmlFile(CAT_NOT_EXISTING_HTML_FILE_PATH)
                    .replace("{{catName}}", catName);
        } else {
            Cat cat = cats.get(catName);

            htmlFileContent = this.htmlReader.readHtmlFile(CAT_PROFILE_HTML_FILE_PATH)
                    .replace("{{catName}}", cat.getName())
                    .replace("{{catBreed}}", cat.getBreed())
                    .replace("{{catColor}}", cat.getColor())
                    .replace("{{catAge}}", cat.getAge().toString());

        }
        res.getWriter().println(htmlFileContent);
    }
}
