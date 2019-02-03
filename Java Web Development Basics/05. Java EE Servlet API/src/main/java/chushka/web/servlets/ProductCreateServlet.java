package chushka.web.servlets;

import chushka.domain.entities.Type;
import chushka.domain.models.service.ProductServiceModel;
import chushka.service.ProductService;
import chushka.utils.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Neycho Damgaliev on 2/2/2019.
 */
@WebServlet("/products/create")
public class ProductCreateServlet extends HttpServlet {

    private final static String CREATE_PRODUCT_HTML_FILE_PATH = "D:\\SoftUni\\JAVA-WEB\\Java Web Development Basics\\05. Java EE Servlet API\\src\\main\\resources\\views\\create-product.html";

    private final ProductService productService;
    private final HtmlReader htmlReader;

    @Inject
    public ProductCreateServlet(ProductService productService, HtmlReader htmlReader) {
        this.productService = productService;
        this.htmlReader = htmlReader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String htmlFileContent = this.htmlReader.readHtmlFile(CREATE_PRODUCT_HTML_FILE_PATH)
                .replace("{{typeOptions}}",this.formatTypeOptions());

        resp.getWriter().println(htmlFileContent);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductServiceModel psm = new ProductServiceModel();
        psm.setName(req.getParameter("name"));
        psm.setDescription(req.getParameter("description"));
        psm.setType(req.getParameter("type"));

        this.productService.saveProduct(psm);

        resp.sendRedirect("/");
    }

    private String formatTypeOptions() {
        StringBuilder options = new StringBuilder();
        options
                .append("<option disabled selected>Choose type...</option>")
                .append(System.lineSeparator());
        Arrays.stream(Type.values())
                .forEach(t -> options
                        .append(
                                String.format("<option value=\"%s\">%s</option>", t.name(), t.name()))
                        .append(System.lineSeparator()));
        return options.toString();
    }
}
