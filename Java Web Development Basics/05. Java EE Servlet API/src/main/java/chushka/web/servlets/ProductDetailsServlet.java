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

import static chushka.constants.HTMLFilePaths.*;

/**
 * Created by Neycho Damgaliev on 2/2/2019.
 */

@WebServlet("/products/details")
public class ProductDetailsServlet extends HttpServlet {

    private final ProductService productService;
    private final HtmlReader reader;

    @Inject
    public ProductDetailsServlet(ProductService productService, HtmlReader reader) {
        this.productService = productService;
        this.reader = reader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String htmlFileContent = this.reader.readHtmlFile(
                this.getClass().getClassLoader().getResource(PRODUCT_DETAILS_HTML_FILE_PATH).getPath());
        ProductServiceModel name = this.productService.findProductByName(req.getParameter("name"));
        htmlFileContent = htmlFileContent.replace("{{productName}}",name.getName())
                .replace("{{productDescription}}",name.getDescription())
                .replace("{{productType}}",name.getType());
        resp.getWriter().println(htmlFileContent);
    }
}
