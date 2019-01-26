package fdmc.web.servlets;

import fdmc.utils.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Neycho Damgaliev on 1/25/2019.
 */
@WebServlet("/")
public class HomeServlet extends HttpServlet {

    private final static String INDEX_HTML_FILE_PATH = "D:\\SoftUni\\JAVA-WEB\\Java Web Development Basics\\04. Introduction to Java EE\\src\\main\\resources\\views\\index.html";
    private final HtmlReader reader;

    @Inject
    public HomeServlet(HtmlReader reader) {
        this.reader = reader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        super.doGet(req, res);
        res.addCookie(new Cookie("Pesho","UHAaaaa"));
        PrintWriter writer = res.getWriter();
        String htmlFile = this.reader.readHtmlFile(INDEX_HTML_FILE_PATH);
        writer.println(htmlFile);
    }
}
