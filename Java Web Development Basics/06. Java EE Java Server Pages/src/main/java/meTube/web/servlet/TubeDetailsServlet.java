package meTube.web.servlet;

import meTube.domain.models.service.TubeServiceModel;
import meTube.domain.models.views.TubeDetailsVM;
import meTube.service.MeTubeService;
import meTube.utils.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * Created by Neycho Damgaliev on 2/3/2019.
 */
@WebServlet("/tubes/details")
public class TubeDetailsServlet extends HttpServlet {

    private final MeTubeService tubeService;
    private final ModelMapper modelMapper;


    @Inject
    public TubeDetailsServlet(MeTubeService tubeService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = URLDecoder.decode(req.getParameter("name"),"UTF-8");
        try {
            TubeServiceModel tubeByName = this.tubeService.findTubeByName(name);
            TubeDetailsVM tubeDetailsVM = this.modelMapper.map(tubeByName, TubeDetailsVM.class);
            tubeDetailsVM.setTitle(tubeByName.getName());

            req.setAttribute("tube", tubeDetailsVM);

            req.getRequestDispatcher("/jsps/details-tube.jsp").forward(req, resp);
        } catch (Exception ex) {
           resp.sendRedirect("/");
        }
    }
}
