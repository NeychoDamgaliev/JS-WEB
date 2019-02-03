package chushka.web.servlets;

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

/**
 * Created by Neycho Damgaliev on 2/2/2019.
 */

@WebServlet("/products/details")
public class ProductDetailsServlet extends HttpServlet {

    private static final String PRODUCT_DETAILS_HTML_FILE_PATH = "D:\\SoftUni\\JAVA-WEB\\Java Web Development Basics\\05. Java EE Servlet API\\src\\main\\resources\\views\\details-product.html";

    private final ProductService productService;
    private final HtmlReader reader;

    @Inject
    public ProductDetailsServlet(ProductService productService, HtmlReader reader) {
        this.productService = productService;
        this.reader = reader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String htmlFileContent = this.reader.readHtmlFile(PRODUCT_DETAILS_HTML_FILE_PATH);
        ProductServiceModel name = this.productService.findProductByName(req.getParameter("name"));
        htmlFileContent = htmlFileContent.replace("{{productName}}",name.getName())
                .replace("{{productDescription}}",name.getDescription())
                .replace("{{productType}}",name.getType());
        resp.getWriter().println(htmlFileContent);
    }
}
