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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Neycho Damgaliev on 1/25/2019.
 */
@WebServlet("/cats/create")
public class CatCreateServlet extends HttpServlet {

    private final static String CAT_CREATE_HTML_FILE_PATH = "D:\\SoftUni\\JAVA-WEB\\Java Web Development Basics\\04. Introduction to Java EE\\src\\main\\resources\\views\\cat_create.html";
    private final HtmlReader htmlReader;

    @Inject
    public CatCreateServlet(HtmlReader reader) {
        this.htmlReader = reader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.getWriter().println(this.htmlReader.readHtmlFile(CAT_CREATE_HTML_FILE_PATH));
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Cat cat = new Cat();
        cat.setName(req.getParameter("name"));
        cat.setBreed(req.getParameter("breed"));
        cat.setColor(req.getParameter("color"));
        cat.setAge(Integer.parseInt(req.getParameter("age")));


        if (req.getSession().getAttribute("Cats") == null) {
            req.getSession().setAttribute("Cats", new LinkedHashMap<String, Cat>());
        }

        ((Map<String,Cat>) req.getSession().getAttribute("Cats")).putIfAbsent(cat.getName(),cat);

        res.sendRedirect(String.format("/cats/profile?catName=%s",cat.getName()));
//        System.out.println();
    }
}
