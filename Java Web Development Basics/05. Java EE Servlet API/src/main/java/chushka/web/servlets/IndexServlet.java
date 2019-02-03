package chushka.web.servlets;

import chushka.domain.models.view.AllProductsViewModel;
import chushka.service.ProductService;
import chushka.utils.HtmlReader;
import chushka.utils.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Neycho Damgaliev on 2/2/2019.
 */

@WebServlet("/")
public class IndexServlet extends HttpServlet {

    private final static String INDEX_HTML_FILE_PATH = "D:\\SoftUni\\JAVA-WEB\\Java Web Development Basics\\05. Java EE Servlet API\\src\\main\\resources\\views\\index.html";

    private final ProductService productService;
    private final HtmlReader htmlReader;
    private final ModelMapper modelMapper;

    @Inject
    public IndexServlet(ProductService productService, HtmlReader htmlReader, ModelMapper modelMapper) {
        this.productService = productService;
        this.htmlReader = htmlReader;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String htmlFileContent = this.htmlReader.readHtmlFile(INDEX_HTML_FILE_PATH);
        htmlFileContent = htmlFileContent.replace("{{productsList}}",this.formatListItems());
        resp.getWriter().println(htmlFileContent);
    }

    private String formatListItems() {
        List<AllProductsViewModel> allProductsViewModels = this.productService.findAllProducts()
                .stream()
                .map(psm -> this.modelMapper.map(psm, AllProductsViewModel.class))
                .collect(Collectors.toList());

        StringBuilder allProducts = new StringBuilder();
        allProductsViewModels.forEach(p -> allProducts.append(
                String.format("<li><a href=\"/products/details?name=%s\">%s</a></li>", p.getName(), p.getName()))
                .append(System.lineSeparator()));
        return allProducts.toString().trim();
    }
}
