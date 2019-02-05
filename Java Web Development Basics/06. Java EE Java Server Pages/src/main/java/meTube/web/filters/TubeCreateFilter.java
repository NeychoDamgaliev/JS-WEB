package meTube.web.filters;

import meTube.domain.models.binding.TubeCreateBM;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Neycho Damgaliev on 2/3/2019.
 */
@WebFilter("/tubes/create")
public class TubeCreateFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if("post".equals(req.getMethod().toLowerCase())) {
            TubeCreateBM createBM = new TubeCreateBM();
            createBM.setTitle(req.getParameter("title"));
            createBM.setDescription(req.getParameter("description"));
            createBM.setYouTubeLink(req.getParameter("youTubeLink"));
            createBM.setUploader(req.getParameter("uploader"));

            req.setAttribute("tubeCreateBM", createBM);
        }

        filterChain.doFilter(req,resp);
    }
}
